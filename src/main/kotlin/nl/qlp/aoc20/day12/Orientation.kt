package nl.qlp.aoc20.day12

enum class Orientation(val east: Int, val north: Int) {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    fun rotate(rotation: Int) = values()[(4 + ordinal + rotation / 90) % 4]
}