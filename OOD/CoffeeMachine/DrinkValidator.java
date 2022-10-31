package com.laioffer.OOD.CoffeeMachine;

import java.util.*;

public class DrinkValidator implements IValidator {

    private Map<DrinkType, Set<Ingredient>> map;

    public DrinkValidator() {
        this.map = new HashMap<DrinkType, Set<Ingredient>>();
    }

    public void Set(DrinkType type, Ingredient ingredient){
        if(!map.containsKey(type))
            map.put(type, new HashSet<Ingredient>());

        map.get(type).add(ingredient);

    }

    public void Remove(DrinkType type, Ingredient ingredient) {
        if(!map.containsKey(type))
            return;

        map.get(type).remove(ingredient);
    }

    public boolean IsValid(DrinkType type, Recipe recipe) {
        if(!map.containsKey(type))
            return false;

        Set<Ingredient> ingredients = map.get(type);

        for (Ingredient ing : recipe.ingredients) {
            if (!ingredients.contains(ing)) {
                return false;
            }
        }
        return true;
    }

}
