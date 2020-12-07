package nl.qlp.aoc20.day7

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Int {
        val rules = readLinesFromInput().map { Rule(it) }

        val bag = Bag("shiny gold")

        val containers = rules.filter { it.holds(bag) }.map{ it.bag() }.toMutableSet()

        do { } while (containers.addAll(rules.filter { rule -> containers.any { rule.holds(it) } }.map { it.bag() }))

        return containers.size
    }


}
