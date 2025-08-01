package org.example.Models;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

public class Material extends Item{
    public ItemSubType subType;
    public Material(int count, ItemSubType subType, String name,Integer price) {
        super(ItemType.MATERIAL,subType,count,name,price);
        this.subType = subType;
    }
    public Material(Material Other){
        super(Other.type,Other.subType, Other.Count, Other.name, Other.price);
        this.subType = Other.subType;
        this.texture = Other.texture;

    }

}
