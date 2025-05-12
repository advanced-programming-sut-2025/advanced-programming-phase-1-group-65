package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class FishShop implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    public FishShop() {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();

        unlimitedItems.add(new ShopItem("Bait", "Used to catch fish faster.", 5));
        unlimitedItems.add(new ShopItem("Fishing Rod", "Basic fishing rod. Can catch common fish.", 500));
        unlimitedItems.add(new ShopItem("Trout Soup", "Improves your fishing skill temporarily.", 250));
        unlimitedItems.add(new ShopItem("Crab Pot", "Place it in the water, load with bait, and check the next day to see if you caught anything.", 1500));

        limitedItems.add(new LimitedShopItem("Fiberglass Rod", "Better fishing rod. Allows using bait.", 1800, 1));
        limitedItems.add(new LimitedShopItem("Iridium Rod", "The best rod. Can use bait and tackle.", 7500, 1));
        limitedItems.add(new LimitedShopItem("Treasure Hunter Tackle", "Makes it easier to find treasure when fishing.", 1000, 1));
        limitedItems.add(new LimitedShopItem("Barbed Hook", "Makes your catch progress bar cling to the fish.", 1000, 1));
    }

    @Override
    public List<ShopItem> getUnlimitedItems() {
        return unlimitedItems;
    }

    @Override
    public List<LimitedShopItem> getLimitedItems() {
        return limitedItems;
    }

    @Override
    public void resetDailyLimits() {
        for (LimitedShopItem item : limitedItems) {
            item.resetPurchasedToday();
        }
    }
}