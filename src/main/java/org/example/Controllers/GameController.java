package org.example.Controllers;

import org.example.Models.*;
import org.example.Models.Enums.*;
import org.example.Models.Game;
import org.example.Models.Player;
import org.example.Models.Shops.FishManager;
import org.example.Models.Tile;

import java.util.*;

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
                        game.gameClock.advanceTimeByOneDay(game,this);
                        game.weatherSystem.advanceDay();
                        return;
                    }
                    game.gameClock.advanceTimeByOneHour(game,this);
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
                        game.gameClock.advanceTimeByOneDay(game,this);
                        game.weatherSystem.advanceDay();
                        return;
                    }
                    game.gameClock.advanceTimeByOneHour(game,this);
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
                        game.gameClock.advanceTimeByOneDay(game,this);
                        game.weatherSystem.advanceDay();
                    }
                    game.gameClock.advanceTimeByOneHour(game,this);
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
            game.gameClock.advanceTimeByOneHour(game,this);
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
        int[][] turns = new int[height][width];
        int startX = game.currentPlayer.PositionX;
        int startY = game.currentPlayer.PositionY;
        if (destx <=0 || destx >= width || desty <=0 || desty >= height) {
           return "Invalid coordinates entered.";
        }

        final int[][] DIRECTIONS = {
                {0, -1}, // 0: up
                {0, 1},  // 1: down
                {-1, 0}, // 2: left
                {1, 0}   // 3: right
        };

        TileType destType = game.Map.get(desty).get(destx).type;

        if (destType == TileType.BLACKSMITH || destType == TileType.JOJAMART ||
                destType == TileType.GENERALSTORE || destType == TileType.CARPENTERSHOP ||
                destType == TileType.FISHSHOP || destType == TileType.RANCH ||
                destType == TileType.STARDROPSALOON) {

            int hour = game.gameClock.getHour();
            boolean isOpen = switch (destType) {
                case BLACKSMITH -> hour >= 9 && hour < 16;
                case CARPENTERSHOP -> hour >= 9 && hour < 20;
                case GENERALSTORE -> hour >= 9 && hour < 17;
                case RANCH -> hour >= 9 && hour < 16;
                case JOJAMART -> hour >= 9 && hour < 23;
                case FISHSHOP -> hour >= 9 && hour < 17;
                case STARDROPSALOON -> hour >= 12 && hour < 24;
                default -> false;
            };

            if (!isOpen) return "This place is currently closed.";
        }

        if (!(destType == TileType.EMPTY ||
                destType == TileType.BLACKSMITH || destType == TileType.JOJAMART ||
                destType == TileType.GENERALSTORE || destType == TileType.CARPENTERSHOP ||
                destType == TileType.FISHSHOP || destType == TileType.RANCH ||
                destType == TileType.STARDROPSALOON || destType == TileType.GREENHOUSE ||
                destType == TileType.QUARRY || destType==TileType.SHACK)) {
            return "You can not walk to this position";
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY, -1}); // x, y, prevDirection
        visited[startY][startX] = true;
        distance[startY][startX] = 0;
        turns[startY][startX] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int prevDir = current[2];

            if (x == destx && y == desty) break;

            for (int dir = 0; dir < 4; dir++) {
                int newX = x + DIRECTIONS[dir][0];
                int newY = y + DIRECTIONS[dir][1];

                if (newX >= 0 && newY >= 0 && newX < width && newY < height && !visited[newY][newX]) {
                    TileType nextType = game.Map.get(newY).get(newX).type;

                    if (nextType == TileType.EMPTY || nextType == TileType.PLAYER ||
                            nextType == TileType.BLACKSMITH || nextType == TileType.JOJAMART ||
                            nextType == TileType.GENERALSTORE || nextType == TileType.CARPENTERSHOP ||
                            nextType == TileType.FISHSHOP || nextType == TileType.RANCH ||
                            nextType == TileType.STARDROPSALOON || nextType == TileType.GREENHOUSE ||
                            nextType == TileType.QUARRY || nextType==TileType.SHACK) {

                        visited[newY][newX] = true;
                        distance[newY][newX] = distance[y][x] + 1;
                        turns[newY][newX] = turns[y][x] + ((prevDir != -1 && prevDir != dir) ? 1 : 0);
                        queue.add(new int[]{newX, newY, dir});
                    }
                }
            }
        }

        if (!visited[desty][destx]) {
            return "You can not walk to this position";
        }

        int minsteps = distance[desty][destx];
        int numTurns = turns[desty][destx];
        double energyNeeded = (minsteps + 10 * numTurns)/20.0;

        WeatherType currentWeather = game.weatherSystem.getTodayWeather();
        double modifier = switch (currentWeather) {
            case RAIN -> 1.5;
            case SNOW -> 2.0;
            default -> 1.0;
        };

        energyNeeded *= modifier;

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
            game.gameClock.advanceTimeByOneHour(game,this);
        }
        System.out.println("Time advanced by " + hours + " hours.");

    }

    public void processAdvanceDays(Game game, int days) {
        for (int i = 0; i < days; i++) {
            game.gameClock.advanceTimeByOneDay(game,this);
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
        if (game.currentPlayer.Energy <= 5){
            System.out.println("You do not have enough energy to use this tool");
            return;
        }
        Scanner temp = new Scanner(System.in);
        int distx = game.currentPlayer.PositionX + x;
        int disty = game.currentPlayer.PositionY - y;
        if (game.currentPlayer.CurrentTool == null) {
            System.out.println("You do not have any tools equipped");
            return;
        }
        else if (game.currentPlayer.CurrentTool.subtype.equals(ItemSubType.AXE)){

            if(game.Map.get(disty).get(distx).type.equals(TileType.TREE)){
                int bonusEnergy =0;
                if (game.currentPlayer.foragingSkill.getLevel()==4){
                    bonusEnergy = -1;
                }

                Trees tree = (Trees) game.Map.get(disty).get(distx);
                Trees tree2 = new Trees(tree);
                    if (tree.name.equalsIgnoreCase("Wild")) {
                        Material seed= tree2.seed;
                        Material wood= new Material(12, ItemSubType.WOOD,"Wood",1);
                        AddItem(game,seed);
                        AddItem(game,wood);

                        game.Map.get(disty).set(distx , new Tile(TileType.EMPTY));
                        game.currentPlayer.Energy-=5+bonusEnergy;
                        bonusEnergy=0;
                        game.currentPlayer.gainForagingXP(5);
                        System.out.println("You have chopped the tree");
                        return;
                    }
                    if (tree.Fruit.subtype.equals(ItemSubType.SYRUP) && tree.isHarvestable) {
                        Food newFruit = new Food(tree2.Fruit);
                        AddItem(game,newFruit);
                        game.currentPlayer.gainForagingXP(5);
                        System.out.println("You have Harvested"+tree.Fruit.Count+" " + tree.Fruit.name);
                        game.currentPlayer.Energy-=5+bonusEnergy;
                        bonusEnergy=0;
                        return;
                    }
                    else if (!tree.isHarvestable){
                        System.out.println("This Tree is not harvestable yer\ndo you wish to proceed?(y/n)");
                        String ch = temp.nextLine();
                        if (ch.equals("y")) {
                            Material seed= tree2.seed;
                            Material wood= new Material(12, ItemSubType.WOOD,"Wood",1);
                            AddItem(game,seed);
                            AddItem(game,wood);

                            game.Map.get(disty).set(distx , new Tile(TileType.EMPTY));
                            game.currentPlayer.gainFarmingXP(5);
                            game.currentPlayer.Energy-=5+bonusEnergy;
                            bonusEnergy=0;

                            System.out.println("You have chopped the tree");
                            return;
                        }
                        else {
                            System.out.println("Command Cancelled");
                            return;
                        }
                    }



            }
            System.out.println("This tool is not proper for the selected tile");
            game.currentPlayer.Energy-=5;
            return;


        }
        else if(game.currentPlayer.CurrentTool.subtype.equals(ItemSubType.HOE)){
            int bonusEnergy =0;
            if(game.currentPlayer.farmingSkill.getLevel()==4){
                bonusEnergy = -1;
            }
            if(game.Map.get(disty).get(distx).type.equals(TileType.EMPTY)){
                game.Map.get(disty).set(distx , new Tile(TileType.FERTILE));
                game.currentPlayer.Energy-=5;
                System.out.println("You have furrowed the ground");
                return;
            }
            System.out.println("This tool is not proper for the selected tile");
            game.currentPlayer.Energy-=5+bonusEnergy;
            bonusEnergy=0;
            return;
        }
        else if(game.currentPlayer.CurrentTool.subtype.equals(ItemSubType.SCYTHE)){
            if(game.Map.get(disty).get(distx).type.equals(TileType.FORAGING)){
                Foraging foraging = (Foraging) game.Map.get(disty).get(distx);
                Foraging newForaging = new Foraging(foraging);
                Food fruit = newForaging.Fruit;
                if (foraging.isHarvestable){
                    if (foraging.OneTime){
                        game.Map.get(disty).set(distx , new Tile(TileType.EMPTY));
                    }
                    Food newFruit = new Food(newForaging.Fruit);
                    game.currentPlayer.Energy-=2;
                    AddItem(game,newFruit);
                    game.currentPlayer.gainForagingXP(10);
                    foraging.isHarvestable=false;
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
            else if(game.Map.get(disty).get(distx).type.equals(TileType.TREE)){
                Trees tree = (Trees) game.Map.get(disty).get(distx);
                Trees newtree = new Trees(tree);
                Food fruit = newtree.Fruit;
                if (tree.isHarvestable){
                    Food newFruit = new Food(fruit);
                    game.currentPlayer.Energy-=2;
                    AddItem(game,newFruit);
                    game.currentPlayer.gainForagingXP(5);
                    System.out.println("You have harvested the tree and got " + fruit.Count +" "+fruit.name);
                    tree.HarvestedFirstTime = true;
                    tree.isHarvestable=false;
                    return;

                }
                else if(!tree.isHarvestable){
                    game.currentPlayer.Energy-=2;
                    System.out.println("This tree is not harvestable yet");
                    return;

                }
            }


            else{
                System.out.println("This tool is not proper for the selected tile");
                game.currentPlayer.Energy-=2;
                return;
            }
        }
        else if (game.currentPlayer.CurrentTool.subtype.equals(ItemSubType.WATERINGCAN)){
            int bonusEnergy =0;
            if(game.currentPlayer.farmingSkill.getLevel()==4){
                bonusEnergy = -1;
            }
            if(game.Map.get(disty).get(distx).type.equals(TileType.LAKE)){
                game.currentPlayer.CurrentTool.volume = 40;
                game.currentPlayer.Energy-=5+bonusEnergy;
                bonusEnergy=0;
                System.out.println("You have filled the watering can");
                return;
            }
            else if (game.Map.get(disty).get(distx).type.equals(TileType.FORAGING)){
                if (game.currentPlayer.CurrentTool.volume == 0){
                    game.currentPlayer.Energy-=5+bonusEnergy;
                    bonusEnergy=0;
                    System.out.println("Watering can is empty");
                    return;
                }
                Foraging foraging = (Foraging) game.Map.get(disty).get(distx);
                foraging.WateredToday = true;
                game.currentPlayer.CurrentTool.volume -= 1;
                game.currentPlayer.Energy-=5+bonusEnergy;
                game.currentPlayer.gainFarmingXP(5);
                bonusEnergy=0;
                System.out.println("You have watered the foraging");
                return;
            }
            else if (game.Map.get(disty).get(distx).type.equals(TileType.TREE)){
                if (game.currentPlayer.CurrentTool.volume == 0){
                    System.out.println("Watering can is empty");
                    game.currentPlayer.Energy-=5+bonusEnergy;
                    bonusEnergy=0;
                    return;
                }
                Trees tree = (Trees) game.Map.get(disty).get(distx);
                tree.WateredToday = true;
                game.currentPlayer.gainFarmingXP(5);
                System.out.println("You have watered the trees");
                game.currentPlayer.CurrentTool.volume -= 1;
                return;
            }
            else{
                System.out.println("This tools is not proper for the selected tile");
                game.currentPlayer.Energy-=5+bonusEnergy;
                bonusEnergy=0;
                return;
            }
        }
        else if(game.currentPlayer.CurrentTool.subtype.equals(ItemSubType.PICKAXE)){
            int bonusEnergy = 0;
            if (game.currentPlayer.miningSkill.getLevel()==4){
                bonusEnergy=-1;
            }
            if(game.Map.get(disty).get(distx).type.equals(TileType.ROCK)){
                Rock rock = (Rock) game.Map.get(disty).get(distx);
                Material mineral = rock.Mineral;
                AddItem(game,mineral);
                game.currentPlayer.gainForagingXP(10);
                game.Map.get(disty).set(distx, new Tile(TileType.QUARRY));
                game.currentPlayer.Energy-=5+bonusEnergy;
                bonusEnergy = 0;
                game.currentPlayer.gainMiningXP(10);

                System.out.println("You have broken the rock and got " + mineral.Count +" "+mineral.name);
                if (game.currentPlayer.miningSkill.getLevel()>=2){
                    Random rand = new Random();
                    int selected;
                    selected = rand.nextInt(game.AllRocksInfo.size());
                    Rock newRock = new Rock(game.AllRocksInfo.get(selected));
                    AddItem(game,newRock.Mineral);
                    System.out.println("You also got " + newRock.Mineral.Count +" "+newRock.Mineral.name);

                }
                return;
            }
            else{
                System.out.println("This tools is not proper for the selected tile");
                game.currentPlayer.Energy-=5+bonusEnergy;
                bonusEnergy = 0;
                return;
            }
        }
        else if(game.currentPlayer.CurrentTool.subtype == ItemSubType.SHEAR){
            if(game.Map.get(disty).get(distx).type.equals(TileType.ANIMAL)){
                Animal animal = (Animal) game.Map.get(disty).get(distx);
                if(!animal.isHarvestable){
                    System.out.println("This animal is not harvestable yet");
                    game.currentPlayer.Energy-=4;
                    return;
                }
                if (animal.animalType==AnimalType.SHEEP){
                    animal.isHarvestable=false;
                    Material newMaterial = new Material(animal.FinalMaterialProduct);
                    AddItem(game,newMaterial);
                    System.out.println("You have shaved the sheep and got " + newMaterial.Count +" "+newMaterial.name);
                    game.currentPlayer.Energy-=4;

                    return;
                }
                System.out.println("This animal can not be shaved");
                game.currentPlayer.Energy-=4;
                return;


            }
            System.out.println("This tools is not proper for the selected tile");
            game.currentPlayer.Energy-=4;
            return;
        }
        else if(game.currentPlayer.CurrentTool.subtype.equals(ItemSubType.MILKPAIL)){
            if (game.Map.get(disty).get(distx).type.equals(TileType.ANIMAL)){
                Animal animal = (Animal) game.Map.get(disty).get(distx);
                if(!animal.isHarvestable){
                    System.out.println("This animal is not harvestable yet");
                    game.currentPlayer.Energy-=4;
                    return;
                }
                if(animal.animalType==AnimalType.COW || animal.animalType==AnimalType.GOAT){
                    Food newFood = new Food(animal.FinalFoodProduct);
                    System.out.println("You have milked the animal and got " + newFood.Count +" "+newFood.name);
                    animal.isHarvestable=false;
                    AddItem(game,newFood);
                    game.currentPlayer.Energy-=4;
                    return;
                }
                System.out.println("This animal can not be milked");
                game.currentPlayer.Energy-=4;
                return;

            }
            System.out.println("This tools is not proper for the selected tile");
            game.currentPlayer.Energy-=4;
            return;
        }

        else {
            System.out.println("invalid tool");
            return;
        }
    }
    public void TendToCropsDaily(Game game){
        Iterator<Foraging> iterator = game.AllCrops.iterator();
        while(iterator.hasNext()){
            Foraging foraging = iterator.next();
            int days = sumOfDigits(foraging.Stage);
            if(foraging.speedFed){
                foraging.speedFed = false;
                foraging.day++;
            }
           if(!foraging.deluxeFed){
               if(!foraging.WateredToday && foraging.daysWithOutWater < 2){
                   foraging.daysWithOutWater++;
               }
               if (foraging.daysWithOutWater >= 2){
                   game.Map.get(foraging.posy).set(foraging.posx, new Tile(TileType.EMPTY));
                   System.out.println(foraging.name + " has withered due to lack of water");
                   iterator.remove();
                   return;
               }
           }
            if (foraging.WateredToday){
                foraging.daysWithOutWater=0;
            }
            if(!foraging.HarvestedFirstTime){
                if (foraging.day >= days){
                    foraging.isHarvestable = true;
                    foraging.day = 0;
                }
            }
            if (foraging.RegrowthTime > 0 && foraging.HarvestedFirstTime){
                if (foraging.day >= foraging.RegrowthTime){
                    foraging.isHarvestable = true;
                    foraging.day = 0;
                }
            }
            foraging.day++;
            foraging.WateredToday = false;
        }

    }
    public void TendToTreesDaily(Game game){
        Iterator<Trees> iterator = game.AllTrees.iterator();
        while(iterator.hasNext()){
            Trees tree = iterator.next();
            int days = 28;
            if(tree.speedFed){
                tree.day++;
                tree.speedFed = false;
            }
            if(!tree.deluxeFed){
                if (!tree.WateredToday && tree.daysWithoutWater<2){
                    tree.daysWithoutWater++;
                }
                if (tree.daysWithoutWater >= 2){
                    game.Map.get(tree.posy).set(tree.posx, new Tile(TileType.EMPTY));
                    System.out.println(tree.name + " has withered due to lack of water");
                    iterator.remove();
                    return;
                }
            }


            if (tree.WateredToday){
                tree.daysWithoutWater=0;
            }
            if (!tree.HarvestedFirstTime){
                if (tree.day >= days){
                    tree.isHarvestable = true;
                    tree.day = 0;
                }
            }
            if (tree.HarvestingCycle>0 && tree.HarvestedFirstTime){
                if (tree.day >= tree.HarvestingCycle){
                    tree.isHarvestable = true;
                    tree.day = 0;
                }
            }
            tree.day++;
            tree.WateredToday = false;
        }

    }
    public void TendToAnimalsDaily(Game game){
        for(User user : game.users){
            for(Building building: user.player.playerBuildings){
                for(Animal animal: building.animals){
                    if(!animal.FedToday){
                        animal.day = 0;
                    }
                    Random rand = new Random();
                    boolean produceSecondary = false;
                    if(animal.FriendSheepPoint>=100 && animal.animalType !=AnimalType.RABBIT && animal.animalType!=AnimalType.PIG  && animal.animalType!=AnimalType.DINOSAUR &&
                    animal.animalType != AnimalType.SHEEP){
                        double chance = (rand.nextDouble() * 150 + animal.FriendSheepPoint) / 1500.0;
                        produceSecondary = rand.nextDouble() < chance;
                    }
                    double R = rand.nextDouble();
                    double quality = (R*0.5*0.5)*(animal.FriendSheepPoint/1000.0);
                    if (animal.day >= animal.ProductionCycle && animal.animalType!=AnimalType.PIG){
                        animal.isHarvestable=true;
                        animal.day = 0;
                    }
                    if ((animal.animalType==AnimalType.DINOSAUR|| animal.animalType==AnimalType.CHICKEN|| animal.animalType==AnimalType.COW
                    || animal.animalType==AnimalType.DUCK||animal.animalType==AnimalType.GOAT || animal.animalType==AnimalType.PIG)){
                        if (produceSecondary){
                            animal.FinalFoodProduct = new Food(animal.foodProduct.get(1));
                        }
                        else if(!produceSecondary){
                            animal.FinalFoodProduct = new Food(animal.foodProduct.get(0));
                        }
                        if(quality <=0.7 && 0.5<quality){
                            animal.FinalFoodProduct.price= (int) Math.floor(1.25*animal.FinalFoodProduct.price);
                        }
                        else if(quality <=0.9 && 0.7<quality){
                            animal.FinalFoodProduct.price= (int) Math.floor(1.5*animal.FinalFoodProduct.price);
                        }
                        else if(0.9<quality){
                            animal.FinalFoodProduct.price= (int) Math.floor(2*animal.FinalFoodProduct.price);
                        }

                    }
                    if((animal.animalType==AnimalType.SHEEP)){
                        animal.FinalMaterialProduct = new Material(animal.materialProduct.get(0));

                    }

                    if(!animal.Inside){
                        animal.FriendSheepPoint -=20;

                    }
                    if(!animal.petToday){
                        animal.FriendSheepPoint += (animal.FriendSheepPoint/200 - 10);
                    }

                    animal.day++;
                    animal.FedToday = false;
                    animal.petToday = false;

                }
            }
        }
    }
    public void ShowNotCollectedProducts(Game game){
        for(Building building: game.currentPlayer.playerBuildings){
            for(Animal animal: building.animals){
                if(animal.isHarvestable && animal.animalType!=AnimalType.SHEEP){
                    System.out.println(animal.name+ " "+animal.FinalFoodProduct.name);
                    return;
                }
                if(animal.animalType==AnimalType.SHEEP && animal.isHarvestable){
                    System.out.println(animal.name+ " "+animal.FinalMaterialProduct.name);
                }
            }
        }
        System.out.println("You have collected all of the products");
    }
    public void GreenHouse(Game game){
        if(game.currentPlayer.money < 1000){
            System.out.println("You don't have enough money");
            return;
        }
        if(!HasItem(game,"Wood",500)){
            System.out.println("You don't have enough wood");
            return;
        }
        System.out.println("GreenHouse Built successfully");

    }
    public void Pet(Game game, String name,int x,int y){
        int posX= game.currentPlayer.PositionX+x;
        int posY= game.currentPlayer.PositionY-y;
        if(game.Map.get(posY).get(posX).type==TileType.ANIMAL){
            Animal animalTile =(Animal) game.Map.get(posY).get(posX);
            animalTile.petToday = true;
            animalTile.FriendSheepPoint+=15;
            System.out.println(animalTile.name+ " has pet today");
            return;
        }
        System.out.println("You don't have an animal near you");
        return;
    }
    public void Fishing(Game game, String toolName) {
        int bonusEnergy = 0;
        if (game.currentPlayer.fishingSkill.getLevel()==4){
            bonusEnergy=-1;
        }

        Tool tool = null;
        for (Item item : game.currentPlayer.items) {
            if (item.name.equalsIgnoreCase(toolName) && item instanceof Tool &&
                    ((Tool) item).subtype == ItemSubType.FISHINGPOLE) {
                tool = (Tool) item;
                break;
            }
        }

        if (tool == null) {
            System.out.println("You do not have the specified fishing pole.");
            return;
        }

        int px = game.currentPlayer.PositionX;
        int py = game.currentPlayer.PositionY;

        boolean isNearLake = false;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                int nx = px + dx;
                int ny = py + dy;

                if (ny >= 0 && ny < game.Map.size() && nx >= 0 && nx < game.Map.get(ny).size()) {
                    if (game.Map.get(ny).get(nx).type == TileType.LAKE) {
                        isNearLake = true;
                        break;
                    }
                }
            }
            if (isNearLake) break;
        }

        if (!isNearLake) {
            System.out.println("You must be near a lake tile to fish.");
            game.currentPlayer.Energy -= 8+bonusEnergy;
            bonusEnergy = 0;
            return;
        }

        String currentSeason = game.gameClock.getCurrentSeason();

        ArrayList<Food> seasonalFish = new ArrayList<>();
        for (FishWithSeason fishData : FishManager.allFish) {
            if (fishData.season.equalsIgnoreCase(currentSeason)) {
                seasonalFish.add(new Food(
                        fishData.fish.Count,
                        fishData.fish.subtype,
                        fishData.fish.name,
                        fishData.fish.energy,
                        fishData.fish.price,
                        fishData.fish.isEdible
                ));
            }
        }

        if (game.currentPlayer.fishingSkill.getLevel() >= 4) {
            if (currentSeason.equalsIgnoreCase("spring"))
                seasonalFish.add(new Food(1, ItemSubType.FISH, "Legend", 50, 5000, true));
            if (currentSeason.equalsIgnoreCase("winter"))
                seasonalFish.add(new Food(1, ItemSubType.FISH, "Glacierfish", 45, 1000, true));
            if (currentSeason.equalsIgnoreCase("fall"))
                seasonalFish.add(new Food(1, ItemSubType.FISH, "Angler", 30, 900, true));
            if (currentSeason.equalsIgnoreCase("summer"))
                seasonalFish.add(new Food(1, ItemSubType.FISH, "Crimsonfish", 40, 1500, true));
        }

        if (seasonalFish.isEmpty()) {
            System.out.println("There are no fish available in this season.");
            return;
        }

        String weather = game.weatherSystem.getTodayWeatherName();
        double M = switch (weather.toLowerCase()) {
            case "sunny" -> 1.5;
            case "rain" -> 1.2;
            case "storm" -> 0.5;
            default -> 1.0;
        };

        double R = Math.random();
        int fishCount = (int) Math.ceil(R * M * (game.currentPlayer.fishingSkill.getLevel() + 2));
        fishCount = Math.min(fishCount, 6);

        if (fishCount == 0) {
            System.out.println("You didn’t catch any fish this time.");
            game.currentPlayer.Energy -= 8 + bonusEnergy;
            return;
        }

        System.out.println("You caught " + fishCount + " fish:");

        for (int i = 0; i < fishCount; i++) {
            int randomIndex = new Random().nextInt(seasonalFish.size());
            Food caughtFish = seasonalFish.get(randomIndex);

            double poleMultiplier = 0.1;
            String toolNameLower = tool.name.toLowerCase();
            if (toolNameLower.contains("bamboo")) poleMultiplier = 0.5;
            else if (toolNameLower.contains("fiberglass")) poleMultiplier = 0.9;
            else if (toolNameLower.contains("iridium")) poleMultiplier = 1.2;

            double qualityScore = (Math.random() * (game.currentPlayer.fishingSkill.getLevel() + 2)  * poleMultiplier)/(7-M);

            String quality;
            double priceMultiplier;

            if (qualityScore <= 0.5) {
                quality = "Normal";
                priceMultiplier = 1.0;
            } else if (qualityScore > 0.5 && qualityScore <= 0.7) {
                quality = "Silver";
                priceMultiplier = 1.25;
            } else if (qualityScore > 0.7 && qualityScore <= 0.9) {
                quality = "Gold";
                priceMultiplier = 1.5;
            } else {
                quality = "Iridium";
                priceMultiplier = 2.0;
            }
            caughtFish.price *= (int)priceMultiplier;

            AddItem(game, caughtFish);
            System.out.println("- " + caughtFish.name + " (Quality: " + quality + ")");
            game.currentPlayer.gainFishingXP(5);
        }
        seasonalFish=null;

        game.currentPlayer.Energy -= 8 + bonusEnergy;
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
        for (Trees tree : game.AllTreesInfo){
            if (tree.name.equalsIgnoreCase(name)) {
                String Season = getSeasonsFromDigitsEN(tree.Season);
                System.out.println("Name: " + tree.name);
                System.out.println("Fruit: " + tree.Fruit.name);
                System.out.println("Stages : 7-7-7-7");
                System.out.println("Source: " + tree.seed.name);
                System.out.println("Total Harvest Time: 28");
                System.out.println("Regrowth Time: " + tree.HarvestingCycle);
                System.out.println("Base Sell Price: " + tree.Fruit.price);
                System.out.println("Is Edible: " + tree.Fruit.isEdible);
                System.out.println("Energy: " + tree.Fruit.energy);
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
    public void HelpMap(){
        System.out.println("Empty Ground : .\n" +
                "Wall : W\n" +
                "Foragings : #\nTrees : T\nQuarry : Q\nGreenHouse : G\n Home : C\nLake : L\nFertile Ground : O\nPlayer : @\n" +
                "Blacksmith : B\nJojaMart : J\nPirre's Shop : S\nCarpenter's Shop : P\nFish Shop : F\nMarnie's Ranch : M\nStardrop Saloon : A");
    }
    public void Plant(Game game, int x, int y,String name) {
        int destx = game.currentPlayer.PositionX+x;
        int desty = game.currentPlayer.PositionY-y;

        if(!(game.Map.get(desty).get(destx).type.equals(TileType.FERTILE))){
            System.out.println("The ground should be fertile.");
            return;
        }

        Foraging newPlant;
        Material tempSeed;
        Trees newTree;
        for (Item item : game.currentPlayer.items){
            if(item.subtype.equals(ItemSubType.SEED)){
                tempSeed = (Material) item;
                if(tempSeed.name.equals(name)){
                    for(Foraging foraging : game.AllCropInfo){

                        if(foraging.Seed.name.equals(name)){
                            if (!containsDigit(game.gameClock.getCurrentSeasonIndex(),foraging.Season)){
                                System.out.println("This Crop can not be planted during this season");
                                return;
                            }
                            newPlant = new Foraging(foraging);
                            if (game.Map.get(desty).get(destx).deluxeFed) {
                                newPlant.deluxeFed = true;
                            }
                            else if(game.Map.get(desty).get(destx).speedFed) {
                                newPlant.speedFed = true;
                                newPlant.day++;
                            }
                            game.Map.get(desty).set(destx,newPlant);
                            newPlant.posx = destx;
                            newPlant.posy = desty;
                            game.AllCrops.add(newPlant);
                            System.out.println("Plant added.");
                            removeItem(game,tempSeed.name,1);
                            return;
                        }
                    }
                    for(Trees tree : game.AllTreesInfo){

                        if(tree.seed.name.equals(name)){
                            if (!containsDigit(game.gameClock.getCurrentSeasonIndex(),tree.Season)){
                                System.out.println("This Tree can not be planted during this season");
                                return;
                            }
                            newTree = new Trees(tree);
                            game.Map.get(desty).set(destx,newTree);
                            newTree.posx = destx;
                            newTree.posy = desty;
                            game.AllTrees.add(newTree);
                            System.out.println("Tree added.");
                            removeItem(game,tempSeed.name,1);
                            return;
                        }
                    }

                }
            }
        }

        System.out.println("You Don't have this seed in your inventory.");
        return;

    }
    public void Shepherd(Game game,String name,int x , int y){

        Animal Target =null;
        for(Building building:game.currentPlayer.playerBuildings){
            for (Animal animal : building.animals) {
                if(animal.name.equals(name)){
                    Target = animal;
                    break;
                }
            }
        }
        if(Target==null){
            System.out.println("Animal was not found.");
            return;
        }
        if(!Target.Inside){
            Target.Inside = true;
            System.out.println("Animal Was moved back inside.");
            game.Map.get(Target.posY).set(Target.posX,new Tile(TileType.EMPTY));
            return;
        }
        if(game.gameClock.getCurrentSeasonIndex() == 4 || game.weatherSystem.getTodayWeatherName().equalsIgnoreCase("rain")||
                game.weatherSystem.getTodayWeatherName().equalsIgnoreCase("storm")){
            System.out.println("Weather is not suitable for this action.");
            return;
        }
        if (!game.Map.get(y).get(x).type.equals(TileType.EMPTY)) {
            System.out.println("You can't shepherd in this position.");
            return;
        }

        if(Target.animalType == AnimalType.PIG){
            Target.isHarvestable=true;
            Target.FinalFoodProduct = new Food(Target.foodProduct.get(0));
            System.out.println("pig can be harvested now.");
        }
        game.Map.get(y).set(x,Target);
        Target.Inside = false;
        Target.FedToday =true;
        Target.FriendSheepPoint+=8;
        Target.posX = x;
        Target.posY = y;
        System.out.println("Animal Was moved outside.");

    }
    public void Fertilize(Game game , int x, int y, String name) {
        int posx = game.currentPlayer.PositionX+x;
        int posy = game.currentPlayer.PositionY-y;
        Tile tile = game.Map.get(posy).get(posx);
        if (tile.type.equals(TileType.FERTILE) || tile.type.equals(TileType.FORAGING) ||tile.type.equals(TileType.TREE)){
            if (name.equalsIgnoreCase("deluxe retaining soil")){
                if (HasItem(game,"deluxe retaining soil", 1)){
                    tile.deluxeFed = true;
                    if(tile instanceof Foraging){
                        Foraging foraging = (Foraging) game.Map.get(posy).get(posx);
                        if (foraging.day > 0){
                            System.out.println("You can't fertilize a crop after one day after planting it.");
                            tile.deluxeFed = false;
                            return;
                        }
                    }
                    else if(tile instanceof Trees){
                        Trees tree = (Trees) game.Map.get(posy).get(posx);
                        if (tree.day > 0){
                            System.out.println("You can't fertilize a tree after one day after planting it.");
                            tile.deluxeFed=false;
                            return;
                        }
                    }
                    System.out.println("deluxe retaining soil added.");
                    removeItem(game ,"deluxe retaining soil",1);
                    return;

                }
                System.out.println("You dont have this fertilizer.");
                return;

            }
            else if(name.equalsIgnoreCase("speed-gro")) {
                if (HasItem(game, "speed-gro",1)) {
                    tile.speedFed = true;
                    if (tile instanceof Foraging) {
                        Foraging foraging = (Foraging) game.Map.get(posy).get(posx);
                        if (foraging.day > 0) {
                            System.out.println("You can't fertilize a crop after one day after planting it.");
                            tile.speedFed = false;
                            return;
                        }
                        foraging.day++;

                    }
                   else if (tile instanceof Trees) {
                        Trees tree = (Trees) game.Map.get(posy).get(posx);
                        if (tree.day > 0) {
                            System.out.println("You can't fertilize a tree after one day after planting it.");
                            tile.speedFed = false;
                            return;
                        }
                        tree.day++;


                    }
                    System.out.println("Speed-gro added.");

                    removeItem(game, "speed-gro", 1);
                    return;

                }
                System.out.println("You don't have this fertilizer.");
                return;
            }
        }
        System.out.println("You can't fertilize this tile.");
        return;

    }
    public void FeedAnimal(Game game , String name){
        if(!HasItem(game,"Hay",1)){
            System.out.println("You don't have Hay.");
            return;
        }
        for(Building building : game.currentPlayer.playerBuildings){
            for(Animal animal : building.animals){
                if(animal.name.equals(name)){
                    System.out.println(animal.name +" was fed");
                    removeItem(game,"Hay",1);
                    animal.FedToday=true;
                    animal.FriendSheepPoint+=8;
                    return;
                }
            }
        }
        System.out.println("Animal was not found.");
        return;

    }
    public void CollectProduce(Game game, String name){
        for(Building building : game.currentPlayer.playerBuildings){
            for(Animal animal : building.animals){
                if(animal.name.equals(name)){
                    if (animal.isHarvestable && (animal.animalType==AnimalType.CHICKEN || animal.animalType==AnimalType.DUCK|| animal.animalType==AnimalType.PIG)){
                        Food newFood = new Food(animal.FinalFoodProduct);
                        AddItem(game,newFood);
                        System.out.println("You have collected "+animal.FinalFoodProduct.name);
                        animal.isHarvestable = false;
                        return;
                    }
                    System.out.println("Animal is not harvestable yet");
                    return;

                }
            }
        }
        System.out.println("Animal was not found.");
        return;
    }
    public void cheatAnimal(Game game,String name , int friendshipPoint){
        for(Building building : game.currentPlayer.playerBuildings){
            for(Animal animal : building.animals){
                if(animal.name.equals(name)){
                    animal.FriendSheepPoint = friendshipPoint;
                    System.out.println("friendship point added.");
                    return;
                }
            }
        }
        System.out.println("Animal not found.");
        return;
    }

    public void Sell(String name,Game game,int count){
        int playerX = game.currentPlayer.PositionX;
        int playerY = game.currentPlayer.PositionY;
        boolean nearShop = false;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = playerX + j;
                int newY = playerY + i;

                if (newX >= 0 && newY >= 0 && newY < game.Map.size() && newX < game.Map.get(0).size()) {
                    Tile tile = game.Map.get(newY).get(newX);
                    if (tile.type.equals(TileType.SHIPPINGBIN)) {
                        nearShop =true;
                    }
                }

            }

        }

        if(nearShop){

            if(!HasItem(game,name,1)){
                System.out.println("You don't have this amount of this item ");
                return;
            }
            Item newitem = new Item(getItem(game,name));
            newitem.Count= count;
            if(newitem.price == 0){
                System.out.println("You can't sell this item");
                return;
            }
            game.currentPlayer.SoldItems.add(newitem);
            removeItem(game,name,count);
            System.out.println("You sold this item");
            return;
        }
        System.out.println("You need to be near a Shipping Bin.");

    }
    public void TendToShippingBins(Game game){
        for (User user : game.users){
            if(!user.player.SoldItems.isEmpty()){
                for (Item item : user.player.SoldItems){
                    user.player.money+=(item.price)*(item.Count);
                }
            }
            user.player.SoldItems.clear();
        }
    }
    public boolean HasItem(Game game , String name,int requiredCount){
        for (Item item : game.currentPlayer.items){
            if(item.name.equalsIgnoreCase(name) && item.Count >= requiredCount){
                return true;
            }
        }
        return false;
    }
    public Item getItem(Game game,String name){
        for (Item item : game.currentPlayer.items){
            if(item.name.equalsIgnoreCase(name)){
                return item;
            }
        }
        return null;
    }
    public void CheatWater(Game game) {
        for (Trees tree : game.AllTrees){
            tree.WateredToday = true;
        }
        for (Foraging foraging : game.AllCrops){
            foraging.WateredToday = true;
        }
        return;
    }
    public void removeItem(Game game, String name, int countToRemove) {
        for (Item item : game.currentPlayer.items) {
            if (item.name.equalsIgnoreCase(name)) {
                if(item.type.equals(ItemType.TOOL)){
                    System.out.println("You can't remove a tool.");
                    return;
                }
                if (item.Count > countToRemove) {
                    item.Count -= countToRemove;
                    System.out.println("You removed "+countToRemove+" from "+name);
                    return;
                } else if (item.Count == countToRemove) {
                    game.currentPlayer.items.remove(item);
                    System.out.println("You removed "+name);
                    return;
                } else {
                    System.out.println("This amount is more than you have.");
                    return ;
                    // به اندازه ی کافی نداشته یعنی
                }
            }
        }
        System.out.println("You don't have this item.");
        return;
    }
    public  boolean containsDigit(int digit, int number) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Digit must be between 0 and 9");
        }

        String numStr = String.valueOf(Math.abs(number));
        char digitChar = (char) (digit + '0');

        return numStr.indexOf(digitChar) != -1;
    }
    public void BuildBarnOrCoop(Game game,String name,int x,int y) {
        BuildingController buildingController = new BuildingController();
        if(!(HasItem(game,name,1))){
            System.out.println("Buy a building first");
            return;
        }
        Item item = new Item(getItem(game,name));
        buildingController.Build(item.name,game,x,y,this);
    }

    public void HowMuchWater(Game game) {
        if(game.currentPlayer.CurrentTool.subtype.equals(ItemSubType.WATERINGCAN)){
            System.out.println("You have water for "+game.currentPlayer.CurrentTool.volume +" tiles");
            return;
        }
        System.out.println("You have to equip watering can");
        return;
    }
    public void ShowPlant(Game game, int x, int y) {
        if(game.Map.get(y).get(x).type.equals(TileType.FORAGING)){
            Foraging foraging = (Foraging) game.Map.get(y).get(x);
            String Waterd = TrueOrFalse(foraging.WateredToday);
            int daystoharvest = 0;
            if (foraging.HarvestedFirstTime){
                daystoharvest  = foraging.RegrowthTime - foraging.day;
            }
            else if (!foraging.HarvestedFirstTime){
                daystoharvest = sumOfDigits(foraging.Stage) - foraging.day;
            }
            System.out.println("Days Until Harvest: " + daystoharvest);
            System.out.println("Watered Today: " + Waterd);
            CraftInfo(game,foraging.name);
        }
        else if (game.Map.get(y).get(x).type.equals(TileType.TREE)){
            Trees tree = (Trees) game.Map.get(y).get(x);
            String Waterd = TrueOrFalse(tree.WateredToday);
            int daystoharvest = 0;
            if (tree.HarvestedFirstTime){
                daystoharvest = tree.HarvestingCycle - tree.day;

            }
            else if (!tree.HarvestedFirstTime){
                daystoharvest = 28 - tree.day;
            }
            System.out.println("Days Until Harvest: " + daystoharvest);
            System.out.println("Watered Today: " + Waterd);
            CraftInfo(game,tree.name);
        }
    }
    public void AnimalsShow(Game game) {
        for(Building building:game.currentPlayer.playerBuildings){
            for(Animal animal:building.animals){
                String fedToday = TrueOrFalse(animal.FedToday);
                String petToday = TrueOrFalse(animal.petToday);
                System.out.println(animal.name+" Fed Today: " + fedToday+" Pet Today: " + petToday+" FriendSheepPoint: " + animal.FriendSheepPoint);
            }
        }
    }
    public void GoHome(Game game) {
        Player currentPlayerTemp = game.currentPlayer;
        for(User user : game.users){
            game.currentPlayer = user.player;
            if (user.player.FarmNumber==1){
                Walk(36,2,game);
            }
            else if (user.player.FarmNumber==2){
                Walk(102,2,game);
            }
            else if (user.player.FarmNumber==3){
                Walk(2,109,game);
            }
            else if (user.player.FarmNumber==4){
                Walk(102,85,game);
            }
        }
        game.currentPlayer = currentPlayerTemp;
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
        number = Math.abs(number);
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }






}