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
    public Player player =null;
    private int mostGoldInOneGame;
    private int gamesPlayed;



    public void setPlayer(Player player) {
        this.player = player;
    }
    public User(String username, String password, String email, String gender, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.nickname = nickname;
        this.mostGoldInOneGame = 0;
        this.gamesPlayed = 0;
    }


    public String getUsername() {
        return username;
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
    public void setNickname(String newNickname) {
        this.nickname = newNickname;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setAnswer(String answerKey, Integer answerNumber) {
        this.answerKey = answerKey;
        this.answerNumber = answerNumber;
    }
    public Game game;
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }



}