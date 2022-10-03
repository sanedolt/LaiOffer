package com.laioffer.OOD.CoffeeMachine;

public class Americano extends Drink{
    public Americano(){
        super("Americano", DrinkType.AMERICANO);
    }

    public Americano(String name, DrinkType type) {
        super(name, type);
    }
}
