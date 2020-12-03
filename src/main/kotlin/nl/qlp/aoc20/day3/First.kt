package nl.qlp.aoc20.day3

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println("${First().run()}")
}

class First {
    fun run() = TreeDetector(Slope(3, 1)).let { treeDetector -> readLinesFromInput().count { treeDetector.detect(it) } }
}