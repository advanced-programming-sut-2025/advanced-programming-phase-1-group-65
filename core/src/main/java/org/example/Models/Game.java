package org.example.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.example.Controllers.NPCController.NPCController;
import org.example.Models.Enums.AnimalType;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.TileType;
import org.example.Models.Shops.FishManager;
import org.example.Views.GameView.GameScreen;
import org.example.Views.GameView.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public NPCController npcController;
    public Map map = null;
    public ArrayList<ArrayList<Tile>> Map = null;
    public Map mapclone = null;
    public ArrayList<ArrayList<Tile>> MapClone = null;
    public int timesLoaded = 0;
    public ArrayList<Trees> AllTrees = new ArrayList<>();
    public ArrayList<Foraging> AllCrops = new ArrayList<>();
    public ArrayList<Foraging> AllCropInfo = new ArrayList<>();
    public ArrayList<Trees> AllTreesInfo = new ArrayList<>();
    public ArrayList<Rock> AllRocksInfo = new ArrayList<>();
    public ArrayList<Animal> AllAnimalInfo = new ArrayList<>();
    public ArrayList<Recipe> AllRecipes = new ArrayList<>();
    public java.util.Map<TileType, Shop> shops = new java.util.HashMap<>();
    public GameScreen gameScreen;
    public User user1 = null;
    public User user2 = null;
    public User user3 = null;
   public List<User> users;

    public Player currentPlayer = null;
    public Player MainPlayer = null;

    public GameClock gameClock;
    public WeatherSystem weatherSystem;

    public Game(User user1, User user2, User user3) {
        this.user1 = user1;
        this.user2 = user2;
        this.user3 = user3;
        this.users = List.of(user1, user2, user3);
        this.gameClock = new GameClock();
        this.weatherSystem = new WeatherSystem("spring");
        npcController = new NPCController(this);

    }
    public void creatAllRecipes(Game game) {
        Recipe FriedEgg = new Recipe("Fried Egg","1 Egg");
        game.AllRecipes.add(FriedEgg);
        Recipe BakedFish = new Recipe("Baked Fish","1 Sardine + 1 Salmon + 1 Wheat");
        game.AllRecipes.add(BakedFish);
        Recipe Salad = new Recipe("Salad","1 Leek + 1 Dandelion");
        game.AllRecipes.add(Salad);
        Recipe Omelet = new Recipe("Omelet" , "1 Egg + 1 Milk");
        game.AllRecipes.add(Omelet);
        Recipe PumpkinPie = new Recipe("Pumpkin Pie" , "1 Pumpkin + 1 Wheat Flour + 1 Milk + 1 Sugar");
        game.AllRecipes.add(PumpkinPie);
        Recipe Spaghetti = new Recipe( "Spaghetti" , "1 Wheat Flour + 1 Tomato");
        game.AllRecipes.add(Spaghetti);
        Recipe Pizza = new Recipe( "Pizza" , "1 Wheat Flour + 1 Tomato + 1 Cheese");
        game.AllRecipes.add(Pizza);
        Recipe Tortilla = new Recipe( "Tortilla" , "1 Corn");
        game.AllRecipes.add(Tortilla);
        Recipe MakiRoll = new Recipe("Maki Roll" , "1 Any Fish + 1 Rice + 1 Fiber");
        game.AllRecipes.add(MakiRoll);
        Recipe TripleShotEspresso = new Recipe("Triple Shot Espresso", "3 Coffee");
        game.AllRecipes.add(TripleShotEspresso);
        Recipe Cookie = new Recipe("Cookie", "1 Wheat Flour + 1 Sugar + 1 Egg");
        game.AllRecipes.add(Cookie);
        Recipe HashBrowns = new Recipe("Hash Browns","1 Potato + 1 Oil");
        game.AllRecipes.add(HashBrowns);
        Recipe Pancakes = new Recipe("Pancakes" , "1 Wheat Flour + 1 Egg");
        game.AllRecipes.add(Pancakes);
        Recipe FruitSalad = new Recipe("Fruit Salad" , "1 Blueberry + 1 Melon + 1 Apricot");
        game.AllRecipes.add(FruitSalad);
        Recipe RedPlate = new Recipe("Red Plate" , "1 Red Cabbage + 1 Radish");
        game.AllRecipes.add(RedPlate);
        Recipe Bread = new Recipe("Bread" , "1 Wheat Flour");
        game.AllRecipes.add(Bread);
        Recipe SalmonDinner = new Recipe("Salmon Dinner" , "1 Salmon + 1 Amaranth + 1 Kale");
        game.AllRecipes.add(SalmonDinner);
        Recipe VegetableMedley = new Recipe("Vegetable Medley" , "1 Tomato + 1 beet");
        game.AllRecipes.add(VegetableMedley);
        Recipe FarmerLunch = new Recipe("Farmer Lunch" , "1 Omelet + 1 Parsnip");
        game.AllRecipes.add(FarmerLunch);
        Recipe SurvivalBurger = new Recipe("Survival Burger" , "1 Bread + 1 Carrot + 1 Eggplant");
        game.AllRecipes.add(SurvivalBurger);
        Recipe DishOfTheSea = new Recipe("Dish Of The Sea", "2 Sardines + 1 Hash Browns");
        game.AllRecipes.add(DishOfTheSea);
        Recipe SeaFormPudding = new Recipe("Seaform Pudding" , "1 Flounder + 1 Midnight Carp ");
        game.AllRecipes.add(SeaFormPudding);
        Recipe MinerTreat = new Recipe("Miner Treat" , "1 Milk + 1 Sugar + 2 Carrot");
        game.AllRecipes.add(MinerTreat);
    }

    public void creatAllAnimals(Game game){
        Food ChickenEgg = new Food(3,ItemSubType.EGG,"Chicken Egg",60,50,true);
        Food ChickenBigEgg = new Food(1,ItemSubType.EGG,"Chicken Big Egg",105,95,true);
        Animal Chicken = new Animal("Chicken", AnimalType.CHICKEN,1,800);
        Chicken.foodProduct.add(ChickenEgg);
        Chicken.foodProduct.add(ChickenBigEgg);
        game.AllAnimalInfo.add(Chicken);

        Food DuckEgg = new Food(3,ItemSubType.EGG,"Duck Egg",60,95,true);
        Food DuckBigEgg = new Food (1,ItemSubType.EGG,"Duck Big Egg",105,250,true);
        Animal Duck = new Animal("Duck", AnimalType.DUCK,2,1200);
        Duck.foodProduct.add(DuckEgg);
        Duck.foodProduct.add(DuckBigEgg);
        game.AllAnimalInfo.add(Duck);

        Material RabbitFur = new Material(5,ItemSubType.FURR,"Rabbit Furr",340);
        Animal Rabbit = new Animal("Rabbit", AnimalType.RABBIT,4,8000);
        Rabbit.materialProduct.add(RabbitFur);
        game.AllAnimalInfo.add(Rabbit);

        Food DinosaurEgg = new Food(1,ItemSubType.EGG,"Dinosaur Egg",200,350,true);
        Animal Dinosaur = new Animal("Dinosaur", AnimalType.DINOSAUR,7,14000);
        Dinosaur.foodProduct.add(DinosaurEgg);
        game.AllAnimalInfo.add(Dinosaur);

        Food CowMilk = new Food(2,ItemSubType.MILK,"Cow Milk", 110,125,true);
        Food CowBigMilk = new Food(1,ItemSubType.MILK,"Cow Big Milk",130,190,true);
        Animal Cow = new Animal("Cow", AnimalType.COW,1,1500);
        Cow.foodProduct.add(CowMilk);
        Cow.foodProduct.add(CowBigMilk);
        game.AllAnimalInfo.add(Cow);

        Food GoatMilk = new Food(2,ItemSubType.MILK,"Goat Milk",95,225,true);
        Food GoatBigMilk = new Food(1,ItemSubType.MILK,"Goat Big Milk",160,345,true);
        Animal Goat = new Animal("Goat", AnimalType.GOAT,2,4000);
        Goat.foodProduct.add(GoatMilk);
        Goat.foodProduct.add(GoatBigMilk);
        game.AllAnimalInfo.add(Goat);

        Material SheepFurr = new Material(3,ItemSubType.FURR,"Sheep Furr",340);
        Animal Sheep = new Animal("Sheep", AnimalType.SHEEP,3,8000);
        Sheep.materialProduct.add(SheepFurr);
        game.AllAnimalInfo.add(Sheep);

        Food Truffle = new Food(5,ItemSubType.TRUFFLE,"Truffle",10,620,true);
        Animal Pig = new Animal("Pig", AnimalType.PIG,0,16000);
        Pig.foodProduct.add(Truffle);
        game.AllAnimalInfo.add(Pig);


    }

    public void createAllTrees(Game game){
        Material ApricotSapling = new Material(1,ItemSubType.SEED,"Apricot Sapling",2000);
        ApricotSapling.texture = new Texture("Material/Apricot_Sapling.png");
        Food Apricot = new Food(4,ItemSubType.FRUIT,"Apricot",38,59,true);
        Apricot.texture = new Texture("Material/Apricot.png");
        Trees ApricotTree = new Trees("Apricot Tree",Apricot,1,1,ApricotSapling);
        ApricotTree.texture1 = new Texture("Trees/Apricot_Stage_5.png");
        ApricotTree.texture2 = new Texture("Trees/Apricot_Stage_5_Fruit.png");
        game.AllTreesInfo.add(ApricotTree);


        Material MapleSeeds = new Material(1,ItemSubType.SEED,"Maple Seeds",3000);
        MapleSeeds.texture = new Texture("Material/Maple_Seed.png");
        Food MapleSyrup = new Food(2,ItemSubType.SYRUP,"Maple Syrup",0,200,false);
        MapleSyrup.texture = new Texture("Material/Maple_Syrup.png");
        Trees MapleTree = new Trees("Maple Tree",MapleSyrup,1234,9,MapleSeeds);
        MapleTree.texture1 = new Texture("Trees/Maple_Stage_5.png");
        MapleTree.texture2 = new Texture("Trees/Maple_Stage_5_Fruit.png");
        game.AllTreesInfo.add(MapleTree);

        Material PineCones = new Material(1,ItemSubType.SEED,"Pine Cones",4000);
        PineCones.texture = new Texture("Material/Pine_Cone.png");
        Food PineTar = new Food(4,ItemSubType.SYRUP,"Pine Tar",0,100,false);
        PineTar.texture = new Texture("Material/Pine_Tar.png");
        Trees PineTree = new Trees("Pine Tree",PineTar,1234,5,PineCones);
        PineTree.texture1 = new Texture("Trees/Pine_Stage_5.png");
        PineTree.texture2 = new Texture("Trees/Pine_Stage_5_Fruit.png");
        game.AllTreesInfo.add(PineTree);

        Material MahoganySeeds = new Material(1,ItemSubType.SEED,"Mahogany Seeds",40000);
        MahoganySeeds.texture = new Texture("Material/Mahogany_Seed.png");
        Food Sap = new Food(4,ItemSubType.SYRUP,"Sap",-2,1000,false);
        Sap.texture = new Texture("Material/Sap.png");
        Trees MahoganyTree = new Trees("Mahogany Tree",Sap,1234,1,MahoganySeeds);
        MahoganyTree.texture1 = new Texture("Trees/Mahogany_Stage_5.png");
        MahoganyTree.texture2 = new Texture("Trees/Mahogany_Stage_5_Fruit.png");
        game.AllTreesInfo.add(MahoganyTree);

        Material MushroomSeeds = new Material(1,ItemSubType.SEED,"Mushroom Tree Seeds",1500);
        MushroomSeeds.texture = new Texture("Material/Mushroom_Tree_Seed.png");
        Food CommonMushroom = new Food(4,ItemSubType.FRUIT,"Common Mushroom",38,40,true);
        CommonMushroom.texture = new Texture("Material/Common_Mushroom.png");
        Trees MushroomTree = new Trees("Mushroom Tree",CommonMushroom,1,1,MushroomSeeds);
        MushroomTree.texture1 = new Texture("Trees/Mushroom_Stage_5.png");
        MushroomTree.texture2 = new Texture("Trees/Mushroom_Stage_5_Fruit.png");
        game.AllTreesInfo.add(MushroomTree);

        Material MysticTreeSeeds = new Material(1,ItemSubType.SEED,"Mystic Tree Seeds",10000);
        MysticTreeSeeds.texture = new Texture("Material/Mystic_Tree_Seed.png");
        Food MysticSyrup = new Food(4,ItemSubType.SYRUP,"Mystic Syrup",-2,1000,true);
        MysticSyrup.texture = new Texture("Material/Mystic_Syrup.png");
        Trees MysticTree = new Trees("Mystic Tree",MysticSyrup,1234,7,MysticTreeSeeds);
        MysticTree.texture1 = new Texture("Trees/Mystic_Tree_Stage_4.png");
        MysticTree.texture2 = new Texture("Trees/Mystic_Tree_Stage_5.png");
        game.AllTreesInfo.add(MysticTree);


    }
    public void creatAllRocks(Game game) {


        Material Diamond = new Material(1,ItemSubType.DIAMOND,"Diamond",750);
        Diamond.texture = new Texture(Gdx.files.internal("Rocks/Diamond.png"));
        Rock DiamondRock = new Rock(Diamond);
        game.AllRocksInfo.add(DiamondRock);


        Material Gold = new Material(1,ItemSubType.GOLD,"Gold Ore",25);
        Gold.texture= new Texture("Rocks/Gold_Ore.png");
        Rock GoldRock = new Rock(Gold);
        game.AllRocksInfo.add(GoldRock);

        Material Coal = new Material(1,ItemSubType.COAL,"Coal",15);
        Coal.texture= new Texture("Rocks/Coal.png");
        Rock CoalRock = new Rock(Coal);
        game.AllRocksInfo.add(CoalRock);

        Material Copper = new Material(1,ItemSubType.COOPER,"Copper Ore",5);
        Copper.texture= new Texture("Rocks/Copper_Ore.png");
        Rock CopperRock = new Rock(Copper);
        game.AllRocksInfo.add(CopperRock);

        Material Iron = new Material(1,ItemSubType.IRON,"Iron Ore",10);
        Iron.texture= new Texture("Rocks/Iron_Ore.png");
        Rock IronRock = new Rock(Iron);
        game.AllRocksInfo.add(IronRock);

        Material Iriduim = new Material(1,ItemSubType.IRIDUIM,"Iriduim Ore",100);
        Iriduim.texture= new Texture("Rocks/Iridium_Ore.png");
        Rock IriduimRock = new Rock(Iriduim);
        game.AllRocksInfo.add(IriduimRock);

    }
    public void creatAllCrops(Game game) {

        Material CarrotSeeds = new Material(1, ItemSubType.SEED, "Carrot Seeds",35);
        Food Carrot = new Food(3, ItemSubType.FRUIT, "Carrot", 75, 35, true);
        Foraging CarrotCrop = new Foraging(111, "Carrot", 1, false, Carrot, true, CarrotSeeds,0);
        game.AllCropInfo.add(CarrotCrop);


        Material ParsnipSeeds = new Material(1, ItemSubType.SEED, "Parsnip Seeds",35);
        Food Parsnip = new Food(3, ItemSubType.FRUIT, "Parsnip", 25, 35, true);
        Foraging ParsnipCrop = new Foraging(1111, "Parsnip", 1, false, Parsnip, true, ParsnipSeeds,0);
        game.AllCropInfo.add(ParsnipCrop);





        Material SpainSeeds = new Material(1, ItemSubType.SEED, "Spain Seeds",90);
        Food SummerSpangle = new Food(3, ItemSubType.FRUIT, "Summer Spangle", 45, 90, true);
        Foraging SummerSpangleCrop = new Foraging(1231,"Summer Spangle",2,false,SummerSpangle,true,SpainSeeds,0);
        game.AllCropInfo.add(SummerSpangleCrop);

        Material SummerSquashSeeds = new Material(1, ItemSubType.SEED, "Summer Squash Seeds",45);
        Food SummerSquash = new Food(3,ItemSubType.FRUIT, "Summer Squash", 63, 45, true);
        Foraging SummerSquashCrop = new Foraging(11121,"Summer Squash",2,false,SummerSquash,false,SummerSquashSeeds,3);
        game.AllCropInfo.add(SummerSquashCrop);

        Material SunflowerSeeds = new Material(1, ItemSubType.SEED, "Sunflower Seeds",80);
        Food Sunflower = new Food(3, ItemSubType.FRUIT, "Sunflower", 45, 80, true);
        Foraging SunflowerCrop = new Foraging(1232,"Sunflower",23,false,Sunflower,true,SunflowerSeeds,0);
        game.AllCropInfo.add(SunflowerCrop);

        Material TomatoSeeds = new Material(1, ItemSubType.SEED, "Tomato Seeds",60);
        Food Tomato = new Food(3, ItemSubType.FRUIT, "Tomato", 20, 60, true);
        Foraging TomatoCrop = new Foraging(22233,"Tomato",2,false,Tomato,false,TomatoSeeds,4);
        game.AllCropInfo.add(TomatoCrop);

        Material WheatSeeds = new Material(1, ItemSubType.SEED, "Wheat Seeds",25);
        Food Wheat = new Food(3, ItemSubType.FRUIT, "Wheat", 0, 25, false);
        Foraging WheatCrop = new Foraging(1111,"Wheat",23,false,Wheat,true,WheatSeeds,0);
        game.AllCropInfo.add(WheatCrop);



        Material NoSeed = new Material(1, ItemSubType.SEED, "No Seeds",0);

        Food CommonMushroom  = new Food(2,ItemSubType.FRUIT,"Common Mushroom",38,40,true);
        Foraging CommonMushroomCrop = new Foraging(0,"Common Mushroom",1234,true,CommonMushroom,true,NoSeed,0);
        game.AllCropInfo.add(CommonMushroomCrop);


        Food Blackberry  = new Food(2,ItemSubType.FRUIT,"Blackberry",25,25,true);
        Foraging BlackberryCrop = new Foraging(0,"Blackberry",3,true,Blackberry,true,NoSeed,0);
        game.AllCropInfo.add(BlackberryCrop);

        Food Chanterelle  = new Food(2,ItemSubType.FRUIT,"Chanterelle",75,160,true);
        Foraging ChanterelleCrop = new Foraging(0,"Chanterelle",3,true,Chanterelle,true,NoSeed,0);
        game.AllCropInfo.add(ChanterelleCrop);

        Food Hazelnut  = new Food(2,ItemSubType.FRUIT,"Hazelnut",38,40,true);
        Foraging HazelnutCrop = new Foraging(0,"Hazelnut",3,true,Hazelnut,true,NoSeed,0);
        game.AllCropInfo.add(HazelnutCrop);


    }



    public void GameRun() {
        //Scanner sc = new Scanner(System.in);

        if (timesLoaded == 0) {
            map = new Map();
         // پایینی اری لیست درسته
            Map = map.buildMap(this);

            mapclone = new Map();

            MapClone = mapclone.cloneMap(this);
            creatAllCrops(this);
            createAllTrees(this);
            creatAllRocks(this);
            creatAllAnimals(this);
            creatAllRecipes(this);
            map.generateShippingBins(Map,this);
            FishManager.createAllFish();
            map.GenerateRandomForagingDaily(Map,this);
            map.GenerateRandomRockDaily(Map,this);


            for (User user : users) {
                if (user.player.getFarmNumber() == 1) {
                    user.player.PositionX = 20;
                    user.player.PositionY = 15;
                } else if (user.player.getFarmNumber() == 2) {
                    user.player.PositionX = 120;
                    user.player.PositionY = 15;
                } else if (user.player.getFarmNumber() == 3) {
                    user.player.PositionX = 20;
                    user.player.PositionY = 95;
                } else if (user.player.getFarmNumber() == 4) {
                    user.player.PositionX = 120;
                    user.player.PositionY = 95;
                }

                user.player.game.Map.get(user.player.PositionY).set(user.player.PositionX, new Tile(TileType.PLAYER));
                user.player.items.add(new Tool(1, ItemSubType.AXE,"Axe",0,new Texture(Gdx.files.internal("Tools/Axe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.WATERINGCAN,"WateringCan",0,new Texture(Gdx.files.internal("Tools/Watering_Can.png"))));
                user.player.items.add(new Tool(1, ItemSubType.FISHINGPOLE,"FishingPole",0,new Texture(Gdx.files.internal("Tools/Bamboo_Pole.png"))));
                user.player.items.add(new Tool(1, ItemSubType.SCYTHE,"Scythe",0,new Texture(Gdx.files.internal("Tools/Scythe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.MILKPAIL,"MilkPail",0,new Texture(Gdx.files.internal("Tools/Milk_Pail.png"))));
                user.player.items.add(new Tool(1, ItemSubType.SHEAR,"Shear",0,new Texture(Gdx.files.internal("Tools/Shears.png"))));
                user.player.items.add(new Tool(1, ItemSubType.HOE,"Hoe",0,new Texture(Gdx.files.internal("Tools/Hoe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.PICKAXE,"Pickaxe",0,new Texture(Gdx.files.internal("Tools/Pickaxe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.AXE,"Axe",0,new Texture(Gdx.files.internal("Tools/Axe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.WATERINGCAN,"WateringCan",0,new Texture(Gdx.files.internal("Tools/Watering_Can.png"))));
                user.player.items.add(new Tool(1, ItemSubType.FISHINGPOLE,"FishingPole",0,new Texture(Gdx.files.internal("Tools/Bamboo_Pole.png"))));
                user.player.items.add(new Tool(1, ItemSubType.SCYTHE,"Scythe",0,new Texture(Gdx.files.internal("Tools/Scythe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.MILKPAIL,"MilkPail",0,new Texture(Gdx.files.internal("Tools/Milk_Pail.png"))));
                user.player.items.add(new Tool(1, ItemSubType.SHEAR,"Shear",0,new Texture(Gdx.files.internal("Tools/Shears.png"))));
                user.player.items.add(new Tool(1, ItemSubType.HOE,"Hoe",0,new Texture(Gdx.files.internal("Tools/Hoe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.PICKAXE,"Pickaxe",0,new Texture(Gdx.files.internal("Tools/Pickaxe.png")))); user.player.items.add(new Tool(1, ItemSubType.AXE,"Axe",0,new Texture(Gdx.files.internal("Tools/Axe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.WATERINGCAN,"WateringCan",0,new Texture(Gdx.files.internal("Tools/Watering_Can.png"))));
                user.player.items.add(new Tool(1, ItemSubType.FISHINGPOLE,"FishingPole",0,new Texture(Gdx.files.internal("Tools/Bamboo_Pole.png"))));
                user.player.items.add(new Tool(1, ItemSubType.SCYTHE,"Scythe",0,new Texture(Gdx.files.internal("Tools/Scythe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.MILKPAIL,"MilkPail",0,new Texture(Gdx.files.internal("Tools/Milk_Pail.png"))));
                user.player.items.add(new Tool(1, ItemSubType.SHEAR,"Shear",0,new Texture(Gdx.files.internal("Tools/Shears.png"))));
                user.player.items.add(new Tool(1, ItemSubType.HOE,"Hoe",0,new Texture(Gdx.files.internal("Tools/Hoe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.PICKAXE,"Pickaxe",0,new Texture(Gdx.files.internal("Tools/Pickaxe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.AXE,"Axe",0,new Texture(Gdx.files.internal("Tools/Axe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.WATERINGCAN,"WateringCan",0,new Texture(Gdx.files.internal("Tools/Watering_Can.png"))));
                user.player.items.add(new Tool(1, ItemSubType.FISHINGPOLE,"FishingPole",0,new Texture(Gdx.files.internal("Tools/Bamboo_Pole.png"))));
                user.player.items.add(new Tool(1, ItemSubType.SCYTHE,"Scythe",0,new Texture(Gdx.files.internal("Tools/Scythe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.MILKPAIL,"MilkPail",0,new Texture(Gdx.files.internal("Tools/Milk_Pail.png"))));
                user.player.items.add(new Tool(1, ItemSubType.SHEAR,"Shear",0,new Texture(Gdx.files.internal("Tools/Shears.png"))));
                user.player.items.add(new Tool(1, ItemSubType.HOE,"Hoe",0,new Texture(Gdx.files.internal("Tools/Hoe.png"))));
                user.player.items.add(new Tool(1, ItemSubType.PICKAXE,"Pickaxe",0,new Texture(Gdx.files.internal("Tools/Pickaxe.png"))));
                user.player.KnownRecipes.add(AllRecipes.get(0));
                user.player.KnownRecipes.add(AllRecipes.get(1));
                user.player.KnownRecipes.add(AllRecipes.get(2));
                //user.player.items.add(new Material(1, ItemSubType.SEED,"Test Seed",25));

                //tester

             /* user.player.items.add(new Material(1, ItemSubType.SEED,"Test Seed",25));
                user.player.items.add(new Material(1000,ItemSubType.WOOD,"Wood",10));
                user.player.items.add(new Material(1000,ItemSubType.STONE,"Stone",10));
                user.player.items.add(new Material(1000,ItemSubType.HAY,"Hay",10));
                user.player.items.add(new Material(1,ItemSubType.FISH,"Sardine",10));
                user.player.items.add(new Material(1,ItemSubType.FIBER,"Fiber",10));
                user.player.items.add(new Material(1,ItemSubType.UNKNOWN,"Rice",10));
*/
                user.player.game.Map.get(user.player.PositionY)
                        .set(user.player.PositionX, new Tile(TileType.PLAYER));
            }
        }


        GameView gameView = new GameView();
       // gameView.check(sc, this);
    }


}
