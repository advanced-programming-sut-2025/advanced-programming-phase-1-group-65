package org.example.Controllers.MenuController;

import org.example.Models.App;
import org.example.Models.Enums.Menu;
import org.example.Models.Player;
import org.example.Models.User;

import java.util.Random;
import java.util.Scanner;

public class SignUpMenuController extends RegisterMenuController {
    public static String answerKey = "";
    public static int answerNumber = 0;

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
            if (user.getUsername().equals(username)) return false;
        }
        return true;
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

    public String registerUser(String username, String password, String confirmPassword, String nickname, String email, String gender) {
        if (!isUsernameValid(username)) return "Username is invalid";
        if (!isUsernameTaken(username)) return "Username is taken";
        if (!isPasswordValid(password)) return "Password is invalid";
        if (!isPasswordStrong(password)) return "Password must be at least 8 characters long and include uppercase, digit, and special character";
        if (!isPasswordConfirmValid(password, confirmPassword)) return "Passwords do not match";
        if (!isEmailValid(email)) return "Email is invalid";
        if (!isGenderValid(gender)) return "Gender is invalid";

        SecurityCheck();

        User user = new User(username, password, email, gender, nickname);
        user.setAnswer(answerKey, answerNumber);
        user.setPlayer(new Player());
        App.getUsers().add(user);
        App.setCurrentMenu(Menu.LOGINMENU);

        return "User created successfully! You are now in login menu!";
    }

    public void registerUserWithRandomPassword(String username, String nickname, String email, String gender, Scanner scanner) {
        while (true) {
            String password = generateRandomPassword();
            System.out.println("Generated Password: " + password);
            System.out.print("Do you want to use this password? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                System.out.println(registerUser(username, password, password, nickname, email, gender));
                return;
            } else if (response.equals("no")) {
                System.out.print("Generate a new one? (yes/no): ");
                String retry = scanner.nextLine().trim().toLowerCase();
                if (!retry.equals("yes")) {
                    System.out.println("Returning to SignUp menu...");
                    return;
                }
            } else {
                System.out.println("Invalid response. Please type 'yes' or 'no'.");
            }
        }
    }

    public void SecurityCheck() {
        Scanner tempscanner = new Scanner(System.in);
        System.out.println("Please choose one of the following questions:");
        System.out.println("1 - What is your favorite color?");
        System.out.println("2 - What was your first school's name?");
        System.out.println("3 - In which city were you born?");
        String choice = tempscanner.nextLine();
        System.out.print("Write your answer: ");
        answerKey = tempscanner.nextLine();
        answerNumber = switch (choice) {
            case "1" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            default -> 0;
        };
    }
}