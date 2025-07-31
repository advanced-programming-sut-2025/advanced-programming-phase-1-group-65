package org.example.Models;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

public class LimitedShopItem extends Item {
    private int price;
    private int dailyLimit;
    private String description;
    private int purchasedToday = 0;

    public LimitedShopItem(String name, String description, int price, int dailyLimit, ItemType type, ItemSubType subtype) {
        super(type,subtype,1,name,price);
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public void resetPurchasedToday() {
        this.purchasedToday = 0;
    }

    public int getPurchasedToday() {
        return purchasedToday;
    }

    public void increasePurchasedToday(int count) {
        this.purchasedToday += count;
    }

    public Integer getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
