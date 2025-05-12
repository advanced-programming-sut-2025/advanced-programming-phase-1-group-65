package org.example.Controllers;

import org.example.Models.*;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;
import org.example.Models.Enums.TileType;
import org.example.Models.Enums.WeatherType;
import org.example.Models.Game;
import org.example.Models.Player;
import org.example.Models.Tile;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GameController {
    private static final int[][] DIRECTIONS = {
            {-1, 0}, // up
            {1, 0},  // down
            {0, -1}, // left
            {0, 1}   // right
    };
    private int nextTurnCounter = 0;

    private boolean[][] visited;
    public void processNextTurn(Game game) {
        if (game.currentPlayer == game.user1.player) {
            if (game.user2.player.Fainted) {
                if (game.user3.player.Fainted) {
                    if (game.user1.player.Fainted) {
                        game.gameClock.advanceTimeByOneDay(game);
                        game.weatherSystem.advanceDay();
                        return;
                    }
                    game.gameClock.advanceTimeByOneHour(game);
                    return;
                }
                game.currentPlayer = game.user3.player;
                System.out.println("You are now playing " + game.user3.getNickname());
                nextTurnCounter++;
                return;
            }
            game.currentPlayer = game.user2.player;
            System.out.println("You are now playing " + game.user2.getNickname());
            nextTurnCounter++;
            return;
        } else if (game.currentPlayer == game.user2.player) {
            if (game.user3.player.Fainted) {
                if (game.user1.player.Fainted) {
                    if (game.user2.player.Fainted) {
                        game.gameClock.advanceTimeByOneDay(game);
                        game.weatherSystem.advanceDay();
                        return;
                    }
                    game.gameClock.advanceTimeByOneHour(game);
                    return;
                }
                game.currentPlayer = game.user1.player;
                System.out.println("You are now playing " + game.user1.getNickname());
                nextTurnCounter++;
                return;
            }
            game.currentPlayer = game.user3.player;
            System.out.println("You are now playing " + game.user3.getNickname());
            nextTurnCounter++;
            return;
        } else if (game.currentPlayer == game.user3.player) {
            if (game.user1.player.Fainted) {
                if (game.user2.player.Fainted) {
                    if (game.user3.player.Fainted) {
                        game.gameClock.advanceTimeByOneDay(game);
                        game.weatherSystem.advanceDay();
                    }
                    game.gameClock.advanceTimeByOneHour(game);
                }
                game.currentPlayer = game.user2.player;
                System.out.println("You are now playing " + game.user2.getNickname());
                nextTurnCounter++;
                return;
            }
            game.currentPlayer = game.user1.player;
            System.out.println("You are now playing " + game.user1.getNickname());
            nextTurnCounter++;
            return;
        }

        if (nextTurnCounter >= 3) {
            game.gameClock.advanceTimeByOneHour(game);
            nextTurnCounter = 0;
            System.out.println("One hour has passed in game time.");
        }
    }

    public String Walk(int destx, int desty, Game game) {
        Scanner temp = new Scanner(System.in);
        int height = 112;
        int width = 140;
        boolean[][] visited = new boolean[height][width];
        int[][] distance = new int[height][width];

        int startX = game.currentPlayer.PositionX;
        int startY = game.currentPlayer.PositionY;

        TileType destType = game.Map.get(desty).get(destx).type;
        game.currentPlayer.previousTileType = game.Map.get(desty).get(destx).type;

        if (destType == TileType.BLACKSMITH || destType == TileType.JOJAMART ||
                destType == TileType.GENERALSTORE || destType == TileType.CARPENTERSHOP ||
                destType == TileType.FISHSHOP || destType == TileType.RANCH ||
                destType == TileType.STARDROPSALOON) {

            int hour = game.gameClock.getHour();
            boolean isOpen = false;

            switch (destType) {
                case BLACKSMITH:
                    if (hour >= 9 && hour < 16) isOpen = true;
                    break;
                case CARPENTERSHOP:
                    if (hour >= 9 && hour < 20) isOpen = true;
                    break;
                case GENERALSTORE:
                    if (hour >= 9 && hour < 17) isOpen = true;
                    break;
                case RANCH:
                    if (hour >= 9 && hour < 16) isOpen = true;
                    break;
                case JOJAMART:
                    if (hour >= 9 && hour < 23) isOpen = true;
                    break;
                case FISHSHOP:
                    if (hour >= 9 && hour < 17) isOpen = true;
                    break;
                case STARDROPSALOON:
                    if (hour >= 12 && hour < 24) isOpen = true;
                    break;
            }

            if (!isOpen) return "This place is currently closed.";
        }

        if (!(destType == TileType.EMPTY ||
                destType == TileType.BLACKSMITH ||
                destType == TileType.JOJAMART ||
                destType == TileType.GENERALSTORE ||
                destType == TileType.CARPENTERSHOP ||
                destType == TileType.FISHSHOP ||
                destType == TileType.RANCH ||
                destType == TileType.STARDROPSALOON)) {
            return "You can not walk to this position";
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startY][startX] = true;
        distance[startY][startX] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == destx && y == desty) break;

            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newY >= 0 && newX < width && newY < height && !visited[newY][newX]) {
                    TileType nextType = game.Map.get(newY).get(newX).type;

                    if (nextType == TileType.EMPTY || nextType == TileType.PLAYER ||
                            nextType == TileType.BLACKSMITH || nextType == TileType.JOJAMART ||
                            nextType == TileType.GENERALSTORE || nextType == TileType.CARPENTERSHOP ||
                            nextType == TileType.FISHSHOP || nextType == TileType.RANCH ||
                            nextType == TileType.STARDROPSALOON) {

                        visited[newY][newX] = true;
                        distance[newY][newX] = distance[y][x] + 1;
                        queue.add(new int[]{newX, newY});
                    }
                }
            }
        }

        if (!visited[desty][destx]) {
            return "You can not walk to this position";
        }

        int minsteps = distance[desty][destx];

        double modifier;
        WeatherType currentWeather = game.weatherSystem.getTodayWeather();

        switch (currentWeather) {
            case RAIN: modifier = 1.5; break;
            case SNOW: modifier = 2.0; break;
            default: modifier = 1.0; break;
        }

        double energyNeeded = ((minsteps / 20.0) + 1) * modifier;

        System.out.println("You need " + energyNeeded + " energy to walk to this position.\nDo you wish to proceed? (y/n)");
        String ch = temp.nextLine();

        if (ch.equalsIgnoreCase("y") || ch.equalsIgnoreCase("yes")) {
            if (energyNeeded > game.currentPlayer.Energy) {
                game.currentPlayer.Fainted = true;
                processNextTurn(game);
                return "Last player has fainted.";
            }

            int prevX = game.currentPlayer.PositionX;
            int prevY = game.currentPlayer.PositionY;

            game.currentPlayer.Energy -= energyNeeded;
            game.currentPlayer.PositionX = destx;
            game.currentPlayer.PositionY = desty;

            game.Map.get(prevY).set(prevX, new Tile(game.currentPlayer.previousTileType));
            game.Map.get(desty).set(destx, new Tile(TileType.PLAYER));

            return "You have reached your destination";
        }

        return "Command cancelled";
    }

    public void processAdvanceHours(Game game, int hours) {
        for (int i = 0; i < hours; i++) {
            game.gameClock.advanceTimeByOneHour(game);
        }
        System.out.println("Time advanced by " + hours + " hours.");

    }

    public void processAdvanceDays(Game game, int days) {
        for (int i = 0; i < days; i++) {
            game.gameClock.advanceTimeByOneDay(game);
            game.weatherSystem.advanceDay();
        }
        System.out.println("Date advanced by " + days + " days.");
    }

    public void ShowCurrentEnergy(Game game) {
        System.out.println("Current energy: " + game.currentPlayer.Energy);
    }

    public void Energy_set(Game game, int energy) {
        game.currentPlayer.Energy = energy;
    }

    public void Energy_unlimited(Game game) {
        game.currentPlayer.Energy = Integer.MAX_VALUE;
    }
    public String EquipTool(Game game,String name) {
        for(Item item : game.currentPlayer.items){
            if(item.type.equals(ItemType.TOOL) && item.subtype.toString().equalsIgnoreCase(name) ){
                game.currentPlayer.CurrentTool= (Tool) item;
                return name+" was equipped";
            }

        }
        return "You do not have this tool";
    }
    public String ShowCurrentTool(Game game) {
        if (game.currentPlayer.CurrentTool == null) {
            return "You do not have any tools equipped";
        }
        return game.currentPlayer.CurrentTool.subtype.toString();
    }
    public void ShowAllTools(Game game) {
        for (Item item : game.currentPlayer.items) {
            if (item.type.equals(ItemType.TOOL)) {
                System.out.println("- "+item.subtype.toString().toLowerCase());
            }
        }
    }
    public void UseTool(Game game, int x, int y) {
        int distx = game.currentPlayer.PositionX + x;
        int disty = game.currentPlayer.PositionY - y;
        if (game.currentPlayer.CurrentTool == null) {
            System.out.println("You do not have any tools equipped");
            return;
        }
        else if (game.currentPlayer.CurrentTool.subtype.equals(ItemSubType.AXE)){

            if(game.Map.get(disty).get(distx).type.equals(TileType.TREE)){
                Trees tree = (Trees) game.Map.get(disty).get(distx);
                Material seed= tree.seed;
                Material wood= new Material(12, ItemSubType.WOOD,"Wood");
                AddItem(game,seed);
                AddItem(game,wood);
                game.Map.get(disty).set(distx , new Tile(TileType.EMPTY));
                System.out.println("You have chopped the tree");
                return;

            }
            System.out.println("This tool is not proper for the selected tile");


        }
        else {
            return;
        }
    }
    public void AddItem(Game game, Item newitem) {
        for (Item item : game.currentPlayer.items) {
            if(item.name.equalsIgnoreCase(newitem.name) && item.subtype.equals(newitem.subtype)){
                item.Count = item.Count + newitem.Count;
                return;
            }
        }
        game.currentPlayer.items.add(newitem);
        return;
    }

    public void setWeatherCheat(Game game, WeatherType weather) {
        game.weatherSystem.setTomorrowWeather(weather);
        System.out.println("Tomorrow's weather has been manually set to: " + weather);
    }

    public void triggerLightning(Game game, int x, int y) {

        System.out.println("Lightning triggered at position (" + x + ", " + y + ")!");

    }
    public void cheatAddDollars(Game game, int amount) {
        if (game.currentPlayer != null) {
            game.currentPlayer.addMoney(amount);
            if(game.currentPlayer == game.user1.player){
            System.out.println("Added " + amount + " dollars to " + game.user1.getNickname() + "'s account.");}
           if(game.currentPlayer == game.user2.player){
            System.out.println("Added " + amount + " dollars to " + game.user2.getNickname() + "'s account.");}
           if(game.currentPlayer == game.user3.player){
        System.out.println("Added " + amount + " dollars to " + game.user3.getNickname() + "'s account.");}
      } else {
            System.out.println("No current player available.");
        }
    }

}