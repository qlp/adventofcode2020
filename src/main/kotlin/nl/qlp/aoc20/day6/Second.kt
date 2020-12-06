package nl.qlp.aoc20.day6

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println("${Second().run()}")
}

class Second {
    fun run() = readFromInput()
        .split("\n\n")
        .map { group ->
            val candidates = group.asSequence().filter { it.isLetter() }.toMutableSet()

            group
                .split("\n")
                .forEach { person -> candidates.retainAll(person.asSequence().toSet()) }

            candidates.size
        }
        .sumBy { it }
}
