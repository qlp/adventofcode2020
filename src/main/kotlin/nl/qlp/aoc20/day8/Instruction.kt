package nl.qlp.aoc20.day8

data class Instruction(val string: String) {
    fun command() = string.split(" ")[0]
    fun parameter() = string.split(" ")[1].toInt()

    fun copyWithCommand(command: String) = Instruction("$command ${parameter()}")
}