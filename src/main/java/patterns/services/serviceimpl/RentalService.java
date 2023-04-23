package patterns.services.serviceimpl;


import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import patterns.models.Customer;
import patterns.models.Rental;
import patterns.services.Service;
import patterns.util.Utils;
import patterns.util.Writer;

public class RentalService implements Service<Rental> {

  private final Collection<Rental> coll;
  private final Writer writer;
  private static Service serviceRental;

  private RentalService(){
    coll = new HashSet<>();
    writer = new Writer(Rental.class.getName());
  }

  public static Service getInstance() {
     if (serviceRental == null) {
       serviceRental = new RentalService();
     }
     return serviceRental;
  }

  @Override
  public boolean addModel() {
    Rental Rental = new Rental(getNextId(),
        null,
        Utils.enterTheNumber("day rent", patterns.models.Rental.MIN_DAYS_RENT),
        getCustomer());
    if (writeToTXT(Rental)) {
      coll.add(Rental);
      return true;
    }
    return false;
  }

  private Customer getCustomer() {
    while (true) {
      String value = Utils.enterTheValue("id or name of customer", 0);
      Service<Customer> service = CustomerService.getInstance();
      Optional<Customer> byName = service.findByName(value);
      if (byName.isPresent())
        return byName.get();
      byName = service.findById(value);
      if (byName.isPresent())
        return byName.get();
      System.out.println("Anybody hasn't been found");
    }
  }

  private int getNextId() {
    int max = coll.stream().mapToInt(Rental::getId).max().orElse(0);
    return ++max;
  }

  private boolean writeToTXT(Rental Rental) {
    try {
      writer.updateById(String.valueOf(Rental.getId()), convertRentalToWright(Rental));
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  private String convertRentalToWright(Rental rental) {
    return rental.getId() + ";" + rental.getMovie().getId() + ";";
  }

  @Override
  public void listModel() {
    System.out.printf("%-3s %20s %10s \n", "id", "name", "role");
    coll.forEach(System.out::println);
  }

  @Override
  public boolean save(Rental model) {
    return writeToTXT(model);
  }

  @Override
  public Rental read(String line) {
    String[] arr = line.split(";");
    //return new Rental(Integer.parseInt(arr[0]),arr[1]));
    return null;
  }

  @Override
  public Optional<Rental> findByName(String name) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<Rental> findById(String id) {
    int digit = Integer.parseInt(id);
    return coll.stream().filter(s -> s.getId() == digit).findFirst();
  }
}
