package org.example.Models;
import org.example.Models.Enums.FoodType;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

public class Food extends Item {
    public ItemSubType subtype;
    public Food(int count, ItemSubType subtype) {
        super(ItemType.FOOD,subtype,count);
        this.subtype = subtype;
    }
    String Quality;
    private int Energy;
    private String Name;
    private String Type;
    private String Recipe;
    private boolean IsEatable;
    public boolean IsGrowable(FoodType foodType) {
        if(foodType == FoodType.FRUIT) {
            return true;
        }
        return false;
    }
    private int TimeToGrow;
    private int StageOfGrowth;
    private String Season;
    private boolean isGiantable;
    private int Price;
    private String HarvestingStages;
    private int HarvestingPeriods;
    private int Quantity;
}
