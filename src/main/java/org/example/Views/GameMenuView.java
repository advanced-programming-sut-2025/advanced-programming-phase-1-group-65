package org.example.Views;

import org.example.Controllers.GameMenuController;
import org.example.Models.Enums.GameMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenuView implements AppMenu {
    private final GameMenuController controller= new GameMenuController();
    @Override
    public void check (Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        if ((matcher = GameMenuCommands.NEWGAME.matcher(input)) != null) {
            String username1 = matcher.group(1);
            String username2 = matcher.group(2);
            String username3 = matcher.group(3);
            System.out.println(controller.newGame(username1, username2, username3));
        }
    }

}
