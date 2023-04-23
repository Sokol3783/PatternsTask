package patterns.services.serviceimpl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import patterns.models.Customer;
import patterns.services.Service;
import patterns.util.Utils;
import patterns.util.Writer;

public class CustomerService implements Service<Customer> {

  private final Collection<Customer> coll;
  private final Writer writer;

  private static CustomerService service;

  private CustomerService(){
    coll = new HashSet<>();
    writer = new Writer(Customer.class.getName());
  }

  public static CustomerService getInstance(){
    if (service == null){
      service = new CustomerService();
    }
    return service;
  };


  @Override
  public boolean addModel() {
    Customer model = new Customer(getNextId(), Utils.enterTheValue("full name", 5));
    if (writeToTXT(model)) {
      coll.add(model);
      return true;
    }
    return false;
  }

  private boolean writeToTXT(Customer model) {
    try {
      writer.updateById(String.valueOf(model.getName()), convertModelToString(model));
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  private String convertModelToString(Customer model) {
    return model.getId() + ";" + model.getName();
  }

  @Override
  public void listModel() {
    System.out.printf("%-3s %20s ", "id", "name");
    coll.forEach(System.out::println);
  }

  @Override
  public boolean save(Customer model) {
    return writeToTXT(model);
  }

  @Override
  public Customer read(String line) {
    String[] arr = line.split(" ");
    //return new Person(Integer.parseInt(arr[0]),arr[1], PersonRole.valueOf(arr[2]));
    return null;
  }

  private int getNextId() {
    int max = coll.stream().mapToInt(Customer::getId).max().orElse(0);
    return ++max;
  }

}
