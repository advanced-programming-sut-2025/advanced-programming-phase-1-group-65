package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class CarpenterShop implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    public CarpenterShop() {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();

        unlimitedItems.add(new ShopItem("Wood", "Basic material used for crafting and building.", 10));
        unlimitedItems.add(new ShopItem("Stone", "Common material used in construction.", 20));
        unlimitedItems.add(new ShopItem("Basic Window", "A decorative window for your house.", 200));
        unlimitedItems.add(new ShopItem("Wooden Floor", "Used to decorate your house.", 100));

        limitedItems.add(new LimitedShopItem("House Upgrade", "Expand your house with a kitchen.", 10000, 1));
        limitedItems.add(new LimitedShopItem("Stable", "Allows you to ride a horse. Requires Hardwood (5).", 10000, 1));
        limitedItems.add(new LimitedShopItem("Shed", "An empty building you can fill with whatever you like.", 15000, 1));
        limitedItems.add(new LimitedShopItem("Coop", "Houses chickens. Requires Wood (300) and Stone (100).", 4000, 1));
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