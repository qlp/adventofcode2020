package nl.qlp.aoc20.day16

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Int {
        val sections = readFromInput().split("\n\n")

        val fields = sections[0].split("\n").map { it.toField() }
        val nearby = sections[2].split("\n").mapIndexedNotNull { index, s -> if (index == 0) null else s.toTicket() }

        val errorRate = nearby.map { ticket ->
            ticket.numbers
                    .filter { number -> !fields.any { field -> field.sizes.any { size -> size.contains(number) } } }
                    .sum()
        }.sum()

        return errorRate
    }

    data class Ticket(val numbers: List<Int>)

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