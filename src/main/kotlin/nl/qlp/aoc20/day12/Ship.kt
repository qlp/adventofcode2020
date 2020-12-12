package nl.qlp.aoc20.day12

data class Ship(val east: Int, val north: Int, val direction: Direction) {
    fun apply(move: Move) = Ship(
            east + move.east + move.forward * direction.east + Direction.values()[(direction.ordinal + 1) % 4].east * move.right,
            north + move.north + move.forward * direction.north + Direction.values()[(direction.ordinal + 1) % 4].north * move.right,
            Direction.values()[(4 + direction.ordinal + move.rotation / 90) % 4]
    )
}