package com.laioffer.OOD.EmployeeSystem;

import java.util.List;
import java.util.ArrayList;

public class Manager extends Employee {
    public List<Employee> reports;

    public Manager(String name, int id, int level) {
        super(name,id,level);
        reports = new ArrayList<>();
    }

    public void addReport(Employee employee) {
        reports.add(employee);
        employee.mgr=this;
    }

    @Override
    public void printInfo() {
        System.out.println("Manager Name: "+name+", ID: "+id);
        for (Employee e: reports) {
            System.out.println("   "+name+"'s report: ");
            e.printInfo();
        }
    }

    public boolean approvePto(int day, Employee employee) {
        return true;
    }
}
