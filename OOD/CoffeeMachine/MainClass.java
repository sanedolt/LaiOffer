package com.laioffer.OOD.CoffeeMachine;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void Main (String[] args) {

        DrinkValidator validator = new DrinkValidator();

        Ingredient ing1 = new Ingredient("ing1", 2, 2.5);
        Ingredient ing2 = new Ingredient("ing1", 2, 2.5);
        Ingredient cing3 = new Ingredient("common ing1");
        Ingredient cing4 = new Ingredient("common ing2");
        Ingredient ing3 = new Ingredient("ing1", 2, 2.5);

        validator.Set(DrinkType.ESPRESSO,ing1);
        validator.Set(DrinkType.ESPRESSO,ing2);
        Recipe recipe = new Recipe();
        recipe.AddIngredient(ing1);
        recipe.AddIngredient(ing2);
        List<Ingredient> adding = new ArrayList<>();
        adding.add(cing3);
        adding.add(cing4);
        recipe.AddCommonIngredients(adding);

        Mixer mixer = new Mixer();

        mixer.SetStrategy(new EspressoStrategy(validator, recipe));
        mixer.Process();

    }

}
