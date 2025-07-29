package org.example.Models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.Models.Enums.Menu;


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


    public static void clearUsers() {
        users.clear();
    }

    public static void checkStayLoggedIn() {
        try (BufferedReader reader = new BufferedReader(new FileReader("stayLoggedIn.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                String[] userInfo = line.split(",");
                if (userInfo.length >= 5) {
                    String username = userInfo[0];
                    String password = userInfo[1];
                    String nickname = userInfo[2];
                    String email = userInfo[3];
                    String gender = userInfo[4];

                    User user = new User(username, password, email, gender, nickname);
                    user.setPlayer(new Player());
                    App.getUsers().add(user);

                    if (user != null) {
                        user.setPassword(password);
                        user.setNickname(nickname);
                        user.setEmail(email);
                        user.setGender(gender);

                        setCurrentUser(user);
                        System.out.println("Welcome back, " + username + "!");
                        setCurrentMenu(Menu.MAINMENU);
                    } else {
                        System.out.println("User not found in the system, please log in again.");
                    }
                } else {
                    System.out.println("Invalid data format in file.");
                }
            } else {
                System.out.println("No username found in file.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void saveLoginState(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("stayLoggedIn.txt"))) {
            writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getNickname() + "," + user.getEmail() + "," + user.getGender());
        } catch (IOException e) {
            System.out.println("Error saving login state: " + e.getMessage());
        }
    }
}
