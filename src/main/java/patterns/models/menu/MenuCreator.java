package patterns.models.menu;

import java.util.LinkedHashMap;
import java.util.Map;
import patterns.models.Person;
import patterns.services.serviceimpl.CustomerService;
import patterns.services.serviceimpl.PersonService;
import patterns.models.menu.menuitems.MenuItem;
import patterns.models.menu.menuitems.MenuItemLeaf;
import patterns.services.serviceimpl.RentalService;
import patterns.services.Service;

public abstract class MenuCreator {

  private static Map<Integer, MenuItem> emptyMenu(){
    return new LinkedHashMap<>();
  }

  private static MenuItem exit(){
    return new MenuItemLeaf("Exit", null);
  }

  private static MenuItem getLeafServiceList(String nameLeaf, Service service) {
    return new MenuItemLeaf("List " + nameLeaf, service::listModel);
  }

  private static MenuItem getLeafServiceAdd(String nameLeaf, Service service) {
    return new MenuItemLeaf("Add " + nameLeaf, service::listModel);
  }

  /*
   For default menu entry contains: 1. list, 2. add  9. exit
   */
  private static Map<Integer, MenuItem> getDefaultLeafs(Service service,String listName, String addName ){
    Map<Integer, MenuItem> menu = MenuCreator.emptyMenu();
    menu.put(1, MenuCreator.getLeafServiceList(listName, service));
    menu.put(2, MenuCreator.getLeafServiceAdd(addName, service));
    menu.put(9, MenuCreator.exit());
    return menu;
  }

  public static class MainMenuCreator extends MenuCreator {

    public static Menu createMainMenu() {
      Map<Integer, MenuItem> menu = MenuCreator.emptyMenu();
      menu.put(1, CustomerMenuCreator.createCustomerMenu());
      menu.put(2, MovieMenuCreator.createMovieMenu());
      menu.put(3, PersonMenuCreator.createPersonMenu());
      menu.put(9, MenuCreator.exit());
      return new Menu("Main menu", menu);
    }
  }

  private static class PersonMenuCreator {

    private static final Service<Person> service = PersonService.getInstance();
    public static MenuItem createPersonMenu() {
      return new Menu("Actors and directors", getDefaultLeafs(service, "persons", "person"));
    }
  }

  private static class MovieMenuCreator {

    public static MenuItem createMovieMenu() {
      return null;
    }

  }

  private static class RentalMenuCreator {

    private static final Service service = RentalService.getInstance();

    public static MenuItem createRentalMenu() {
      Map<Integer, MenuItem> menu = getDefaultLeafs(service, "rentals", "rental");
      return new Menu("Customer", menu);
    }
  }

  private static class CustomerMenuCreator {

    private static final Service service = CustomerService.getInstance();

    public static MenuItem createCustomerMenu() {
      Map<Integer, MenuItem> menu = getDefaultLeafs(service, "customers", "customer");
      menu.put(4, MenuCreator.RentalMenuCreator.createRentalMenu());
      return new Menu("Customer", menu);
    }
  }
}
