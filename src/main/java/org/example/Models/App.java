package org.example.Models;

import org.example.Models.Enums.Menu;
import org.example.Views.SignUpMenuView;
import org.example.Views.LoginMenuView;
import org.example.Views.AppMenu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static Menu currentMenu = Menu.SIGNUPMENU;
    private static final Scanner scanner = new Scanner(System.in);
    private static List<User> users = new ArrayList<>();

    public static void setCurrentMenu(Menu menu) {
        currentMenu = menu;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void run() {
        while (true) {
            AppMenu menuView;
            if (currentMenu == Menu.SIGNUPMENU) {
                menuView = new SignUpMenuView();
            } else if (currentMenu == Menu.LOGINMENU) {
                menuView = new LoginMenuView();
            } else {
                System.out.println("Unknown menu");
                break;
            }
            menuView.check(scanner);
        }
    }
}