package nl.qlp.aoc20.day24

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run() = readLinesFromInput()
        .map { line ->
            Regex("(e|se|sw|w|nw|ne)").findAll(line).map { match -> match.groupValues.last() }
                .map { it.toVector() }
                .toList() }
        .map { it.reduce { acc, vector -> acc.add(vector) } }
        .groupingBy { it }.eachCount()
        .filter { it.value % 2 == 1 }
        .count()

    private fun String.toVector() = when(this) {
        "e" -> Vector(1, 0)
        "se" -> Vector(0, 1)
        "sw" -> Vector(-1, 1)
        "w" -> Vector(-1, 0)
        "nw" -> Vector(0, -1)
        "ne" -> Vector(1, -1)
        else -> throw IllegalStateException("Invalid direction $this")
    }

    data class Vector(val dx: Int, val dy: Int) {
        override fun toString() = "<$dx, $dy>"

        fun add(vector: Vector) = Vector(dx + vector.dx, dy + vector.dy)
    }
}