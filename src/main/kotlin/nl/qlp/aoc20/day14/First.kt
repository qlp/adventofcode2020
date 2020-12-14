package nl.qlp.aoc20.day14

import nl.qlp.aoc20.day14.First.Computer.Companion.BITS
import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Long = Computer()
            .also { computer -> readLinesFromInput().forEach { computer.process(it) } }
            .memory
            .values
            .map { it.toLong(2) }
            .sum()

    data class Computer(val memory: MutableMap<Int, String> = mutableMapOf(), var mask: Mask = Mask(ZERO)) {

        fun process(line: String) {
            MASK.matchEntire(line)?.let { process(Mask(it.groupValues[1])) }
            MEM.matchEntire(line)?.let { process(Mem(it.groupValues[1].toInt(), it.groupValues[2].toLong())) }

            dump()
        }

        private fun process(mask: Mask) {
            println("process $mask")

            this.mask = mask
        }

        private fun dump() {
            println()
            println(mask)
            memory.keys.sorted().forEach {
                println("${it.toString().padStart(9)}: ${memory[it]}")
            }
            println()
        }

        private fun process(mem: Mem) {
            println("process $mem")
            memory[mem.address] = mask.apply(mem.value)
        }

        companion object {
            const val ZERO = "000000000000000000000000000000000000"
            const val BITS = ZERO.length
            val MASK = Regex("mask = ([X01]{$BITS})")
            val MEM = Regex("mem\\[([0-9]+)\\] = ([0-9]+)")
        }
    }

    data class Mask(val value: String) {
        fun apply(memory: Long) = StringBuilder(memory.toString(2).padStart(BITS, '0'))
                .apply {
                    this@Mask.value.forEachIndexed { index, c ->
                        when (c) {
                            'X' -> { /* ignore */
                            }
                            else -> this[index] = c
                        }
                    }
                }.toString().also {
                    println()
                    println(" value: ${memory.toString(2).padStart(BITS, '0')}")
                    println("  mask: $value")
                    println("result: $it")
                    println()
                }
    }

    data class Mem(val address: Int, val value: Long)
}