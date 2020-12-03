package nl.qlp.aoc20.day3

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println("${First().run()}")
}

class First {
    fun run() = readLinesFromInput().filterIndexed { index, row -> Slope(3, 1).hasTree(row, index) }.count()
}