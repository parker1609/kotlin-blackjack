package blackjack.domain.rule

import blackjack.domain.card.DrewCards
import blackjack.domain.card.State

class MatchedProfitRule {

    fun profit(dealerDrewCards: DrewCards, participantDrewCards: DrewCards, bet: Int): Int {
        require(participantDrewCards.isFinished) { "참가자는 카드를 모두 뽑은 후에 베팅 금액을 계산할 수 있습니다." }

        if (participantDrewCards.state == State.BUST) {
            return -bet
        }

        if (participantDrewCards.state == State.BLACKJACK && dealerDrewCards.state == State.BLACKJACK) {
            return 0
        }

        if (participantDrewCards.state == State.BLACKJACK && dealerDrewCards.state != State.BLACKJACK) {
            return (bet * 1.5).toInt()
        }

        if (dealerDrewCards.state == State.BUST) {
            return bet
        }

        if (participantDrewCards.totalScore == dealerDrewCards.totalScore) {
            return 0
        }

        if (participantDrewCards.totalScore > dealerDrewCards.totalScore) {
            return bet
        }

        return -bet
    }
}