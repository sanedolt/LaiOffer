package com.laioffer.OOD.CoffeeMachine;

public class Espresso extends Drink{
    public Espresso(){
        super("Espresso", DrinkType.ESPRESSO);
    }

    public Espresso(String name, DrinkType type) {
        super(name, type);
    }
}
