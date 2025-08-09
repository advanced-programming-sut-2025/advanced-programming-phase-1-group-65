package org.example.Models;
import com.badlogic.gdx.graphics.Texture;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

public class Food extends Item {
    public ItemSubType subtype;


    public Food(int count, ItemSubType subtype, String name, int energy, Integer price, boolean isEdible) {
        super(ItemType.FOOD,subtype,count,name,price);
        this.subtype = subtype;
        this.energy = energy;
        this.isEdible = isEdible;

    }
    public Food(Food Other){
        super(ItemType.FOOD,Other.subtype, Other.Count, Other.name, Other.price);
        this.subtype = Other.subtype;
        this.energy = Other.energy;
        this.isEdible = Other.isEdible;
        this.texture = Other.texture;
    }
    public int energy;
    public boolean isEdible;
}
