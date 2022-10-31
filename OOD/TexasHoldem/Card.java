package com.laioffer.OOD.TexasHoldem;

public class Card implements Comparable<Card> {
    private final Suit suit;
    private final int value;

    Card(Suit suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public int getValue(){
        return value>1?value:14; // put A as largest
    }

    public Suit getSuit(){
        return suit;
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

    @Override
    public int compareTo(Card other) {
        return (other.getValue()*4+other.getSuit().ordinal())-(this.getValue()*4+this.getSuit().ordinal());
    }
}
