package org.example.Models;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

public class Item {
    public ItemType type;
    public ItemSubType subtype;
    public int Count;
    public String name;
    public Integer price;
    public Item(ItemType type, ItemSubType subtype , int count,String name, Integer price) {
        this.type = type;
        this.subtype = subtype;
        this.Count = count;
        this.name = name;
        this.price = price;
    }
    public Item(Item Other){
        this.type = Other.type;
        this.subtype = Other.subtype;
        this.Count = Other.Count;
        this.name = Other.name;
        this.price = Other.price;

    }
    public String getName() { return name; }
    public Integer getPrice() { return price; }


}
