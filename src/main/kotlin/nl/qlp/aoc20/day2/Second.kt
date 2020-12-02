package nl.qlp.aoc20.day2

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println("${Second().run()}")
}

class Second {
    fun run() = readLinesFromInput().map { Password(it) }.filter { it.valid() }.count()

    data class Password(val line: String) {
        private val regex = Regex("""([0-9]+)-([0-9]+) ([a-zA-Z]): ([a-zA-Z]+)""")

        fun valid(): Boolean {
            regex.matchEntire(line)!!.groupValues.let { groups ->
                val oneIndex = groups[1].toInt() - 1
                val otherIndex = groups[2].toInt() - 1
                val char = groups[3][0]
                val password = groups[4]

                val oneChar = password[oneIndex]
                val otherChar = password[otherIndex]

                return (oneChar == char) xor (otherChar == char)
            }
        }
    }
}