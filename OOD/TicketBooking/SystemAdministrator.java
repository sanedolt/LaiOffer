package com.laioffer.OOD.TicketBooking;

public class SystemAdministrator extends Member {
    private TicketBookingSystem system;

    public SystemAdministrator(TicketBookingSystem system) {
        this.system = system;
    }
    public void addNewTheaterCompany(TheaterCompany theaterCompany) {
        system.addTheaterCompany(theaterCompany);
    }

    public void removeTheaterCompany(TheaterCompany theaterCompany) {
        system.removeClient(theaterCompany);
    }

    public TicketBookingSystem getSystem() {
        return system;
    }

    public void setSystem(TicketBookingSystem system) {
        this.system = system;
    }
}
