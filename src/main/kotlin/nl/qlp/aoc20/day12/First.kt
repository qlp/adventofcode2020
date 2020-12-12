package nl.qlp.aoc20.day12

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run() = FirstShip(orientation = Orientation.EAST).applyMoves().distance()
}

data class FirstShip(override val east: Int = 0, override val north: Int = 0, val orientation: Orientation): Ship {
    override fun apply(move: Move) = FirstShip(
            east + move.east + move.forward * orientation.east,
            north + move.north + move.forward * orientation.north,
            orientation.rotate(move.rotation)
    )
}

enum class Orientation(val east: Int, val north: Int) {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    fun rotate(rotation: Int) = values()[(4 + ordinal + rotation / 90) % 4]
}