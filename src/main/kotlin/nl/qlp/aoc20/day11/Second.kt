package nl.qlp.aoc20.day11

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run(): Int {
        var world = SecondWorld(readLinesFromInput())
        var newWorld = world.tick()

        while (world != newWorld) {
            world = newWorld
            newWorld = world.tick()
        }

        return world.statuses().filter { it == SecondWorld.Status.OCCUPIED }.count()
    }
}

data class SecondWorld(private val lines: List<String>) {

    override fun toString() = lines.joinToString("\n")

    fun statuses() = IntRange(0, lines.size - 1)
            .flatMap { y -> IntRange(0, lines[0].length - 1)
                    .map { x -> Area(x, y) } }
            .map { it.status() }

    fun tick() = SecondWorld(
            lines.mapIndexed { y, line ->
                line.mapIndexed { x, c ->
                    when {
                        Area(x, y).isEmptyAndHasNoOccupiedSeats() -> '#'
                        Area(x, y).seatIsOccupiedAndFourOrMoreAdjacentSeatsAreOccupied() -> 'L'
                        else -> c
                    }
                }.joinToString("")
            })

    private fun Area.seen(): Set<Area> = IntRange(-1, 1).flatMap { dy ->
        IntRange(-1, 1)
                .filter { dx -> dx != 0 || dy != 0 }
                .mapNotNull { dx -> firstSeatInDirection(dx, dy)}
    }.toSet()

    private fun Area.firstSeatInDirection(dx: Int, dy: Int): Area? {
        var result = this
        do {
            result = Area(result.x + dx, result.y + dy)

            if (result.onMap() && result.isSeat()) {
                return result
            }
        } while (result.onMap())

        return null
    }

    private fun Area.seatIsOccupiedAndFourOrMoreAdjacentSeatsAreOccupied() =
            status() == Status.OCCUPIED &&
            seen().filter { it.status() == Status.OCCUPIED }.count() >= 5

    private fun Area.isEmptyAndHasNoOccupiedSeats() =
            status() == Status.EMPTY &&
            seen().filter { it.status() == Status.OCCUPIED }.count() == 0

    private fun Area.onMap() =
        IntRange(0, lines.size - 1).contains(y) &&
        IntRange(0, lines[0].length - 1).contains(x)


    enum class Status { EMPTY, OCCUPIED, FLOOR }

    private fun Area.status() = when(lines[y][x]) {
        '#' -> Status.OCCUPIED
        '.' -> Status.FLOOR
        'L' -> Status.EMPTY
        else -> throw IllegalStateException("Invalid area: $this")
    }

    private fun Area.isSeat() = status() != Status.FLOOR


    data class Area(val x: Int, val y: Int)
}


