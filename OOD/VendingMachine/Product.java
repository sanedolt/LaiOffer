package com.laioffer.OOD.VendingMachine;

public class Product {

    private ProductType type;
    private double price;

    Product (ProductType type, double price) {
        this.type = type;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public ProductType getProductType() {
        return type;
    }
    public void setProductType(ProductType type) {
        this.type = type;
    }

}
