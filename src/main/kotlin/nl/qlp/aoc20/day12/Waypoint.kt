package nl.qlp.aoc20.day12

data class Waypoint(val east: Int, val north: Int) {
    fun rotate(rotation: Int) = when((rotation + 360) % 360) {
        0 -> this
        90 -> Waypoint(north, -east)
        180 -> Waypoint(-east, -north)
        270 -> Waypoint(-north, east)
        else -> throw IllegalArgumentException("Unsupported rotation: $rotation")
    }
}
