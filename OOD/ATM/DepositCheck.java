package com.laioffer.OOD.ATM;

class DepositCheck extends Transaction {
    public DepositCheck(Account account) {
        super(account);
        type = TransactionType.DepositCheck;
    }

    @Override
    public boolean makeTransaction(double amount) {
        double limit = Math.min(account.getDepositLimit(),ATM.DEPOSIT_LIMIT);
        if (amount > limit) {
            System.out.println("Cannot deposit more than " + limit);
            return false;
        }
        account.setBalance(account.getBalance() + amount);
        // do not update the money left in ATM
        return true;
    }
}
