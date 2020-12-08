package nl.qlp.aoc20.day8

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run(): Int {
        val instructions = readLinesFromInput().map { Instruction(it) }

        val mutations = mutationsOf(instructions)

        mutations.forEach {
            try {
                return run(it)
            } catch (e: InfiniteLoopException) {
                // try again
            }
        }

        throw IllegalStateException("No valid mutation found")
    }

    private fun mutationsOf(instructions: List<Instruction>): List<List<Instruction>> {
        val result = mutableListOf<List<Instruction>>()

        instructions.forEachIndexed { index, instruction ->
            when(instruction.command()) {
                "nop" -> result.add(instructions.toMutableList().apply { this[index] = instruction.copyWithCommand("jmp") })
                "jmp" -> result.add(instructions.toMutableList().apply { this[index] = instruction.copyWithCommand("nop") })
            }
        }

        return result
    }

    private fun run(instructions: List<Instruction>): Int {
        val computer = Computer(instructions)

        val visitedPointers = mutableSetOf<Int>()

        do {
            visitedPointers.add(computer.pointer)

            computer.tick()

            if (visitedPointers.contains(computer.pointer)) {
                throw InfiniteLoopException(computer.pointer)
            }
        } while (computer.pointer != computer.instructions.size)

        return computer.accumulator
    }

    class InfiniteLoopException(pointer: Int): Exception("Infinite loop running $pointer again")
}
