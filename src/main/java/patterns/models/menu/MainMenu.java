package patterns.models.menu;

import java.util.Map;
import patterns.models.menu.menuitems.MenuItem;
import patterns.models.menu.menuitems.MenuItemComposite;

public class MainMenu extends MenuItemComposite {

  public MainMenu(String name, Map<Integer, MenuItem> subMenu) {
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
}
