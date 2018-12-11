package kr.ac.knu.lecture.game.blackjack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Hand {
    private Deck deck;
    @Getter
    private List<Card> cardList = new ArrayList<>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public int getCardSum() {
        // 21 넘는 경우의 수에 대해서는 아예 고려하지 않음
        int[][] aceCases = {{}, {1, 11}, {2, 12}, {3, 13}, {4, 14}};
        AtomicInteger aceCount = new AtomicInteger();

        int cardSum = cardList.stream().mapToInt(card -> {
            int rank = card.getRank();
            if (rank > 10) {
                return 10;
            } else if(rank == 1) {
                aceCount.getAndIncrement();
                return 0;
            }
            return rank;
        }).sum();

        if(aceCount.get() > 0) {
            int[] cardSums = new int[2];
            for (int i = 0; i < aceCases[aceCount.get()].length; i++) {
                cardSums[i] = cardSum + aceCases[aceCount.get()][i];
            }
            return getBestSum(cardSums);
        }

        return cardSum;
    }
    public int getBestSum(int[] cardSums) {
        int smallerCase = cardSums[0];
        int largerCase = cardSums[1];

        if(largerCase > 21) {
            return smallerCase;
        } else if(largerCase > 18) {
            return largerCase;
        } else {
            return smallerCase;
        }
    }

    public void reset() {
        cardList.clear();
    }
}
