package nl.qlp.aoc20.day23

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run() = Game(readFromInput().map { it.toString().toInt() })
            .move(100)
            .toString()
            .let {
                val indexOfOne = it.indexOf('1')

                it.substring(indexOfOne + 1, it.length) + it.substring(0, indexOfOne)
            }

    data class Game(val cups: List<Int>) {
        fun move(times: Int): Game {
            var result = this

            repeat(times) {
                println("${it + 1}: $result")
                result = result.move()
            }

            return result
        }

        override fun toString() = cups.joinToString(separator = "")

        private fun move() = Game(mutableListOf<Int>().apply {
            addAll(cups.subList(4, cups.size))
            add(cups[0])

            val insertionPoint = indexOf(filter { it < cups[0]}.maxOrNull()?:maxOrNull()) + 1

            addAll(insertionPoint, cups.subList(1, 4))
        }.toList())
    }
}