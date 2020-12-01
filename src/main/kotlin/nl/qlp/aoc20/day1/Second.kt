package nl.qlp.aoc20.day1

import java.io.File

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run() = File("input.txt").readLines().map { it.toInt() }.let { numbers ->
        numbers.flatMap { one ->
            numbers.filter { it != one }.flatMap { two ->
                numbers.filter { it != one && it != two }.map { three -> Triple(one, two, three)}
            }
        }.first { it.sum() == 2020 }
        .multiplication()
    }

    data class Triple(val one: Int, val two: Int, val three: Int) {
        fun sum() = one + two + three
        fun multiplication() = one * two * three
    }
}

