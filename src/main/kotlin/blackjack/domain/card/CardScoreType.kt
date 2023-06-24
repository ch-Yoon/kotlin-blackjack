package blackjack.domain.card

sealed class CardScoreType {

    object Joker : CardScoreType() {
        const val smallValue = 1
        const val largeValue = 11
    }

    data class Fixed(
        val value: Int
    ) : CardScoreType() {

        init {
            require(value in FIXED_VALUE_RANGE) {
                "require value in ${FIXED_VALUE_RANGE.first} <= value <= ${FIXED_VALUE_RANGE.last}. your input : $value"
            }
        }

        companion object {
            private val FIXED_VALUE_RANGE = 2..10
        }
    }

    companion object {

        fun joker(): CardScoreType {
            return Joker
        }

        fun fixed(value: Int): CardScoreType {
            return Fixed(value)
        }
    }
}

fun List<CardScoreType.Fixed>.sum() = sumOf { it.value }

fun List<CardScoreType.Joker>.sumSmallValue() = sumOf { it.smallValue }

fun List<CardScoreType.Joker>.sumLargeValue() = sumOf { it.largeValue }