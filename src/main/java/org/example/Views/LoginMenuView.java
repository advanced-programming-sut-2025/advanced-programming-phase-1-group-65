package org.example.Views;

import org.example.Controllers.LoginMenuController;
import org.example.Models.App;
import org.example.Models.Enums.Menu;
import org.example.Models.Enums.LoginMenuCommands;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenuView implements AppMenu {

    @Override
    public void check(Scanner scanner) {
        System.out.println("Welcome to the Login Menu\n" +
                "Command : login -u <username> -p <password> ");
        String input = scanner.nextLine();
        Matcher matcher;
        LoginMenuController controller = new LoginMenuController();

        if ((matcher = LoginMenuCommands.LOGIN.matcher(input)) != null) {
            String username = matcher.group(1);
            String password = matcher.group(2);

            boolean found = false;

            if (controller.login(username, password)) {
                System.out.println("Login successful!");
                App.setCurrentMenu(Menu.MAINMENU);
            } else {
                System.out.println("Username or password is incorrect!");
            }
        }
        else if ((matcher = LoginMenuCommands.MENU_EXIT.matcher(input)) != null) {
            System.out.println("You have exited the Login menu. Goodbye!");
            System.exit(0);

        } else if ((matcher = LoginMenuCommands.SHOW_CURRENT_MENU.matcher(input)) != null) {
            System.out.println("You are in LoginMenu");

        } else if ((matcher = LoginMenuCommands.FORGOTPASSWORD.matcher(input)) != null) {
            String username = matcher.group(1).trim();
            String result = controller.forgotPassword(username);
            System.out.println(result);

        } else if ((matcher = LoginMenuCommands.MENU_ENTER.matcher(input)) != null) {
            String menuName = matcher.group(1).toUpperCase();
            try {
                Menu menu = Menu.valueOf(menuName);
                App.setCurrentMenu(menu);
                System.out.println("You are now in " + menuName.toLowerCase());
            } catch (IllegalArgumentException e) {
                System.out.println("No such menu: " + menuName.toLowerCase());
            }

        } else {
            System.out.println("Invalid input! You are in Login Menu");
        }
    }
}