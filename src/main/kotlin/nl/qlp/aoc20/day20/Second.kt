package nl.qlp.aoc20.day20

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run(): Int {
        val tiles = readFromInput().split("\n\n").map { tile ->
            tile.split("\n").let { lines ->
                Tile(Regex("Tile ([0-9]+):").matchEntire(lines[0])!!.groupValues[1].toLong(), lines.subList(1, lines.size))
            }
        }

        val tileToNeighbours = tiles
            .map { tile -> tile.id to tiles.filterNot { candidate -> candidate == tile }.filter { candidate -> candidate.connectsWith(tile) }}
            .toMap()

        val tileById = tiles.map { tile -> tile.id to tile }.toMap()

        val rows = mutableListOf<MutableList<Tile>>()

        do {
            if (rows.isEmpty()) {
                val firstTile = tileById.getValue(tileToNeighbours.filterValues { it.size == 2 }.keys.single { candidate ->
                    tiles.filterNot { candidate == it.id }.any { it.connectsToBottomSideOf(tileById.getValue(candidate))} &&
                    tiles.filterNot { candidate == it.id }.any { it.connectsToRightSideOf(tileById.getValue(candidate))} // find the top-left tile
                })

                rows.add(mutableListOf(firstTile))
            } else {
                val topTile = rows.last().first()

                val firstTile = tileToNeighbours.getValue(topTile.id).single { it.connectsToBottomSideOf(topTile) }

                rows.add(mutableListOf(firstTile.variantThatAlignsToBottomSideOf(topTile)))
            }

            do {
                val lastTile = rows.last().last()
                val nextTile = tileToNeighbours.getValue(lastTile.id).single {
                    it.connectsToRightSideOf(lastTile)
                }

                rows.last().add(nextTile.variantThatAlignsToRightSideOf(lastTile))
            } while((rows.size == 1 && tileToNeighbours.getValue(nextTile.id).size != 2) || // until added a corner piece
                    (rows.size != 1 && rows.last().size != rows.first().size))           // until row is complete
        } while(rows.size != tiles.size / rows.first().size)

        val image = Image(rows.map { it.toList()}.toList())

//        println(image.withoutBorders())
//
//        println("tile:")

        val tile = image.withoutBorders().toTile(0)
//        println(tile.representation())
//

        val monster = Monster(listOf(
                "                  # ",
                "#    ##    ##    ###",
                " #  #  #  #  #  #   "))

        val (variant, coordinates) = tile.variants()
                .map { variant -> variant to variant.monsterCoordinates(monster) }
                .single { it.second.isNotEmpty() }

        println(variant.representation())
        println(coordinates)

        val coordinatesToDelete = coordinates.flatMap { coordinate ->
            monster.hashes.map { hash ->
                Coordinate(coordinate.x + hash.x, coordinate.y + hash.y)
            }
        }

        val variantWithoutMonsters = variant.mark(coordinatesToDelete)

        println(variantWithoutMonsters.representation())

        return variantWithoutMonsters.hashCount()
    }

    data class Image(val rows: List<List<Tile>>) {
        override fun toString(): String {
            val result = StringBuilder()

            rows.forEach { row ->
                repeat(row.first().rows.size) { index ->
                    row.forEach { tile ->
                        result.append(tile.rows[index])
                        result.append(" ")
                    }
                    result.append('\n')
                }
                result.append('\n')
            }

            return result.toString()
        }

        fun withoutBorders() = Image(rows.map { row -> row.map { tile -> tile.withoutBorder() } })

        fun toTile(id: Long): Tile {
            val result = mutableListOf<String>()

            rows.forEach { row ->
                repeat(row.first().rows.size) { index ->
                    val builder = StringBuilder()
                    row.forEach { tile ->
                        builder.append(tile.rows[index])
                    }
                    result.add(builder.toString())
                }
            }

            return Tile(id, result.toList())
        }
    }

    data class Monster(val lines: List<String>) {
        val hashes = lines.flatMapIndexed { y, s ->
            s.mapIndexedNotNull { x, c -> if (c == '#') Coordinate(x, y) else null }
        }

        val width = lines.first().length

        val height = lines.size
    }

    data class Coordinate(val x: Int, val y: Int)

    data class Tile(val id: Long, val rows: List<String>) {
        override fun toString() = "$id"

        fun representation() = rows.fold(StringBuilder()) { builder, string ->
            builder.append(string)
            builder.append('\n')
        }

        private val firstRow = rows.first()
        private val lastRow = rows.last()
        private val firstColumn = rows.fold(StringBuilder()) { builder, string -> builder.append(string.first()) }.toString()
        private val lastColumn = rows.fold(StringBuilder()) { builder, string -> builder.append(string.last()) }.toString()

        private val rowConnections = setOf(firstRow, firstRow.reversed(), lastRow, lastRow.reversed())
        private val columnConnections = setOf(firstColumn, firstColumn.reversed(), lastColumn, lastColumn.reversed())

        private val connections = setOf(rowConnections, columnConnections).flatten()

        private val size = rows.size

        fun connections() = connections

        fun variants(): Set<Tile> = listOf(
            rotations(),
            flipVertically().rotations()
        ).flatten().toSet()

        private fun rotations() = setOf(
            this,
            rotate90(),
            rotate90().rotate90(),
            rotate90().rotate90().rotate90()
        )

        private fun flipVertically() = Tile(id, rows.map { it.reversed() })

        private fun rotate90() = Tile(id,
                IntRange(0, size - 1).map { y ->
                    IntRange(0, size - 1).fold(StringBuilder()) { acc, x -> acc.append(rows[size - 1 - x][y]) }.toString()
        })

        fun withoutBorder() = Tile(id, rows.subList(1, rows.size - 1).map { it.substring(1, it.length - 1) })

        fun connectsWith(tile: Tile) = tile.connections.any { connections.contains(it) }
        fun connectsToRightSideOf(tile: Tile) = connections.contains(tile.lastColumn)
        fun connectsToBottomSideOf(tile: Tile) = connections.contains(tile.lastRow)

        fun variantThatAlignsToRightSideOf(tile: Tile) = variants().single { variant -> tile.lastColumn == variant.firstColumn }
        fun variantThatAlignsToBottomSideOf(tile: Tile) = variants().single { variant -> tile.lastRow == variant.firstRow }

        fun monsterCoordinates(monster: Monster) = IntRange(0, rows.size - 1 - monster.height).flatMap { y ->
            IntRange(0, rows.first().length - 1 - monster.width).mapNotNull { x ->
                if(monster.hashes.all { rows[y + it.y][x + it.x] == '#' }) Coordinate(x, y) else null
            }
        }

        fun mark(coordinatesToDelete: List<Coordinate>): Tile {
            val mutableRows = rows.map { StringBuilder(it) }

            coordinatesToDelete.forEach { mutableRows[it.y][it.x] = 'O' }

            return Tile(id, mutableRows.map { it.toString() })
        }

        fun hashCount() = rows.map { row -> row.count { it == '#' }}.sum()
    }
}