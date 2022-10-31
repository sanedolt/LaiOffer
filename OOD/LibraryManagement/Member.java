package com.laioffer.OOD.LibraryManagement;

import java.util.List;

public class Member extends Account {
    private static final int MAX_BOOKS_ISSUED_TO_A_USER = 5;

    private List<BookItem> borrowed;

    public boolean reserveBookItem(BookItem bookItem) {
        Reservation reservation =
                BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if (reservation != null) {
            throw IllegalAccessException("This book is already reserved");
            return false;
        }
        BookReservation.Reserve(bookItem, this);
        return true;
    }

    ;

    public boolean checkoutBookItem(BookItem bookItem) {
        if (borrowed.size() >= MAX_BOOKS_ISSUED_TO_A_USER) {
            throw IllegalStateException("The user has already checked-out maximum number of books");
            return false;
        }
        Reservation reservation =
                BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if (reservation != null && reservation.getMemberId() != this.getID()) {
            // book item has a pending reservation from another user
            throw IllegalAccessException("This book is reserved by another member");
            return false;
        } else if (reservation != null) {
            // book item has a pending reservation from the given member, update it
            reservation.updateStatus(ReservationStatus.COMPLETED);
        }

        if (!bookItem.checkout(this.getID())) {
            return false;
        }
        borrowed.add(bookItem);
        return true;
    }

    public void returnBookItem(BookItem bookItem) {
        // this.checkForFine(bookItem.getBarcode());
        borrowed.remove(bookItem);
        BookReservation bookReservation =
                BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if (bookReservation != null) {
            // book item has a pending reservation
            bookItem.updateBookItemStatus(BookStatus.RESERVED);
        } else {
            bookItem.updateBookItemStatus(BookStatus.AVAILABLE);
        }
    }
}