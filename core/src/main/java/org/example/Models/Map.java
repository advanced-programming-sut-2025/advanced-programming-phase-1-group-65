package org.example.Models;

import org.example.Controllers.GameController.GameController;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.TileType;

import java.util.ArrayList;
import java.util.Random;

public class Map {


    public void generateRandomObjects(ArrayList<ArrayList<Tile>> MapArrayList,Game game) {
        generateRandomTrees(MapArrayList, 0,0, 39, 27,game);

        generateRandomTrees(MapArrayList, 100, 0, 139, 27,game);

        generateRandomTrees(MapArrayList, 0, 85, 39, 111,game);
        //generateRandomForagings(MapArrayList, 0, 82, 39, 111,game);
       // generateRandomRocks(MapArrayList, 0, 82, 39, 111);

        // generating random objects for farm number 4
        generateRandomTrees(MapArrayList, 100, 85, 139, 111,game);
       // generateRandomForagings(MapArrayList, 100, 82, 139, 111,game);
        //generateRandomRocks(MapArrayList, 100, 82, 139, 111);
    }

    public void generateRandomTrees(ArrayList<ArrayList<Tile>> MapArrayList ,int x0, int y0, int x1, int y1,Game game) {
        int numOfTrees = (int) ((Math.random() * 10) + 20);
        Trees tree;
        int randomX = (int) ((Math.random() * (x1 - x0 + 1)) + x0);
        int randomY = (int) ((Math.random() * (y1 - y0 + 1)) + y0);
        Material seed;
        for (int i = 0; i < numOfTrees; i++) {
            while (MapArrayList.get(randomY).get(randomX).getType() != TileType.EMPTY) {
                randomX = (int) ((Math.random() * (x1 - x0 + 1)) + x0);
                randomY = (int) ((Math.random() * (y1 - y0 + 1)) + y0);
            }
            seed = getRandomSeed(game);
            tree = new Trees("Wild",null,1234,0,seed);
            MapArrayList.get(randomY).set(randomX, tree);
        }
    }
   public Material getRandomSeed(Game game) {
        Material seed = null;
        if(game.gameClock.getCurrentSeasonIndex()==1){
            String[] Trees = {"Apricot Tree", "Cherry Tree", "Mystic Tree", "Mushroom Tree", "Mahogany Tree", "Pine Tree"
            ,"Maple Tree", "Oak Tree"};
            Random rand = new Random();
            int index = rand.nextInt(Trees.length);
            switch (index){
                case 0:
                     seed = new Material(1,ItemSubType.SEED,"Apricot Sapling",1000);
                    break;
                case 1:
                    seed = new Material(1,ItemSubType.SEED,"Cherry Sapling",4000);
                    break;
                case 2:
                    seed = new Material(1,ItemSubType.SEED,"Mystic Tree Seeds",5000);
                    break;
                case 3:
                    seed = new Material(1,ItemSubType.SEED,"Mushroom Tree Seeds",6000);
                    break;
                case 4:
                    seed = new Material(1,ItemSubType.SEED,"Mahogany Seeds",5000);
                    break;
                case 5:
                    seed = new Material(1,ItemSubType.SEED,"Pine Cones",4000);
                    break;
                 case 6:
                     seed = new Material(1,ItemSubType.SEED,"Maple Seeds",3000);
                     break;
                     case 7:
                         seed = new Material(1,ItemSubType.SEED,"Acorns",4000);
                         break;
                default:
                    seed = new Material(1,ItemSubType.SEED,"Maple Seeds",4000);
            }

        }
        else if(game.gameClock.getCurrentSeasonIndex()==2){
            String[] Trees = {"Banana Tree","Mango Tree","Mystic Tree", "Mushroom Tree", "Mahogany Tree", "Pine Tree"
                    ,"Maple Tree", "Oak Tree","Orange Tree","Peach Tree"};
            Random rand = new Random();
            int index = rand.nextInt(Trees.length);
            switch (index){
                case 0:
                    seed = new Material(1,ItemSubType.SEED,"Banana Sapling",4000);
                    break;
                case 1:
                    seed = new Material(1,ItemSubType.SEED,"Mango Sapling",4000);
                    break;
                case 2:
                    seed = new Material(1,ItemSubType.SEED,"Mystic Tree Seeds",5000);
                    break;
                case 3:
                    seed = new Material(1,ItemSubType.SEED,"Mushroom Tree Seeds",4000);
                    break;
                case 4:
                    seed = new Material(1,ItemSubType.SEED,"Mahogany Seeds",3000);
                    break;
                case 5:
                    seed = new Material(1,ItemSubType.SEED,"Pine Cones",4000);
                    break;
                case 6:
                    seed = new Material(1,ItemSubType.SEED,"Maple Seeds",3000);
                    break;
                case 7:
                    seed = new Material(1,ItemSubType.SEED,"Acorns",4000);
                    break;
                    case 8:
                        seed = new Material(1,ItemSubType.SEED,"Orange Sapling",4000);
                        break;
                        case 9:
                            seed = new Material(1,ItemSubType.SEED,"Peach Sapling",4000);
                            break;
                default:
                    seed = new Material(1,ItemSubType.SEED,"Banana Sapling",4000);


            }
        }
        else if(game.gameClock.getCurrentSeasonIndex()==3){
            String[] Trees = {"Apple Tree", "Pomegranate Tree", "Mystic Tree", "Mushroom Tree", "Mahogany Tree", "Pine Tree"
                    ,"Maple Tree", "Oak Tree"};
            Random rand = new Random();
            int index = rand.nextInt(Trees.length);
            switch (index){
                case 0:
                    seed = new Material(1,ItemSubType.SEED,"Apple Sapling",4000);
                    break;
                case 1:
                    seed = new Material(1,ItemSubType.SEED,"Pomegranate Sapling",4000);
                    break;
                case 2:
                    seed = new Material(1,ItemSubType.SEED,"Mystic Tree Seeds",4000);
                    break;
                case 3:
                    seed = new Material(1,ItemSubType.SEED,"Mushroom Tree Seeds",4000);
                    break;
                case 4:
                    seed = new Material(1,ItemSubType.SEED,"Mahogany Seeds",4000);
                    break;
                case 5:
                    seed = new Material(1,ItemSubType.SEED,"Pine Cones",4000);
                    break;
                case 6:
                    seed = new Material(1,ItemSubType.SEED,"Maple Seeds",4000);
                    break;
                case 7:
                    seed = new Material(1,ItemSubType.SEED,"Acorns",4000);
                    break;
                default:
                    seed = new Material(1,ItemSubType.SEED,"Pine Cones",4000);
            }

        }
        else if(game.gameClock.getCurrentSeasonIndex()==4){
            String[] Trees = {"Mystic Tree", "Mushroom Tree", "Mahogany Tree", "Pine Tree"
                    ,"Maple Tree", "Oak Tree"};
            Random rand = new Random();
            int index = rand.nextInt(Trees.length);
            switch (index){

                case 0:
                    seed = new Material(1,ItemSubType.SEED,"Mystic Tree Seeds",4000);
                    break;
                case 1:
                    seed = new Material(1,ItemSubType.SEED,"Mushroom Tree Seeds",4000);
                    break;
                case 2:
                    seed = new Material(1,ItemSubType.SEED,"Mahogany Seeds",4000);
                    break;
                case 3:
                    seed = new Material(1,ItemSubType.SEED,"Pine Cones",4000);
                    break;
                case 4:
                    seed = new Material(1,ItemSubType.SEED,"Maple Tree Seeds",4000);
                    break;
                case 5:
                    seed = new Material(1,ItemSubType.SEED,"Acorns",4000);
                    break;
                    default:
                        seed = new Material(1,ItemSubType.SEED,"Acorns",4000);
            }
        }
        return seed;
    }



    /*public void generateRandomForagings(ArrayList<ArrayList<Tile>> MapArrayList ,int x0, int y0, int x1, int y1,Game game) {
        int numOfForagings = (int) ((Math.random() * 10) + 20);

        int randomX = (int) ((Math.random() * (x1 - x0 + 1)) + x0);
        int randomY = (int) ((Math.random() * (y1 - y0 + 1)) + y0);

        for (int i = 0; i < numOfForagings; i++) {
            while (MapArrayList.get(randomY).get(randomX).getType() != TileType.EMPTY ) {
                randomX = (int) ((Math.random() * (x1 - x0 + 1)) + x0);
                randomY = (int) ((Math.random() * (y1 - y0 + 1)) + y0);
            }
            MapArrayList.get(randomY).set(randomX, new Tile(TileType.FORAGING));
        }
    }*/
    public void GenerateRandomRockDaily(ArrayList<ArrayList<Tile>> Map, Game game) {
        Random rand = new Random();
        int chance;
        int selected;
        for (int i = 0; i < Map.size(); i++) {
            for (int j = 0; j < Map.get(i).size(); j++) {
                if (Map.get(i).get(j).type.equals(TileType.QUARRY)) {
                    chance = rand.nextInt(100);
                    if (chance ==0) {
                       selected = rand.nextInt(game.AllRocksInfo.size());
                       Rock newRock = new Rock (game.AllRocksInfo.get(selected));
                       game.Map.get(i).set(j, newRock);
                    }
                }
            }
        }
    }
    public void GenerateRandomForagingDaily(ArrayList<ArrayList<Tile>> Map, Game game) {
        ArrayList<Foraging> foragingCrops = new ArrayList<>();
        ArrayList<Foraging> foragingSeeds=new ArrayList<>();
        GameController tempController = new GameController();
        Random rand = new Random();
        int chance = 1;
        int selected = 1;
        for (Foraging crop : game.AllCropInfo){
            if(crop.isHarvestable && tempController.containsDigit(game.gameClock.getCurrentSeasonIndex(),crop.Season)){
                foragingCrops.add(crop);
            }
            else if(!(crop.isHarvestable) && tempController.containsDigit(game.gameClock.getCurrentSeasonIndex(),crop.Season)) {
                foragingSeeds.add(crop);
            }
        }
        for (int y = 0; y < 28 ; y++) {
            for (int x = 0; x < 39; x++){
                chance = rand.nextInt(200);
                if (Map.get(y).get(x).getType() == TileType.EMPTY) {
                    selected = rand.nextInt(foragingCrops.size());
                    Foraging newForaging = new Foraging(foragingCrops.get(selected));

                    if (chance ==0) {
                        Map.get(y).set(x, newForaging);
                    }
                }
                else if(Map.get(y).get(x).getType()==TileType.FERTILE){
                    selected = rand.nextInt(foragingSeeds.size());
                    Foraging newForaging = new Foraging(foragingSeeds.get(selected));
                    if (chance ==0) {
                        Map.get(y).set(x, newForaging);
                        game.AllCrops.add(newForaging);
                    }
                }
            }
        }
        for (int y = 0; y < 28 ; y++) {
            for (int x = 100; x < 139; x++){
                chance = rand.nextInt(200);
                if (Map.get(y).get(x).getType() == TileType.EMPTY) {
                    selected = rand.nextInt(foragingCrops.size());
                    Foraging newForaging = new Foraging(foragingCrops.get(selected));

                    if (chance ==0) {
                        Map.get(y).set(x, newForaging);
                    }
                }
                else if(Map.get(y).get(x).getType()==TileType.FERTILE){
                    selected = rand.nextInt(foragingSeeds.size());
                    Foraging newForaging = new Foraging(foragingSeeds.get(selected));

                    if (chance ==0) {
                        Map.get(y).set(x, newForaging);
                        game.AllCrops.add(newForaging);
                    }
                }
            }
        }
        for (int y = 84; y < 111 ; y++) {
            for (int x = 0; x < 39; x++){
                chance = rand.nextInt(200);
                if (Map.get(y).get(x).getType() == TileType.EMPTY) {
                    selected = rand.nextInt(foragingCrops.size());
                    Foraging newForaging = new Foraging(foragingCrops.get(selected));

                    if (chance ==0) {
                        Map.get(y).set(x, newForaging);
                    }
                }
                else if(Map.get(y).get(x).getType()==TileType.FERTILE){
                    selected = rand.nextInt(foragingSeeds.size());
                    Foraging newForaging = new Foraging(foragingSeeds.get(selected));

                    if (chance ==0) {
                        Map.get(y).set(x, newForaging);
                        game.AllCrops.add(newForaging);
                    }
                }
            }
        }
        for (int y = 84; y < 111 ; y++) {
            for (int x = 100; x < 139; x++){
                chance = rand.nextInt(200);
                if (Map.get(y).get(x).getType() == TileType.EMPTY) {
                    selected = rand.nextInt(foragingCrops.size());
                    Foraging newForaging = new Foraging(foragingCrops.get(selected));

                    if (chance ==0) {
                        Map.get(y).set(x, newForaging);
                    }
                }
                else if(Map.get(y).get(x).getType()==TileType.FERTILE){
                    selected = rand.nextInt(foragingSeeds.size());
                    Foraging newForaging = new Foraging(foragingSeeds.get(selected));

                    if (chance ==0) {
                        Map.get(y).set(x, newForaging);
                        game.AllCrops.add(newForaging);
                    }
                }
            }
        }
        foragingCrops = null;
        foragingSeeds = null;

    }

    public void generateRandomRocks(ArrayList<ArrayList<Tile>> MapArrayList ,int x0, int y0, int x1, int y1) {
        int numOfRocks = (int) ((Math.random() * 10) + 20);

        int randomX = (int) ((Math.random() * (x1 - x0 + 1)) + x0);
        int randomY = (int) ((Math.random() * (y1 - y0 + 1)) + y0);

        for (int i = 0; i < numOfRocks; i++) {
            while (MapArrayList.get(randomY).get(randomX).getType() != TileType.EMPTY) {
                randomX = (int) ((Math.random() * (x1 - x0 + 1)) + x0);
                randomY = (int) ((Math.random() * (y1 - y0 + 1)) + y0);
            }
            MapArrayList.get(randomY).set(randomX, new Tile(TileType.ROCK));
        }

    }

    public void generateShippingBins(ArrayList<ArrayList<Tile>> Map ,Game game) {
        Tile Shippingbin1 = new Tile(TileType.SHIPPINGBIN);
        Tile Shippingbin2 = new Tile(TileType.SHIPPINGBIN);
        Tile Shippingbin3 = new Tile(TileType.SHIPPINGBIN);
        Tile Shippingbin4 = new Tile(TileType.SHIPPINGBIN);
        Shippingbin1.FarmNumber=1;
        Shippingbin2.FarmNumber=2;
        Shippingbin3.FarmNumber=3;
        Shippingbin4.FarmNumber=4;
        Map.get(30).set(46,Shippingbin1);
        Map.get(30).set(93,Shippingbin2);
        Map.get(81).set(46,Shippingbin3);
        Map.get(81).set(93,Shippingbin4);
    }

    public String[] MapTemplate={
            //ROW1
              "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW............................................................WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
            , // row2
              "WQQQQQQQQQQ....GGGGGG..............CCCCW............................................................WCCCC........................QQQQQQQQQQW"
            //row3
            , "WQQQQQQQQQQ....GGGGGG..............CCCCW............................................................WCCCC........................QQQQQQQQQQW"
            //row4
            , "WQQQQQQQQQQ....GGGGGG..............CCCCW............................................................WCCCC........................QQQQQQQQQQW"
            //row5
            , "WQQQQQQQQQQ....GGGGGG..............CCCCW............................................................WCCCC........................QQQQQQQQQQW"
            //row 6
            , "WQQQQQQQQQQ....GGGGGG..................W............................................................W............................QQQQQQQQQQW"
            // row 7
            , "WQQQQQQQQQQ............................W............................................................W............................QQQQQQQQQQW"
            //ROW 8
            , "WQQQQQQQQQQ............................W............................................................W............................QQQQQQQQQQW"
            // ROW 9
            , "WQQQQQQQQQQ............................W............................................................W............................QQQQQQQQQQW"
            //ROW 10
            , "W......................................W............................................................W......................................W"
            //ROW 11
            , "W......................................W............................................................W......................................W"
            //ROW 12
            , "W......................................W............................................................W......................................W"
            //ROW 13
            , "W......................................W............................................................W......................................W"
            //ROW 14
            , "W......................................W............................................................W......................................W"
            //ROW 15
            , "W......................................W............................................................W......................................W"
            //ROW 16
            , "W......................................W............................................................W......................................W"
            //ROW 17
            , "W......................................W............................................................W......................................W"
            //ROW 18
            , "W......................................W............................................................W......................................W"
            //ROW 19
            , "W......................................W............................................................W......................................W"
            // ROW 20
            , "W..........LLLLLLLLLL..................W............................................................W......................................W"
            // ROW 21
            , "W..........LLLLLLLLLL..................W............................................................W......................................W"
            // ROW 22
            , "W..........LLLLLLLLLL..................W............................................................W......................................W"
            // ROW 23
            , "W..........LLLLLLLLLL..................W............................................................W......................................W"
            // ROW 24
            , "W..........LLLLLLLLLL..................W............................................................W............LLLLLLLLLL................W"
            // ROW 25
            , "W..........LLLLLLLLLL..................W............................................................W............LLLLLLLLLL..........GGGGGGW"
            // ROW 26
            , "W..........LLLLLLLLLL..................W............................................................W............LLLLLLLLLL..........GGGGGGW"
// ROW 27
            , "W..........LLLLLLLLLL..................W............................................................W............LLLLLLLLLL..........GGGGGGW"
// ROW 28
            , "W..........LLLLLLLLLL..................W............................................................W............LLLLLLLLLL..........GGGGGGW"
// ROW 29
            , "W..........LLLLLLLLLL..................W............................................................W............LLLLLLLLLL..........GGGGGGW"
            //ROW 30
            , "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW.WWWWW.....WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW.....WWWWW.WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
            //ROW 31
            , ".................................W.W.........W................................................W.........W.W................................."
//ROW 32
            , ".................................W.W.........W................................................W.........W.W................................."
//ROW 33
            , ".................................W.W.........W................................................W.........W.W................................."
//ROW 34
            , ".................................W.W.........W................................................W.........W.W................................."
//ROW 35
            , ".................................W.W.........W.........111...........222............333.......W.........W.W................................."
//ROW 36
            , ".................................W.W.........W.........111...........222............333.......W.........W.W................................."
//ROW 37
            , ".................................W.W.........W.........111...........222............333.......W.........W.W................................."
//ROW 38
            , ".................................W.W.........W..........s.............a.............h.........W.........W.W................................."
//ROW 39
            , ".................................W.W.........W................................................W.........W.W................................."
//ROW 40
            , ".................................W.W.........W................................................W.........W.W................................."
            // ROW 41
            , ".................................W.WWWWWWWWWWW.........BBBB.........JJJJ.........SSSS.........WWWWWWWWWWW.W................................."
            //ROW 42
            , ".................................W.....................BBBB.........JJJJ.........SSSS.....................W................................."
            // ROW 43
            , ".................................WWWWWWWWWWWWW.........BBBB.........JJJJ.........SSSS.........WWWWWWWWWWWWW................................."
            // ROW 44
            , ".............................................W.........BBBB.........JJJJ.........SSSS.........W............................................."
            // ROW 45
            , ".............................................W................................................W............................................."
// ROW 46
            , ".............................................W................................................W............................................."
// ROW 47
            , ".............................................W................................................W............................................."
// ROW 48
            , ".............................................W..........444..................555..............W............................................."
// ROW 49
            , ".............................................W..........444..................555..............W............................................."
// ROW 50
            , ".............................................W..........444..................555..............W............................................."
// ROW 51
            , ".............................................W..........l.....................r...............W............................................."
// ROW 52
            , ".............................................W................................................W............................................."
// ROW 53
            , ".............................................W................................................W............................................."
            //ROW 54
            , ".............................................W.........PPPP.........FFFF.........MMMM.........W............................................."
            // ROW 55
            , ".............................................W.........PPPP.........FFFF.........MMMM.........W............................................."
            // ROW 56
            , ".............................................W.........PPPP.........FFFF.........MMMM.........W............................................."
// ROW 57
            , ".............................................W.........PPPP.........FFFF.........MMMM.........W............................................."
            // ROW 58
            , ".............................................W................................................W............................................."
// ROW 59
            , ".............................................W................................................W............................................."
// ROW 60
            , ".............................................W................................................W............................................."
// ROW 61
            , ".............................................W................................................W............................................."
// ROW 62
            , ".............................................W................................................W............................................."
// ROW 63
            , ".............................................W................................................W............................................."
// ROW 64
            , ".............................................W................................................W............................................."
// ROW 65
            , ".............................................W................................................W............................................."
// ROW 66
            , ".............................................W................................................W............................................."
// ROW 67
            , ".............................................W......................AAAA......................W............................................."
// ROW 68
            , ".............................................W......................AAAA......................W............................................."
// ROW 69
            , ".............................................W......................AAAA......................W............................................."
// ROW 70
            , "..................................WWWWWWWWWWWW......................AAAA......................WWWWWWWWWWWW.................................."
// ROW 71
            , "..................................W......................................................................W.................................."
// ROW 72
            , "..................................W.WWWWWWWWWW................................................WWWWWWWWWW.W.................................."
// ROW 73
            , "..................................W.W........W................................................W........W.W.................................."
// ROW 74
            , "..................................W.W........W................................................W........W.W.................................."
// ROW 75
            , "..................................W.W........W................................................W........W.W.................................."
// ROW 76
            , "..................................W.W........W................................................W........W.W.................................."
// ROW 77
            , "..................................W.W........W................................................W........W.W.................................."
// ROW 78
            , "..................................W.W........W................................................W........W.W.................................."
// ROW 79
            , "..................................W.W........W................................................W........W.W.................................."
// ROW 80
            , "..................................W.W........W................................................W........W.W.................................."
// ROW 81
            , "..................................W.W........W................................................W........W.W.................................."
// ROW 82
            , "..................................W.W........W................................................W........W.W.................................."
// ROW 83
            , "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW.WWWW.....WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW.....WWWW.WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
// ROW 84
            , "W.....LLLLLLLLLL....LLLLLLLLLL.........W............................................................W............................QQQQQQQQQQW"
// ROW 85
            , "W.....LLLLLLLLLL....LLLLLLLLLL.........W............................................................WCCCC........................QQQQQQQQQQW"
// ROW 86
            , "W.....LLLLLLLLLL....LLLLLLLLLL.........W............................................................WCCCC........................QQQQQQQQQQW"
// ROW 87
            , "W.....LLLLLLLLLL....LLLLLLLLLL.........W............................................................WCCCC........................QQQQQQQQQQW"
// ROW 90
            , "W......................................W............................................................WCCCC........................QQQQQQQQQQW"
// ROW 91
            , "W......................................W............................................................W............................QQQQQQQQQQW"
// ROW 92
            , "W......................................W............................................................W............................QQQQQQQQQQW"
// ROW 93
            , "W......................................W............................................................W............................QQQQQQQQQQW"
// ROW 94
            , "W......................................W............................................................W......................................W"
// ROW 95
            , "W......................................W............................................................W......................................W"
// ROW 96
            , "W......................................W............................................................W......................................W"
// ROW 97
            , "W......................................W............................................................W......................................W"
// ROW 98
            , "W......................................W............................................................W......................................W"
// ROW 99
            , "W......................................W............................................................W......................................W"
// ROW 100
            , "W......................................W............................................................W......................................W"
// ROW 101
            , "W......................................W............................................................W......................................W"
// ROW 102
            , "W......................................W............................................................W......................................W"
// ROW 103
            , "W......................................W............................................................W......................................W"
// ROW 104
            , "W......................................W............................................................W......................................W"
// ROW 105
            , "W......................................W............................................................W......................................W"
// ROW 106
            , "W......................................W............................................................W......................................W"
// ROW 107
            , "W......................................W............................................................W......................................W"
// ROW 108
            , "W..............................QQQQQQQQW............................................................W............LLLLLLLLLL................W"
// ROW 109
            , "W............GGGGGG............QQQQQQQQW............................................................W............LLLLLLLLLL..........GGGGGGW"
// ROW 110
            , "WCCCC........GGGGGG............QQQQQQQQW............................................................W............LLLLLLLLLL..........GGGGGGW"
// ROW 111
            , "WCCCC........GGGGGG............QQQQQQQQW............................................................W............LLLLLLLLLL..........GGGGGGW"
// ROW 112
            , "WCCCC........GGGGGG............QQQQQQQQW............................................................W............LLLLLLLLLL..........GGGGGGW"
// ROW 113
            , "WCCCC........GGGGGG............QQQQQQQQW............................................................W............LLLLLLLLLL..........GGGGGGW"
// ROW 114
            , "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW............................................................WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
    };

    public ArrayList<ArrayList<Tile>> buildMap(Game game) {
        ArrayList<ArrayList<Tile>> map = new ArrayList<>();


        for (String row : MapTemplate) {
            ArrayList<Tile> tilerow = new ArrayList<>();
            for (char c : row.toCharArray()) {
                Tile tile = null;
                switch (c) {
                    case 'W':
                        tile = new Tile(TileType.WALL);
                        break;
                    case 'Q':
                        tile = new Tile(TileType.QUARRY);
                        break;
                    case 'G':
                        tile = new Tile(TileType.GREENHOUSE);
                        break;
                    case 'C':
                        tile = new Tile(TileType.SHACK);
                        break;
                    case '.':
                        tile = new Tile(TileType.EMPTY);
                        break;
                    case 'L':
                        tile = new Tile(TileType.LAKE);
                        break;
                    case 'B':
                        tile = new Tile(TileType.BLACKSMITH);
                        break;
                    case 'S':
                        tile = new Tile(TileType.GENERALSTORE);
                        break;
                    case 'P':
                        tile = new Tile(TileType.CARPENTERSHOP);
                        break;
                    case 'F':
                        tile = new Tile(TileType.FISHSHOP);
                        break;
                    case 'M':
                        tile = new Tile(TileType.RANCH);
                        break;
                    case 'A':
                        tile = new Tile(TileType.STARDROPSALOON);

                        break;
                    case 'J':
                        tile = new Tile(TileType.JOJAMART);
                        break;
                    case '1':
                        tile = new Tile(TileType.SEBASTIAN_HOUSE);
                        break;
                    case '2':
                        tile = new Tile(TileType.ABIGAIL_HOUSE);
                        break;
                    case '3':
                        tile = new Tile(TileType.HARVEY_HOUSE);
                        break;
                    case '4':
                        tile = new Tile(TileType.LEAH_HOUSE);
                        break;
                    case '5':
                        tile = new Tile(TileType.ROBIN_HOUSE);
                        break;
                    case 's':
                        tile = new Tile(TileType.SEBASTIAN_NPC);
                        break;
                    case 'a':
                        tile = new Tile(TileType.ABIGAIL_NPC);
                        break;
                    case 'h':
                        tile = new Tile(TileType.HARVEY_NPC);
                        break;
                    case 'l':
                        tile = new Tile(TileType.LEAH_NPC);
                        break;
                    case 'r':
                        tile = new Tile(TileType.ROBIN_NPC);
                        break;
                    default:
                        tile = new Tile(TileType.EMPTY);
                        break;


                }
                tilerow.add(tile);
            }
            map.add(tilerow);
        }
        generateRandomObjects(map,game);
        return map;

    }
    public ArrayList<ArrayList<Tile>> cloneMap(Game game) {
        ArrayList<ArrayList<Tile>> map = new ArrayList<>();


        for (String row : MapTemplate) {
            ArrayList<Tile> tilerow = new ArrayList<>();
            for (char c : row.toCharArray()) {
                Tile tile = null;
                switch (c) {
                    case 'W':
                        tile = new Tile(TileType.WALL);
                        break;
                    case 'Q':
                        tile = new Tile(TileType.QUARRY);
                        break;
                    case 'G':
                        tile = new Tile(TileType.GREENHOUSE);
                        break;
                    case 'C':
                        tile = new Tile(TileType.SHACK);
                        break;
                    case '.':
                        tile = new Tile(TileType.EMPTY);
                        break;
                    case 'L':
                        tile = new Tile(TileType.LAKE);
                        break;
                    case 'B':
                        tile = new Tile(TileType.BLACKSMITH);
                        break;
                    case 'S':
                        tile = new Tile(TileType.GENERALSTORE);
                        break;
                    case 'P':
                        tile = new Tile(TileType.CARPENTERSHOP);
                        break;
                    case 'F':
                        tile = new Tile(TileType.FISHSHOP);
                        break;
                    case 'M':
                        tile = new Tile(TileType.RANCH);
                        break;
                    case 'A':
                        tile = new Tile(TileType.STARDROPSALOON);

                        break;
                    case 'J':
                        tile = new Tile(TileType.JOJAMART);
                        break;
                    case '1':
                        tile = new Tile(TileType.SEBASTIAN_HOUSE);
                        break;
                    case '2':
                        tile = new Tile(TileType.ABIGAIL_HOUSE);
                        break;
                    case '3':
                        tile = new Tile(TileType.HARVEY_HOUSE);
                        break;
                    case '4':
                        tile = new Tile(TileType.LEAH_HOUSE);
                        break;
                    case '5':
                        tile = new Tile(TileType.ROBIN_HOUSE);
                        break;
                    case 's':
                        tile = new Tile(TileType.SEBASTIAN_NPC);
                        break;
                    case 'a':
                        tile = new Tile(TileType.ABIGAIL_NPC);
                        break;
                    case 'h':
                        tile = new Tile(TileType.HARVEY_NPC);
                        break;
                    case 'l':
                        tile = new Tile(TileType.LEAH_NPC);
                        break;
                    case 'r':
                        tile = new Tile(TileType.ROBIN_NPC);
                        break;
                    default:
                        tile = new Tile(TileType.EMPTY);
                        break;


                }
                tilerow.add(tile);
            }
            map.add(tilerow);
        }
        return map;

    }



    public void printMap(ArrayList<ArrayList<Tile>> map) {
        final String RESET = "\u001B[0m";
        final String BLUE = "\u001B[34m";
        final String BLACK = "\u001B[30m";
        final String GREEN = "\u001B[32m";
        final String BRIGHT_GREEN = "\u001B[92m";
        final String BROWN = "\u001B[33m";
        final String RED = "\u001B[31m";
        final String STONE_GRAY = "\u001B[38;2;128;128;128m";
        final String SHACK_BROWN = "\u001B[38;2;139;69;19m";
        final String GREENHOUSE_GREEN = "\u001B[38;2;50;205;50m";
        for (ArrayList<Tile> row : map) {
            for (Tile tile : row) {
                String color;
                switch (tile.getType()) {
                    case LAKE -> color = BLUE;
                    case WALL -> color = BLACK;
                    case TREE -> color = GREEN;
                    case QUARRY -> color = BROWN;
                    case PLAYER -> color = RED;
                    case FORAGING -> color = BRIGHT_GREEN;
                    case ROCK -> color = STONE_GRAY;
                    case SHACK -> color = SHACK_BROWN;
                    case GREENHOUSE -> color = GREENHOUSE_GREEN;
                    case SEBASTIAN_HOUSE -> color = "\u001B[35m";
                    case ABIGAIL_HOUSE -> color = "\u001B[95m";   // Pink
                    case HARVEY_HOUSE -> color = "\u001B[36m";    // Cyan
                    case LEAH_HOUSE -> color = "\u001B[32m";      // Green
                    case ROBIN_HOUSE -> color = "\u001B[33m";
                    case SEBASTIAN_NPC -> color = "\u001B[35m";  // بنفش
                    case ABIGAIL_NPC -> color = "\u001B[95m";   // صورتی
                    case HARVEY_NPC -> color = "\u001B[36m";    // آبی روشن
                    case LEAH_NPC -> color = "\u001B[32m";      // سبز
                    case ROBIN_NPC -> color = "\u001B[33m";     // زرد// Yellow
                    default -> color = RESET;
                }

                System.out.print(color + tile.getSymbol() + RESET + " ");
            }
            System.out.println();
        }
    }
}
