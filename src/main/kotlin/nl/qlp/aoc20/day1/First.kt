package nl.qlp.aoc20.day1

import java.io.File

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run() = File("input.txt").readLines().map { it.toInt() }.let { numbers ->
        numbers.flatMapIndexed { i_one, one ->
            numbers.subList(i_one + 1, numbers.size).map { two -> Pair(one, two) }
        }.first { it.sum() == 2020 }
            .multiplication()
    }

    data class Pair(val one: Int, val other: Int) {
        fun sum() = one + other
        fun multiplication() = one * other
    }
}
