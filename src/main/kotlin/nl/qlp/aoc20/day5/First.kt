package nl.qlp.aoc20.day5

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run() = readLinesFromInput()
        .map { Seat(it) }
        .map { it.id() }
        .maxOrNull()
}
