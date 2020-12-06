package nl.qlp.aoc20.day5

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println("${Second().run()}")
}

class Second {
    fun run() : Int {
        val bookedSeatIds = readLinesFromInput()
            .map { Seat(it) }
            .map { it.id() }

        val candidateSeats = IntRange(8, 126 * 8)

        return candidateSeats
            .filter { bookedSeatIds.contains(it - 1) && bookedSeatIds.contains(it + 1) }
            .single { !bookedSeatIds.contains(it) }
    }
}
