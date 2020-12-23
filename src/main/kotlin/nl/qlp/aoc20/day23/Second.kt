package nl.qlp.aoc20.day23

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run() = Game(readFromInput().map { it.toCup() }.toMutableList().apply {
        repeat(1_000_000 - size) { add((size + 1).toCup())} })
        .move(10_000_000)
        .stars()
        .map { it.number.toLong() }
        .reduce { acc, i -> acc * i }

    class Game(cups: List<Cup>) {
        private val connections = cups.map { cup -> CupConnection(cup) }.toList().apply {
            forEachIndexed { index, cupConnection -> cupConnection.next = this[(index + 1) % size] }
        }
        private val cupToConnection = connections.map { it.cup to it }.toMap()
        private var current = connections[0]

        fun move(times: Int): Game {
            repeat(times) {
                if (it % 1000000 == 0) println(it)

                move()
            }

            return this
        }

        private fun move() {
            val pickUpStart = current.next
            val pickUpEnd = current.next.next.next

            var insertionCup = Cup(if (current.cup.number == 1) connections.size else current.cup.number - 1)
            while(listOf(pickUpStart.cup, pickUpStart.next.cup, pickUpStart.next.next.cup).contains(insertionCup)) {
                insertionCup = Cup(if (insertionCup.number == 1) connections.size else insertionCup.number - 1)
            }

            val insertionCupConnection = getConnectionTo(insertionCup)

            current.next = pickUpEnd.next // connect current to the next one after removing
            pickUpEnd.next = insertionCupConnection.next // connect pickup end to the one insertion cup is pointing to
            insertionCupConnection.next = pickUpStart // connect the start of the pick to the insertion cup

            current = current.next
        }

        override fun toString(): String {
            val result = StringBuilder()

            var next = current

            do {
                if (next == current) {
                    result.append("(${next.cup.number}) ")
                } else {
                    result.append("${next.cup.number} ")
                }
                next = next.next
            } while (next != current)

            return result.toString()
        }

        fun stars() = listOf(
            getConnectionTo(Cup(1)).next.cup,
            getConnectionTo(Cup(1)).next.next.cup)

        private fun getConnectionTo(cup: Cup) = cupToConnection[cup] ?: throw IllegalStateException("Cup $cup does not exist")
    }

    fun Char.toCup() = toString().toInt().toCup()
    fun Int.toCup() = Cup(this)

    data class Cup(val number: Int)

    data class CupConnection(val cup: Cup) {
        lateinit var next: CupConnection
    }
}