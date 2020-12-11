package nl.qlp.aoc20.day11

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Int {
        var world = FirstWorld(readLinesFromInput())
        var newWorld = world.tick()

        while (world != newWorld) {
            world = newWorld
            newWorld = world.tick()
        }

        return world.statuses().filter { it == FirstWorld.Status.OCCUPIED }.count()
   }
}

data class FirstWorld(private val lines: List<String>) {

    override fun toString() = lines.joinToString("\n")

    fun statuses() = IntRange(0, lines.size - 1)
            .flatMap { y -> IntRange(0, lines[0].length - 1)
            .map { x -> Seat(x, y) } }
            .map { it.status() }

    fun tick() = FirstWorld(
            lines.mapIndexed { y, line ->
                line.mapIndexed { x, c ->
                    when {
                        Seat(x, y).isEmptyAndHasNoOccupiedSeats() -> '#'
                        Seat(x, y).seatIsOccupiedAndFourOrMoreAdjacentSeatsAreOccupied() -> 'L'
                        else -> c
                    }
                }.joinToString("")
            })

    private fun Seat.adjacent(): Set<Seat> =
        IntRange(this.x - 1, this.x + 1)
                .flatMap { candidate_x -> IntRange(this.y - 1, this.y + 1)
                        .filter { candidate_y ->
                            Seat(candidate_x, candidate_y) != this &&
                            IntRange(0, lines.size - 1).contains(candidate_y) &&
                            IntRange(0, lines[0].length - 1).contains(candidate_x)
                        }
                        .map { y -> Seat(candidate_x, y) } }
                .toSet()

    private fun Seat.seatIsOccupiedAndFourOrMoreAdjacentSeatsAreOccupied() =
            status() == Status.OCCUPIED &&
            adjacent().filter { it.status() == Status.OCCUPIED }.count() >= 4

    private fun Seat.isEmptyAndHasNoOccupiedSeats() =
            status() == Status.EMPTY &&
            adjacent().filter { it.status() == Status.OCCUPIED }.count() == 0

    enum class Status { EMPTY, OCCUPIED, FLOOR }

    private fun Seat.status() = when(lines[y][x]) {
        '#' -> Status.OCCUPIED
        '.' -> Status.FLOOR
        'L' -> Status.EMPTY
        else -> throw IllegalStateException("Invalid seat: $this")
    }

    data class Seat(val x: Int, val y: Int)
}
