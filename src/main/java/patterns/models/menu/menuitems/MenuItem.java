package patterns.models.menu.menuitems;


public abstract class MenuItem implements Runnable {

  public abstract void run();

  public abstract String getName();

}
