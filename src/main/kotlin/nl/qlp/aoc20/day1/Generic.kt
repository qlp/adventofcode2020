package nl.qlp.aoc20.day1

import java.io.File

fun main(args: Array<String>) {
    println(Generic().run(2020, 2))
    println(Generic().run(2020, 3))
}

class Generic {
    fun run(number: Int, size: Int) = File("input.txt").readLines().map { it.toInt() }.let { numbers ->
        numbers.permutations(size)
            .first { it.sum() == number }
            .reduce { total, next -> total * next }
    }
}

private fun List<Int>.permutations(width: Int): List<IntArray> = permutations(width, IntArray(width))

private fun List<Int>.permutations(width: Int, result: IntArray): List<IntArray> = when(width) {
    1 -> map { number ->
        result.apply {
            this[0] = number
        }.copyOf()
    }
    else -> flatMapIndexed { index, number ->
        val newResult = result.apply { this[width - 1] = number }

        subList(index + 1, size).permutations(width - 1, newResult) }
}
