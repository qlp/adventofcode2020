package nl.qlp.aoc20

fun Any.readLinesFromInput() = readFromInput().lines()

fun Any.readFromInput() = javaClass.getResource("input.txt").readText()
