package nl.qlp.aoc20.day22

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Long {
        var game = readFromInput()
                .split("\n\n")
                .map { it.split("\n") }
                .map { it.subList(1, it.size) }
                .map { strings -> strings.map { it.toLong() } }
                .map { Deck(it) }
                .let { Game(it[0], it[1]) }

        while (game.winner() == null) {
            game = game.tick()

            println(game)
        }

        println(game.winner())
        println(game.winner()!!.score())

        return 0
    }


    data class Game(val player1: Deck, val player2: Deck) {
        fun tick() =
            if (players().any { it.lost() }) {
                this
            } else {
                Game(player1.nextTurn(player2), player2.nextTurn(player1))
            }

        private fun players() = listOf(player1, player2)

        fun winner(): Deck? = players().filterNot { it.lost() }.let { if (it.size == 1) it[0] else null }
    }

    data class Deck(val cards: List<Long>) {
        fun lost() = cards.isEmpty()

        fun nextTurn(other: Deck) = if (cards[0] > other.cards[0]) {
            Deck(withoutTopCard().toMutableList().apply { addAll(listOf(cards[0], other.cards[0])) }.toList())
        } else {
            Deck(withoutTopCard())
        }

        private fun withoutTopCard() = cards.subList(1, cards.size)

        fun score() = if (cards.isEmpty()) 0 else cards.reversed().reduceIndexed { index, acc, l -> l * (index + 1) + acc }
    }
}