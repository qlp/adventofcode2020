package nl.qlp.aoc20.day20

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Long {
        val tiles = readFromInput().split("\n\n").map { tile ->
            tile.split("\n").let { lines ->
                Tile(Regex("Tile ([0-9]+):").matchEntire(lines[0])!!.groupValues[1].toLong(), lines.subList(1, lines.size))
            }
        }

        val tileToNeighbours = tiles
            .map { tile -> tile to tiles.filterNot { candidate -> candidate == tile }.filter { candidate -> candidate.connectsWith(tile) }}
            .toMap()

        tileToNeighbours.forEach {
            println(it)
        }

        return tileToNeighbours
            .filterValues { it.size == 2 }
            .map { it.key.id }
            .reduce { acc, l -> acc * l }
    }

    data class Tile(val id: Long, val rows: List<String>) {
        override fun toString() = "$id"

        private val firstRow = rows.first()
        private val lastRow = rows.last()
        private val firstColumn = rows.fold(StringBuilder()) { builder, string -> builder.append(string.first()) }.toString()
        private val lastColumn = rows.fold(StringBuilder()) { builder, string -> builder.append(string.last()) }.toString()

        private val rowConnections = setOf(firstRow, firstRow.reversed(), lastRow, lastRow.reversed())
        private val columnConnections = setOf(firstColumn, firstColumn.reversed(), lastColumn, lastColumn.reversed())

        private val connections = setOf(rowConnections, columnConnections).flatten()

        fun connectsWith(tile: Tile) = tile.connections.any { connections.contains(it) }
    }
}