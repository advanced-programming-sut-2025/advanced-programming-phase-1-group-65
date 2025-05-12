package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Ranch implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    public Ranch() {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();

        unlimitedItems.add(new ShopItem("Hay", "Animal feed. Essential for keeping animals fed.", 50));
        unlimitedItems.add(new ShopItem("Milk Pail", "Used to milk cows.", 1000));
        unlimitedItems.add(new ShopItem("Shears", "Used to collect wool from sheep.", 1000));
        unlimitedItems.add(new ShopItem("Heater", "Keeps animals warm and happy during winter.", 2000));

        limitedItems.add(new LimitedShopItem("Chicken", "Provides eggs daily. Requires coop.", 800, 1));
        limitedItems.add(new LimitedShopItem("Cow", "Provides milk daily. Requires barn.", 1500, 1));
        limitedItems.add(new LimitedShopItem("Goat", "Provides goat milk. Requires barn upgrade.", 4000, 1));
        limitedItems.add(new LimitedShopItem("Sheep", "Provides wool. Requires deluxe barn.", 8000, 1));
        limitedItems.add(new LimitedShopItem("Pig", "Finds truffles. Requires deluxe barn.", 16000, 1));
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
            item.resetPurchaseCount();
        }
    }
}