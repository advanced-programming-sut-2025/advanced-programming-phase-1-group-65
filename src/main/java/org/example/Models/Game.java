package org.example.Models;

import org.example.Models.Enums.AnimalType;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.TileType;
import org.example.Models.Shops.FishManager;
import org.example.Views.GameView.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
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
        Food Apricot = new Food(4,ItemSubType.FRUIT,"Apricot",38,59,true);
        Trees ApricotTree = new Trees("Apricot Tree",Apricot,1,1,ApricotSapling);
        game.AllTreesInfo.add(ApricotTree);

        Material CherrySapling = new Material(1,ItemSubType.SEED,"Cherry Sapling",3400);
        Food Cherry = new Food(4,ItemSubType.FRUIT,"Apricot",38,80,true);
        Trees CherryTree = new Trees("Cherry Tree",Apricot,1,1,CherrySapling);
        game.AllTreesInfo.add(CherryTree);

        Material BananaSapling = new Material(1,ItemSubType.SEED,"Banana Sapling",2000);
        Food Banana = new Food(4,ItemSubType.FRUIT,"Banana",75,150,true);
        Trees BananaTree = new Trees("Banana Tree",Banana,2,1,BananaSapling);
        game.AllTreesInfo.add(BananaTree);

        Material MangoSapling = new Material(1,ItemSubType.SEED,"Mango Sapling",2000);
        Food Mango = new Food(4,ItemSubType.FRUIT,"Mango",100,130,true);
        Trees MangoTree = new Trees("Mango Tree",Mango,2,1,MangoSapling);
        game.AllTreesInfo.add(MangoTree);

        Material OrangeSapling = new Material(1,ItemSubType.SEED,"Orange Sapling",4000);
        Food Orange = new Food(4,ItemSubType.FRUIT,"Orange",38,100,true);
        Trees OrangeTree = new Trees("Orange Tree",Orange,2,1,OrangeSapling);
        game.AllTreesInfo.add(OrangeTree);

        Material PeachSapling = new Material(1,ItemSubType.SEED,"Peach Sapling",6000);
        Food Peach = new Food(4,ItemSubType.FRUIT,"Peach",38,140,true);
        Trees PeachTree = new Trees("Peach Tree",Peach,2,1,PeachSapling);
        game.AllTreesInfo.add(PeachTree);

        Material AppleSapling = new Material(1,ItemSubType.SEED,"Apple Sapling",4000);
        Food Apple = new Food(4,ItemSubType.FRUIT,"Apple",38,100,true);
        Trees AppleTree = new Trees("Apple Tree",Apple,3,1,AppleSapling);
        game.AllTreesInfo.add(AppleTree);

        Material PomegranateSapling = new Material(1,ItemSubType.SEED,"Pomegranate Sapling",6000);
        Food Pomegranate = new Food(4,ItemSubType.FRUIT,"Pomegranate",38,140,true);
        Trees PomegranateTree = new Trees("Pomegranate Tree",Pomegranate,3,1,PomegranateSapling);
        game.AllTreesInfo.add(PomegranateTree);

        Material Acorns = new Material(1,ItemSubType.SEED,"Acorns",6200);
        Food OakResin = new Food(4,ItemSubType.SYRUP,"Oak Resin",0,150,false);
        Trees OakTree = new Trees("Oak Tree",OakResin,1234,7,Acorns);
        game.AllTreesInfo.add(OakTree);

        Material MapleSeeds = new Material(1,ItemSubType.SEED,"Maple Seeds",3000);
        Food MapleSyrup = new Food(2,ItemSubType.SYRUP,"Maple Syrup",0,200,false);
        Trees MapleTree = new Trees("Maple Tree",MapleSyrup,1234,9,MapleSeeds);
        game.AllTreesInfo.add(MapleTree);

        Material PineCones = new Material(1,ItemSubType.SEED,"Pine Cones",4000);
        Food PineTar = new Food(4,ItemSubType.SYRUP,"Pine Tar",0,100,false);
        Trees PineTree = new Trees("Pine Tree",PineTar,1234,5,PineCones);
        game.AllTreesInfo.add(PineTree);

        Material MahoganySeeds = new Material(1,ItemSubType.SEED,"Mahogany Seeds",40000);
        Food Sap = new Food(4,ItemSubType.SYRUP,"Sap",-2,2,false);
        Trees MahoganyTree = new Trees("Mahogany Tree",Sap,1234,1,MahoganySeeds);
        game.AllTreesInfo.add(MahoganyTree);

        Material MushroomSeeds = new Material(1,ItemSubType.SEED,"Mushroom Seeds",1500);
        Food CommonMushroom = new Food(4,ItemSubType.FRUIT,"Common Mushroom",38,40,true);
        Trees MushroomTree = new Trees("Mushroom Tree",CommonMushroom,1,1,MushroomSeeds);
        game.AllTreesInfo.add(MushroomTree);

        Material MysticTreeSeeds = new Material(1,ItemSubType.SEED,"Mystic Tree Seeds",10000);
        Food MysticSyrup = new Food(4,ItemSubType.SYRUP,"Sap",500,1000,true);
        Trees MysticTree = new Trees("Mystic Tree",MysticSyrup,1234,7,MysticTreeSeeds);
        game.AllTreesInfo.add(MysticTree);


    }
    public void creatAllRocks(Game game) {
        Material Quartz = new Material(1,ItemSubType.QUARTZ,"Quartz",25);
        Rock QuartzRock = new Rock(Quartz);
        game.AllRocksInfo.add(QuartzRock);

        Material EarthCrystal = new Material(1,ItemSubType.EARTHCRYSTAL,"Earth Crystal",50);
        Rock EarthCrystalRock = new Rock(EarthCrystal);
        game.AllRocksInfo.add(EarthCrystalRock);

        Material FrozenTear = new Material(1,ItemSubType.FROZENTEAR,"Frozen Tear",75);
        Rock FrozenTearRock = new Rock(FrozenTear);
        game.AllRocksInfo.add(FrozenTearRock);

        Material FireQuartz = new Material(1,ItemSubType.FIREQUARTZ,"Fire Quartz",100);
        Rock FireQuartzRock = new Rock(FireQuartz);
        game.AllRocksInfo.add(FireQuartzRock);

        Material Emerald = new Material(1,ItemSubType.EMERALD,"Emerald",250);
        Rock EmeraldRock = new Rock(Emerald);
        game.AllRocksInfo.add(EmeraldRock);

        Material Aquamarine = new Material(1,ItemSubType.AQUAMARINE,"Aquamarine",180);
        Rock AquamaRock = new Rock(Aquamarine);
        game.AllRocksInfo.add(AquamaRock);

        Material Ruby = new Material(1,ItemSubType.RUBY,"Ruby",250);
        Rock RubyRock = new Rock(Ruby);
        game.AllRocksInfo.add(RubyRock);

        Material Amethyst = new Material(1,ItemSubType.AMETHYST,"Amethyst",100);
        Rock AmethystRock = new Rock(Amethyst);
        game.AllRocksInfo.add(AmethystRock);

        Material Topaz = new Material(1,ItemSubType.TOPAZ,"Topaz",80);
        Rock TopazRock = new Rock(Topaz);
        game.AllRocksInfo.add(TopazRock);

        Material Jade = new Material(1,ItemSubType.JADE,"Jade",200);
        Rock JadeRock = new Rock(Jade);
        game.AllRocksInfo.add(JadeRock);

        Material Diamond = new Material(1,ItemSubType.DIAMOND,"Diamond",750);
        Rock DiamondRock = new Rock(Diamond);
        game.AllRocksInfo.add(DiamondRock);

        Material PrismaticShard = new Material(1,ItemSubType.PRISMATICSHARD,"Prismatic Shard",2000);
        Rock PrismaticShardRock = new Rock(PrismaticShard);
        game.AllRocksInfo.add(PrismaticShardRock);

        Material Gold = new Material(1,ItemSubType.GOLD,"Gold Ore",25);
        Rock GoldRock = new Rock(Gold);
        game.AllRocksInfo.add(GoldRock);

        Material Coal = new Material(1,ItemSubType.COAL,"Coal",15);
        Coal.price = 15;
        Rock CoalRock = new Rock(Coal);
        game.AllRocksInfo.add(CoalRock);

        Material Copper = new Material(1,ItemSubType.COOPER,"Copper Ore",5);
        Rock CopperRock = new Rock(Copper);
        game.AllRocksInfo.add(CopperRock);

        Material Iron = new Material(1,ItemSubType.IRON,"Iron Ore",10);
        Rock IronRock = new Rock(Iron);
        game.AllRocksInfo.add(IronRock);

        Material Iriduim = new Material(1,ItemSubType.IRIDUIM,"Iriduim Ore",100);
        Rock IriduimRock = new Rock(Iriduim);
        game.AllRocksInfo.add(IriduimRock);

    }
    public void creatAllCrops(Game game) {
        Material JazzSeeds = new Material(1,ItemSubType.SEED,"Jazz Seeds",45);
        Food BlueJazz = new Food(3,ItemSubType.FRUIT,"BlueJazz",45,50,true);
        Foraging BlueJazzCrop = new Foraging(1222,"BlueJazz",1,false,BlueJazz,true,JazzSeeds,0);
        game.AllCropInfo.add(BlueJazzCrop);

        Material CarrotSeeds = new Material(1, ItemSubType.SEED, "Carrot Seeds",35);
        Food Carrot = new Food(3, ItemSubType.FRUIT, "Carrot", 75, 35, true);
        Foraging CarrotCrop = new Foraging(111, "Carrot", 1, false, Carrot, true, CarrotSeeds,0);
        game.AllCropInfo.add(CarrotCrop);

        Material CauliflowerSeeds = new Material(1, ItemSubType.SEED, "Cauliflower Seeds",175);
        Food Cauliflower = new Food(3, ItemSubType.FRUIT, "Cauliflower", 75, 175, true);
        Foraging CauliflowerCrop = new Foraging(12441, "Cauliflower", 1, false, Cauliflower, true, CauliflowerSeeds,0);
        game.AllCropInfo.add(CauliflowerCrop);

        Material GarlicSeeds = new Material(1, ItemSubType.SEED, "Garlic Seeds",60);
        Food Garlic = new Food(3, ItemSubType.FRUIT, "Garlic", 20, 60, true);
        Foraging GarlicCrop = new Foraging(1225, "Garlic", 1, false, Garlic, false, GarlicSeeds,0);
        game.AllCropInfo.add(GarlicCrop);

        Material KaleSeeds = new Material(1, ItemSubType.SEED, "Kale Seeds",110);
        Food Kale = new Food(3, ItemSubType.FRUIT, "Kale", 50, 110, true);
        Foraging KaleCrop = new Foraging(1221, "Kale", 1, false, Kale, true, KaleSeeds,0);
        game.AllCropInfo.add(KaleCrop);

        Material ParsnipSeeds = new Material(1, ItemSubType.SEED, "Parsnip Seeds",35);
        Food Parsnip = new Food(3, ItemSubType.FRUIT, "Parsnip", 25, 35, true);
        Foraging ParsnipCrop = new Foraging(1111, "Parsnip", 1, false, Parsnip, true, ParsnipSeeds,0);
        game.AllCropInfo.add(ParsnipCrop);

        Material PotatoSeeds = new Material(1, ItemSubType.SEED, "Potato Seeds",80);
        Food Potato = new Food(3, ItemSubType.FRUIT, "Potato", 25, 80, true);
        Foraging PotatoCrop = new Foraging(11121, "Potato", 1, false, Potato, true, PotatoSeeds,0);
        game.AllCropInfo.add(PotatoCrop);

        Material RhubarbSeeds = new Material(1, ItemSubType.SEED, "Rhubarb Seeds",220);
        Food Rhubarb = new Food(3, ItemSubType.FRUIT, "Rhubarb", 0, 220, false);
        Foraging RhubarbCrop = new Foraging(22234, "Rhubarb", 1, false, Rhubarb, true, RhubarbSeeds,0);
        game.AllCropInfo.add(RhubarbCrop);

        Material StrawberrySeeds = new Material(1, ItemSubType.SEED, "Strawberry Seeds",120);
        Food Strawberry = new Food(3, ItemSubType.FRUIT, "Strawberry", 50, 120, true);
        Foraging StrawberryCrop = new Foraging(11222, "Strawberry", 1, false, Strawberry, true, StrawberrySeeds,0);
        game.AllCropInfo.add(StrawberryCrop);

        Material TulipBulb = new Material(1, ItemSubType.SEED, "Tulip Bulb",30);
        Food Tulip = new Food(3, ItemSubType.FRUIT, "Tulip", 45, 30, true);
        Foraging TulipCrop = new Foraging(1122, "Tulip", 1, false, Tulip, true, TulipBulb,0);
        game.AllCropInfo.add(TulipCrop);

        Material RiceShoot = new Material(1, ItemSubType.SEED, "Rice Shoot",30);
        Food UnmilledRice = new Food(3, ItemSubType.FRUIT, "Unmilled Rice", 3, 30, true);
        Foraging RiceCrop = new Foraging(1223, "Unmilled Rice", 1, false, UnmilledRice, true, RiceShoot,0);
        game.AllCropInfo.add(RiceCrop);

        Material CoffeeBeanSeeds = new Material(1, ItemSubType.SEED, "Coffee Bean",15);
        Food CoffeeBean = new Food(3, ItemSubType.FRUIT, "Coffee Bean", 0, 15, false);
        Foraging CoffeeCrop = new Foraging(12232,"Coffee Bean", 12, false, CoffeeBean, false, CoffeeBeanSeeds,2);
        game.AllCropInfo.add(CoffeeCrop);

        Material GreenBeanSeeds = new Material(1, ItemSubType.SEED, "Bean Starter",25);
        Food GreenBean = new Food(3, ItemSubType.FRUIT, "Green Bean", 40, 25, true);
        Foraging GreenBeanCrop = new Foraging(11134, "Green Bean", 1, false, GreenBean, false, GreenBeanSeeds,3);
        game.AllCropInfo.add(GreenBeanCrop);

        Material Blueberryseeds = new Material(1, ItemSubType.SEED, "Blueberry Seeds",50);
        Food Blueberry = new Food(3, ItemSubType.FRUIT, "Blueberry", 25, 50, true);
        Foraging BlueberryCrop = new Foraging(13342, "Blueberry", 2, false, Blueberry, false, Blueberryseeds,4);
        game.AllCropInfo.add(BlueberryCrop);

        Material ComSeeds = new Material(1, ItemSubType.SEED, "Com Seeds",50);
        Food Com = new Food(3, ItemSubType.FRUIT, "Com", 25, 50, true);
        Foraging ComCrop = new Foraging(23333,"Com", 23, false, Com, false, ComSeeds,4);
        game.AllCropInfo.add(ComCrop);

        Material HopsStarter = new Material(1, ItemSubType.SEED, "Hops Starter",25);
        Food Hops = new Food(3, ItemSubType.FRUIT, "Hops", 45, 25, true);
        Foraging HopsCrop = new Foraging(11234,"Hops",2,false,Hops,false,HopsStarter,1);
        game.AllCropInfo.add(HopsCrop);

        Material PepperSeeds = new Material(1, ItemSubType.SEED, "Pepper Seeds",40);
        Food HotPepper = new Food(3, ItemSubType.FRUIT, "HotPepper", 13, 40, true);
        Foraging HotPepperCrop = new Foraging(11111, "Hot Pepper",2,false,HotPepper,false,PepperSeeds,3);
        game.AllCropInfo.add(HotPepperCrop);

        Material MelonSeeds = new Material(1, ItemSubType.SEED, "Melon Seeds",250);
        Food Melon = new Food(3, ItemSubType.FRUIT, "Melon", 113, 250, true);
        Foraging MelonCrop = new Foraging(12333,"Melon",2,false,Melon,true,MelonSeeds,0);
        game.AllCropInfo.add(MelonCrop);

        Material PoppySeeds = new Material(1, ItemSubType.SEED, "Poppy Seeds",140);
        Food Poppy = new Food(3, ItemSubType.FRUIT, "Poppy", 45, 140, true);
        Foraging PoppyCrop = new Foraging(1222,"Poppy",2,false,Poppy,true,PoppySeeds,0);
        game.AllCropInfo.add(PoppyCrop);

        Material RadishSeeds = new Material(1, ItemSubType.SEED, "Radish Seeds",90);
        Food Radish = new Food(3, ItemSubType.FRUIT, "Radish", 45, 90, true);
        Foraging RadishCrop = new Foraging(2121,"Radish",2,false,Radish,true,RadishSeeds,0);
        game.AllCropInfo.add(RadishCrop);

        Material RedCabbageSeeds = new Material(1, ItemSubType.SEED, "Red Cabbage Seeds",260);
        Food RedCabbage = new Food(1,ItemSubType.FRUIT, "Red Cabbage", 75, 260, true);
        Foraging RedCabbageCrop = new Foraging(21222, "Red Cabbage", 2, false,RedCabbage,true,RedCabbageSeeds,0);
        game.AllCropInfo.add(RedCabbageCrop);

        Material StarfruitSeeds = new Material(1, ItemSubType.SEED, "Starfruit Seeds",750);
        Food Starfruit = new Food(3, ItemSubType.FRUIT, "Starfruit", 125, 750, true);
        Foraging StarfruitCrop = new Foraging(23233,"Starfruit",2,false,Starfruit,true,StarfruitSeeds,0);
        game.AllCropInfo.add(StarfruitCrop);

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

        Material AmaranthSeeds = new Material(1, ItemSubType.SEED, "Amaranth Seeds",150);
        Food Amaranth = new Food(3, ItemSubType.FRUIT, "Amaranth", 50, 150, true);
        Foraging AmaranthCrop = new Foraging(1222,"Amaranth",3,false,Amaranth,true,AmaranthSeeds,0);
        game.AllCropInfo.add(AmaranthCrop);

        Material ArtichokeSeeds = new Material(1, ItemSubType.SEED, "Artichoke Seeds",160);
        Food Artichoke = new Food(3, ItemSubType.FRUIT, "Artichoke", 30, 160, true);
        Foraging ArtichokeCrop = new Foraging(22121,"Artichoke",3,false,Artichoke,true,ArtichokeSeeds,0 );
        game.AllCropInfo.add(ArtichokeCrop);

        Material BeetSeeds = new Material(1, ItemSubType.SEED, "Beet Seeds",100);
        Food Beet = new Food(3, ItemSubType.FRUIT, "Beet", 30, 100, true);
        Foraging BeetCrop = new Foraging(1122,"Beet",2,false,Beet,true,BeetSeeds,0);
        game.AllCropInfo.add(BeetCrop);

        Material BokChoySeeds = new Material(1, ItemSubType.SEED, "Bok Choy Seeds",80);
        Food BokChoy = new Food(3, ItemSubType.FRUIT, "Bok Choy", 25, 80, true);
        Foraging BokChoyCrop = new Foraging(1111,"Bok Choy",3,false,BokChoy,true,BokChoySeeds,0);
        game.AllCropInfo.add(BokChoyCrop);

        Material BroccoliSeeds = new Material(1, ItemSubType.SEED, "Broccoli Seeds",70);
        Food Broccoli = new Food(3, ItemSubType.FRUIT, "Broccoli", 63, 70, true);
        Foraging BroccoliCrop = new Foraging(2222,"Broccoli",3,false,Broccoli,false,BroccoliSeeds,4);
        game.AllCropInfo.add(BroccoliCrop);

        Material CranberrySeeds = new Material(1, ItemSubType.SEED, "Cranberry Seeds",75);
        Food Cranberries = new Food(1,ItemSubType.FRUIT, "Cranberries", 38, 75, true);
        Foraging CranberriesCrop = new Foraging(12112,"Cranberries",3,false,Cranberries,false,CranberrySeeds,5);
        game.AllCropInfo.add(CranberriesCrop);

        Material EggplantSeeds = new Material(1, ItemSubType.SEED, "Eggplant Seeds",60);
        Food Eggplant = new Food(3, ItemSubType.FRUIT, "Eggplant", 20, 60, true);
        Foraging EggplantCrop = new Foraging(1111,"Eggplant",3,false,Eggplant,false,EggplantSeeds,5);
        game.AllCropInfo.add(EggplantCrop);

        Material FairySeeds = new Material(1,ItemSubType.SEED,"Fairy Seeds",290);
        Food FairyRose = new Food(3,ItemSubType.FRUIT,"Fairy Rose",45,290,true);
        Foraging FairyRoseCrop = new Foraging(1443,"Fairy Rose",3,false,FairyRose,true,FairySeeds,0);
        game.AllCropInfo.add(FairyRoseCrop);

        Material GrapeStarter = new Material(1, ItemSubType.SEED, "Grape Starter",80);
        Food Grape = new Food(3,ItemSubType.FRUIT, "Grape", 38, 80, true);
        Foraging GrapeCrop = new Foraging(11233,"Grape",3,false,Grape,false,GrapeStarter,3);
        game.AllCropInfo.add(GrapeCrop);

        Material PumpkinSeeds = new Material(1, ItemSubType.SEED, "Pumpkin Seeds",320);
        Food Pumpkin = new Food(3,ItemSubType.FRUIT, "Pumpkin", 0, 320, false);
        Foraging PumpkinCrop = new Foraging(12343,"Pumpkin",3,false,Pumpkin,true,PumpkinSeeds,0);
        game.AllCropInfo.add(PumpkinCrop);

        Material YamSeeds = new Material(1, ItemSubType.SEED, "Yam Seeds",160);
        Food Yam = new Food(3,ItemSubType.FRUIT, "Yam", 45, 160, true);
        Foraging YamCrop = new Foraging(1333,"Yam",3,false,Yam,true,YamSeeds,0);
        game.AllCropInfo.add(YamCrop);

        Material RareSeeds = new Material(1, ItemSubType.SEED, "Rare Seeds",4000);
        Food SweetGemBerry = new Food(1,ItemSubType.FRUIT, "Sweet Gem Berry", 0, 3000, true);
        Foraging SweetGemBerryCrop = new Foraging(24666,"Sweet Gem Berry",3,false,SweetGemBerry,true,RareSeeds,0);
        game.AllCropInfo.add(SweetGemBerryCrop);

        Material PowdermelonSeeds = new Material(1, ItemSubType.SEED, "Powdermelon Seeds",60);
        Food Powdermelon = new Food(1,ItemSubType.FRUIT, "Powdermelon", 63, 60, true);
        Foraging PowdermelonCrop = new Foraging(12121,"Powdermelon",4,false,Powdermelon,true,PowdermelonSeeds,0);
        game.AllCropInfo.add(PowdermelonCrop);

        Material AncientSeeds = new Material(1, ItemSubType.SEED, "Ancient Seeds",600);
        Food AncientFruit = new Food(3,ItemSubType.FRUIT, "Ancient Fruit", 0, 550, false);
        Foraging AncientFruitCrop = new Foraging(27775,"Ancient Fruit",123,false,AncientFruit,true,AncientSeeds,0);
        game.AllCropInfo.add(AncientFruitCrop);

        Material NoSeed = new Material(1, ItemSubType.SEED, "No Seeds",0);

        Food CommonMushroom  = new Food(2,ItemSubType.FRUIT,"Common Mushroom",38,40,true);
        Foraging CommonMushroomCrop = new Foraging(0,"Common Mushroom",1234,true,CommonMushroom,true,NoSeed,0);
        game.AllCropInfo.add(CommonMushroomCrop);

        Food Daffodil  = new Food(2,ItemSubType.FRUIT,"Daffodil",0,30,true);
        Foraging DaffodilCrop = new Foraging(0,"Daffodil",1,true,Daffodil,true,NoSeed,0);
        game.AllCropInfo.add(DaffodilCrop);

        Food Dandelion = new Food(2,ItemSubType.FRUIT,"Dandelion",25,40,true);
        Foraging DandelionCrop = new Foraging(0,"Dandelion",1,true,Dandelion,true,NoSeed,0);
        game.AllCropInfo.add(DandelionCrop);

        Food Leek  = new Food(2,ItemSubType.FRUIT,"Leek",40,60,true);
        Foraging LeekCrop = new Foraging(0,"Leek",1,true,Leek,true,NoSeed,0);
        game.AllCropInfo.add(LeekCrop);

        Food Morel  = new Food(2,ItemSubType.FRUIT,"Morel",20,150,true);
        Foraging MorelCrop = new Foraging(0,"Morel",1,true,Morel,true,NoSeed,0);
        game.AllCropInfo.add(MorelCrop);

        Food Salmonberry  = new Food(3,ItemSubType.FRUIT,"Salmonberry",25,5,true);
        Foraging SalmonberryCrop = new Foraging(0,"SalmonBerry",1,true,Salmonberry,true,NoSeed,0);
        game.AllCropInfo.add(SalmonberryCrop);

        Food SpringOnion  = new Food(2,ItemSubType.FRUIT,"Spring Onion",0,30,true);
        Foraging SpringOnionCrop = new Foraging(0,"Spring Onion",1,true,SpringOnion,true,NoSeed,0);
        game.AllCropInfo.add(SpringOnionCrop);

        Food WildHorseradish  = new Food(2,ItemSubType.FRUIT,"Wild Horseradish",0,30,true);
        Foraging WildHorseradishCrop = new Foraging(0,"Wild Horseradish",1,true,WildHorseradish,true,NoSeed,0);
        game.AllCropInfo.add(WildHorseradishCrop);

        Food FiddleheadFern  = new Food(2,ItemSubType.FRUIT,"Fiddlehead Fern",0,30,true);
        Foraging FiddleheadFernCrop = new Foraging(0,"Fiddlehead Fern",2,true,FiddleheadFern,true,NoSeed,0);
        game.AllCropInfo.add(FiddleheadFernCrop);

        Food RedMushroom  = new Food(2,ItemSubType.FRUIT,"Red Mushroom",-50,75,false);
        Foraging RedMushroomCrop = new Foraging(0,"Red Mushroom",2,true,RedMushroom,true,NoSeed,0);
        game.AllCropInfo.add(RedMushroomCrop);

        Food SpiceBerry  = new Food(2,ItemSubType.FRUIT,"Spice Berry",25,80,true);
        Foraging SpiceBerryCrop = new Foraging(0,"Spice Berry",2,true,SpiceBerry,true,NoSeed,0);
        game.AllCropInfo.add(SpiceBerryCrop);

        Food SweetPea  = new Food(2,ItemSubType.FRUIT,"Sweet Pea",0,50,false);
        Foraging SweetPeaCrop = new Foraging(0,"Sweet Pea",2,true,SweetPea,true,NoSeed,0);
        game.AllCropInfo.add(SweetPeaCrop);

        Food Blackberry  = new Food(2,ItemSubType.FRUIT,"Blackberry",25,25,true);
        Foraging BlackberryCrop = new Foraging(0,"Blackberry",3,true,Blackberry,true,NoSeed,0);
        game.AllCropInfo.add(BlackberryCrop);

        Food Chanterelle  = new Food(2,ItemSubType.FRUIT,"Chanterelle",75,160,true);
        Foraging ChanterelleCrop = new Foraging(0,"Chanterelle",3,true,Chanterelle,true,NoSeed,0);
        game.AllCropInfo.add(ChanterelleCrop);

        Food Hazelnut  = new Food(2,ItemSubType.FRUIT,"Hazelnut",38,40,true);
        Foraging HazelnutCrop = new Foraging(0,"Hazelnut",3,true,Hazelnut,true,NoSeed,0);
        game.AllCropInfo.add(HazelnutCrop);

        Food PurpleMushroom  = new Food(2,ItemSubType.FRUIT,"Purple Mushroom",30,90,true);
        Foraging PurpleMushroomCrop = new Foraging(0,"Purple Mushroom",3,true,PurpleMushroom,true,NoSeed,0);
        game.AllCropInfo.add(PurpleMushroomCrop);

        Food WildPlum  = new Food(2,ItemSubType.FRUIT,"Wild Plum",25,80,true);
        Foraging WildPlumCrop = new Foraging(0,"Wild Plum",3,true,WildPlum,true,NoSeed,0);
        game.AllCropInfo.add(WildPlumCrop);

        Food Crocus  = new Food(2,ItemSubType.FRUIT,"Crocus",0,60,false);
        Foraging CrocusCrop = new Foraging(0,"Crocus",4,true,Crocus,true,NoSeed,0);
        game.AllCropInfo.add(CrocusCrop);

        Food CrystalFruit  = new Food(2,ItemSubType.FRUIT,"Crystal Fruit",53,150,true);
        Foraging CrystalFruitCrop = new Foraging(0,"Crystal Fruit",4,true,CrystalFruit,true,NoSeed,0);
        game.AllCropInfo.add(CrystalFruitCrop);

        Food Holly  = new Food(2,ItemSubType.FRUIT,"Holly Crop",-37,80,false);
        Foraging HollyCrop = new Foraging(0,"Holly Crop",4,true,Holly,true,NoSeed,0);
        game.AllCropInfo.add(HollyCrop);

        Food SnowYam  = new Food(2,ItemSubType.FRUIT,"Snow Yam",30,100,true);
        Foraging SnowYamCrop = new Foraging(0,"Snow Yam",4,true,SnowYam,true,NoSeed,0);
        game.AllCropInfo.add(SnowYamCrop);

        Food WinterRoot  = new Food(2,ItemSubType.FRUIT,"Winter Crop",25,70,true);
        Foraging WinterRootCrop = new Foraging(0,"Winter Crop",4,true,WinterRoot,true,NoSeed,0);
        game.AllCropInfo.add(WinterRootCrop);

        Food Fiber = new Food(5,ItemSubType.FRUIT,"Fiber",0,0,false);
        Foraging GRASS = new Foraging(0,"Grass",1234,true,Fiber,true,NoSeed,0);
    }



    public void GameRun() {
        Scanner sc = new Scanner(System.in);

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
                user.player.items.add(new Tool(1, ItemSubType.AXE,"Axe",0));
                user.player.items.add(new Tool(1, ItemSubType.WATERINGCAN,"WateringCan",0));
                user.player.items.add(new Tool(1, ItemSubType.FISHINGPOLE,"FishingPole",0));
                user.player.items.add(new Tool(1, ItemSubType.SCYTHE,"Scythe",0));
                user.player.items.add(new Tool(1, ItemSubType.MILKPAIL,"MilkPail",0));
                user.player.items.add(new Tool(1, ItemSubType.SHEAR,"Shear",0));
                user.player.items.add(new Tool(1, ItemSubType.TRASH_CAN,"TrashCan",0));
                user.player.items.add(new Tool(1, ItemSubType.HOE,"Hoe",0));
                user.player.items.add(new Tool(1, ItemSubType.PICKAXE,"Pickaxe",0));
                user.player.KnownRecipes.add(AllRecipes.get(0));
                user.player.KnownRecipes.add(AllRecipes.get(1));
                user.player.KnownRecipes.add(AllRecipes.get(2));
                //tester

              user.player.items.add(new Material(1, ItemSubType.SEED,"Test Seed",25));
                user.player.items.add(new Material(1000,ItemSubType.WOOD,"Wood",10));
                user.player.items.add(new Material(1000,ItemSubType.STONE,"Stone",10));
                user.player.items.add(new Material(1000,ItemSubType.HAY,"Hay",10));
                user.player.items.add(new Material(1,ItemSubType.FISH,"Sardine",10));
                user.player.items.add(new Material(1,ItemSubType.FIBER,"Fiber",10));
                user.player.items.add(new Material(1,ItemSubType.UNKNOWN,"Rice",10));
                user.player.items.add(new Food(3,ItemSubType.MILK,"Test Milk",12,12,true));
                user.player.items.add(new Food(3,ItemSubType.EGG,"Test Egg",12,12,true));


                user.player.game.Map.get(user.player.PositionY)
                        .set(user.player.PositionX, new Tile(TileType.PLAYER));
            }
        }


        GameView gameView = new GameView();
        gameView.check(sc, this);
    }


}