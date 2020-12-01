package nl.qlp.aoc20.day1

import java.io.File

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run() = File("input.txt").readLines().map { it.toInt() }.let { numbers ->
        numbers.flatMapIndexed { i_one, one ->
            numbers.filterIndexed { fi_one, _ -> fi_one != i_one }.flatMapIndexed { i_two, two ->
                numbers.filterIndexed { fi_two, _ -> fi_two != i_two && fi_two != i_two }.map { three -> Triple(one, two, three)}
            }
        }.first { it.sum() == 2020 }
        .multiplication()
    }

    data class Triple(val one: Int, val two: Int, val three: Int) {
        fun sum() = one + two + three
        fun multiplication() = one * two * three
    }
}

