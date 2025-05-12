package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class StardropSaloon implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    public StardropSaloon() {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();

        unlimitedItems.add(new ShopItem("Beer", "A refreshing alcoholic beverage.", 400));
        unlimitedItems.add(new ShopItem("Salad", "Healthy and nutritious.", 220));
        unlimitedItems.add(new ShopItem("Bread", "A crusty loaf of bread.", 120));
        unlimitedItems.add(new ShopItem("Spaghetti", "Delicious pasta with sauce.", 240));
        unlimitedItems.add(new ShopItem("Pizza", "Popular all-around meal.", 600));
        unlimitedItems.add(new ShopItem("Coffee", "Increases movement speed.", 300));


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