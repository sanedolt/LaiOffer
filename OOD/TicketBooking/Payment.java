package com.laioffer.OOD.TicketBooking;

public class Payment {
    public enum PaymentStatus {
        UNPAID, PENDING, COMPLETED, DECLINED, CANCELLED, REFUNDED
    }

    public enum PaymentMethod {
        CASH, CREDIT_CARD
    }

    private double amount;
    private long createdOn;
    private int transactionId;
    private PaymentStatus status;
    private PaymentMethod paymentMethod;

    public Payment(double amount, int transactionId, PaymentStatus status, PaymentMethod paymentMethod) {
        this.amount = amount;
        this.createdOn = System.currentTimeMillis();
        this.transactionId = transactionId;
        this.paymentMethod = paymentMethod;
    }

    public void updatePaymentStatus(PaymentStatus status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
