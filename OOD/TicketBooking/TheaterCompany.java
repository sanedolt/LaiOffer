package com.laioffer.OOD.TicketBooking;

import java.util.Set;

public class TheaterCompany {
    private String companyId;
    private String name;
    private Set< MovieHall > theatersOwned;
    private Address headQuarter;

    public TheaterCompany(String companyId, String name, Address headQuarter) {
        this.companyId = companyId;
        this.name = name;
        this.headQuarter = headQuarter;
    }

    public void addTheater(MovieHall theater) {
        theatersOwned.add(theater);
    }

    public void shutdownTheater(MovieHall theater) {
        theatersOwned.remove(theater);
    }

    public String getCompanyId() {
        return companyId;
    }

    public void updateCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public Set< MovieHall > getTheatersOwned() {
        return theatersOwned;
    }

    public Address getHeadQuarter() {
        return headQuarter;
    }

    public void updateHeadQuarter(Address headQuarter) {
        this.headQuarter = headQuarter;
    }
}