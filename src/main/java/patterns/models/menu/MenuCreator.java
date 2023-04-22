package patterns.models.menu;

import java.util.LinkedHashMap;
import java.util.Map;
import patterns.services.PersonService;
import patterns.models.menu.menuitems.MenuItem;
import patterns.models.menu.menuitems.MenuItemLeaf;

public abstract class MenuCreator {

  private static Map<Integer, MenuItem> emptyMenu(){
    return new LinkedHashMap<>();
  }

  private static MenuItem exit(){
    return new MenuItemLeaf("Exit", null);
  }

  public static class MainMenuCreator extends MenuCreator {

    public static Menu createMainMenu() {
      Map<Integer, MenuItem> menu = MenuCreator.emptyMenu();
      menu.put(1, PersonMenuCreator.createPersonMenu());
      menu.put(2, MovieMenuCreator.createMovieMenu());
      menu.put(3, RentalMenuCreator.createRentalMenu());
      menu.put(9, MenuCreator.exit());
      return new Menu("Main menu", menu);
    }
  }

  private static class PersonMenuCreator {

    private static PersonService service = new PersonService();
    public static MenuItem createPersonMenu() {
      Map<Integer, MenuItem> menu = MenuCreator.emptyMenu();      
      menu.put(1, listPerson());
      menu.put(2, addPerson());      
      menu.put(9, MenuCreator.exit());
      return new Menu("Person", menu);
    }

    private static MenuItem addPerson() {
      return new MenuItemLeaf("Add person", new Runnable() {
        @Override
        public void run() {
          service.addPerson();
        }
      });
    }

    private static MenuItem listPerson() {
      return new MenuItemLeaf("Add person", new Runnable() {
        @Override
        public void run() {
          service.listPerson();
        }
      });
    }
  }

  private static class MovieMenuCreator {

    public static MenuItem createMovieMenu() {
      return null;
    }
  }

  private static class RentalMenuCreator {

    public static MenuItem createRentalMenu() {
      return null;
    }
  }
}
