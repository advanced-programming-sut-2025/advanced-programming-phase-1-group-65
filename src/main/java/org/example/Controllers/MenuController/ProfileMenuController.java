package org.example.Controllers.MenuController;

import org.example.Models.App;
import org.example.Models.User;
import org.example.Models.Enums.Menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuController {

    private boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()-+]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public void changeUsername(String newUsername) {
        User currentUser = App.getCurrentUser();
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        if (currentUser.getUsername().equals(newUsername)) {
            System.out.println("The username is the same as the current one.");
            return;
        }

        if (!isValidUsername(newUsername)) {
            System.out.println("Invalid username. Username must only contain letters, numbers, and hyphens.");
            return;
        }

        for (User user : App.getUsers()) {
            if (user.getUsername().equals(newUsername)) {
                System.out.println("Username already taken. Try a different one.");
                return;
            }
        }

        currentUser.setUsername(newUsername);
        System.out.println("Username changed successfully.");
    }

    public void changePassword(String oldPassword, String newPassword) {
        User currentUser = App.getCurrentUser();
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        if (!currentUser.getPassword().equals(oldPassword)) {
            System.out.println("Old password is incorrect.");
            return;
        }

        if (!isValidPassword(newPassword)) {
            System.out.println("Weak password. Password must be at least 8 characters long and include uppercase letters, lowercase letters, numbers, and special characters.");
            return;
        }

        if (oldPassword.equals(newPassword)) {
            System.out.println("The new password cannot be the same as the old one.");
            return;
        }

        currentUser.setPassword(newPassword);
        System.out.println("Password changed successfully.");
    }

    public void changeEmail(String newEmail) {
        User currentUser = App.getCurrentUser();
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        if (currentUser.getEmail().equals(newEmail)) {
            System.out.println("The email is the same as the current one.");
            return;
        }

        if (!isValidEmail(newEmail)) {
            System.out.println("Invalid email format.");
            return;
        }

        for (User user : App.getUsers()) {
            if (user.getEmail().equals(newEmail)) {
                System.out.println("Email already taken. Try a different one.");
                return;
            }
        }

        currentUser.setEmail(newEmail);
        System.out.println("Email changed successfully.");
    }

    public void changeNickname(String newNickname) {
        User currentUser = App.getCurrentUser();
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        if (currentUser.getNickname().equals(newNickname)) {
            System.out.println("The nickname is the same as the current one.");
            return;
        }

        currentUser.setNickname(newNickname);
        System.out.println("Nickname changed successfully.");
    }

    public void logout() {
        App.setCurrentUser(null);
        App.setCurrentMenu(Menu.LOGINMENU);
        System.out.println("You have logged out.you are in login menu.");
    }

    public void displayUserInfo() {
        User currentUser = App.getCurrentUser();
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        System.out.println("User Info:");
        System.out.println("Username: " + currentUser.getUsername());
        System.out.println("Nickname: " + currentUser.getNickname());
        System.out.println("Highest Gold in One Game: " + currentUser.getMostGoldInOneGame());
        System.out.println("Games Played: " + currentUser.getGamesPlayed());
    }
}