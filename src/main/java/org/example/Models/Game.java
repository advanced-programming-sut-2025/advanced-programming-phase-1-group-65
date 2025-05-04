package org.example.Models;

import org.example.Models.Enums.TileType;
import org.example.Views.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {
    public Map map;
    public ArrayList<ArrayList<Tile>> Map;
    public int timesLoaded=0;
    public User user1=null;
    public User user2=null;
    public User user3=null;
    List<User> users;
    public Game (User user1, User user2, User user3) {
        this.user1 = user1;
        this.user2 = user2;
        this.user3 = user3;
        this.users = List.of(user1,user2,user3);
    }
    public Player currentPlayer=null;
    public Player MainPlayer=null;

    public void GameRun(){

        Scanner sc = new Scanner(System.in);

        if(timesLoaded==0){
            map = new Map();
            Map = map.buildMap();
            for (User user : users){
                if (user.player.getFarmNumber()==1){
                    user.player.PositionX= 20;
                    user.player.PositionY= 15;

                }
                else if (user.player.getFarmNumber()==2){
                    user.player.PositionX= 120;
                    user.player.PositionY= 15;
                }
                else if (user.player.getFarmNumber()==3){
                    user.player.PositionX= 20;
                    user.player.PositionY= 95;
                }
                else if (user.player.getFarmNumber()==4){
                    user.player.PositionX= 120;
                    user.player.PositionY= 95;
                }

                user.player.game.Map.get(user.player.PositionY).set(user.player.PositionX,new Tile(TileType.PLAYER));
            }
        }

        GameView gameView = new GameView();
        gameView.check(sc, this);

    }


}
