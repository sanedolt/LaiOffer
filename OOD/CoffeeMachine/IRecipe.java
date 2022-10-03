package com.laioffer.OOD.CoffeeMachine;

import java.util.List;

public interface IRecipe {
    double GetTotalPrice();
    void AddIngredient(Ingredient ingredient);
    void RemoveIngredient(Ingredient ingredient);
    void AddCommonIngredients(List<Ingredient> ingredients);
}
