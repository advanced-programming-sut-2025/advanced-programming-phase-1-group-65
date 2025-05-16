package org.example.Controllers;

import org.example.Models.App;
import org.example.Models.User;
import org.example.Models.Enums.Menu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class LoginMenuController {

    public boolean login(String username, String password , boolean stayLoggedIn) {
        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                App.setCurrentUser(user);
                if (stayLoggedIn) {
                    App.saveLoginState(user);
                }
                return true;
            }
        }
        return false;
    }

    public String forgotPassword(String username) {
        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username)) {

                Scanner scanner = new Scanner(System.in);
                System.out.println("Answer the security question to reset your password:");

                String question = getSecurityQuestion(user.getAnswerNumber());
                System.out.println(question);

                String answer = scanner.nextLine();

                if (user.getAnswerKey().equals(answer)) {
                    System.out.println("Correct answer! Please enter a new password (or type 'random' to generate one):");

                    while (true) {
                        String newPassword = scanner.nextLine();

                        if (newPassword.equalsIgnoreCase("random")) {
                            newPassword = generateRandomPassword();
                            System.out.println("Your new random password is: " + newPassword);
                            user.setPassword(newPassword);
                            return "Password has been successfully reset!";
                        }

                        if (!isPasswordValid(newPassword)) {
                            System.out.println("Password is invalid. Only specific characters are allowed.");
                            continue;
                        }

                        if (!isPasswordStrong(newPassword)) {
                            System.out.println("Password is too weak. It must be at least 8 characters long, include an uppercase letter, a digit, and a special character.");
                            continue;
                        }

                        user.setPassword(newPassword);
                        return "Password has been successfully reset!";
                    }
                } else {
                    App.setCurrentMenu(Menu.LOGINMENU);
                    return "Incorrect answer. Returning to the login menu...";
                }
            }
        }
        return "Username not found!";
    }

    private String getSecurityQuestion(int number) {
        return switch (number) {
            case 1 -> "What is your favorite color?";
            case 2 -> "What was your first school's name?";
            case 3 -> "In which city were you born?";
            default -> "Unknown security question.";
        };
    }

    private boolean isPasswordValid(String password) {
        return password.matches("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]+$");
    }

   public boolean isPasswordStrong(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]|:;\"'<>,.?/]).{8,}$");
    }

    public String generateRandomPassword() {
        int length = 12;
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?";
        String allCharacters = upperCase + lowerCase + digits + specialChars;

        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        password.append(upperCase.charAt(random.nextInt(upperCase.length())));
        password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        for (int i = password.length(); i < length; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        return password.toString();
    }private void saveLoginState(User user) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("stayLoggedIn.txt"))) {
            writer.write(user.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}