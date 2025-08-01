package org.example.Views.MenuView;

import org.example.Controllers.GameController.GameController;
import org.example.Models.Enums.GameCommands;
import org.example.Models.Enums.KitchenCommands;
import org.example.Models.Game;

import java.util.Scanner;
import java.util.regex.Matcher;

public class KitchenView {
    Matcher matcher;
    public void check(Scanner scanner, Game game, GameController controller) {
        while (true) {
            String input = scanner.nextLine();
            if ((matcher = KitchenCommands.EXIT.matcher(input)) !=null){
                System.out.println("You have exited the kitchen.");
                game.timesLoaded++;
                game.GameRun();
                break;
            }
            else if((matcher = KitchenCommands.REFRIGERATORPUT.matcher(input)) != null){
                String name = matcher.group(1);
                controller.RefrigeratorPut(game,name);
            }
            else if ((matcher = KitchenCommands.REFRIGERATORPICK.matcher(input)) != null){
                String name = matcher.group(1);
                controller.RefrigeratorPick(game,name);
            }
            else if ((matcher = KitchenCommands.SHOWRECIPE.matcher(input)) != null){
                controller.ShowRecipe(game);
            }
            else if ((matcher = KitchenCommands.COOKINGPREPARE.matcher(input)) != null){
                String name = matcher.group(1);
                controller.PrepareRecipe(game,name);
            }
            else {
                System.out.println("invalid command");
            }
        }
    }
}
