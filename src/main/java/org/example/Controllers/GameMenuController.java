package org.example.Controllers;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenuController{
    public String newGame(String username1 , String username2,String username3){
         User user1 =null;
         User user2 =null;
         User user3 =null;
         user1 = App.getUser(username1);
         user2 = App.getUser(username2);
         user3 = App.getUser(username3);
        if (user1==null || user2==null || user3==null){
            return "One or more of usernames do not exist";
        }
        if(user1.player.game!=null || user2.player.game!=null || user3.player.game!=null){
            return "One of the Users has another active game";
        }
        Game game = new Game(user1,user2,user3);
        user1.player.game = game;
        user2.player.game = game;
        user3.player.game = game;
        System.out.println("Choose your map number\n" +
                "Command : game map <map_number>");
        int[] mapnum = GameMapChoose();
        user1.player.setFarmNumber(mapnum[0]);
        user2.player.setFarmNumber(mapnum[1]);
        user3.player.setFarmNumber(mapnum[2]);

        return "New game was successfully created";

    }
    public int[] GameMapChoose(){
        int i =0;
        int[] GameMap = new int[3];
        while(i <3){

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            Pattern pattern = Pattern.compile("^game map (\\d+)$");
            Matcher matcher = pattern.matcher(input);


            if (matcher.matches()) {

                int map1 = Integer.parseInt(matcher.group(1));
                if(map1<=4 && map1>=1 ){
                    GameMap[i]   = map1;
                    i++;
                }
                else{
                    System.out.println("Invalid map number");
                }


                // استفاده از mapNumber
            }
            else {
                System.out.println("Invalid input! choose your map number");
            }

        }
        return GameMap;
    }

}
