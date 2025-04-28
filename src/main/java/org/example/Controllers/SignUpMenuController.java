package org.example.Controllers;

import org.example.Models.App;
import org.example.Models.Enums.Menu;
import org.example.Models.User;

import java.util.Scanner;

public class SignUpMenuController extends RegisterMenuController {
    public static String answerKey = "";
    public static int answerNumber = 0;
    private boolean isUsernameValid(String username) {
        if (!username.matches("^[a-zA-Z0-9-]+$")) {
            return false;
        }
        return true;
    }
    private boolean isUsernameTaken(String username){
        for (User user: App.getUsers()){
            if (user.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        if (!password.matches("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]+$")) {
            return false;
        }
        return true;
    }
    private boolean isPasswordStrong(String password) {
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]|:;\"'<>,.?/]).{8,}$")) {
            return false;
        }
        return true;
    }
    private boolean isPasswordConfirmValid(String password, String confirmPassword){
        if (!password.equals(confirmPassword)){
            return false;
        }
        return true;
    }
    private boolean isEmailValid(String email){
        if (!email.matches("^[a-zA-Z0-9](?!.*\\.\\.)[a-zA-Z0-9._-]*[a-zA-Z0-9]@[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?(\\.[a-zA-Z]{2,})+$")){
            return false;
        }
        return true;
    }
    private boolean isGenderValid(String gender){
        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")){
            return false;
        }
        return true;
    }
    private boolean isNicknameValid(String nickname){
        return true;
    }
    public String registerUser(String username, String password,String confirmpassword ,String nickname,String email, String gender) {
        if (!isUsernameValid(username)){
            return "Username is invalid";
        }
        if (!isUsernameTaken(username)){
            return "Username is taken";
        }
        if (!isPasswordValid(password)){
            return "Password is invalid";
        }
        if (!isPasswordStrong(password)){
            return "Password needs to have at least 8 characters,\n one capital letter,\n one numeric digit,\n one special character";
        }
        if (!isPasswordConfirmValid(password, confirmpassword)){
            System.out.println();
            return "Passwords do not match";
        }
        if (!isEmailValid(email)){
            System.out.println();
            return "Email is invalid";
        }
        if (!isGenderValid(gender)){
            System.out.println();
            return "Gender is invalid";
        }
        User user;
        SecurityCheck();


         user =new User(username , password, email,gender,nickname);
         user.setAnswer(answerKey, answerNumber);
        App.getUsers().add(user);

        App.setCurrentMenu(Menu.LOGINMENU);
        return "User created successfully! you are now in login menu!";


    }
    public void SecurityCheck(){
        Scanner tempscanner = new Scanner(System.in);
        System.out.println("Please Choose one of the following options to answer: \n" +
                "1 - What is your favorite color?\n" +
                "2 - What was your first school's name?\n" +
                "3 - In which City where you born");
        String choice = tempscanner.nextLine();
        System.out.println("Wright your answer : ");
        if (choice.equals("1")) {
            answerKey = tempscanner.nextLine();
            answerNumber = 1;
        }
        else if (choice.equals("2")) {
             answerKey = tempscanner.nextLine();
             answerNumber = 2;
        }
        else if (choice.equals("3")) {
            answerKey = tempscanner.nextLine();
            answerNumber = 3;
        }


    }


}
