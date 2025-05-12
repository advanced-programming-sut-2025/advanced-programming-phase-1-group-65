package org.example.Models;

import org.example.Models.Enums.Menu;
import org.example.Views.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static Menu currentMenu = Menu.LOGINREGISTERMENU;
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<User> users = new ArrayList<>();
    private static User currentUser;

    public static void setCurrentMenu(Menu menu) {
        currentMenu = menu;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static List<User> getUsers() {
        return users;
    }
    public static User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void run() {
        while (true) {
            AppMenu menuView;
            switch (currentMenu) {
                case SIGNUPMENU -> menuView = new SignUpMenuView();
                case LOGINMENU -> menuView = new LoginMenuView();
                case MAINMENU -> menuView = new MainMenuView();
                case PROFILEMENU -> menuView = new ProfileMenuView();
                default -> {
                    System.out.println("Unknown menu");
                    return;
                }
            }
            menuView.check(scanner);
        }
    }
}