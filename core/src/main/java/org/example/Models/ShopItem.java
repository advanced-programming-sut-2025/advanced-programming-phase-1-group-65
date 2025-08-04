package org.example.Models;

import com.badlogic.gdx.graphics.Texture;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

public class ShopItem extends Item {
    private String description;
    private int price;
    public Texture texture;

    public ShopItem(String name, String description, int price,ItemType type, ItemSubType subType) {

        super(type,subType,1,name,price);
        this.description = description;
        this.price = price;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Integer getPrice() { return price; }
}
