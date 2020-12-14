package nl.qlp.aoc20.day14

import nl.qlp.aoc20.day14.Second.Computer.Companion.BITS
import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run(): Long = Computer()
            .also { computer -> readLinesFromInput().forEach { computer.process(it) } }
            .memory
            .values
            .sum()

    data class Computer(val memory: MutableMap<Long, Long> = mutableMapOf(), var mask: Mask = Mask(ZERO)) {

        fun process(line: String) {
            MASK.matchEntire(line)?.let { process(Mask(it.groupValues[1])) }
            MEM.matchEntire(line)?.let { process(Mem(it.groupValues[1].toLong(), it.groupValues[2].toLong())) }

//            dump()
        }

        private fun process(mask: Mask) {
//            println("process $mask")

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
//            println("process $mem")

            mask.apply(mem.address).forEach {
//                println("update $${it.toString(2).padStart(BITS, '0')} - ${mem.value}")

                memory[it] = mem.value
            }
        }

        companion object {
            const val ZERO = "000000000000000000000000000000000000"
            const val BITS = ZERO.length
            val MASK = Regex("mask = ([X01]{$BITS})")
            val MEM = Regex("mem\\[([0-9]+)\\] = ([0-9]+)")
        }
    }

    data class Mask(val value: String) {
        private fun calPower(baseValue: Int, powerValue: Int): Int {
            return if (powerValue != 0)  baseValue * calPower(baseValue, powerValue - 1) else 1
        }

        fun apply(address: Long): List<Long> {

            val pattern = StringBuilder(address.toString(2).padStart(BITS, '0'))
                    .apply {
                        this@Mask.value.forEachIndexed { index, c ->
                            when (c) {
                                '0' -> { /* ignore */
                                }
                                else -> this[index] = c
                            }
                        }
                    }.toString()

            val floatingIndices = pattern.mapIndexedNotNull { index, c ->
                when (c) {
                    'X' -> index
                    else -> null
                }
            }

            return if (floatingIndices.isEmpty()) {
                listOf(pattern.toLong(2))
            } else {
                IntRange(0, calPower(2, floatingIndices.size) - 1).map {
                    val mask = it.toString(2).padStart(floatingIndices.size, '0')

                    StringBuilder(pattern).apply {
                        floatingIndices.forEachIndexed { index, i ->
                            this[i] = mask[index]
                        }
                    }.toString().toLong(2)
                }
            }
        }
    }

    data class Mem(val address: Long, val value: Long)
}