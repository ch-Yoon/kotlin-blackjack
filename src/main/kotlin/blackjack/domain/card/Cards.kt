package blackjack.domain.card

import blackjack.domain.score.CardScoreCalculator

data class Cards(
    val value: List<Card>,
) {
    val score = CardScoreCalculator.calculateScore(value)

    fun first(): Card {
        return value.first()
    }

    fun isEmpty(): Boolean {
        return value.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return value.isNotEmpty()
    }

    operator fun plus(card: Card): Cards {
        return Cards(value.plus(card))
    }

    companion object {

        fun empty(): Cards {
            return Cards(emptyList())
        }
    }
}
