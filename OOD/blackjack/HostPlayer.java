package com.laioffer.OOD.blackjack;

public class HostPlayer extends Player{
    //子类field 默认继承全部父类field
    private static final int DEALER_MUST_HIT = 17;
    private int popularity;
    public HostPlayer(String name){
        super(name);
    }
    public HostPlayer(String name, int popularity){
        super(name);
        this.popularity = popularity;
    }

    @Override
    public Action decideAction(Deck d){
        if (score() < DEALER_MUST_HIT) {
            hit(d);
            return Action.Hit;
        } else {
            stand();
            return Action.Stand;
        }
    }
}
