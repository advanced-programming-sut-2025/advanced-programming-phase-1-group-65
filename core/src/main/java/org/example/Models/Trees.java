package org.example.Models;

import com.badlogic.gdx.graphics.Texture;
import org.example.Models.Enums.TileType;

public class Trees extends Tile{
    public String name;
    public Food Fruit ;
    public int Season;
    public Material seed;
    public Trees(String name, Food Fruit, int Season, int HarvestingCycle, Material seed) {
        super(TileType.TREE);
        this.name = name;
        this.Fruit = Fruit;
        this.Season = Season;
        this.HarvestingCycle = HarvestingCycle;
        this.seed = seed;
    }
    public Texture texture1;
    public Texture texture2;
    public Trees (Trees Other){
        super(TileType.TREE);
        this.name = Other.name;
        this.Fruit = Other.Fruit;
        this.Season = Other.Season;
        this.HarvestingCycle = Other.HarvestingCycle;
        this.seed = Other.seed;
        this.texture1 = Other.texture1;
        this.texture2 = Other.texture2;
    }
    public boolean isHarvestable=false;
    public int HarvestingCycle;
    public boolean WateredToday=false;
    public int daysWithoutWater;
    public int day;
    public int posx;
    public int posy;
    public boolean HarvestedFirstTime=false;

}
