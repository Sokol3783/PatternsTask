package patterns.services.serviceimpl;

import java.util.Optional;
import patterns.models.movie.Movie;
import patterns.services.Service;

public class MovieService implements Service<Movie> {

  public static MovieService getInstance() {
    return null;
  }

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

  @Override
  public Optional<Movie> findByName(String name) {
    return null;
  }

  @Override
  public Optional<Movie> findById(String id) {
    return null;
  }
}
