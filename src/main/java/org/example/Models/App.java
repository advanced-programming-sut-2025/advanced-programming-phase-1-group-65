package org.example.Models;

import org.example.Models.Enums.Menu;

import java.util.ArrayList;

public class App {
    private static Menu currentMenu = Menu.SIGNUPMENU;
    public static Menu getCurrentMenu() {
        return currentMenu;
    }
    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }
    public static ArrayList<User> users = new ArrayList<>();
    private static User currentUser = null;
    public static User getCurrentUser() {
        return currentUser;
    }
    public static ArrayList<User> getUsers() {
        return users;
    }
}
