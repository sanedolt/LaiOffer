package com.laioffer.OOD.VendingMachine;

public class Cell {
    private static final int MAX_NUM_OF_PRODUCT = 10;
    private Product product;
    private int count; // num of products

    public Cell(Product p, int count) {
        if (count > MAX_NUM_OF_PRODUCT) {
            throw RuntimeException("You cannot put more than " + MAX_NUM_OF_PRODUCT
                    + " items in a cell");
        }
        this.product = p;
        this.count = count;
    }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
    public Product getProduct() { return product; }
    public void setProduct() { this.product = product; }
}
