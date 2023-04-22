package patterns.task;

import java.util.List;
import patterns.models.movietypes.MovieType;
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

    public MovieType getPriceCode() {
        return priceCode;
    }

    public String getTitle() {
        return title;
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
            actors = movie.actors;
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
            this.actors = actors;
            return this;
        }

    }


}