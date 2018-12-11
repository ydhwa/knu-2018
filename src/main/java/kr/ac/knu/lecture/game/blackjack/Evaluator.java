package kr.ac.knu.lecture.game.blackjack;

import java.util.Map;

/**
 * Created by rokim on 2018. 5. 27..
 */
public class Evaluator {
    private Map<String, Player> playerMap;
    private Dealer dealer;

    public Evaluator(Map<String, Player> playerMap, Dealer dealer) {
        this.playerMap = playerMap;
        this.dealer = dealer;
    }

    public boolean evaluate() {
        if (playerMap.values().stream().anyMatch(player -> player.isPlaying())) {
            return false;
        }

        int dealerResult = dealer.getHand().getCardSum();
        boolean isDealerBlackjack = (dealerResult == 21) && (dealer.getHand().getCardList().size() == 2);

        playerMap.forEach((s, player) -> {
            int playerResult = player.getHand().getCardSum();
            boolean isPlayerBlackjack = (playerResult == 21) && (player.getHand().getCardList().size() == 2);

            if (playerResult > 21) {
                player.lost(isDealerBlackjack);
            } else if (dealerResult > 21) {
                player.win(isPlayerBlackjack);
            } else if (playerResult > dealerResult) {
                player.win(isPlayerBlackjack);
            } else if (playerResult == dealerResult) {
                player.tie();
            } else {
                player.lost(isDealerBlackjack);
            }
        });

        return true;
    }

}
