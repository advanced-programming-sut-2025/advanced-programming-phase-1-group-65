package org.example.Models;

import org.example.Models.Enums.TileSubType;
import org.example.Models.Enums.TileType;

public class Foraging extends Tile{
    public Foraging(int Stage,String name,int Season,boolean isHarvestable,Food Fruit,boolean oneTime,Material Seed,int RegrowthTime){
        super(TileType.FORAGING);
        this.Stage = Stage;
        this.name = name;
        this.Season = Season;
        this.isHarvestable = isHarvestable;
        this.Fruit = Fruit;
        this.OneTime = oneTime;
        this.Seed = Seed;
        this.RegrowthTime = RegrowthTime;
    }
    public int Stage;
    public String name;
    public Food Fruit;
    public int Season;
    public boolean OneTime;
    public boolean isHarvestable;
    public Material Seed;
    public int RegrowthTime;
    public boolean WateredToday = false;
    public int daysWithOutWater=0;
    public int posx;
    public int posy;
    public int day;
    public boolean HarvestedFirstTime = false;
}
