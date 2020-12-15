package nl.qlp.aoc20.day15

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run(): Int {
        var lastTurn = -1

        val turns = readFromInput()
                .split(",")
                .map { it.toInt() }
                .onEach { lastTurn = it }
                .mapIndexed { index, n -> n to index  }
                .groupByTo(mutableMapOf(), { it.first }, { it.second })

        repeat(30000000 - turns.size) {
            if (it % 100000 == 0) println("$it")
            lastTurn = turns.addNextTurn(lastTurn)
        }

        return lastTurn
    }

    private fun MutableMap<Int, MutableList<Int>>.lastIndex() = this[this.keys.maxOrNull()!!]

    private fun MutableMap<Int, MutableList<Int>>.addNextTurn(lastTurn: Int): Int {
        val previousTurns = this[lastTurn]!!
        val lastIndex = previousTurns.last()

        val nextTurn = when (previousTurns.size == 1) {
            true -> 0
            false -> lastIndex - previousTurns[previousTurns.size - 2]
        }

        val nextTurnIndices = getOrDefault(nextTurn, mutableListOf())

        nextTurnIndices.add(lastIndex + 1)

        this[nextTurn] = nextTurnIndices

        return nextTurn
    }
}
