package com.laioffer.OOD.ATM;

public class Card {
    private final String cardNo;
    private final Account account;
    private int pin;

    public Card(String cardNo, Account account) {
        this.cardNo = cardNo;
        this.account = account;
    }

    public int getPin() {
        return pin;
    }
    public int setPin(int pin) {
        this.pin = pin;
    };
    public String getCardNo() {
        return cardNo;
    };
    public Account getAccount() {
        return account;
    };

    public void updateBalance(double amount) {
        account.setBalance(amount);
    };

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public double getDepositLimit() {
        return depositLimit;
    }

    public void setDepositLimit(double depositLimit) {
        this.depositLimit = depositLimit;
    }
}
