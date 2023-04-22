package patterns.models.menu.menuitems;

import java.awt.Menu;

public abstract class MenuItem implements Runnable {

  public abstract void run();

  public abstract String getName();

}
