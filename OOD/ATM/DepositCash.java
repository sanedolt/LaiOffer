package com.laioffer.OOD.ATM;

class DepositCash extends Transaction {
    public DepositCash(Account account) {
        super(account);
        type = TransactionType.DepositCash;
    }

    @Override
    public boolean makeTransaction(double amount) {
        double limit = Math.min(account.getDepositLimit(),ATM.DEPOSIT_LIMIT);
        if (amount > limit) {
            System.out.println("Cannot deposit more than " + limit);
            return false;
        }
        account.setBalance(account.getBalance() + amount);
        // if we know how the amount was distributed, we can add API to update the money left in ATM
        return true;
    }
}
