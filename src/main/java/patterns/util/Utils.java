package patterns.util;

import patterns.main.Main;

public class Utils {

  public static String enterTheValue(String nameValue, int length){
    System.out.println("Enter the " + nameValue);
    String name = "";
    while (name.length() > length){
      if (Main.in.hasNextLine()) {
        name = Main.in.nextLine().trim();
        if (name.length() <= length) {
          System.out.println(nameValue + " should be longer than " + length);
          name = "";
        }
      }
    }
    return name;
  }

  public static int enterTheNumber(String nameValue, int min){
    System.out.println("Enter the number of" + nameValue);
    int value = 0;
    while (true){
      if (Main.in.hasNextInt()) {
        value = Main.in.nextInt();
        if (value >= min) {
          return value;
        } else {
          System.out.printf("Minimal number %d!! \n", min);
        }
      }
    }
  }

}
