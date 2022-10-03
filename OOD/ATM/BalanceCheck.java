package com.laioffer.OOD.ATM;

class BalanceCheck extends Transaction{
    public BalanceCheck(Account account) {
        super(account);
        type = TransactionType.BalanceCheck;
    }
    @Override
    public boolean makeTransaction(double amount) {
        System.out.println(account.getBalance());
        return true;
    }
}
