package patterns.models.menu;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import patterns.main.Main;
import patterns.models.Person;
import patterns.models.movie.Movie;
import patterns.models.movie.Movie.Builder;
import patterns.services.serviceimpl.CustomerService;
import patterns.services.serviceimpl.MovieService;
import patterns.services.serviceimpl.PersonService;
import patterns.models.menu.menuitems.MenuItem;
import patterns.models.menu.menuitems.MenuItemLeaf;
import patterns.services.serviceimpl.RentalService;
import patterns.services.Service;
import patterns.util.Utils;

public abstract class MenuCreator {

  private static Builder EDITABLE = null;

  public static Menu createMenu(){
    return MainMenuCreator.createMainMenu();
  }

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
    private static final Service<Movie> service = MovieService.getInstance();
    public static MenuItem createMovieMenu() {
      Map<Integer, MenuItem> menu = getDefaultLeafs(service, "movies", "movie");
      menu.put(4, MovieEditorMenuCreator.createMenu());
      return new Menu("Movie menu", menu);
    }

  }

  private static class MovieEditorMenuCreator {

    private static final Service<Movie> service = MovieService.getInstance();

    public static MenuItem createMenu() {
      Map<Integer, MenuItem> menu = emptyMenu();
      menu.put(1, chooseMovie());
      menu.put(9, exit());

      return new Menu("Movie editor", menu);

    }

    private static MenuItem chooseMovie() {
      return new MenuItemLeaf("Select movie to edit", new Runnable() {
        @Override
        public void run() {
            if (EDITABLE != null){
              saveChanges();
            }
            while (true){
              String value = Utils.enterTheValue("name or id of movie", 1);
              Optional<Movie> movie = service.findById(value);
              if (movie.isPresent()) {
                EDITABLE = new Builder(movie.get());
                break;
              }
              movie = service.findByName(value);
              if (movie.isPresent()) {
                EDITABLE = new Builder(movie.get());
                break;
              }
            }
        }

        private void saveChanges() {
          System.out.println("Save changes enter X/Y");
          Scanner in = Main.in;
          while (in.hasNextLine()){
            char c = Character.toUpperCase((char) in.nextByte());
            if (c == 'X') {
              service.save(EDITABLE.build());
            } else if (c == 'Y'){
              return;
            }
          }
        }
      });
    }
  }

  private static class RentalMenuCreator {

    private static final Service service = RentalService.getInstance();

    public static MenuItem createRentalMenu() {
      Map<Integer, MenuItem> menu = getDefaultLeafs(service, "rentals", "rental");
      return new Menu("Rentals", menu);
    }
  }

  //TODO
  private static class CustomerMenuCreator {

    private static final Service service = CustomerService.getInstance();

    public static MenuItem createCustomerMenu() {
      Map<Integer, MenuItem> menu = getDefaultLeafs(service, "customers", "customer");
      menu.put(4, MenuCreator.RentalMenuCreator.createRentalMenu());
      return new Menu("Customer", menu);
    }
  }
}
