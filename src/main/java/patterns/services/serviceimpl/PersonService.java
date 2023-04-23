package patterns.services.serviceimpl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import patterns.main.Main;
import patterns.models.Person;
import patterns.models.Person.PersonRole;
import patterns.services.Service;
import patterns.util.Utils;
import patterns.util.Writer;

public class PersonService implements Service<Person> {

  private final Collection<Person> coll;
  private final Writer writer;
  private static PersonService service;

  private PersonService(){
    coll = new HashSet<>();
    writer = new Writer(Person.class.getName());
  }

  public static PersonService getInstance() {
     if (service == null) {
       service = new PersonService();
     }
     return service;
  }

  @Override
  public boolean addModel() {
    Person person = new Person(getNextId(), Utils.enterTheValue("name", 3),enterRole());
    if (writeToTXT(person)) {
      coll.add(person);
      return true;
    }
    return false;
  }

  private int getNextId() {
    int max = coll.stream().mapToInt(Person::getId).max().orElse(0);
    return ++max;
  }

  private boolean writeToTXT(Person person) {
    try {
      writer.updateById(String.valueOf(person.getId()), convertPersonToWright(person));
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  private String convertPersonToWright(Person person) {
    return person.getId() + ";" + person.getName() + ";" + person.getRole();
  }

  private PersonRole enterRole() {
    System.out.printf("Enter the role of person '%s' or '%s' \n", PersonRole.ACTOR, PersonRole.DIRECTOR);
    while (true) {
      if (Main.in.hasNextLine()) {
        switch (Main.in.nextLine()) {
          case "ACTOR" :return PersonRole.ACTOR;
          case "DIRECTOR":return PersonRole.DIRECTOR;
        }
      }
    }
  }


  @Override
  public void listModel() {
    System.out.printf("%-3s %20s %10s \n", "id", "name", "role");
    coll.forEach(System.out::println);
  }

  @Override
  public boolean save(Person model) {
    return writeToTXT(model);
  }

  @Override
  public Person read(String line) {
    String[] arr = line.split(" ");
    return new Person(Integer.parseInt(arr[0]),arr[1], PersonRole.valueOf(arr[2]));
  }

  @Override
  public Optional<Person> findByName(String name) {
    return coll.stream().filter(s -> s.getName().compareToIgnoreCase(name) == 0).findFirst();
  }

  @Override
  public Optional<Person> findById(String id) {
    int digit = Integer.parseInt(id);
    return coll.stream().filter(s -> s.getId() == digit).findFirst();
  }
}
