package patterns.services;

interface Service<T> {

  boolean addPerson();

  void listPerson();

  boolean save(T model);

}
