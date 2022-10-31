package com.laioffer.OOD.TexasHoldem;

import java.util.ArrayList;
import java.util.List;

public class Player extends BasePlayer {
    private Hand hand;
    private int balance;
    private List<Integer> bets;
    private int currentBet;
    private boolean isFold;
    public Player(String name, int balance) {
        super(name);
        this.balance = balance;
        this.bets = new ArrayList<>();
        this.hand = new Hand();
        this.isFold = false;
    }
    public void addBalance(int amount) {
        balance+=amount;
    }
    public boolean getIsFold() {
        return isFold;
    }
    public void fold() {
        isFold = true;
        hand.clearHand();
        bets.clear();
    }
    public void match(int amount) {
        if (amount > balance) {
            fold();
        }
        playBet(amount);
    }
    public void playBet(int amount) {
        amount = Math.min(amount, balance);
        bets.add(amount);
        balance -= amount;
    }
    public int getBetInput(int amount) {
        if (amount > balance) {
            fold();
        }
        return amount;
    }
    public Hand getHand() {
        return hand;
    }
    public void play() {

    }
    public Action getPlayAction(boolean first) {
        int numCards = hand.getCards().size();
        int confidence = hand.getConfidence();
        if (confidence<5) {
            return Action.Fold;
        } else if (confidence>90) {
            return Action.Allin;
        } else if (confidence>70) {
            return Action.Raise;
        } else if (confidence>50){
            if (first) {
                return Action.Bet;
            } else {
                return Action.Match;
            }
        }
        return Action.Check;
    }
}
