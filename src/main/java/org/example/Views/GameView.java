package org.example.Views;

import org.example.Controllers.GameController;
import org.example.Models.App;
import org.example.Models.Enums.GameCommands;
import org.example.Models.Enums.Menu;
import org.example.Models.Game;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameView {
    public void check(Scanner scanner, Game game) {
        GameController controller = new GameController();
        Matcher matcher;

        while (true) {
            String input = scanner.nextLine();

            if ((matcher = GameCommands.EXIT.matcher(input)) != null) {
                if (game.currentPlayer == game.MainPlayer) {
                    game.timesLoaded++;
                    App.setCurrentMenu(Menu.MAINMENU);
                    break;
                } else {
                    System.out.println("You don't have the authority to do this.");
                }

            } else if ((matcher = GameCommands.NEXTTURN.matcher(input)) != null) {
                controller.processNextTurn(game);

            } else if ((matcher = GameCommands.SEASON.matcher(input)) != null) {
                System.out.println("Current season: " + game.gameClock.getCurrentSeason());

            } else if ((matcher = GameCommands.TIME.matcher(input)) != null) {
                System.out.println("Current time: " + game.gameClock.getFormattedTime());

            } else if ((matcher = GameCommands.DATE.matcher(input)) != null) {
                System.out.println("Current date: " + game.gameClock.getFormattedDate());

            } else if ((matcher = GameCommands.DATETIME.matcher(input)) != null) {
                System.out.println("Current datetime: " + game.gameClock.getFormattedDateTime());

            } else if ((matcher = GameCommands.DAYOFWEEK.matcher(input)) != null) {
                System.out.println("Day of week: " + game.gameClock.getDayOfWeek());

            } else if ((matcher = GameCommands.CHEAT_ADVANCE_TIME.matcher(input)) != null) {
                int hours = Integer.parseInt(matcher.group(1));
                if (hours < 0) {
                    System.out.println("Invalid value. X must be non-negative.");
                } else {
                    controller.processAdvanceHours(game, hours);
                }

            } else if ((matcher = GameCommands.CHEAT_ADVANCE_DATE.matcher(input)) != null) {
                int days = Integer.parseInt(matcher.group(1));
                if (days < 0) {
                    System.out.println("Invalid value. X must be non-negative.");
                } else {
                    controller.processAdvanceDays(game, days);
                }

            } else if (input.equals("print map")) {
                game.map.printMap(game.Map);

            } else {
                System.out.println("Invalid input");
            }
        }
    }
}