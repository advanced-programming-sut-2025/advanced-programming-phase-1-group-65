package org.example.Controllers;

import org.example.Models.Game;

public class GameController {
    private int nextTurnCounter = 0;

    public void processNextTurn(Game game) {
        if (game.currentPlayer == game.user1.player) {
            game.currentPlayer = game.user2.player;
            System.out.println("You are now playing " + game.user2.getNickname());
        } else if (game.currentPlayer == game.user2.player) {
            game.currentPlayer = game.user3.player;
            System.out.println("You are now playing " + game.user3.getNickname());
        } else if (game.currentPlayer == game.user3.player) {
            game.currentPlayer = game.user1.player;
            System.out.println("You are now playing " + game.user1.getNickname());
        }

        nextTurnCounter++;

        if (nextTurnCounter >= 3) {
            game.gameClock.advanceTimeByOneHour();
            nextTurnCounter = 0;
            System.out.println("One hour has passed in game time.");
        }
    }

    public void processAdvanceHours(Game game, int hours) {
        for (int i = 0; i < hours; i++) {
            game.gameClock.advanceTimeByOneHour();
        }
        System.out.println("Time advanced by " + hours + " hours.");
    }

    public void processAdvanceDays(Game game, int days) {
        for (int i = 0; i < days; i++) {
            game.gameClock.advanceTimeByOneDay();
        }
        System.out.println("Date advanced by " + days + " days.");
    }
}