package org.example.Models;

import org.example.Models.Enums.TileSubType;
import org.example.Models.Enums.TileType;

public class Trees extends Tile{
    public TileSubType subType;
    public String name;
    public Food Fruit ;
    public int Season;
    public Material seed;
    public Trees(TileSubType subType, String name, Food Fruit, int Season) {
        super(TileType.TREE);
        this.subType = subType;
        this.name = name;
        this.Fruit = Fruit;
        this.Season = Season;
    }
    public int firstDay;

}
