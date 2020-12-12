package nl.qlp.aoc20.day12

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run() = FirstShip(0, 0, Orientation.EAST).applyMoves().distance()
}

data class FirstShip(override val east: Int, override val north: Int, val orientation: Orientation): Ship {
    override fun apply(move: Move) = FirstShip(
            east + move.east + move.forward * orientation.east,
            north + move.north + move.forward * orientation.north,
            orientation.rotate(move.rotation)
    )
}