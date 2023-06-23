package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames
import blackjack.domain.player.captureAllCardDecks
import blackjack.domain.shuffle.Shuffler
import java.util.LinkedList

class BlackJackGame(
    shuffler: Shuffler<Card>,
    playerNames: PlayerNames,
) {

    private val cardDeck = CardDeck(shuffler)
    private val waitPlayers = LinkedList(playerNames.map { Player(it) })

    fun distributeCardsToPlayers(): CardDistributionResult {
        require(needCardDistribution()) {
            "already card distribution completed"
        }
        waitPlayers.forEach { player ->
            val cards = cardDeck.pick(CARD_DISTRIBUTION_SIZE)
            player.pass(cards)
        }
        return CardDistributionResult(
            playerCardDeckCaptures = waitPlayers.captureAllCardDecks()
        )
    }

    private fun needCardDistribution(): Boolean {
        return isCardDistributionCompleted().not()
    }

    private fun isCardDistributionCompleted(): Boolean {
        return waitPlayers.find { player -> player.notHasCard() } == null
    }

    companion object {

        private const val CARD_DISTRIBUTION_SIZE = 2
    }
}