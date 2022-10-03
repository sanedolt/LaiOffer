package com.laioffer.OOD.NotificationSystem;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Employee implements Notifiable{
    private final String id;
    private String name;
    private Employee manager;
    private boolean isIC;
    private boolean optedOutNotification;
    private List<Notification> inbox;

    public Employee(String id, String name, boolean isIC) {
        this.id = id;
        this.name = name;
        this.isIC = isIC;
    }

    public void sendNotification(String msg) {
        Notification notification = new Notification(msg,this);
        if (isIC) {
            this.manager.getNotified(notification);
            List<Employee> peers = ((Manager) this.manager).getSubordinates();
            for (Employee peer : peers) {
                if (peer.optedOutNotification) {continue;}
                peer.getNotified(notification);
            }
        } else { // manager
            sendToAllSubordinates(this,notification);
        }
    }
    private void sendToAllSubordinates(Employee employee, Notification notification) {
        Queue<Employee> q = new ArrayDeque<>();
        q.offer(employee);
        while (!q.isEmpty()) {
            Employee em = q.poll();
            if (!em.isOptedOutNotification()) {
                em.getNotified(notification);
            }
            if (!em.isIC()) {
                for (Employee e : (em.getManager().getSubordinates())) {
                    q.offer(e);
                }
            }
        }
    }

    public void getNotified(Notification notification) {
        inbox.add(notification);
    }
    public void setOptedOutNotification(boolean optedOutNotification) {
        this.optedOutNotification = optedOutNotification;
    }
    public boolean isOptedOutNotification(){
        return optedOutNotification;
    }
    public boolean isIC() {
        return isIC;
    }
    public Manager getManager() {
        return manager;
    }

}
