package com.laioffer.OOD.NotificationSystem;

public interface Notifiable {
    public void sendNotification(String msg);
    public void getNotified(Notification notification);
}
