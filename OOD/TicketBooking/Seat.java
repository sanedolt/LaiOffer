package com.laioffer.OOD.TicketBooking;

public class Seat {
    public enum SeatType {
        REGULAR, PREMIUM, ACCESSIBLE, RECLINER
    }

    private int seatId;
    private boolean isReserved;
    private double price;

    private Show show;

    public boolean book() {
        if (!isReserved) {
            isReserved = true;
            return true;
        }
        return false;
    }

    public void markAsAvailable() {
        isReserved = false;
    }

    public int getSeatId() {
        return seatId;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public double getPrice() {
        return price;
    }

    public void updatePrice(double price) {
        this.price = price;
    }

    public Show getShow() {
        return show;
    }

    public void updateShow(Show show) {
        this.show = show;
    }
}
