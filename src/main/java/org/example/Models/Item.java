package org.example.Models;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

public class Item {
    public ItemType type;
    public ItemSubType subtype;
    public int Count;
    public String name;
    public int price;
    public Item(ItemType type, ItemSubType subtype , int count,String name) {
        this.type = type;
        this.subtype = subtype;
        this.Count = count;
        this.name = name;
    }
    public Item(Item Other){
        this.type = Other.type;
        this.subtype = Other.subtype;
        this.Count = Other.Count;
        this.name = Other.name;
    }
    public String getName() { return name; }
    public int getPrice() { return price; }


}
