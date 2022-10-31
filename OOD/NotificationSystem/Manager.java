package com.laioffer.OOD.NotificationSystem;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    List<Employee> subordinates;
    public Manager(String id, String name) {
        super(id, name, false);
        subordinates = new ArrayList<>();
    }
    public List<Employee> getSubordinates() {
        return subordinates;
    }
    public boolean addSubordinate(Employee em) {
        return subordinates.add(em);
    }
}
