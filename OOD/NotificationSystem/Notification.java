package com.laioffer.OOD.NotificationSystem;

public class Notification {
    String content;
    Employee sender;
    public Notification(String msg, Employee sender) {
        this.content = msg;
        this.sender = sender;
    }
}
