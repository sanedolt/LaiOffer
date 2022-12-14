package com.laioffer.OOD.CoffeeMachine;

public class EspressoStrategy implements IStrategy {

    public IValidator validator;
    public Recipe recipe;
    public EspressoStrategy(IValidator validator, Recipe recipe) {
        this.validator = validator;
        this.recipe = recipe;
    }

    public boolean IsValid() {
        return validator.IsValid(DrinkType.ESPRESSO,this.recipe);
    }

    public Ready Process() throws Exception {
        if(!IsValid())
            throw new Exception("Recipe isn't valid");
        Drink newEspresso = new Espresso();
        newEspresso.SetRecipe(this.recipe);
        return new Ready (recipe.GetTotalPrice(), newEspresso);
    }

}
