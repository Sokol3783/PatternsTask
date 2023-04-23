package patterns.services;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import patterns.main.Main;
import patterns.models.Person;
import patterns.models.Person.PersonRole;
import patterns.util.Writer;

public class PersonService implements Service<Person> {

  private final Collection<Person> persons;
  private final Scanner in;
  private final Writer writer;

  public PersonService(){
    in = Main.in;
    persons = new HashSet<>();
    writer = new Writer(Person.class.getName());
  }


  @Override
  public boolean addModel() {
    Person person = new Person(getPersonId(),enterName(),enterRole());
    if (writeToTXT(person)) {
      persons.add(person);
      return true;
    }
    return false;
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
  public void listModel() {
    System.out.printf("%-3s %20s %10s", "id", "name", "role");
    persons.forEach(System.out::println);
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
}
