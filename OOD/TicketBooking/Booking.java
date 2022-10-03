package com.laioffer.OOD.TicketBooking;

import java.util.List;

public class Booking {
    public enum BookingStatus {
        PENDING, CONFIRMED, CHECKED_IN, CANCELED
    }

    private String bookingNumber;
    private long createdOn;
    private BookingStatus status;

    private Show show;
    private List< Seat > seats;
    private Payment payment;

    public Booking(String bookingNumber, Show show, List< Seat > seats, Payment payment) {
        this.bookingNumber = bookingNumber;
        this.createdOn = System.currentTimeMillis();
        this.status = BookingStatus.PENDING;
        this.show = show;
        this.seats = seats;
        this.payment = payment;
    }

    public boolean makePayment(Payment payment) {
        // if payments succeeds
        // this.payment = payment;
        // return true;

        // if payment is unsuccessful:
        // return false;
    }

    public boolean cancel() {
        if (status != BookingStatus.CHECKED_IN) {
            status = BookingStatus.CANCELED;
            return true;
        }
        return false;
    }

    public boolean reserveSeat(Seat seat) {
        return seat.book();
    }

    public boolean reserveSeats(List< Seat > requestedSeats) {
        for (Seat seat : requestedSeats) {
            if (!seat.book()) {
                unreserve(seats); // if we are not able to
                // reserve all the requested seats we do NOT do partial booking
                return false;
            }
        }
        return true;
    }

    private void unreserve(List< Seat > seats) {
        for (Seat seat : seats) {
            if (!seat.isReserved()) {
                return;
            }
            seat.markAsAvailable();
        }
    }

    public boolean confirmBooking() {
        if (status == BookingStatus.PENDING) {
            status = BookingStatus.CONFIRMED;
            return true;
        }
        return false;
    }

    public boolean checkIn() {
        if (status == BookingStatus.CONFIRMED) {
            status = BookingStatus.CHECKED_IN;
            return true;
        }
        return false;
    }
}