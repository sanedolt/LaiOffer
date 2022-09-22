package com.laioffer.blackjack;

import java.util.*;

public class Deck {
    private static final Random random = new Random();
    private final List<Card> cards = new ArrayList<>();
    Deck(){
        for(int i = 1; i<=13; i++){
            for(Suit s: Suit.values()){
                cards.add(new Card(s, i));
            }
        }
    }

    Card dealCard(){
        return cards.remove(cards.size()-1);
    }

    int deckSize(){
        return cards.size();
    }

    void shuffle(){
        int size = cards.size();
        for(int i = 0; i<size; i++){
            int j = (int) (size * Math.random());
            Card a = cards.get(i);
            Card b = cards.get(j);
            cards.set(i, b);
            cards.set(j, a);
        }
    }

//    void shuffle(){
//        int size = cards.size();
//        for (int i=size-1;i>=0;i--) {
//            int j = random.nextInt(i+1);
//            Card a = cards.get(i);
//            Card b = cards.get(j);
//            cards.set(i, b);
//            cards.set(j, a);
//        }
//    }
//
//    public Card[] dealHand(int number) {
//        if (number>remainingCards()) {return null;}
//        Card[] dh = new Card[number];
//        for (int i=0;i<number;i++) {
//            dh[i]=dealCard();
//        }
//        return dh;
//    }
//
//    public Card dealCard() {
//        return remainingCards()==0?null:cards.get(dealtIndex++);
//    }
//
//    private int remainingCards() {
//        return cards.size()-dealtIndex;
//    }

}