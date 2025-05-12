package org.example.Models;

import com.sun.source.tree.Tree;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.TileSubType;
import org.example.Models.Enums.TileType;
import org.example.Models.Enums.TileType.*;

import java.util.ArrayList;
import java.util.Random;

public class Map {


    public void generateRandomObjects(ArrayList<ArrayList<Tile>> MapArrayList,Game game) {
        // generating random objects for farm number 1
        generateRandomTrees(MapArrayList, 0,0, 39, 29,game);
        //generateRandomForagings(MapArrayList, 0, 0, 39, 29,game);
        generateRandomRocks(MapArrayList, 0, 0, 39, 29);

        // generating random objects for farm number 2
        generateRandomTrees(MapArrayList, 100, 0, 139, 29,game);
       // generateRandomForagings(MapArrayList, 100, 0, 139, 29,game);
        generateRandomRocks(MapArrayList, 100, 0, 139, 29);

        // generating random objects for farm number 3
        generateRandomTrees(MapArrayList, 0, 82, 39, 111,game);
        //generateRandomForagings(MapArrayList, 0, 82, 39, 111,game);
        generateRandomRocks(MapArrayList, 0, 82, 39, 111);

        // generating random objects for farm number 4
        generateRandomTrees(MapArrayList, 100, 82, 139, 111,game);
       // generateRandomForagings(MapArrayList, 100, 82, 139, 111,game);
        generateRandomRocks(MapArrayList, 100, 82, 139, 111);
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
            tree = new Trees("Wild",null,5);
            tree.seed = seed;
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
                     seed = new Material(1,ItemSubType.SEED,"Apricot Sapling");
                    break;
                case 1:
                    seed = new Material(1,ItemSubType.SEED,"Cherry Sapling");
                    break;
                case 2:
                    seed = new Material(1,ItemSubType.SEED,"Mystic Tree Seeds");
                    break;
                case 3:
                    seed = new Material(1,ItemSubType.SEED,"Mushroom Tree Seeds");
                    break;
                case 4:
                    seed = new Material(1,ItemSubType.SEED,"Mahogany Tree Seeds");
                    break;
                case 5:
                    seed = new Material(1,ItemSubType.SEED,"Pine Tree Seeds");
                    break;
                 case 6:
                     seed = new Material(1,ItemSubType.SEED,"Maple Tree Seeds");
                     break;
                     case 7:
                         seed = new Material(1,ItemSubType.SEED,"Oak Tree Seeds");
                         break;
                default:
                    seed = new Material(1,ItemSubType.SEED,"Maple Tree Seeds");
            }

        }
        else if(game.gameClock.getCurrentSeasonIndex()==2){
            String[] Trees = {"Banana Tree","Mango Tree","Mystic Tree", "Mushroom Tree", "Mahogany Tree", "Pine Tree"
                    ,"Maple Tree", "Oak Tree","Orange Tree","Peach Tree"};
            Random rand = new Random();
            int index = rand.nextInt(Trees.length);
            switch (index){
                case 0:
                    seed = new Material(1,ItemSubType.SEED,"Banana Sapling");
                    break;
                case 1:
                    seed = new Material(1,ItemSubType.SEED,"Mango Sapling");
                    break;
                case 2:
                    seed = new Material(1,ItemSubType.SEED,"Mystic Tree Seeds");
                    break;
                case 3:
                    seed = new Material(1,ItemSubType.SEED,"Mushroom Tree Seeds");
                    break;
                case 4:
                    seed = new Material(1,ItemSubType.SEED,"Mahogany Tree Seeds");
                    break;
                case 5:
                    seed = new Material(1,ItemSubType.SEED,"Pine Tree Seeds");
                    break;
                case 6:
                    seed = new Material(1,ItemSubType.SEED,"Maple Tree Seeds");
                    break;
                case 7:
                    seed = new Material(1,ItemSubType.SEED,"Oak Tree Seeds");
                    break;
                    case 8:
                        seed = new Material(1,ItemSubType.SEED,"Orange Sapling");
                        break;
                        case 9:
                            seed = new Material(1,ItemSubType.SEED,"Peach Sapling");
                            break;
                default:
                    seed = new Material(1,ItemSubType.SEED,"Banana Sapling");


            }
        }
        else if(game.gameClock.getCurrentSeasonIndex()==3){
            String[] Trees = {"Apple Tree", "Pomegranate Tree", "Mystic Tree", "Mushroom Tree", "Mahogany Tree", "Pine Tree"
                    ,"Maple Tree", "Oak Tree"};
            Random rand = new Random();
            int index = rand.nextInt(Trees.length);
            switch (index){
                case 0:
                    seed = new Material(1,ItemSubType.SEED,"Apple Sapling");
                    break;
                case 1:
                    seed = new Material(1,ItemSubType.SEED,"Pomegranate Sapling");
                    break;
                case 2:
                    seed = new Material(1,ItemSubType.SEED,"Mystic Tree Seeds");
                    break;
                case 3:
                    seed = new Material(1,ItemSubType.SEED,"Mushroom Tree Seeds");
                    break;
                case 4:
                    seed = new Material(1,ItemSubType.SEED,"Mahogany Tree Seeds");
                    break;
                case 5:
                    seed = new Material(1,ItemSubType.SEED,"Pine Tree Seeds");
                    break;
                case 6:
                    seed = new Material(1,ItemSubType.SEED,"Maple Tree Seeds");
                    break;
                case 7:
                    seed = new Material(1,ItemSubType.SEED,"Oak Tree Seeds");
                    break;
                default:
                    seed = new Material(1,ItemSubType.SEED,"Pine Tree Seeds");
            }

        }
        else if(game.gameClock.getCurrentSeasonIndex()==4){
            String[] Trees = {"Mystic Tree", "Mushroom Tree", "Mahogany Tree", "Pine Tree"
                    ,"Maple Tree", "Oak Tree"};
            Random rand = new Random();
            int index = rand.nextInt(Trees.length);
            switch (index){

                case 0:
                    seed = new Material(1,ItemSubType.SEED,"Mystic Tree Seeds");
                    break;
                case 1:
                    seed = new Material(1,ItemSubType.SEED,"Mushroom Tree Seeds");
                    break;
                case 2:
                    seed = new Material(1,ItemSubType.SEED,"Mahogany Tree Seeds");
                    break;
                case 3:
                    seed = new Material(1,ItemSubType.SEED,"Pine Tree Seeds");
                    break;
                case 4:
                    seed = new Material(1,ItemSubType.SEED,"Maple Tree Seeds");
                    break;
                case 5:
                    seed = new Material(1,ItemSubType.SEED,"Oak Tree Seeds");
                    break;
                    default:
                        seed = new Material(1,ItemSubType.SEED,"Oak Tree Seeds");
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
    public Foraging getForaging(Game game){
        if (game.gameClock.getCurrentSeasonIndex()==1){

        }
        return null;
    }
    public void GenerateRandomForagingDaily(ArrayList<ArrayList<Tile>> Map, Game game) {
        ArrayList<Foraging> foragingCrops = new ArrayList<>();
        ArrayList<Foraging> foragingSeeds=new ArrayList<>();
        Random rand = new Random();
        int chance = 1;
        int selected = 1;
        for (Foraging crop : game.AllCropInfo){
            if(crop.isHarvestable && crop.Season==game.gameClock.getCurrentSeasonIndex()){
                foragingCrops.add(crop);
            }
            else if(!(crop.isHarvestable && crop.Season!=game.gameClock.getCurrentSeasonIndex())) {
                foragingSeeds.add(crop);
            }
        }
        for (int y = 0; y < 29 ; y++) {
            for (int x = 0; x < 39; x++){
                chance = rand.nextInt(100);
                if (Map.get(y).get(x).getType() == TileType.EMPTY) {
                    selected = rand.nextInt(foragingCrops.size());

                    if (chance ==0) {
                        Map.get(y).set(x, foragingCrops.get(selected));
                    }
                }
                else if(Map.get(y).get(x).getType()==TileType.FERTILE){
                    selected = rand.nextInt(foragingSeeds.size());

                    if (chance ==0) {
                        Map.get(y).set(x, foragingSeeds.get(selected));
                        game.AllCrops.add(foragingSeeds.get(selected));
                    }
                }
            }
        }
        for (int y = 0; y < 29 ; y++) {
            for (int x = 100; x < 139; x++){
                chance = rand.nextInt(100);
                if (Map.get(y).get(x).getType() == TileType.EMPTY) {
                    selected = rand.nextInt(foragingCrops.size());

                    if (chance ==0) {
                        Map.get(y).set(x, foragingCrops.get(selected));
                    }
                }
                else if(Map.get(y).get(x).getType()==TileType.FERTILE){
                    selected = rand.nextInt(foragingSeeds.size());

                    if (chance ==0) {
                        Map.get(y).set(x, foragingSeeds.get(selected));
                        game.AllCrops.add(foragingSeeds.get(selected));
                    }
                }
            }
        }
        for (int y = 83; y < 111 ; y++) {
            for (int x = 0; x < 39; x++){
                chance = rand.nextInt(100);
                if (Map.get(y).get(x).getType() == TileType.EMPTY) {
                    selected = rand.nextInt(foragingCrops.size());

                    if (chance ==0) {
                        Map.get(y).set(x, foragingCrops.get(selected));
                    }
                }
                else if(Map.get(y).get(x).getType()==TileType.FERTILE){
                    selected = rand.nextInt(foragingSeeds.size());

                    if (chance ==0) {
                        Map.get(y).set(x, foragingSeeds.get(selected));
                        game.AllCrops.add(foragingSeeds.get(selected));
                    }
                }
            }
        }
        for (int y = 83; y < 111 ; y++) {
            for (int x = 100; x < 139; x++){
                chance = rand.nextInt(100);
                if (Map.get(y).get(x).getType() == TileType.EMPTY) {
                    selected = rand.nextInt(foragingCrops.size());

                    if (chance ==0) {
                        Map.get(y).set(x, foragingCrops.get(selected));
                    }
                }
                else if(Map.get(y).get(x).getType()==TileType.FERTILE){
                    selected = rand.nextInt(foragingSeeds.size());

                    if (chance ==0) {
                        Map.get(y).set(x, foragingSeeds.get(selected));
                        game.AllCrops.add(foragingSeeds.get(selected));
                    }
                }
            }
        }

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
            , ".................................W.W.........W................................................W.........W.W................................."
//ROW 36
            , ".................................W.W.........W................................................W.........W.W................................."
//ROW 37
            , ".................................W.W.........W................................................W.........W.W................................."
//ROW 38
            , ".................................W.W.........W................................................W.........W.W................................."
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
            , ".............................................W................................................W............................................."
// ROW 49
            , ".............................................W................................................W............................................."
// ROW 50
            , ".............................................W................................................W............................................."
// ROW 51
            , ".............................................W................................................W............................................."
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
            , "W.....LLLLLLLLLL....LLLLLLLLLL.........W............................................................WCCCC........................QQQQQQQQQQW"
// ROW 85
            , "W.....LLLLLLLLLL....LLLLLLLLLL.........W............................................................WCCCC........................QQQQQQQQQQW"
// ROW 86
            , "W.....LLLLLLLLLL....LLLLLLLLLL.........W............................................................WCCCC........................QQQQQQQQQQW"
// ROW 87
            , "W.....LLLLLLLLLL....LLLLLLLLLL.........W............................................................WCCCC........................QQQQQQQQQQW"
// ROW 90
            , "W......................................W............................................................W............................QQQQQQQQQQW"
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
        for (ArrayList<Tile> row : map) {
            for (Tile tile : row) {
                System.out.print(tile.getSymbol() + " ");
            }
            System.out.println();
        }
    }

}