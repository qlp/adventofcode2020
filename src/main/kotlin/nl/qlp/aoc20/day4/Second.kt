package nl.qlp.aoc20.day4

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println("${Second().run()}")
}

class Second {
    fun run(): Int {
        val passportStrings = mutableListOf<String>()

        val buffer = StringBuilder()

        readLinesFromInput().forEach {
            if (it.isEmpty()) {
                passportStrings.add(buffer.toString())
                buffer.clear()
            } else {
                if (buffer.isNotBlank()) {
                    buffer.append(" ")
                }
                buffer.append(it)
            }
        }
        passportStrings.add(buffer.toString())

        return passportStrings
            .map { Passport(it) }
            .filter { it.valid() }
            .count()
    }

    class Passport(private val string: String) {
        private val regex = Regex("""(?:([a-z]+):([\S]+))+""")
        private fun map() = regex.findAll(string).map { it.groupValues[1] to it.groupValues[2] }.toMap()

        fun valid() = hasRequiredKeys() &&
                birthYearIsValid() &&
                issueYearIsValid() &&
                expirationYearIsValid() &&
                heightIsValid() &&
                hairColorIsValid() &&
                eyeColorIsValid() &&
                passportIdIsValid()

        private fun hasRequiredKeys() = map().keys.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))

        private fun birthYearIsValid() = IntRange(1920, 2002).contains(map()["byr"]!!.toInt())

        private fun issueYearIsValid() = IntRange(2010, 2020).contains(map()["iyr"]!!.toInt())

        private fun expirationYearIsValid() = IntRange(2020, 2030).contains(map()["eyr"]!!.toInt())

        private fun heightIsValid(): Boolean {
            val match = Regex("""(?:([0-9]+)cm)|(?:([0-9]+)in)""").matchEntire(map()["hgt"]!!)

            return if (match != null) {
                IntRange(150, 193).contains(match.groupValues[1].toIntOrNull()) ||
                IntRange(59, 76).contains(match.groupValues[2].toIntOrNull())
            } else {
                false
            }
        }

        private fun hairColorIsValid() = Regex("""\#[0-9a-f]{6}""").matches(map()["hcl"]!!)

        private fun eyeColorIsValid() = Regex("""amb|blu|brn|gry|grn|hzl|oth""").matches(map()["ecl"]!!)

        private fun passportIdIsValid() = Regex("""[0-9]{9}""").matches(map()["pid"]!!)
    }
}
