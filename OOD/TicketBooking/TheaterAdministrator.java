package com.laioffer.OOD.TicketBooking;

public class TheaterAdministrator extends Member {
    private TheaterCompany theaterCompany;

    public TheaterAdministrator(String id, String name, String email, String phone) {
        super(id, name, email, phone);
    }

    public boolean addMovie(Movie movie, MovieHall theater) {
        theater.addMovie(movie);
    }

    public boolean addShow(Show show, MovieHall theater) {
        theater.addShow(show);
    }
}