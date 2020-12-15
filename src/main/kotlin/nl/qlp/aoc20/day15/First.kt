package nl.qlp.aoc20.day15

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Int {
        val turns = readFromInput()
                .split(",")
                .map { it.toInt() }
                .toMutableList()

        repeat(2020 - turns.size) {
            turns.addNextTurn()
        }

        return turns.last()
    }

    private fun MutableList<Int>.addNextTurn() {
        val lastTurn = last()
        val lastIndex = size - 1

        val nextTurn = when (val beforeLastIndex = subList(0, size - 1).lastIndexOf(lastTurn)) {
            -1 -> 0
            else -> lastIndex - beforeLastIndex
        }

        add(nextTurn)
    }

}