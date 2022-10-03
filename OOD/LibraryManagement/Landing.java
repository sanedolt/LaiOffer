package com.laioffer.OOD.LibraryManagement;

import java.time.LocalDate;

public class Landing {
    private String barcode;
    private String memberID;
    private LocalDate lended;
    private LocalDate dueDate;
    private static final int DEFAULT_LENDING_DAYS = 14;

    Landing(String barcode, String memberID) {
        this.barcode = barcode;
        this.memberID = memberID;
        this.lended = LocalDate.now();
        this.dueDate = this.lended.plusDays(DEFAULT_LENDING_DAYS);
    }

}
