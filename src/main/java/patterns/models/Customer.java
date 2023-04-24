package patterns.models;

public class Customer {
    private final String name;
    private final int id;

    public Customer(int id, String name) {
        this.name = name;
        this.id = id;
    }

   public String getName() {
        return name;
    }

  public int getId() {
    return id;
  }
}
