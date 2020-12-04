package nl.qlp.aoc20.day4

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println("${First().run()}")
}

class First {
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

    data class Passport(private val string: String) {
        private val regex = Regex("""(?:([a-z]+):([\S]+))+""")
        private fun map() = regex.findAll(string).map { it.groupValues[1] to it.groupValues[2] }.toMap()

        fun valid() = map().keys.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))
    }
}
