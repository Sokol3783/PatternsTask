package patterns.services;

import java.util.List;

public class ModelReader<T> {
  private Service<T> service;

  public ModelReader(Service<T> service) {
    this.service = service;
  }

  public void readAll(List<String> lines) {
    for (String line : lines){
      service.read(line);
    }
  }
}
