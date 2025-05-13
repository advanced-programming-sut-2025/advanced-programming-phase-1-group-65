package org.example.Models;

import org.example.Models.Enums.TileSubType;
import org.example.Models.Enums.TileType;

public class Trees extends Tile{
    public String name;
    public Food Fruit ;
    public int Season;
    public Material seed;
    public Trees( String name, Food Fruit, int Season,int HarvestingCycle,Material seed) {
        super(TileType.TREE);
        this.name = name;
        this.Fruit = Fruit;
        this.Season = Season;
        this.HarvestingCycle = HarvestingCycle;
        this.seed = seed;
    }
    public boolean isHarvestable=false;
    public int HarvestingCycle;
    public boolean WateredToday=false;
    public int daysWithoutWater;
    public int FirstDay;

}
