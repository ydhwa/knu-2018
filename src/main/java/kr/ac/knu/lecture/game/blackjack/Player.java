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
    final int DEFAULT_BET_MONEY = 1000;

    public Player(long seedMoney, Hand hand) {
        this.balance = seedMoney - DEFAULT_BET_MONEY;
        this.hand = hand;
        this.currentBet = DEFAULT_BET_MONEY;

        isPlaying = false;
    }

    public void reset() {
        hand.reset();
        isPlaying = false;
    }

    public void placeBet(long bet) {
        if (balance < bet) {
            // all-in
            throw new NotEnoughBalanceException();
        }
        if(bet > DEFAULT_BET_MONEY) {
            balance -= bet;
            currentBet = bet;
        }

        isPlaying = true;
    }

    public void deal() {
        hand.drawCard();
        hand.drawCard();
    }

    public void win(boolean isBlackjack) {
        if(isBlackjack) {
            balance += (double)currentBet * 2.5;
        } else {
            balance += currentBet * 2;
        }
        setBetMoney();
    }

    public void tie() {
        balance += currentBet;
        setBetMoney();
    }

    public void lost(boolean isBlackjack) {
        if(isBlackjack) {
            balance -= (double)currentBet * 1.5;
        }
        setBetMoney();
    }

    public void setBetMoney() {
        balance -= DEFAULT_BET_MONEY;
        currentBet = DEFAULT_BET_MONEY;
    }

    public Card hitCard() {
        return hand.drawCard();
    }

    public void stand() {
        this.isPlaying = false;
    }

}
