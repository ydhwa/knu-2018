package kr.ac.knu.lecture.game.blackjack;

import kr.ac.knu.lecture.exception.NotEnoughBalanceException;
import lombok.Getter;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Player {
    @Getter
    private long balance;
    @Getter
    private long currentBet;
    @Getter
    private boolean isPlaying;
    @Getter
    private Hand hand;

    public Player(long seedMoney, Hand hand) {
        this.balance = seedMoney;
        this.hand = hand;

        isPlaying = false;
    }

    public void reset() {
        hand.reset();
        isPlaying = false;
    }

    public void placeBet(long bet) {
        if (balance < bet) {
            throw new NotEnoughBalanceException();
        }
        balance -= bet;
        currentBet = bet;

        isPlaying = true;
    }

    public void deal() {
        hand.drawCard();
        hand.drawCard();
    }

    public void win(boolean isBlackjack) {
        if(isBlackjack) {
            balance += (double)currentBet * 1.5;
        } else {
            balance += currentBet * 2;
        }
        currentBet = 0;
    }

    public void tie() {
        balance += currentBet;
        currentBet = 0;
    }

    public void lost(boolean isBlackjack) {
        if(isBlackjack) {
            balance += currentBet / 2;
        }
        currentBet = 0;
    }

    public Card hitCard() {
        return hand.drawCard();
    }

    public void stand() {
        this.isPlaying = false;
    }

}
