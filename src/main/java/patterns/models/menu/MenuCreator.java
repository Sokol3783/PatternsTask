package patterns.models.menu;

import java.util.LinkedHashMap;
import java.util.Map;
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

    public static MainMenu createMainMenu() {
      Map<Integer, MenuItem> menu = MenuCreator.emptyMenu();
      menu.put(1, PersonMenuCreator.createPersonMenu());
      menu.put(2, MovieMenuCreator.createMovieMenu());
      menu.put(3, RentalMenuCreator.createRentalMenu());
      menu.put(4, MenuCreator.exit());
      return new MainMenu("Main menu", menu);
    }
  }

  private static class PersonMenuCreator {

    public static MenuItem createPersonMenu() {
      return null;
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
