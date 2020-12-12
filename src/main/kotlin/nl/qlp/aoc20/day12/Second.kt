package nl.qlp.aoc20.day12

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run() = SecondShip(0, 0, Waypoint(10, 1)).applyMoves().distance()
}

data class SecondShip(override val east: Int, override val north: Int, val waypoint: Waypoint) : Ship {
    override fun apply(move: Move) = SecondShip(
            east + waypoint.east * move.forward,
            north + waypoint.north * move.forward,
            waypoint = Waypoint(
                    waypoint.east + move.east,
                    waypoint.north + move.north
            ).rotate(move.rotation)
    )
}