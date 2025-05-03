package org.example.Models;

public class User {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String gender;
    private String nickname;
    private String answerKey;  // پاسخ به سوالات امنیتی
    private int answerNumber;  // شماره سوال امنیتی
    public Player player =null;
    // سازنده
    public void setPlayer(Player player) {
        this.player = player;
    }
    public User(String username, String password, String email, String gender, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.nickname = nickname;
    }

    // متدهای دسترسی (Getters)
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
        return gender;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAnswerKey() {
        return answerKey;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    // متد برای تنظیم سوال و پاسخ امنیتی
    public void setAnswer(String answerKey, Integer answerNumber) {
        this.answerKey = answerKey;
        this.answerNumber = answerNumber;
    }

    // متد برای تنظیم رمز عبور جدید
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getSecurityQuestion() {
        switch (answerNumber) {
            case 1:
                return "What is your favorite color?";
            case 2:
                return "What was the name of your first school?";
            case 3:
                return "In which city were you born?";
            default:
                return "No security question set!";
        }
    }
}