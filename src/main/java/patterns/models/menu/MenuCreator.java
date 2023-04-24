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

  private static boolean isTrue(String question) {
    System.out.println(question + " (Enter X - yes/ Y - no)");
    Scanner in = Main.in;
    while (true){
      char c = Character.toUpperCase((char) in.nextByte());
      if (c == 'X') {
        return true;
      } else if (c == 'Y'){
        return false;
      }
    }
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
      menu.put(2, changeTitle());
      menu.put(3, changeDirector());
      menu.put(4, addActor());
      menu.put(5, changeCountry());
      menu.put(6, changeDescription());
      menu.put(9, askSaveAndExit());

      return new Menu("Movie editor", menu);

    }

    private static MenuItem askSaveAndExit() {
      return new MenuItemLeaf("Exit", new Runnable() {
        @Override
        public void run() {
          if (EDITABLE != null){
            if (isTrue("Save changes?")){
              service.save(EDITABLE.build());
            }
          }
        }
      });
    }

    private static MenuItem changeDescription() {
      return new MenuItemLeaf("Change description", new Runnable() {

        @Override
        public void run() {
          EDITABLE.title(Utils.enterTheValue("description of movie", 0));
        }
      });
    }

    private static MenuItem addActor() {
      return new MenuItemLeaf("Add actor", new Runnable() {

        Service service = PersonService.getInstance();
        @Override
        public void run() {
          Optional<Person> person;
          while (true) {
            String value = Utils.enterTheValue("id or name actor", 1);
            person = service.findById(value);
            if (person.isPresent()) {
              EDITABLE.actors(person.get());
              break;
            }
            person = service.findByName(value);
            if (person.isPresent()) {
              EDITABLE.actors(person.get());
              break;
            }
            System.out.println("Actor hasn't been found!!!");
          }
        }
      });
    }

    private static MenuItem changeCountry() {
      return new MenuItemLeaf("Change title", new Runnable() {

        @Override
        public void run() {
          EDITABLE.title(Utils.enterTheValue("country", 2));
        }
      });
    }

    private static MenuItem changeDirector() {
      return new MenuItemLeaf("Change director", new Runnable() {

        Service service = PersonService.getInstance();

        @Override
        public void run() {
          Optional<Person> person;
          while (true) {
            String value = Utils.enterTheValue("id or name of director", 1);
            person = service.findById(value);
            if (person.isPresent()) {
              EDITABLE.director(person.get());
              break;
            }
            person = service.findByName(value);
            if (person.isPresent()) {
              EDITABLE.director(person.get());
              break;
            }
            System.out.println("Director hasn't been found!!!");
          }
        }
      });
    }

    private static MenuItem changeTitle() {
      return new MenuItemLeaf("Change title", new Runnable() {

        @Override
        public void run() {
          EDITABLE.title(Utils.enterTheValue("new title", 5));
        }
      });
    }

    private static MenuItem chooseMovie() {
      return new MenuItemLeaf("Select movie to edit", new Runnable() {
        @Override
        public void run() {
            if (EDITABLE != null){
              if (isTrue("Save changes?")){
                service.save(EDITABLE.build());
              }
            }
            while (true){
              //TODO
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
