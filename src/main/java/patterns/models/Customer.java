package patterns.models;

import java.util.List;

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

  /*
  public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        for (Rental each : rentals) {
            double thisAmount = each.getAmount();
            frequentRenterPoints += each.getFrequentRenterPoints();
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }
        //add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
   */

}
