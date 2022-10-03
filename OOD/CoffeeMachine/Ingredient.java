package com.laioffer.OOD.CoffeeMachine;

public class Ingredient {
    public String name;
    public int quantity;
    public double price;

    public Ingredient(String name, int quantity, double price){
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }

    public Ingredient(String name){
        this.quantity = 0;
        this.price = 0;
        this.name = name;
    }
}
