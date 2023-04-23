package patterns.models;

import patterns.models.movie.Movie;

public class Rental {

    public static final int MIN_DAYS_RENT = 1;
    private final Movie movie;
    private final int daysRented;

    private int id;

    public Rental(int id, Movie movie, int daysRented) {
        this.id = id;
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getAmount() {
        return getMovie().getPriceCode().getAmount(daysRented);
    }

    public int getFrequentRenterPoints() {
        return getMovie().getPriceCode().getFrequentRenterPoints(getDaysRented());
    }

    public int getId() {
        return id;
    }
}
