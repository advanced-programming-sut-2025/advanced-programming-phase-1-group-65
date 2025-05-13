package org.example.Controllers;

import org.example.Models.*;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;
import org.example.Models.Enums.TileType;
import org.example.Models.Enums.WeatherType;
import org.example.Models.Game;
import org.example.Models.Player;
import org.example.Models.Tile;

import java.awt.event.FocusAdapter;
import java.util.*;
import java.util.regex.MatchResult;

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

    public void DailySpawner(ArrayList<ArrayList<Tile>> map, Game game){

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
                destType == TileType.STARDROPSALOON||
                destType == TileType.GREENHOUSE||
                destType == TileType.QUARRY)) {
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
                            nextType == TileType.STARDROPSALOON||nextType==TileType.GREENHOUSE||nextType==TileType.QUARRY) {

                        visited[newY][newX] = true;
                        distance[newY][newX] = distance[y][x] + 1;



                        queue.add(new int[]{newX, newY});
                    }
                }
            }
        }

        int minsteps = distance[desty][destx];
        if (!visited[desty][destx]) {
            return "You can not walk to this position";
        }


        double modifier;
        WeatherType currentWeather = game.weatherSystem.getTodayWeather();

        switch (currentWeather) {
            case RAIN: modifier = 1.5; break;
            case SNOW: modifier = 2.0; break;
            default: modifier = 1.0; break;
        }

        double energyNeeded = ((minsteps / 20) + 1) * modifier;

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

            game.Map.get(prevY).set(prevX, game.MapClone.get(prevY).get(prevX));
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
        Scanner temp = new Scanner(System.in);
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
                game.currentPlayer.Energy-=5;
                System.out.println("You have chopped the tree");
                return;

            }
            System.out.println("This tool is not proper for the selected tile");
            game.currentPlayer.Energy-=5;
            return;


        }
        else if(game.currentPlayer.CurrentTool.subtype.equals(ItemSubType.HOE)){
            if(game.Map.get(disty).get(distx).type.equals(TileType.EMPTY)){
                game.Map.get(disty).set(distx , new Tile(TileType.FERTILE));
                game.currentPlayer.Energy-=5;
                System.out.println("You have furrowed the ground");
                return;
            }
            System.out.println("This tool is not proper for the selected tile");
            game.currentPlayer.Energy-=5;
            return;
        }
        else if(game.currentPlayer.CurrentTool.subtype.equals(ItemSubType.SCYTHE)){
            if(game.Map.get(disty).get(distx).type.equals(TileType.FORAGING)){
                Foraging foraging = (Foraging) game.Map.get(disty).get(distx);
                Food fruit = foraging.Fruit;
                if (foraging.isHarvestable){
                    if (foraging.OneTime){
                        game.Map.get(disty).set(distx , new Tile(TileType.EMPTY));
                    }
                    game.currentPlayer.Energy-=2;
                    AddItem(game,fruit);
                    System.out.println("You have chopped the foraging and harvested " + fruit.Count +" "+fruit.name);
                    return;

                }
                else if(!foraging.isHarvestable){
                    System.out.println("This Foraging is not harvestable yet\ndo you wish to proceed?(y/n)");
                    String ch = temp.nextLine();
                    if (ch.equalsIgnoreCase("y") || ch.equalsIgnoreCase("yes")) {
                        game.Map.get(disty).set(distx , new Tile(TileType.EMPTY));
                        game.currentPlayer.Energy-=2;
                        System.out.println("You have chopped the foraging");
                        return;
                    }
                    else if (ch.equalsIgnoreCase("n") || ch.equalsIgnoreCase("no")) {
                        System.out.println("Command Cancelled");
                        return;
                    }
                }


            }

            else{
                System.out.println("This tool is not proper for the selected tile");
                game.currentPlayer.Energy-=2;
                return;
            }
        }
        else {
            System.out.println("invalid tool");
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
    public void CraftInfo(Game game,String name) {
        for (Foraging foraging : game.AllCropInfo){
            if(foraging.name.equalsIgnoreCase(name)){
                String Stages = numberWithDashes(foraging.Stage);
                String Season = getSeasonsFromDigitsEN(foraging.Season);
                String OneTime = TrueOrFalse(foraging.OneTime);
                String IsEdible = TrueOrFalse(foraging.Fruit.isEdible);
                int HarvestTime = sumOfDigits(foraging.Stage);
                System.out.println("Name: " + foraging.name);
                if (foraging.Seed != null) {
                    System.out.println("Source: " + foraging.Seed.name);
                }
                System.out.println("Stage: " + Stages);
                System.out.println("Total Harvest Time: " + HarvestTime);
                System.out.println("One Time: " + OneTime);
                System.out.println("Regrowth Time: " + foraging.RegrowthTime);
                System.out.println("Base Sell Price: "+foraging.Fruit.price);
                System.out.println("Is Edible: " + IsEdible);
                System.out.println("Energy: " + foraging.Fruit.energy);
                System.out.println("Season: " + Season);
                return;
            }
        }
        System.out.println("This Crop doesn't exist.");

    }
    public void ShowInventory(Game game) {
        for(Item item : game.currentPlayer.items){
            System.out.println("- "+item.Count+" "+item.name);
        }
    }
    public static String numberWithDashes(int number) {
        String numStr = String.valueOf(Math.abs(number));
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < numStr.length(); i++) {
            result.append(numStr.charAt(i));
            if (i != numStr.length() - 1) {
                result.append("-");
            }
        }

        if (number < 0) {
            result.insert(0, "-");
        }

        return result.toString();
    }
    public static String getSeasonsFromDigitsEN(int number) {
        String numStr = String.valueOf(number);
        Set<Character> seen = new LinkedHashSet<>();
        for (char c : numStr.toCharArray()) {
            if ("1234".indexOf(c) != -1) {
                seen.add(c);
            }
        }

        List<String> seasons = new ArrayList<>();
        for (char c : seen) {
            switch (c) {
                case '1' -> seasons.add("Spring");
                case '2' -> seasons.add("Summer");
                case '3' -> seasons.add("Fall");
                case '4' -> seasons.add("Winter");
            }
        }

        if (seasons.isEmpty()) return "No valid seasons.";

        if (seasons.size() == 1) return seasons.get(0);
        return String.join(", ", seasons.subList(0, seasons.size() - 1)) + " and " + seasons.get(seasons.size() - 1);
    }
    public String TrueOrFalse(boolean bool) {
        return bool ? "True" : "False";
    }
    public  int sumOfDigits(int number) {
        number = Math.abs(number); // برای پشتیبانی از عدد منفی
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }






}