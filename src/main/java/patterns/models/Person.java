package patterns.models;

public class Person {
  private final String name;

  private final PersonRole role;

  private final int id;

  public Person(int id, String name, PersonRole role) {
    this.name = name;
    this.id = id;
    this.role = role;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public PersonRole getRole() {
    return role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Person person = (Person) o;

    if (!name.equals(person.name)) {
      return false;
    }
    return role == person.role;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 39 * result + role.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return String.format("%-3d %20s %10s", id, name, role);
  }

  public enum PersonRole{
    ACTOR,
    DIRECTOR
  }

}
