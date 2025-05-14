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
    public int energy;
    public boolean isEdible;

    public int price;
}
