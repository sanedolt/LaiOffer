package com.laioffer.OOD.VendingMachine;

public class VendingMachine {

    private final Cell[] inventory;
    double cash; // bills inserted
    double balance; // cur balance
    Cell selected; // cur selected cell

    public VendingMachine(int numOfCell) {
        this.inventory = new Cell[numOfCell];
        ProductType[] arr = ProductType.values();
        for (int i=0;i<numOfCell;i++) {
            Product p = new Product(arr[i%arr.length],2.0);
            Cell[i] = new Cell(p,1);
        }
    }

    public void takeCash(double cash) {
        // check if cash input valid: over $20 bill, or broken cash
        // throw exceptions: invalid cashInput, refund()
        this.cash += cash;
    }

    public boolean selectItem(int cellNum) {
        System.out.println("Please enter the product number:" );
        if (cellNum < 0 || cellNum > inventory.length) {
            throw RuntimeException("Invalid num, please select the product number:");
            return false;
        }
        selected = inventory[cellNum];
        return true;
    }

    public Product purchase() {
        if (selected == null) {
            System.out.println("You haven't selected any item yet." );
            return null;
        }
        if (selected.getCount() <= 0) {
            System.out.println("The item you selected is out of stock." );
            return null;
        }
        if (cash < selected.getProduct().getPrice()) {
            System.out.println("Not enough cash for this item");
            return null;
        }
        selected.setCount(selected.getCount() - 1); //decrease count by 1
        cash -= selected.getProduct().getPrice(); //subtract price from balance
        balance += selected.getProduct().getPrice();
        return selected.getProduct();
    }

    public double refund() {
        return cash;
    }
}
