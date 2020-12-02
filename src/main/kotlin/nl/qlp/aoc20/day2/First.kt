package nl.qlp.aoc20.day2

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println("${First().run()}")
}

class First {
    fun run() = readLinesFromInput().map { Password(it) }.filter { it.valid() }.count()

    data class Password(val line: String) {
        private val regex = Regex("""([0-9]+)-([0-9]+) ([a-zA-Z]): ([a-zA-Z]+)""")

        fun valid(): Boolean {
            regex.matchEntire(line)!!.groupValues.let { groups ->
                val from = groups[1].toInt()
                val until = groups[2].toInt()
                val char = groups[3][0]
                val password = groups[4]

                val range = IntRange(from, until)
                val count = password.count { it == char }

                return range.contains(count)
            }
        }
    }
}