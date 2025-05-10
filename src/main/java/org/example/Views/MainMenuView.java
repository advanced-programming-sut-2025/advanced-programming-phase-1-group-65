package org.example.Views;

import org.example.Models.App;
import org.example.Models.Enums.MainMenuCommands;
import org.example.Models.Enums.Menu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenuView implements AppMenu {

    @Override
    public void check(Scanner scanner) {
        System.out.println("Welcome to the Main Menu!");
        System.out.println("Commands: menu enter <menu_name>");
        System.out.println("Available Menus: \nLoginRegisterMenu\nProfileMenu\nGameMenu");

        while (true) {
            String input = scanner.nextLine();
            Matcher matcher;

            if ((matcher = MainMenuCommands.MENU_ENTER.matcher(input)) != null) {
                String menuName = matcher.group(1).toUpperCase();

                try {
                    Menu menu = Menu.valueOf(menuName);
                    if (menu == Menu.LOGINREGISTERMENU || menu == Menu.PROFILEMENU || menu == Menu.GAMEMENU) {
                        App.setCurrentMenu(menu);
                        System.out.println("You are now in " + menuName.toLowerCase());
                        return;
                    } else {
                        System.out.println("Access to " + menuName.toLowerCase() + " is not allowed from here.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("No such menu: " + menuName.toLowerCase());
                }
            } else if ((matcher = MainMenuCommands.SHOW_CURRENT_MENU.matcher(input)) != null) {
                System.out.println("You are currently in the Main Menu.");
            } else {
                System.out.println("Invalid command. Use: menu enter <menu_name>");
            }
        }
    }
}