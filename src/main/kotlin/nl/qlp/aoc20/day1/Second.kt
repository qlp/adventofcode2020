package nl.qlp.aoc20.day1

import java.io.File

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run() = File("input.txt").readLines().map { it.toInt() }.let { numbers ->
        numbers.flatMapIndexed { i_one, one ->
            numbers.subList(i_one + 1, numbers.size).flatMapIndexed { i_two, two ->
                numbers.subList(i_one + 1 + i_two + 1, numbers.size).map { three -> Triple(one, two, three) }
            }
        }.first { it.sum() == 2020 }
        .multiplication()
    }

    data class Triple(val one: Int, val two: Int, val three: Int) {
        fun sum() = one + two + three
        fun multiplication() = one * two * three
    }
}
