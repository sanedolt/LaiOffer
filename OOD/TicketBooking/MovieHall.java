package com.laioffer.OOD.TicketBooking;

import java.util.Set;
import java.util.HashSet;

public class MovieHall {

    private TheaterCompany owningCompany;
    private String name;
    private Set< Show > shows = new HashSet<>();
    private Set< Movie > moviesCurrentlyPlaying = new HashSet<>();

    public MovieHall(TheaterCompany owningCompany, String name, Set shows, Set moviesCurrentlyPlaying) {
        this.owningCompany = owningCompany;
        this.name = name;
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public void addMovie(Movie movie) {
        moviesCurrentlyPlaying.add(movie);
    }

    public boolean removeMovie(Movie movie) {
        if (!shows.contains(movie)) {
            moviesCurrentlyPlaying.remove(movie);
            return true;
        }
        return false;
    }

    public boolean removeShow(Show show) {
        if (!shows.contains(show)) {
            shows.remove(show);
            return true;
        }
        return false;
    }
}
