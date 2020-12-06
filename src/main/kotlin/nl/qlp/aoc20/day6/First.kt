package nl.qlp.aoc20.day6

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run() = readFromInput()
        .split("\n\n")
        .map { string -> string.asSequence().filter { it.isLetter() }.distinct().count() }
        .sum()
}
