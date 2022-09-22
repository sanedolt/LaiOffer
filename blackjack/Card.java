package com.laioffer.blackjack;

public class Card {
    private final Suit suit;
    private final int value;

    Card(Suit suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public Suit getSuit(){
        return suit;
    }

    public boolean isAce(){
        return value == 1;
    }

    public boolean isFace(){
        return value > 10;
    }

    @Override
    public String toString() {
        Character c = null;
        switch (value) {
            case 1:
                c = 'A';
                break;
            case 11:
                c = 'J';
                break;
            case 12:
                c = 'Q';
                break;
            case 13:
                c = 'K';
                break;
            default:
                c = (char) (value+'0');
        }
        return "Card { " + "suite = " + suit + " " + "value = " + (c==':'?"10":c) + " }";
    }

//    @Override
    public int compareTo(Card o) {
        return this.value - o.value;
    }
}
