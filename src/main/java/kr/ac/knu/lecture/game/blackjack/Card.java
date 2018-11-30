package kr.ac.knu.lecture.game.blackjack;

import kr.ac.knu.lecture.exception.NoSuchRankException;
import lombok.Data;

/**
 * Created by rokim on 2018. 5. 26..
 */
@Data
public class Card {
    private final int rank;
    private final Suit suit;

    public Card(int rank, Suit suit) {
        if (rank > 13) {
            throw new NoSuchRankException();
        }
        this.rank = rank;
        this.suit = suit;
    }
}
