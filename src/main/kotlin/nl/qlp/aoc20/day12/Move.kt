package nl.qlp.aoc20.day12

data class Move(
        val east: Int = 0,
        val north: Int = 0,
        val forward: Int = 0,
        val rotation: Int = 0
)

fun String.toMove(): Move {
    val value = substring(1).toInt()

    return when(this[0]) {
        'R' -> Move(rotation = value)
        'L' -> Move(rotation = -value)
        'N' -> Move(north = value)
        'E' -> Move(east = value)
        'S' -> Move(north = -value)
        'W' -> Move(east = -value)
        'F' -> Move(forward = value)
        else -> throw IllegalStateException("Invalid move: $this")
    }
}