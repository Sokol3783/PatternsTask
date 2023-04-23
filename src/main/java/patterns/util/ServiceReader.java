package patterns.util;

import java.util.ArrayList;
import java.util.List;
import patterns.services.Service;

public class ServiceReader<T> {
  private Service<T> service;

  public ServiceReader(Service<T> service) {
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
