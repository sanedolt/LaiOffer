package com.laioffer.OOD.CoffeeMachine;

public class AmericanoStrategy implements IStrategy {

    public IValidator validator;
    public Recipe recipe;
    public AmericanoStrategy(IValidator validator, Recipe recipe) {
        this.validator = validator;
        this.recipe = recipe;
    }

    public boolean IsValid() {
        return validator.IsValid(DrinkType.AMERICANO,this.recipe);
    }

    public Ready Process() throws Exception {
        if(!IsValid())
            throw new Exception("Recipe isn't valid");
        Drink newAmericano = new Espresso();
        newAmericano.SetRecipe(this.recipe);
        return new Ready (recipe.GetTotalPrice(), newAmericano);
    }

}
