package patterns.services.serviceimpl;

import patterns.models.movie.Movie;
import patterns.services.Service;

public class MovieService implements Service<Movie> {

  @Override
  public boolean addModel() {
    return false;
  }

  @Override
  public void listModel() {

  }

  @Override
  public boolean save(Movie model) {
    return false;
  }

  @Override
  public Movie read(String line) {
    return null;
  }
}
