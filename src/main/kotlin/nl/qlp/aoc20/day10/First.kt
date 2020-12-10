package nl.qlp.aoc20.day10

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Int {
        val joltages = readLinesFromInput().map { it.toInt() }.toMutableList().apply { add(3 + maxOrNull()!!) }

        val solution = joltages.solution()!!

        println(solution)

        var differences = mutableMapOf<Int, Int>()

        repeat(solution.size - 1) { index ->
            (solution[index + 1] - solution[index]).let {
                differences[it] = differences.getOrDefault(it, 0) + 1
            }
        }

        println(differences)

        return differences.getOrDefault(1, 0) * differences.getOrDefault(3, 0)
   }
}
