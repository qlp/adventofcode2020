package nl.qlp.aoc20.day13

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run() = readLinesFromInput()[1]
        .split(",")
        .mapIndexedNotNull { index, string -> if (string != "x") Bus(string.toLong(), index.toLong()) else null }
        .map { bus -> ModuloEquation(bus.id, bus.id - bus.index) }
    .chineseRemainder()

    data class Bus(val id: Long, val index: Long)

    data class ModuloEquation(val divisor: Long, val remainder: Long)

    // Heavily inspired on https://rosettacode.org/wiki/Chinese_remainder_theorem#Kotlin
    private fun List<ModuloEquation>.chineseRemainder(): Long {
        val prod = this.map { it.divisor }.fold(1L) { acc, i -> acc * i }
        var sum = 0L

        this.forEach { equation ->
            val p = prod / equation.divisor
            sum += equation.remainder * multInv(p, equation.divisor) * p
        }

        return sum % prod
    }

    // Modular multiplicative inverse.
    private fun multInv(a: Long, b: Long): Long {
        if (b == 1L) return 1L
        var aa = a
        var bb = b
        var x0 = 0L
        var x1 = 1L
        while (aa > 1) {
            val q = aa / bb
            var t = bb
            bb = aa % bb
            aa = t
            t = x0
            x0 = x1 - q * x0
            x1 = t
        }
        if (x1 < 0) x1 += b
        return x1
    }
}



