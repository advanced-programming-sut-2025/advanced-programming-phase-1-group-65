package org.example.Models;
import java.util.ArrayList;
public class User {
    String username;
    String password;
    String confirmPassword;
    String email;
    String Gender;
    String Nickname;
    public boolean IsUsernameValid(String username) {
        return true;
    }
    public boolean IsPasswordValid(String password) {
        return true;
    }
    public boolean IsEmailValid(String email) {
        return true;
    }
    public boolean IsGenderValid(String gender) {
        return true;
    }
    public boolean IsUsernameTaken(String username) {
        return true;
    }
    public boolean IsConfirmPasswordValid(String confirmPassword) {
        return true;
    }
    public static String forgetPassword(String username) {
        return "";
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public String getEmail() {
        return email;
    }
    public String getGender() {
        return Gender;
    }
    public String getNickname() {
        return Nickname;
    }
    public User(String username, String password, String email, String gender, String nickname) {
        this.username = username;
        this.password = password;
        //this.confirmPassword = confirmPassword;
        this.email = email;
        this.Gender = gender;
        this.Nickname = nickname;

    }


}
