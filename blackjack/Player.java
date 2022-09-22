package com.laioffer.blackjack;

import java.util.*;

public class Player {
    private static final Random random = new Random();
    protected List<Card> cardInHand;
    private String playerName;

    Player(String name) {
        this.cardInHand = new ArrayList<>();
        this.playerName = name;
    }

    public String getName() {
        return this.playerName;
    }
    public Action decideAction(Deck d) {
        int seed = score();
        if (seed <= 11) {
            hit(d);
            return Action.Hit;
        }
        if (random.nextDouble() > ((seed - 8) / 10)) { //在分数为12~17时 动态决定抽卡概率 12: 60%抽牌，13:50%抽牌..
            hit(d);
            return Action.Hit;
        } else {
            stand();
            return Action.Stand;
        }
    }

    public void hit(Deck d) {
        cardInHand.add(d.dealCard());
    }

    public void stand() {
    }

    public int score() {
        int res = 0;
        int numberOfAce = 0;
        for (int i = 0; i < cardInHand.size(); i++) {
            if (cardInHand.get(i).isAce()) {
                numberOfAce++;
                res++;
            } else if (cardInHand.get(i).isFace()) {
                res += 10;
            } else {
                res += cardInHand.get(i).getValue();
            }
        }
        if (numberOfAce > 0 && res <= 11) {
            res += 10;
        }
        if (res > 21) {
            return -1;
        }
        if (cardInHand.size() > 5) {
            return 21 + res;
        }
        return res;
    }

    public boolean isBusted() {
        return score() == -1;
    }

    public boolean isBlackJack() {
        if (cardInHand.size() != 2) {
            return false;
        }
        Card a = cardInHand.get(0);
        Card b = cardInHand.get(1);
        return (a.isAce() && b.isFace()) || (b.isAce() && a.isFace());
    }

    public void currentStatus() {
        System.out.println("Current Score: " + score());
        for (Card c : cardInHand) {
            System.out.println(c.toString());
        }
    }
}
