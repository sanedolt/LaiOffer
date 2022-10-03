package com.laioffer.OOD.LibraryManagement;

import java.util.HashMap;
import java.util.Map;

public class BookReservation {
    private static long id;
    private static Map<String,Reservation> map;
    BookReservation() {
        this.id = 10_000_000_000L;
        this.map = new HashMap<>();
    }

    public static void Reserve (BookItem bookItem, Member member) {
        Reservation reservation = new Reservation(id,bookItem,ReservationStatus.RESERVED,member);
        map.put(bookItem.getBarcode(),reservation); // can overwrite previous completed reservation
    }
    public static Reservation fetchReservationDetails(String barcode) {
        return map.get(barcode);
    }
}
