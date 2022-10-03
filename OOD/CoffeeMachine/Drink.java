package com.laioffer.OOD.CoffeeMachine;

public abstract class Drink {
    public String name;
    public DrinkType type;
    private Recipe recipe;

    public Drink(String name, DrinkType type) {
        this.name = name;
        this.type = type;
    }

    public void SetRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void GetRecipe(Recipe recipe) {
        return this.recipe;
    }
}
