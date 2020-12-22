package nl.qlp.aoc20.day22

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run() = readFromInput()
            .split("\n\n")
            .map { it.split("\n") }
            .map { it.subList(1, it.size) }
            .map { strings -> strings.map { it.toLong() } }
            .map { Deck(it) }
            .let { Game(it[0], it[1]) }
            .winner()
            .score()

    data class Game(val player1: Deck, val player2: Deck, val history: Set<Set<Deck>> = emptySet()) {
        fun tick() =
                when {
                    history.contains(setOf(player1, player2)) -> next(Deck(listOf(player1.cards, player2.cards).flatten()), Deck(emptyList()))
                    player1.canRecurse() && player2.canRecurse() -> Game(player1.recurse(), player2.recurse()).play().let {
                        if (it.player1.lost()) {
                            next(player1.losingDeck(), player2.winningDeck(player1))
                        } else {
                            next(player1.winningDeck(player2), player2.losingDeck())
                        }
                    }
                    else -> next(player1.nextTurn(player2), player2.nextTurn(player1))
                }

        private fun next(nextPlayer1: Deck, nextPlayer2: Deck) = Game(nextPlayer1, nextPlayer2, history.toMutableSet().apply { add(setOf(player1, player2)) }.toSet())

        private fun players() = listOf(player1, player2)

        private fun play(): Game {
            return if (players().any { it.lost() }) this else tick().play()
        }

        fun winner(): Deck = play().players().filterNot { it.lost() }.single()
    }

    data class Deck(val cards: List<Long>) {
        fun lost() = cards.isEmpty()

        fun nextTurn(other: Deck) = if (cards[0] > other.cards[0]) {
            Deck(withoutTopCard().toMutableList().apply { addAll(listOf(cards[0], other.cards[0])) }.toList())
        } else {
            Deck(withoutTopCard())
        }

        fun winningDeck(other: Deck) = Deck(withoutTopCard().toMutableList().apply { addAll(listOf(cards[0], other.cards[0]))})
        fun losingDeck() = Deck(withoutTopCard())

        fun canRecurse() = cards[0] <= cards.size - 1

        fun recurse() = Deck(cards.subList(1, cards[0].toInt() + 1))

        private fun withoutTopCard() = cards.subList(1, cards.size)

        fun score() = if (cards.isEmpty()) 0 else cards.reversed().reduceIndexed { index, acc, l -> l * (index + 1) + acc }
    }
}