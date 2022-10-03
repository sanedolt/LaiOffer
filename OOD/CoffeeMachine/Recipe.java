package com.laioffer.OOD.CoffeeMachine;

import java.util.List;

public class Recipe implements IRecipe{

    public List<Ingredient> ingredients;
    private double totalPrice;
    public Recipe() {
        ingredients = new List<Ingredient>();
        totalPrice = 0.0d;
    }

    public double GetTotalPrice() {
        return totalPrice;
    }

    public void AddIngredient(Ingredient ingredient) {
        if(ingredient==null)
            throw new Exception("Ingredient isn't correct");

        this.ingredients.Add(ingredient);
        totalPrice+=ingredient.price;
    }

    public void RemoveIngredient(Ingredient ingredient) {
        if(ingredient==null)
            throw new Exception("Ingredient isn't correct");

        this.ingredients.Remove(ingredient);
        totalPrice-=ingredient.price;
    }

    public void  AddCommonIngredients(List<Ingredient> ingredients) {
        this.ingredients.AddRange(ingredients);
    }


}
