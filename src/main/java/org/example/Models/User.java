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

    // اضافه کردن فیلد برای ذخیره بیشترین طلا در یک بازی
    private int mostGoldInOneGame;
    private int gamesPlayed;

    // سازنده
    public User(String username, String password, String email, String gender, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.nickname = nickname;
        this.mostGoldInOneGame = 0;  // مقدار پیش‌فرض
        this.gamesPlayed = 0;        // تعداد بازی‌های پیش‌فرض
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

    // اضافه کردن متد برای دریافت بیشترین طلا در یک بازی
    public int getMostGoldInOneGame() {
        return mostGoldInOneGame;
    }

    // اضافه کردن متد برای تنظیم بیشترین طلا در یک بازی
    public void setMostGoldInOneGame(int mostGoldInOneGame) {
        this.mostGoldInOneGame = mostGoldInOneGame;
    }

    // اضافه کردن متد برای دریافت تعداد بازی‌های انجام شده
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    // اضافه کردن متد برای افزایش تعداد بازی‌های انجام شده
    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    // متدهای setters برای تغییر اطلاعات کاربر
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
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