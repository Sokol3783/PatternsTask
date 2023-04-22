package patterns.models.menu;

import java.util.LinkedHashMap;
import java.util.Map;
import patterns.main.Main;

public abstract class MenuItemComposite extends MenuItem {
  private String name;
  private Map<Integer, Menu> subMenu;

  public MenuItemComposite(String name, Map<Integer, Menu> subMenu) {
    this.name = name;
    this.subMenu = subMenu;
  }

  public void run(){
    System.out.println(this.name);
    System.out.println("------------------");
    subMenu.entrySet().forEach(System.out::println);
    System.out.println("Enter command:");
  }

  @Override
  public MenuItem createMenu() {
    throw new UnsupportedOperationException();
  }

  private static Map<Integer, Menu> getMenu() {
    return new LinkedHashMap<>();
  }

  public static class MainMenu extends MenuItemComposite {

    private MainMenu(String name, Map<Integer, Menu> subMenu) {
      super(name, subMenu);
    }

    @Override
    public void run() {
      super.run();
    }


    @Override
    public String toString() {
      return super.toString();
    }

    @Override
    public MainMenu createMenu(){
      return new MainMenu("Main menu", getSubMenu());
    }

    private Map<Integer, Menu> getSubMenu() {
      Map<Integer, Menu> menu = MenuItemComposite.getMenu();
      menu.put(1, getPersonMenu());
    }
  }
}
