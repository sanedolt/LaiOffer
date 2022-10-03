package com.laioffer.OOD.LibraryManagement;

import java.time.LocalDate;

public class BookItem extends Book{
    private String barcode;
    private boolean isReferenceOnly;
    private LocalDate borrowed;
    private LocalDate dueDate;
    private double price;
    private BookStatus status;
    private Location placedAt;

    public boolean checkout(String memberId) {
        if (this.getIsReferenceOnly()) {
            throw IllegalAccessException("This book is Reference only and can't be borrowed");
            return false;
        }
        if(!BookLending.lendBook(this.getBarcode(), memberId)){
            return false;
        }
        this.updateBookItemStatus(BookStatus.LOANED);
        return true;
    }

    public void updateBookItemStatus(BookStatus status) {
        this.status = status;
    }

    public boolean getIsReferenceOnly() {
        return isReferenceOnly;
    }
    public String getBarcode() {
        return barcode;
    }
    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
