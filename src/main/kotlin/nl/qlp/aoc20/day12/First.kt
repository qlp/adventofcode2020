package nl.qlp.aoc20.day12

import nl.qlp.aoc20.readLinesFromInput
import kotlin.math.abs

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Int {
        var ship = Ship(0, 0, Direction.EAST)

        readLinesFromInput()
                .mapNotNull { it.toMove() }
                .forEach { ship = ship.apply(it) }

       return abs(ship.east) + abs(ship.north)
   }
}
