package nl.qlp.aoc20.day5

import java.lang.IllegalArgumentException

data class Seat(val string: String) {
    fun row(): Int {
        var range = IntRange(0, 127)

        string
            .subSequence(0, 7)
            .forEach {
                range = when (it) {
                    'B' -> range.upperHalf()
                    'F' -> range.lowerHalf()
                    else -> throw IllegalArgumentException("Invalid character $it")
                }
            }

        return range.first
    }

    fun column(): Int {
        var range = IntRange(0, 7)

        string
            .subSequence(7, 10)
            .forEach {
                range = when (it) {
                    'R' -> range.upperHalf()
                    'L' -> range.lowerHalf()
                    else -> throw IllegalArgumentException("Invalid character $it")
                }
            }

        return range.first
    }

    override fun toString() = "${row()} - ${column()} => ${id()}"

    fun id() = row() * 8 + column()

    private fun IntRange.upperHalf() = IntRange(start + halfSize(), endInclusive)
    private fun IntRange.lowerHalf() = IntRange(start, endInclusive - halfSize())

    private fun IntRange.halfSize() = this.count() / 2
}