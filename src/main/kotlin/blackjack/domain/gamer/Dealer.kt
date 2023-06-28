package blackjack.domain.gamer

import blackjack.domain.card.InitCard

class Dealer : Gamer() {

    override fun canHit(): Boolean {
        return state.isHit() &&
            state.cards.size == InitCard.INIT_CARD_SIZE &&
            state.cards.score.value <= NEED_HIT_SCORE
    }

    companion object {

        private const val NEED_HIT_SCORE = 16
    }
}