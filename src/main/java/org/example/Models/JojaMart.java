package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class JojaMart implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    public JojaMart() {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();

        unlimitedItems.add(new ShopItem("Joja Cola", "A sugary drink made by Joja Corporation.", 75));
        unlimitedItems.add(new ShopItem("Parsnip Seeds", "Plant these in spring. Takes 4 days to mature.", 25));
        unlimitedItems.add(new ShopItem("Fertilizer", "Improves soil quality, increasing your chance to grow quality crops.", 100));

        limitedItems.add(new LimitedShopItem("Joja Membership", "Grants access to special Joja benefits.", 5000, 1));
        limitedItems.add(new LimitedShopItem("Battery Pack", "Used to power some machines.", 2500, 2));
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