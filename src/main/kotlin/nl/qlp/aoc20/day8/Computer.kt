package nl.qlp.aoc20.day8

import java.lang.IllegalArgumentException

data class Computer(val instructions: List<Instruction>, var accumulator: Int = 0, var pointer: Int = 0) {
    fun tick() {
        printState()

        val instruction = instructions[pointer]

        when(instruction.command()) {
            "acc" -> acc(instruction.parameter())
            "jmp" -> jmp(instruction.parameter())
            "nop" -> nop()
            else -> throw IllegalArgumentException("Invalid instruction: $instruction")
        }

        printState()
    }

    private fun acc(parameter: Int) {
        println("  acc $parameter")

        accumulator += parameter
        pointer += 1
    }

    private fun jmp(parameter: Int) {
        println("  jmp $parameter")

        pointer += parameter
    }

    private fun nop() {
        println("  nop")

        pointer += 1
    }

    private fun printState() {
        println("accumulator: $accumulator, pointer: $pointer")
    }

}