package nl.qlp.aoc20.day3

class TreeDetector(private val slope: Slope) {
    var y = 0
    var x = 0

    fun detect(row: String) =
        if (x % slope.down == 0) {
            (row[y] == '#').also {
                y += slope.right
                y %= row.length
            }
        } else { false }.also {
            x += 1
        }
}

data class Slope(val right: Int, val down: Int)
