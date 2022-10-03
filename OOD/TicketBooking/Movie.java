package com.laioffer.OOD.TicketBooking;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private int durationInMins;
    private String language;
    private long releaseDate;
    private String genre;
    private TheaterAdministrator movieAddedBy;
    private List< Show > shows = new ArrayList<>();

    public Movie(
            String title,
            String description,
            int durationInMins,
            String language,
            long releaseDate,
            String genre,
            TheaterAdministrator movieAddedBy) {

        this.title = title;
        this.description = description;
        this.durationInMins = durationInMins;
        this.language = language;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.movieAddedBy = movieAddedBy;
    }

    public String getTitle() {
        return title;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public int getDurationInMins() {
        return durationInMins;
    }

    public void updateDurationInMins(int durationInMins) {
        this.durationInMins = durationInMins;
    }

    public String getLanguage() {
        return language;
    }

    public void updateLanguage(String language) {
        this.language = language;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public void updateReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void updateGenre(String genre) {
        this.genre = genre;
    }

    public TheaterAdministrator getMovieAddedBy() {
        return movieAddedBy;
    }

    public void updateMovieAddedBy(TheaterAdministrator movieAddedBy) {
        this.movieAddedBy = movieAddedBy;
    }

    public List< Show > getShows() {
        return shows;
    }

    public void adShow(Show show) {
        shows.add(show);
    }
}
