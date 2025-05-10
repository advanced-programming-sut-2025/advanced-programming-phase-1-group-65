package org.example.Models;

import org.example.Models.Enums.TileType;
import org.example.Views.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public Map map=null;
    public ArrayList<ArrayList<Tile>> Map=null;
    public int timesLoaded=0;
    public User user1=null;
    public User user2=null;
    public User user3=null;
    List<User> users;

    public Player currentPlayer = null;
    public Player MainPlayer = null;

    public GameClock gameClock;

    public Game(User user1, User user2, User user3) {
        this.user1 = user1;
        this.user2 = user2;
        this.user3 = user3;
        this.users = List.of(user1, user2, user3);
        this.gameClock = new GameClock();
    }


    public void GameRun(){

        Scanner sc = new Scanner(System.in);

        if (timesLoaded == 0) {
            map = new Map();
         // پایینی اری لیست درسته
            Map = map.buildMap();

            for (User user : users) {
                if (user.player.getFarmNumber() == 1) {
                    user.player.PositionX = 20;
                    user.player.PositionY = 15;
                } else if (user.player.getFarmNumber() == 2) {
                    user.player.PositionX = 120;
                    user.player.PositionY = 15;
                } else if (user.player.getFarmNumber() == 3) {
                    user.player.PositionX = 20;
                    user.player.PositionY = 95;
                } else if (user.player.getFarmNumber() == 4) {
                    user.player.PositionX = 120;
                    user.player.PositionY = 95;
                }

                user.player.game.Map.get(user.player.PositionY).set(user.player.PositionX, new Tile(TileType.PLAYER));
            }
        }

        GameView gameView = new GameView();
        gameView.check(sc, this);
    }

    public void advanceGameClock() {
        if (gameClock != null) {
            gameClock.advanceTimeByOneHour(this);
        }
    }

    public void advanceGameDate() {
        if (gameClock != null) {
            gameClock.advanceTimeByOneDay(this);
        }
    }

    public void advanceTimeByHours(int hours) {
        if (hours < 0) {
            System.out.println("Error: number of hours must be non-negative.");
            return;
        }
        for (int i = 0; i < hours; i++) {
            gameClock.advanceTimeByOneHour(this);

        }
    }

    public void advanceDateByDays(int days) {
        if (days < 0) {
            System.out.println("Error: number of days must be non-negative.");
            return;
        }
        for (int i = 0; i < days; i++) {
            gameClock.advanceTimeByOneDay(this);
        }
    }
}