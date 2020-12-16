package nl.qlp.aoc20.day16

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run(): Long {
        val sections = readFromInput().split("\n\n")

        val fields = sections[0].split("\n").map { it.toField() }
        val myTicket = sections[1].split("\n")[1].toTicket()
        val nearby = sections[2].split("\n").mapIndexedNotNull { index, s -> if (index == 0) null else s.toTicket() }

        val fieldCandidates = IntRange(1, fields.size).map { fields.toMutableSet() }

        val validTickets = nearby.filter { it.isValid(fields) } + myTicket

        while (fieldCandidates.any { it.size > 1}) {
            fieldCandidates.forEachIndexed { index, candidates ->
                if (candidates.size == 1) {
                    fieldCandidates.forEach { candidatesToRemoveFrom ->
                        if (candidatesToRemoveFrom.size != 1) {
                            candidatesToRemoveFrom.remove(candidates.single())
                        }
                    }
                }
            }

            validTickets.forEach { ticket ->
                ticket.numbers.forEachIndexed { index, number ->
                    fieldCandidates[index].removeIf { field -> field.sizes.none { size -> size.contains(number) } }
                }
            }
        }

        return fieldCandidates
                .asSequence()
                .map { it.single() }
                .mapIndexed { index, field -> field to myTicket.numbers[index] }
                .filter { it.first.name.startsWith("departure") }
                .map { it.second.toLong() }
                .reduce { acc, i -> acc * i }
    }

    data class Ticket(val numbers: List<Int>) {
        fun isValid(fields: List<Field>) = numbers.all { number -> fields.any { field -> field.sizes.any { size -> size.contains(number) } } }
    }

    data class Field(val name: String, val sizes: Set<IntRange>)

    private fun String.toTicket() = Ticket(split(",").map { it.toInt()} )

    private fun String.toField() = Field(
            substring(0, indexOf(':')),
            substring(indexOf(':') + 1, length)
                    .split("or")
                    .map { it.trim().toIntRange() }
                    .toSet()
    )

    private fun String.toIntRange() = IntRange(
            substring(0, indexOf('-')).toInt(),
            substring(indexOf('-') + 1, length).toInt()
    )


}