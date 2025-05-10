package org.example.Models;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

public class Item {
    public ItemType type;
    public ItemSubType subtype;
    public int Count;
    public Item(ItemType type, ItemSubType subtype , int count) {
        this.type = type;
        this.subtype = subtype;
        this.Count = count;
    }


}
