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
        GameController Controller = new GameController();
        Matcher matcher;
        while (true){
            String input = scanner.nextLine();
            if ((matcher= GameCommands.EXIT.matcher(input))!=null){
                if(game.currentPlayer == game.MainPlayer){
                    game.timesLoaded++;
                    App.setCurrentMenu(Menu.MAINMENU);
                    break;
                }
                else{
                    System.out.println("You don't have the authority to do this.");
                }
            }
            else if ((matcher= GameCommands.NEXTTURN.matcher(input))!=null){
                if (game.currentPlayer == game.user1.player){
                    game.currentPlayer = game.user2.player;
                    System.out.println("You are now playing " + game.user2.getNickname());

                }
                else if (game.currentPlayer == game.user2.player){
                    game.currentPlayer = game.user3.player;
                    System.out.println("You are now playing " + game.user3.getNickname());
                }
                else if (game.currentPlayer == game.user3.player){
                    game.currentPlayer = game.user1.player;
                    System.out.println("You are now playing " + game.user1.getNickname());
                }
            }
            // for test
            else if(input.equals("print map")){
                game.map.printMap(game.Map);
            }
            else {
                System.out.println("invalid input");
            }
        }
    }
}
