package nl.qlp.aoc20.day13

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Int {
        readLinesFromInput().let { lines ->
            val earliest = lines[0].toInt()
            val buses = lines[1].split(",").filter { it != "x" }.map { it.toBus() }

            return buses.minByOrNull { bus ->
                bus.nextRide(earliest).also {
                    println("bus: $bus => $it")
                }
            }!!.let { it.id * (it.nextRide(timestamp = earliest) - earliest) }
        }
    }
}

data class Bus(val id: Int) {
    fun nextRide(timestamp: Int) = timestamp + if (timestamp % id == 0) 0 else id - timestamp % id
}

fun String.toBus() = Bus(this.toInt())