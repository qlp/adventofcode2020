package nl.qlp.aoc20.day3

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println("${First().run()}")
}

class First {
    fun run(): Int {
        var treeCount = 0
        var y = 0

        readLinesFromInput().forEach {
            if (it[y] == '#') {
                treeCount++
            }

            y += 3
            y %= it.length
        }

        return treeCount
    }
}