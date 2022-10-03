package com.laioffer.OOD.CoffeeMachine;

public class Cappuccino extends Drink{
    public Cappuccino(){
        super("Cappuccino", DrinkType.CAPPUCCINO);
    }

    public Cappuccino(String name, DrinkType type) {
        super(name, type);
    }
}
