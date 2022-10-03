package com.laioffer.OOD.LibraryManagement;

import java.util.HashMap;
import java.util.Map;

public class BookLending {
    private static long id;
    private static Map<Long,Landing> map;
    public BookLending() {
        this.id = 10_000_000_000L;
        this.map = new HashMap<>();
    }

    public static boolean lendBook(String barcode, String memberId) {
        Landing ld = new Landing(barcode,memberId);
        map.put(id,ld);
        return true;
    }

}
