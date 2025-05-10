package org.example.Views;

import org.example.Controllers.GameMenuController;
import org.example.Models.App;
import org.example.Models.Enums.GameMenuCommands;
import org.example.Models.Enums.Menu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenuView implements AppMenu {
    private final GameMenuController controller= new GameMenuController();
    @Override
    public void check (Scanner scanner) {
        System.out.println(
        "Commands : game new -u <username1> <username2> <username3>\n"
        + "load game");
        String input = scanner.nextLine();
        Matcher matcher;
        if ((matcher = GameMenuCommands.NEWGAME.matcher(input)) != null) {
            String username1 = matcher.group(1);
            String username2 = matcher.group(2);
            String username3 = matcher.group(3);
            System.out.println(controller.newGame(username1, username2, username3));
        }
        else if((matcher = GameMenuCommands.EXIT.matcher(input)) != null) {
            System.out.println("You are now in MainMenu");
            App.setCurrentMenu(Menu.MAINMENU);
        }
        else if((matcher = GameMenuCommands.LOADGAME.matcher(input)) != null) {
            if(App.getCurrentUser().player.game == null){
                System.out.println("You don't have an active game");
            }
            else {
                System.out.println("game loaded");
                App.getCurrentUser().player.game.MainPlayer= App.getCurrentUser().player;
                App.getCurrentUser().player.game.currentPlayer = App.getCurrentUser().player;
                App.getCurrentUser().player.game.GameRun();
            }
        }

        else {
            System.out.println("Invalid input");
        }
    }

}
