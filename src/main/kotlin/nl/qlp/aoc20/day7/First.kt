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


    data class Rule(val line: String) {
        fun bag() = Bag(line.substring(0, line.indexOf(" bags")))

        private fun constraints() = line.substring(line.indexOf("contain ") + "contain ".length, line.length - 1)
                .split(", ")
                .mapNotNull {
                    if (it == "no other bags") null else Constraint(
                            count = it.substring(0, it.indexOf(" ")).toInt(),
                            bag = Bag(it.substring(it.indexOf(" ") + 1, it.indexOf(" bag"))))
                }

        fun holds(bag: Bag) = constraints().filter { it.bag == bag }.count() > 0
    }

    data class Bag(val color: String)

    data class Constraint(val count: Int, val bag: Bag)
}
