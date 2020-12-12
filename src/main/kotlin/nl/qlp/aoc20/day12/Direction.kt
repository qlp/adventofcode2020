package nl.qlp.aoc20.day12

enum class Direction(val east: Int, val north: Int) {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0)
}