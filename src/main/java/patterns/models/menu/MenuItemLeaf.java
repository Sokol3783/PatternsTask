package patterns.models.menu;

public abstract class MenuItemLeaf extends MenuItem{
  private String name;
  private Runnable command;

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
