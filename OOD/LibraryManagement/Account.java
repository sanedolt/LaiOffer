package com.laioffer.OOD.LibraryManagement;

public class Account {
    private String id;
    private String password;

    public boolean resetPassword(String password) {
        this.password = password;
    };

    public String getID() {
        return id;
    };
}
