package nl.qlp.aoc20.day8

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Int {
        val computer = Computer(readLinesFromInput().map { Instruction(it) })

        val visitedPointers = mutableSetOf<Int>()

        do {
            visitedPointers.add(computer.pointer)

            computer.tick()
        } while (!visitedPointers.contains(computer.pointer))

        return computer.accumulator
    }

}
