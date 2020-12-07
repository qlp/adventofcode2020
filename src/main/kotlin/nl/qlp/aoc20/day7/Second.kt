package nl.qlp.aoc20.day7

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run(): Int {
        val rulesByBag = readLinesFromInput().map { Rule(it) }.associateBy { it.bag() }

        return count(bag = Bag("shiny gold"), rulesByBag = rulesByBag)
    }

    fun count(bag: Bag, rulesByBag: Map<Bag, Rule>): Int {
        val constraints = rulesByBag[bag]!!.constraints()

        return if (constraints.isEmpty()) {
            0
        } else {
            constraints.map { constraint -> constraint.count + constraint.count * count(constraint.bag, rulesByBag) }.sum()
        }
    }
}