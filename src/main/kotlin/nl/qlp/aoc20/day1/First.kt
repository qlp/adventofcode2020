package nl.qlp.aoc20.day1

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println("${First().run(2020)} == 864864")
}

class First {
    fun run(number: Int) = readLinesFromInput().map { it.toInt() }.let { numbers ->
        numbers.flatMapIndexed { i_one, one ->
            numbers.subList(i_one + 1, numbers.size).map { two -> Pair(one, two) }
        }.first { it.sum() == number }
            .multiplication()
    }

}

data class Pair(val one: Int, val other: Int) {
    fun sum() = one + other
    fun multiplication() = one * other
}
