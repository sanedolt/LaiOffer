package com.laioffer.employeesystem;

public class EmployeeSystem {
    public static void main(String[] args) {
        Manager sun = new Manager("Sun",0,10);
        Employee anna = new Employee("Anna",888,5);
        Employee newBie = new Employee("NewBie",888,1);

        sun.addReport(anna);
        sun.addReport(newBie);

        sun.printInfo();
        System.out.println();
        anna.printInfo();
        newBie.printInfo();

        anna.takePto(1);
    }
}
