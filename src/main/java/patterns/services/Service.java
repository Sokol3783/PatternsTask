package patterns.services;

public interface Service<T> {

  boolean addModel();

  void listModel();

  boolean save(T model);

  T read(String line);

}
