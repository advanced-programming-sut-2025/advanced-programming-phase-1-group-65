package org.example.Controllers;

import org.example.Models.App;
import org.example.Models.Enums.Menu;
import org.example.Models.User;

public class SignUpMenuController extends RegisterMenuController {
    private boolean isUsernameValid(String username){
       // ریجکس رو اضافه کن
        if (!username.matches("Arya")){
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
    private boolean isPasswordValid(String password){
        if (!password.matches("Arya")){
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
        User user = new User(username , password, email,gender,nickname);
        App.getUsers().add(user);
        // set current user
        App.setCurrentMenu(Menu.LOGINMENU);
        return "User created successfully! you are now in login menu!";



    }






}
