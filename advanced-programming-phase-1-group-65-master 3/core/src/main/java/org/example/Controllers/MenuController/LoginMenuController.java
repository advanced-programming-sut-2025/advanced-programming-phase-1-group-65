package org.example.Controllers.MenuController;

import org.example.Models.App;
import org.example.Models.User;
import org.example.Models.Enums.Menu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class LoginMenuController {

    public boolean login(String username, String password , boolean stayLoggedIn) {
        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                App.setCurrentUser(user);
                if (stayLoggedIn) {
                    saveLoginState(user);
                }
                return true;
            }
        }
        return false;
    }

    public String getSecurityQuestion(int number) {
        return switch (number) {
            case 1 -> "What is your favorite color?";
            case 2 -> "What was your first school's name?";
            case 3 -> "In which city were you born?";
            default -> "Unknown security question.";
        };
    }

    public boolean checkSecurityAnswer(User user, String answer) {
        return user.getAnswerKey().equalsIgnoreCase(answer.trim());
    }

    public boolean isPasswordValid(String password) {
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
    }

    private void saveLoginState(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("stayLoggedIn.txt"))) {
            writer.write(user.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
