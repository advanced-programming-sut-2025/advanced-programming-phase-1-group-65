package org.example.Controllers.MenuController;

import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenuController {

    public String newGame(String username1, String username2, String username3) {
        User user1 = null;
        User user2 = null;
        User user3 = null;

        user1 = App.getUser(username1);
        user2 = App.getUser(username2);
        user3 = App.getUser(username3);

        if (user1 == null || user2 == null || user3 == null) {
            return "One or more of usernames do not exist";
        }

        if (user1.game != null || user2.game != null || user3.game != null) {
            return "One of the Users has another active game";
        }

        Game game = new Game(user1, user2, user3);

        user1.player.game = game;
        user2.player.game = game;
        user3.player.game = game;

        user1.game = game;
        user2.game = game;
        user3.game = game;

        System.out.println("Choose your map number\nCommand : game map <map_number>");

        int[] mapnum = GameMapChoose();

        user1.player.setFarmNumber(mapnum[0]);
        user2.player.setFarmNumber(mapnum[1]);
        user3.player.setFarmNumber(mapnum[2]);

        return "New game was successfully created";
    }

    public int[] GameMapChoose() {
        int i = 0;
        int[] GameMap = new int[3];
        Scanner sc = new Scanner(System.in);

        while (i < 3) {
            String input = sc.nextLine();
            Pattern pattern = Pattern.compile("^game map (\\d+)$");
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                int mapNumber = Integer.parseInt(matcher.group(1));
                if (mapNumber >= 1 && mapNumber <= 4) {
                    GameMap[i] = mapNumber;
                    i++;
                } else {
                    System.out.println("Invalid map number");
                }
            } else {
                System.out.println("Invalid input! choose your map number");
            }
        }
        return GameMap;
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
}
