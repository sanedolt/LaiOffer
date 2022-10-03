package com.laioffer.OOD.ATM;

class Withdraw extends Transaction {
    public Withdraw(Account account) {
        super(account);
        type = TransactionType.Withdraw;
    }

    @Override
    public boolean makeTransaction(double amount) {
        double limit = Math.min(account.getWithdrawLimit(),ATM.WITHDRAW_LIMIT);
        if (amount > limit) {
            System.out.println("Cannot withdraw more than " + limit);
            return false;
        }
        if (account.getBalance() < amount) {
            System.out.println("Amount exceed your current balance");
            return false;
        }
        account.setBalance(account.getBalance() - amount);
        return true;
    }
}
