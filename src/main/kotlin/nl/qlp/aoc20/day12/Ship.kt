package nl.qlp.aoc20.day12

import nl.qlp.aoc20.readLinesFromInput
import kotlin.math.abs

interface Ship {
    val east: Int
    val north: Int

    fun distance() = abs(east) + abs(north)

    fun apply(move: Move): Ship

    fun applyMoves(): Ship {
        var result = this

        readLinesFromInput()
                .map { it.toMove() }
                .forEach { result = result.apply(it) }

        return result
    }
}