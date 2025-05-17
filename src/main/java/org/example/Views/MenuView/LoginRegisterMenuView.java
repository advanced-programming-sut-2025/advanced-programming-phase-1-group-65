package org.example.Views.MenuView;

import org.example.Models.App;
import org.example.Models.Enums.LoginRegisterMenuCommands;
import org.example.Models.Enums.Menu;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginRegisterMenuView implements AppMenu {

    @Override
    public void check(Scanner scanner) {
        System.out.println("Welcome to the Login/Register Menu!");
        System.out.println("Commands: menu enter <menu_name>");
        System.out.println("Available Menus: \nLoginMenu \nSignupMenu \nMainMenu");

        while (true) {
            String input = scanner.nextLine();
            Matcher matcher;

            if ((matcher = LoginRegisterMenuCommands.MENU_ENTER.matcher(input)) != null) {
                String menuName = matcher.group(1).toUpperCase();

                try {
                    Menu menu = Menu.valueOf(menuName);
                    if (menu == Menu.LOGINMENU || menu == Menu.SIGNUPMENU || menu == Menu.MAINMENU) {
                        App.setCurrentMenu(menu);
                        System.out.println("You are now in " + menuName.toLowerCase());
                        return;
                    } else {
                        System.out.println("Access to " + menuName.toLowerCase() + " is not allowed from here.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("No such menu: " + menuName.toLowerCase());
                }
            } else if ((matcher = LoginRegisterMenuCommands.SHOW_CURRENT_MENU.matcher(input)) != null) {
                System.out.println("You are currently in the Login/Register Menu.");
            } else if ((matcher = LoginRegisterMenuCommands.MENU_EXIT.matcher(input)) != null) {
                System.out.println("Exiting application...");
                System.exit(0);
            } else {
                System.out.println("Invalid command. Use: menu enter <menu_name>");
            }
        }
    }
}