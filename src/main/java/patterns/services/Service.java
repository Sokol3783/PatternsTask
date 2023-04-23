package patterns.services;

import java.util.Optional;

public interface Service<T> {

  boolean addModel();

  void listModel();

  boolean save(T model);

  T read(String line);

  Optional<T> findByName(String name);

  Optional<T> findById(String id);

}
