package com.laioffer.OOD.CoffeeMachine;

public class CappuccinoStrategy implements IStrategy {

    public IValidator validator;
    public Recipe recipe;
    public CappuccinoStrategy(IValidator validator, Recipe recipe) {
        this.validator = validator;
        this.recipe = recipe;
    }

    public boolean IsValid() {
        return validator.IsValid(DrinkType.CAPPUCCINO,this.recipe);
    }

    public (double, Drink) Process() {
        if(!IsValid())
            throw new Exception("Recipe isn't valid");
        Drink newCappuccino = new Cappuccino();
        newCappuccino.SetRecipe(this.recipe);
        return (recipe.GetTotalPrice(), newCappuccino);
    }

}
