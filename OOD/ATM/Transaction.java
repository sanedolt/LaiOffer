package com.laioffer.OOD.ATM;

import java.time.LocalTime;
import java.util.UUID;

public abstract class Transaction {
    protected final String transactionId; //UUID.randomUUID().toString()
    protected Account account;
    protected LocalTime transactionTime;
    protected TransactionType type;

    public Transaction(Account account) {
        this.account = account;
        this.transactionId = UUID.randomUUID().toString();
        this.transactionTime = LocalTime.now();
    }
    public abstract boolean makeTransaction (double amount);