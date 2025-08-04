package org.example.Models;

import com.badlogic.gdx.graphics.Texture;
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

    public Foraging(Foraging Other){
        super(TileType.FORAGING);
        this.Stage = Other.Stage;
        this.name = Other.name;
        this.Season = Other.Season;
        this.isHarvestable = Other.isHarvestable;
        this.Fruit = Other.Fruit;
        this.OneTime = Other.OneTime;
        this.Seed = Other.Seed;
        this.RegrowthTime = Other.RegrowthTime;
        this.texture1=Other.texture1;
        this.texture2=Other.texture2;
    }
    public Texture texture1;
    public Texture texture2;
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
    public boolean Fertilized = false;
}
