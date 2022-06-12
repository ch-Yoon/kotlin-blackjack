package blackjack.view

import blackjack.PlayerDecision

object InputView {
    private const val PLAYER_NAMES_QUESTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val TAKE_A_CARD_QUESTION = "한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val INVALID_PLAYER_DECISION = "y 또는 n 으로 답해주세요"

    fun getPlayerNames(): List<String> {
        println(PLAYER_NAMES_QUESTION)

        return readln()
            .split(",")
            .toNonBlankStrings()
            .throwExceptionIfEmpty()
    }

    fun getPlayerDecision(playerName: String): PlayerDecision {
        println("${playerName}은(는) $TAKE_A_CARD_QUESTION")

        while (true) {
            PlayerDecision
                .ofText(readln())
                ?.let { return(it) }
                ?: println(INVALID_PLAYER_DECISION)
        }
    }

    private fun List<String>.toNonBlankStrings(): List<String> = map { it.trim() }
        .filter { it.isNotBlank() }

    private fun List<String>.throwExceptionIfEmpty(): List<String> = takeUnless { it.isEmpty() }
        ?: throw IllegalArgumentException("최소 1개 이상의 값을 입력해주세요.")
}