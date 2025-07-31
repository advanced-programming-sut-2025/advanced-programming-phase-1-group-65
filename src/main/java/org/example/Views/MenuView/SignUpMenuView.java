package org.example.Views.MenuView;

import org.example.Controllers.MenuController.SignUpMenuController;
import org.example.Models.App;
import org.example.Models.Enums.Menu;
import org.example.Models.Enums.SignUpMenuCommands;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SignUpMenuView implements AppMenu {
    private final SignUpMenuController controller = new SignUpMenuController();

    @Override
    public void check(Scanner scanner) {
        System.out.println("Welcome to the Sign Up Menu\n" +
                "Command : register -u <username> -p <password> <password> -n <nickname> -e <email> -g <gender>\n" +
                "write random instead of password for random password");
        String input = scanner.nextLine();
        Matcher matcher;
        if ((matcher = SignUpMenuCommands.REGISTER.matcher(input)) != null) {
            String username = matcher.group(1).trim();
            String password = matcher.group(2).trim();
            String confirmPassword = matcher.group(3).trim();
            String nickname = matcher.group(4).trim();
            String email = matcher.group(5).trim();
            String gender = matcher.group(6).trim();

            if (password.equalsIgnoreCase("random")) {
                controller.registerUserWithRandomPassword(username, nickname, email, gender, scanner);
            } else {
                System.out.println(controller.registerUser(
                        username, password, confirmPassword, nickname, email, gender));
            }

        } else if ((matcher = SignUpMenuCommands.MENU_ENTER.matcher(input)) != null) {
            String menuName = matcher.group(1).toUpperCase();
            try {
                Menu menu = Menu.valueOf(menuName);
                App.setCurrentMenu(menu);
                System.out.println("You are now in " + menuName.toLowerCase() );
            } catch (IllegalArgumentException e) {
                System.out.println("No such menu: " + menuName.toLowerCase());
            }

        } else if ((matcher = SignUpMenuCommands.MENU_EXIT.matcher(input)) != null) {
            System.out.println("You have exited the SignUp menu. Goodbye!");
            System.exit(0);

        } else if ((matcher = SignUpMenuCommands.REGISTERRANDOM.matcher(input)) != null) {
            controller.registerUserWithRandomPassword(
                    matcher.group(1).trim(),
                    matcher.group(2).trim(),
                    matcher.group(3).trim(),
                    matcher.group(4).trim(),
                    scanner
            );

        } else if ((matcher = SignUpMenuCommands.SHOW_CURRENT_MENU.matcher(input)) != null) {
            System.out.println("You are in SignUpMenu");

        } else {
            System.out.println("Invalid input! You are in SignUpMenu");
        }
    }
}