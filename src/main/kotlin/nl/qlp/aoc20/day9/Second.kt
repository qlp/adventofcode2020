package nl.qlp.aoc20.day9

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run(preampleSize: Int = 25): Long {
        val numbers = readLinesFromInput().map { it.toLong() }

        val incorrect = numbers
                .subList(preampleSize, numbers.size)
                .filterIndexed { index, value ->
                    !numbers.subList(index, index + preampleSize).sums().contains(value)
                }.first()

        repeat(numbers.size) { from ->
            var sum = 0L

            var until = from

            do {
                sum += numbers[until]

                if (sum == incorrect) {
                    val set = numbers.subList(from, until + 1)

                    return set.minOrNull()!! + set.maxOrNull()!!
                }
                until++
            } while (sum < incorrect)
        }

        return -1
    }
}

