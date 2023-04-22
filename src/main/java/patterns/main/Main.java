package patterns.main;

import java.util.Scanner;
import patterns.models.menu.MenuCreator;
import patterns.models.menu.menuitems.MenuItem;

public class Main {

    public static Scanner in =new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        MenuItem menu = MenuCreator.MainMenuCreator.createMainMenu();
        menu.run();
    }
}

// Task:

// Уявімо, що нам треба додати нові фічі.
// По-перше, клієнт хоче виведення ще й у HTML.
// По-друге, клієнт хоче додати інші типи фільмів - наприклад, драми, комедії, трилери.
// По-третє, ми хочемо додати до фільмів нові характеристики (країна походження, короткий опис, режисер, актори)

// У поточну структуру ці правки лягають важко, тому нам доведеться її змінити.

// Створити можливість обліку декількох клієнтів, роботи з текстовими(!?) файлами для зберігання даних
// Програма повинна надавати можливості
// - перегляду каталога всіх фільмів,
// - додавання фільму,
// - пошуку за певними характеристиками
// - та ін.
// Створити зручне меню для доступу до функцій