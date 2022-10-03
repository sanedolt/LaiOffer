package com.laioffer.OOD.TicketBooking;

import java.util.List;
import java.util.ArrayList;

public class Customer extends Member {

    private List< Booking > currentBookings = new ArrayList<>();

    public Customer(String name, String email, String phone) {
        super(name, email, phone);
    }
    public boolean makeBooking(Booking booking) {
        // making a booking for a movie show
        // if booking goes through successfully:
        // add the booking to currentBookings list
        // currentBookings.add(booking);
        // return true

        // if the booking fails:
        // return false;
    }

    public List< Booking > getBookings() {
        return currentBookings;
    }

    public boolean cancelBooking(Booking booking) {
        // if the cancellation go through
        // remove the booking from currentBookings list and
        // return true
        //
        // otherwise return false
    }

}
