package nl.qlp.aoc20.day12

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run() = SecondShip(waypoint = Waypoint(10, 1)).applyMoves().distance()
}

data class SecondShip(override val east: Int = 0, override val north: Int = 0, val waypoint: Waypoint) : Ship {
    override fun apply(move: Move) = SecondShip(
            east + waypoint.east * move.forward,
            north + waypoint.north * move.forward,
            waypoint = Waypoint(
                    waypoint.east + move.east,
                    waypoint.north + move.north
            ).rotate(move.rotation)
    )
}

data class Waypoint(val east: Int, val north: Int) {
    fun rotate(rotation: Int) = when((rotation + 360) % 360) {
        0 -> this
        90 -> Waypoint(north, -east)
        180 -> Waypoint(-east, -north)
        270 -> Waypoint(-north, east)
        else -> throw IllegalArgumentException("Unsupported rotation: $rotation")
    }
}
