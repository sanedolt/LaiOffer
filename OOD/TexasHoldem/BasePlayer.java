package com.laioffer.OOD.TexasHoldem;

public abstract class BasePlayer {
    private String name;

    public BasePlayer(String name) {
        this.name = name;
    }
    public abstract void play();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
