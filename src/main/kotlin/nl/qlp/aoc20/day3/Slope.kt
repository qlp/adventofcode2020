package nl.qlp.aoc20.day3

data class Slope(val right: Int, val down: Int) {
    fun hasTree(row: String, index: Int) = (index % down == 0) && row[(index / down * right) % row.length] == '#'
}
