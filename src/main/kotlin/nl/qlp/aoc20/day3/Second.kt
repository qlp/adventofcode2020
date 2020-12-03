package nl.qlp.aoc20.day3

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println("${Second().run()}")
}

class Second {
    fun run() = listOf(
        Slope(1, 1),
        Slope(3, 1),
        Slope(5, 1),
        Slope(7, 1),
        Slope(1, 2)
    ).map { TreeDetector(it).let { treeDetector -> readLinesFromInput().count { row -> treeDetector.detect(row) } } }
    .map { it.toLong() }
    .reduce { total, next -> total * next }
}