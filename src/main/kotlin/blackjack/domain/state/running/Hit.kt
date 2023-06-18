package blackjack.domain.state.running

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards
import blackjack.domain.state.FinishState
import blackjack.domain.state.RunningState
import blackjack.domain.state.State
import blackjack.domain.state.finish.Blackjack
import blackjack.domain.state.finish.Bust
import blackjack.domain.state.finish.Stay

class Hit(playingCards: PlayingCards) : RunningState(playingCards = playingCards) {

    override fun draw(card: Card): State {
        playingCards.add(card = card)

        if (playingCards.isBust()) {
            return Bust(playingCards = playingCards)
        }

        return Hit(playingCards = playingCards)
    }

    override fun stay(): FinishState = when {
        playingCards.isBlackjack() -> Blackjack(playingCards = playingCards)
        else -> Stay(playingCards = playingCards)
    }
}
