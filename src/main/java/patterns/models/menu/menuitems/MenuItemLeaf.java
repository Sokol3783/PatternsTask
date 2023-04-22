package patterns.models.menu.menuitems;

public class MenuItemLeaf extends MenuItem{
  private final String name;
  private final Runnable command;

  public MenuItemLeaf(String name, Runnable command) {
    this.name = name;
    this.command = command;
  }

  public String getName() {
    return name;
  }

  public void run() {
    command.run();
  }

}
