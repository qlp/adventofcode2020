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
    ).map { run(it) }
    .reduce { total, next -> total * next }

    private fun run(slope: Slope): Long {
        with(slope) {
            var treeCount = 0L
            var y = 0
            var x = 0

            readLinesFromInput().forEach {
                if (x % down == 0) {
                    if (it[y] == '#') {
                        treeCount++
                    }

                    y += right
                    y %= it.length
                }

                x += 1
            }

            return treeCount
        }
    }
}

data class Slope(val right: Int, val down: Int)
