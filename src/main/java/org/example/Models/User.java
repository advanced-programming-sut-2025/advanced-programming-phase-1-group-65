package org.example.Models;
import java.util.ArrayList;
public class User {
    String username;
    String password;
    String confirmPassword;
    String email;
    String Gender;
    String Nickname;
    String AnswerKey;
    int AnswerNumber;
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
    public void setAnswer(String answerKey, Integer answerNumber) {
        AnswerKey = answerKey;
        AnswerNumber = answerNumber;
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
