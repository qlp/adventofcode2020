package nl.qlp.aoc20.day9

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(preampleSize: Int = 25): Long {
        val numbers = readLinesFromInput().map { it.toLong() }

        return numbers
                .subList(preampleSize, numbers.size)
                .filterIndexed { index, value ->
                    !numbers.subList(index, index + preampleSize).sums().contains(value)
                }.first()
    }
}
