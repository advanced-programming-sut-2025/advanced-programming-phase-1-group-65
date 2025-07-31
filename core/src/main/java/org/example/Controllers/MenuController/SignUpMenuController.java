package org.example.Controllers.MenuController;

import org.example.Models.App;
import org.example.Models.Enums.Menu;
import org.example.Models.Player;
import org.example.Models.User;

import java.util.Random;

public class SignUpMenuController extends RegisterMenuController {

    public String generateRandomPassword() {
        int length = 12;
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()_+-=[]{}|;:',.<>/?";
        String allCharacters = upperCase + lowerCase + digits + specialChars;

        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        password.append(upperCase.charAt(random.nextInt(upperCase.length())));
        password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        // پر کردن بقیه
        for (int i = password.length(); i < length; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        return password.toString();
    }

    private boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9-]+$");
    }

    private boolean isUsernameTaken(String username) {
        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username)) return true;
        }
        return false;
    }

    private boolean isPasswordValid(String password) {
        return password.matches("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]+$");
    }

    private boolean isPasswordStrong(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]|:;\"'<>,.?/]).{8,}$");
    }

    private boolean isPasswordConfirmValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9](?!.*\\.\\.)[a-zA-Z0-9._-]*[a-zA-Z0-9]@[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?(\\.[a-zA-Z]{2,})+$");
    }

    private boolean isGenderValid(String gender) {
        return gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female");
    }

    public String registerUser(String username, String password, String confirmPassword,
                               String nickname, String email, String gender,
                               int questionNumber, String answer) {
        if (!isUsernameValid(username)) return "Username is invalid";
        if (isUsernameTaken(username)) return "Username is taken";
        if (!isPasswordValid(password)) return "Password is invalid";
        if (!isPasswordStrong(password)) return "Password must be at least 8 characters long and include uppercase, digit, and special character";
        if (!isPasswordConfirmValid(password, confirmPassword)) return "Passwords do not match";
        if (!isEmailValid(email)) return "Email is invalid";
        if (!isGenderValid(gender)) return "Gender is invalid";
        if (questionNumber == 0 || answer == null) {
            return "User created successfully! You are now in login menu!";
        }

        if (answer.trim().isEmpty()) return "Security answer cannot be empty";

        User user = new User(username, password, email, gender, nickname);
        user.setAnswer(answer.trim(), questionNumber);
        user.setPlayer(new Player());
        App.getUsers().add(user);
        App.setCurrentMenu(Menu.LOGINMENU);

        return "User created successfully!";
    }
}
