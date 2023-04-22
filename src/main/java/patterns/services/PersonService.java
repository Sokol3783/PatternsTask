package patterns.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import patterns.main.Main;
import patterns.models.Person;
import patterns.models.Person.PersonRole;

public class PersonService implements Service<Person> {

  private final Collection<Person> persons = new HashSet<>();
  private final Scanner in = Main.in;

  @Override
  public boolean addPerson() {
    Person person = new Person(enterName(), getPersonId(),enterRole());
    if (writeToTXT(person)) {
      persons.add(person);
      return true;
    }
    return false;
  }

  private boolean writeToTXT(Person person) {
    return true;
  }

  private PersonRole enterRole() {
    System.out.printf("Enter the role of person '%s' or '%s'", PersonRole.ACTOR, PersonRole.DIRECTOR);
    while (true) {
      if (in.hasNextLine()) {
        switch (in.nextLine()) {
          case "ACTOR" :return PersonRole.ACTOR;
          case "DIRECTOR":return PersonRole.DIRECTOR;
        }
      }
    }
  }

  private int getPersonId() {
     int max = persons.stream().mapToInt(Person::getId).max().orElse(0);
     return ++max;
  }

  private String enterName() {
    System.out.println("Enter the name");
    String name = "";
    while (name.length() > 3){
      if (in.hasNextLine()) {
        name = in.nextLine().trim();
        if (name.length() <= 3) {
          System.out.println("Name should be longer than 3");
          name = "";
        }
      }
    }
    return name;
  }

  @Override
  public void listPerson() {
    persons.forEach(System.out::println);
  }

  @Override
  public boolean save(Person model) {
    return false;
  }
}
