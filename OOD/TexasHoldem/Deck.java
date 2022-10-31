package com.laioffer.OOD.TexasHoldem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            int j = (int) ((size-i-1) * Math.random()+(i+1));
            Card a = cards.get(i);
            Card b = cards.get(j);
            cards.set(i, b);
            cards.set(j, a);
        }
    }
}
