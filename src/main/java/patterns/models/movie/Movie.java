package patterns.models.movie;

import java.util.List;
import java.util.stream.Collectors;
import patterns.models.Person;

public class Movie {
    private String title;

    private int id;

    private String country;
    private String description;
    private Person director;
    private List<Person> actors;
    private MovieType priceCode;

    private Movie(){
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public Person getDirector() {
        return director;
    }

    public List<Person> getActors() {
        return actors;
    }
    public MovieType getPriceCode() {
        return priceCode;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Movie movie)) {
            return false;
        }

        if (!getTitle().equals(movie.getTitle())) {
            return false;
        }
        if (getCountry() != null ? !getCountry().equals(movie.getCountry())
            : movie.getCountry() != null) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(movie.getDescription())
            : movie.getDescription() != null) {
            return false;
        }
        if (getDirector() != null ? !getDirector().equals(movie.getDirector())
            : movie.getDirector() != null) {
            return false;
        }
        return getPriceCode() != null ? getPriceCode().equals(movie.getPriceCode())
            : movie.getPriceCode() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 47 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 47 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 47 * result + (getDirector() != null ? getDirector().hashCode() : 0);
        result = 47 * result + (getPriceCode() != null ? getPriceCode().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%-3d %20s %6d %6d %15s %30s %50s",
            id, title, priceCode.getAmount(1),
            priceCode.getFrequentRenterPoints(1),
            director,
            actors.stream().map(Person::getName).collect(Collectors.joining(",")),
            description);
    }

    public static class Builder {

        private String title;
        private String country;
        private String description;
        private Person director;
        private List<Person> actors;

        private MovieType priceCode;

        private int id;

        public Builder(Movie movie){
            title = movie.title;
            country = movie.country;
            actors.addAll(movie.actors);
            director = movie.director;
            description = movie.description;
            priceCode = movie.priceCode;
        }

        public Builder() {

        }

        public Movie build(){
            Movie movie = new Movie();
            movie.title = this.title;
            movie.country = this.country;
            movie.actors = this.actors;
            movie.director = this.director;
            movie.description = this.description;
            return movie;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder priceCode(MovieType priceCode) {
            this.priceCode = priceCode;
            return this;
        }

        public Builder director(Person director) {
            this.director = director;
            return this;
        }

        public Builder actors(List<Person> actors) {
            this.actors.addAll(actors);
            this.actors = actors.stream().distinct().toList();
            return this;
        }

        public Builder actors(Person actor) {
            if(!this.actors.contains(actor)){
                this.actors.add(actor);
            }
            return this;
        }

    }


}