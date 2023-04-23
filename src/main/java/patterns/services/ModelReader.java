package patterns.services;

import java.util.ArrayList;
import java.util.List;

public class ModelReader<T> {
  private Service<T> service;

  public ModelReader(Service<T> service) {
    this.service = service;
  }

  public List<T> readAll(String line) {
    List<T> objects = new ArrayList<>();
    T object = service.read(line);
    while (object != null) {
      objects.add(object);
      object = service.read(line);
    }
    return objects;
  }
}
