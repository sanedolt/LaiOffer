package com.laioffer.OOD.CoffeeMachine;

public interface IValidator {
    void Set(DrinkType type, Ingredient ingredient);
    void Remove(DrinkType type, Ingredient ingredient);
    boolean IsValid(DrinkType type, Recipe recipe);
}
