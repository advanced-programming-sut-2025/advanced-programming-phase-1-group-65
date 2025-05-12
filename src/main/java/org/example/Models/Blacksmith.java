package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Blacksmith implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    public Blacksmith() {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();

        unlimitedItems.add(new ShopItem("Copper Ore", "A common ore that can be smelted into bars.", 75));
        unlimitedItems.add(new ShopItem("Iron Ore", "A fairly common ore that can be smelted into bars.", 150));
        unlimitedItems.add(new ShopItem("Coal", "A combustible rock that is useful for crafting and smelting.", 150));
        unlimitedItems.add(new ShopItem("Gold Ore", "A precious ore that can be smelted into bars.", 400));

        limitedItems.add(new LimitedShopItem("Copper Tool", "Copper Bar (5)", 2000, 1));
        limitedItems.add(new LimitedShopItem("Steel Tool", "Iron Bar (5)", 5000, 1));
        limitedItems.add(new LimitedShopItem("Gold Tool", "Gold Bar (5)", 10000, 1));
        limitedItems.add(new LimitedShopItem("Iridium Tool", "Iridium Bar (5)", 25000, 1));
        limitedItems.add(new LimitedShopItem("Copper Trash Can", "Copper Bar (5)", 1000, 1));
        limitedItems.add(new LimitedShopItem("Steel Trash Can", "Iron Bar (5)", 2500, 1));
        limitedItems.add(new LimitedShopItem("Gold Trash Can", "Gold Bar (5)", 5000, 1));
        limitedItems.add(new LimitedShopItem("Iridium Trash Can", "Iridium Bar (5)", 12500, 1));
    }

    public List<ShopItem> getUnlimitedItems() {
        return unlimitedItems;
    }

    public List<LimitedShopItem> getLimitedItems() {
        return limitedItems;
    }

    public void resetDailyLimits() {
        for (LimitedShopItem item : limitedItems) {
            item.resetPurchaseCount();
        }
    }
}