package org.example.Views;

import org.example.Controllers.LoginMenuController;
import org.example.Models.App;
import org.example.Models.Enums.Menu;
import org.example.Models.Enums.LoginMenuCommands;
import org.example.Models.User; // اضافه کن
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenuView implements AppMenu {

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        LoginMenuController controller = new LoginMenuController();

        if ((matcher = LoginMenuCommands.LOGIN.matcher(input)) != null) {
            String username = matcher.group(1);
            String password = matcher.group(2);
           // boolean stayLoggedIn = matcher.group(3) != null;

            boolean found = false;


            if (controller.login(username, password)) {
                System.out.println("Login successful!");
               /* if (stayLoggedIn) {
                    System.out.println("You will stay logged in.");
                }*/

            } else {
                System.out.println("Username or password is incorrect!");
            }
        } else if ((matcher = LoginMenuCommands.BACK.matcher(input)) != null) {
            App.setCurrentMenu(Menu.SIGNUPMENU);
            System.out.println("You are back in Sign Up Menu");
        } else if ((matcher = LoginMenuCommands.MENUSHOW.matcher(input)) != null) {
            System.out.println("Current menu: Login Menu");
        }
        else if((matcher= LoginMenuCommands.EXIT.matcher(input)) != null){
            System.out.println("Goodbye!");
            System.exit(0);

        }
        else {
            System.out.println("Invalid input! You are in Login Menu");
        }
    }
}