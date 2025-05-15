package org.example.Models;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

public class Material extends Item{
    public ItemSubType subType;
    public Material(int count, ItemSubType subType, String name,Integer price) {
        super(ItemType.MATERIAL,subType,count,name,price);
        this.subType = subType;
    }

}
