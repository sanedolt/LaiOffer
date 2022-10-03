package com.laioffer.OOD.ATM;

public class Account {
    private int accountNum;
    private final User accountHolder;
    private double balance;
    private double withdrawLimit = 500;
    private double depositLimit = 1000;

    public Account (int account, User user) {
        this.accountNum = account;
        this.accountHolder = user;
    }
    public double getBalance() {
        return balance;
    };
    public void setBalance(double amount) {
        this.balance=amount;
    };

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public double getDepositLimit() {
        return depositLimit;
    }
}
