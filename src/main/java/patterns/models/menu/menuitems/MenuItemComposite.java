package patterns.models.menu.menuitems;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import patterns.main.Main;

public abstract class MenuItemComposite extends MenuItem {
  private String name;
  private Map<Integer, MenuItem> subMenu;

  public MenuItemComposite(String name, Map<Integer, MenuItem> subMenu) {
    this.name = name;
    this.subMenu = subMenu;
  }
  public void run(){
    System.out.println(this.name);
    System.out.println("------------------");
    subMenu.entrySet().forEach(System.out::println);
    System.out.println("Enter number of command to execute:");
    Scanner in = Main.in;
    while (in.hasNext()) {
      MenuItem menuItem = subMenu.get(in.nextInt());
      if (menuItem.getName().compareToIgnoreCase("exit") == 0) {
        return;
      } else {
        menuItem.run();
      }
    }
  }

  @Override
  public String getName() {
    return this.name;
  }
}