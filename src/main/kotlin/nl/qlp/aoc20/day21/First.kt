package nl.qlp.aoc20.day21

import nl.qlp.aoc20.readFromInput
import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Int {
        val foods = readLinesFromInput().map { it.toFood() }

        val allergenToFood = foods
                .flatMap { food -> food.allergens.map { allergen -> allergen to food.ingredients } }
                .groupByTo(mutableMapOf(), { pair -> pair.first }, { pair -> pair.second })
                .map { entry -> entry.key to entry.value.reduce { acc, set -> acc.intersect(set) }.toMutableSet() }
                .toMap()

        while (allergenToFood.filterValues { it.size > 1}.isNotEmpty()) {
            val assignedIngredients = allergenToFood.filterValues { it.size == 1 }.map { it.value.single() }.toSet()

            allergenToFood.filterValues { it.size > 1 }.values.forEach { it.removeAll(assignedIngredients) }
        }

        val ingredientsWithAllergen = allergenToFood.map { it.value.single() }.toSet()

        return foods.flatMap { it.ingredients }.filterNot { ingredientsWithAllergen.contains(it)}.size
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