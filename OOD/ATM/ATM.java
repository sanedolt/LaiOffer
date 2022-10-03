package com.laioffer.OOD.ATM;

import java.util.ArrayList;
import java.util.List;

public class ATM {
    public static final double WITHDRAW_LIMIT = 1000.0;
    public static final double DEPOSIT_LIMIT = 10000.0;

    private final String atmNo;  //machine no
    private Card currentCard;
    private List<Transaction> transactions; // list of transactions made for curCard
    private List<Integer> cash;

    public ATM(String no) {
        this.atmNo = no;
    }
    // take a card and a pin
    public void insertCard(Card card, int pin) {
        // TODO: check pin matches card
        if (validatePin(card, pin)) {
            currentCard = card;
            transactions = new ArrayList<>();
        } else {
            throw new AuthenticationException("Invalid pin");
        }
    }

    private boolean validatePin(Card card, int pin) {
        return pin == card.getPin(); // reality -> getPin() is via bank system
    }

    public boolean returnCard() {
        if (currentCard == null) return false;
        currentCard = null;
        return true;
    }

}
