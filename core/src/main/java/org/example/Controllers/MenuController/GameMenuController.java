package org.example.Controllers.MenuController;

import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.User;


public class GameMenuController {
    private Game currentGame;

    public String newGame(String username1, String username2, String username3, int[] mapNumbers) {
        User user1 = App.getUser(username1);
        User user2 = App.getUser(username2);
        User user3 = App.getUser(username3);

        if (user1 == null || user2 == null || user3 == null) {
            return "One or more of usernames do not exist";
        }

        if (user1.game != null || user2.game != null || user3.game != null) {
            return "One of the Users has another active game";
        }

        for (int mapNum : mapNumbers) {
            if (mapNum < 1 || mapNum > 4) {
                return "Invalid map number: " + mapNum;
            }
        }
        user1.player.setFarmNumber(mapNumbers[0]);
        user2.player.setFarmNumber(mapNumbers[1]);
        user3.player.setFarmNumber(mapNumbers[2]);

        Game game = new Game(user1, user2, user3);
        currentGame = game;

        user1.player.game = game;
        user2.player.game = game;
        user3.player.game = game;

        user1.game = game;
        user2.game = game;
        user3.game = game;


        return "New game was successfully created";
    }

    public String checkPlayers(String username1, String username2, String username3) {
        if (username2.isEmpty() || username3.isEmpty()) {
            return "Please enter both second and third player usernames.";
        }
        if (username1.equals(username2) || username1.equals(username3) || username2.equals(username3)) {
            return "Usernames must be different.";
        }
        if (App.getUser(username2) == null || App.getUser(username3) == null) {
            return "One or more usernames do not exist.";
        }
        if (App.getUser(username2).game != null || App.getUser(username3).game != null) {
            return "One or more users have an active game.";
        }
        return "Players validated. Ready to start.";
    }
    public Game getCurrentGame() {
        return currentGame;
    }
}