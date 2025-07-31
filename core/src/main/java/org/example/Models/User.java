package org.example.Models;

public class User {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String gender;
    private String nickname;
    private String answerKey;
    private int answerNumber;
    private String avatarPath;

    public Player player = null;
    public Game game;

    private int mostGoldInOneGame;
    private int gamesPlayed;

    public User(String username, String password, String email, String gender, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.nickname = nickname;
        this.mostGoldInOneGame = 0;
        this.gamesPlayed = 0;

        String[] defaultAvatars = {
            "avatars/avatar1.png",
            "avatars/avatar2.png",
            "avatars/avatar3.png",
            "avatars/avatar4.png"
        };
        int randomIndex = (int) (Math.random() * defaultAvatars.length);
        this.avatarPath = defaultAvatars[randomIndex];
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public int getMostGoldInOneGame() {
        return mostGoldInOneGame;
    }

    public void setMostGoldInOneGame(int mostGoldInOneGame) {
        this.mostGoldInOneGame = mostGoldInOneGame;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public String getAnswerKey() {
        return answerKey;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswer(String answerKey, Integer answerNumber) {
        this.answerKey = answerKey;
        this.answerNumber = answerNumber;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
