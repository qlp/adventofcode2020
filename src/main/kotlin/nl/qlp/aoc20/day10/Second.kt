package nl.qlp.aoc20.day10

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run(): Long {
        val joltages = readLinesFromInput().map { it.toInt() }.toMutableList().apply { add(3 + maxOrNull()!!) }

        val solution = joltages.solution()?.toList()!!

        println(solution)
        println(solution.countArrangements())

        return solution.countArrangements()
   }
}

private fun List<Int>.countArrangements(index: Int = 0, cache: MutableMap<Int, Long> = mutableMapOf()): Long =
    if (index == size - 2) {
        1
    } else {
        val joltage = this[index]

        val validStartIndices = IntRange(index + 1, kotlin.math.min(size - 1, index + 3))
                .filter { joltage.validNext(this[it]) }

        validStartIndices
                .map { index ->
                    if (cache[index] != null) {
                        cache[index]!!
                    } else {
                        countArrangements(index, cache).also { cache[index] = it }
                    }
                }
                .sum()
    }
