package com.laioffer.OOD.TicketBooking;

public class Show {

    private int showId;
    private long createdOn;
    private String startTime; // example: 6:30PM
    private int durationInMin;
    private MovieHall playingAt;
    private Movie movie;

    public Show(int showId, String startTime, MovieHall playingAt, Movie movie) {
        this.showId = showId;
        this.createdOn = System.currentTimeMillis();
        this.startTime = startTime;
        this.playingAt = playingAt;
        this.movie = movie;
        this.durationInMin = movie.getDurationInMins();
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDurationInMin() {
        return durationInMin;
    }

    public void setDurationInMin(int durationInMin) {
        this.durationInMin = durationInMin;
    }

    public MovieHall getPlayingAt() {
        return playingAt;
    }

    public void setPlayingAt(MovieHall playingAt) {
        this.playingAt = playingAt;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
