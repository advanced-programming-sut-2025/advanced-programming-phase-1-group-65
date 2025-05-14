package org.example.Models;
import org.example.Models.Enums.FoodType;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

public class Food extends Item {
    public ItemSubType subtype;
    public Food(int count, ItemSubType subtype, String name, int energy, int price,boolean isEdible) {
        super(ItemType.FOOD,subtype,count,name);
        this.subtype = subtype;
        this.energy = energy;
        this.price = price;
        this.isEdible = isEdible;

    }
    public Food(Food Other){
        super(ItemType.FOOD,Other.subtype, Other.Count, Other.name);
        this.subtype = Other.subtype;
        this.energy = Other.energy;
        this.price = Other.price;
        this.isEdible = Other.isEdible;
    }
    public int energy;
    public boolean isEdible;

    public int price;
}
