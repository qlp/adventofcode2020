package nl.qlp.aoc20.day21

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run(): String {
        val foods = readLinesFromInput().map { it.toFood() }

        val allergenToIngredients = foods
                .flatMap { food -> food.allergens.map { allergen -> allergen to food.ingredients } }
                .groupByTo(mutableMapOf(), { pair -> pair.first }, { pair -> pair.second })
                .map { entry -> entry.key to entry.value.reduce { acc, set -> acc.intersect(set) }.toMutableSet() }
                .toMap()

        while (allergenToIngredients.filterValues { it.size > 1}.isNotEmpty()) {
            val assignedIngredients = allergenToIngredients.filterValues { it.size == 1 }.map { it.value.single() }.toSet()

            allergenToIngredients.filterValues { it.size > 1 }.values.forEach { it.removeAll(assignedIngredients) }
        }

        val allergenToIngredient = allergenToIngredients
                .map { it.key to it.value.single() }
                .sortedBy { it.first.name }
                .map { it.second }
                .joinToString(",")

        return allergenToIngredient
    }

    fun String.toFood() = split(" (contains ").let { sections -> Food(
            sections[0].split(' ').map { Ingredient(it) }.toSet(),
            if (sections.size == 2) sections[1].split(")")[0].split(", ").map { Allergen(it) }.toSet() else emptySet())
    }

    data class Food(val ingredients: Set<Ingredient>, val allergens: Set<Allergen>)

    data class Ingredient(val name: String) {
        override fun toString() = name
    }

    data class Allergen(val name: String) {
        override fun toString() = name
    }
}