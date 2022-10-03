package com.laioffer.OOD.CoffeeMachine;

public class MainClass {
    public static void Main (string[] args) {

        var validator = new DrinkValidator();
        validator.Set(DrinkType.ESPRESSO,ing1);
        validator.Set(DrinkType.ESPRESSO,ing2);

        var ing1 = new Ingredient("ing1", 2, 2.5);
        var ing2 = new Ingredient("ing1", 2, 2.5);
        var cing3 = new Ingredient("common ing1");
        var cing4 = new Ingredient("common ing2");
        var ing3 = new Ingredient("ing1", 2, 2.5);

        var recipe = new Recipe();
        recipe.AddIngredient(ing1);
        recipe.AddIngredient(ing2);
        recipe.AddCommonIngredients(new List<Ingredient>{cing3,cing4});

        var mixer = new Mixer();

        mixer.SetStrategy(new EspressoStrategy(validator, recipe));
        mixer.Process();

    }

}
