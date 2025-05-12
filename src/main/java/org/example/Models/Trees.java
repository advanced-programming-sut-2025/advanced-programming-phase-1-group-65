package org.example.Models;

import org.example.Models.Enums.TileSubType;
import org.example.Models.Enums.TileType;

public class Trees extends Tile{
    public String name;
    public Food Fruit ;
    public int Season;
    public Material seed;
    public Trees( String name, Food Fruit, int Season) {
        super(TileType.TREE);
        this.name = name;
        this.Fruit = Fruit;
        this.Season = Season;
    }
    public boolean isHarvestable=false;
    public int firstDay;
    public int HarvestingCycle;

}
