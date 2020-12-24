package nl.qlp.aoc20.day24

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run(): Int {
        var floor = Floor(readLinesFromInput()
            .map { line ->
                Regex("(e|se|sw|w|nw|ne)").findAll(line).map { match -> match.groupValues.last() }
                    .map { it.toVector() }
                    .toList()
            }
            .map { it.reduce { acc, vector -> acc.add(vector) } }
            .groupingBy { it }.eachCount()
            .filter { it.value % 2 == 1 }
            .map { BlackTile(it.key.dx, it.key.dy) }
            .toSet())

        IntRange(1, 100).forEach {
            floor = floor.nextDay()
        }

        return floor.blackTiles.size
    }

    private fun String.toVector() = when(this) {
        "e" -> Vector(1, 0)
        "se" -> Vector(0, 1)
        "sw" -> Vector(-1, 1)
        "w" -> Vector(-1, 0)
        "nw" -> Vector(0, -1)
        "ne" -> Vector(1, -1)
        else -> throw IllegalStateException("Invalid direction $this")
    }

    data class Floor(val blackTiles: Set<BlackTile>) {
        private val neighbours = listOf(
            Vector(1, 0),
            Vector(0, 1),
            Vector(-1, 1),
            Vector(-1, 0),
            Vector(0, -1),
            Vector(1, -1)
        )

        private val yRange = IntRange(blackTiles.minOf { it.y } - 1, blackTiles.maxOf { it.y } + 1)
        private val xRange = IntRange(blackTiles.minOf { it.x } - 1, blackTiles.maxOf { it.x } + 1)

        fun nextDay() = Floor(
            yRange.flatMap { y ->
                xRange.mapNotNull { x ->
                    if (when(isBlack(x, y)) {
                        true -> IntRange(1, 2).contains(blackTileCount(x, y))
                        false -> blackTileCount(x, y) == 2
                    }) BlackTile(x, y) else null
                }
            }.toSet())

        private fun isBlack(x: Int, y: Int) = blackTiles.contains(BlackTile(x, y))

        private fun blackTileCount(x: Int, y: Int) = neighbours
            .filter { isBlack(x + it.dx, y + it.dy) }
            .count()
    }

    data class BlackTile(val x: Int, val y: Int)

    data class Vector(val dx: Int, val dy: Int) {
        override fun toString() = "<$dx, $dy>"

        fun add(vector: Vector) = Vector(dx + vector.dx, dy + vector.dy)
    }
}