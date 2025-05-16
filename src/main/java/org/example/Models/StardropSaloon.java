package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class StardropSaloon implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    @Override
    public String getName() {
        return "The Stardrop Saloon";
    }

    public StardropSaloon() {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();

        /*unlimitedItems.add(new ShopItem("Beer", "Drink in moderation.", 400));
        unlimitedItems.add(new ShopItem("Salad", "A healthy garden salad.", 220));
        unlimitedItems.add(new ShopItem("Bread", "A crusty baguette.", 120));
        unlimitedItems.add(new ShopItem("Spaghetti", "An old favorite.", 240));
        unlimitedItems.add(new ShopItem("Pizza", "It's popular for all the right reasons.", 600));
        unlimitedItems.add(new ShopItem("Coffee", "It smells delicious. This is sure to give you a boost.", 300));

        limitedItems.add(new LimitedShopItem("Hashbrowns Recipe", "A recipe to make Hashbrowns.", 50, 1));
        limitedItems.add(new LimitedShopItem("Omelet Recipe", "A recipe to make Omelet.", 100, 1));
        limitedItems.add(new LimitedShopItem("Pancakes Recipe", "A recipe to make Pancakes.", 100, 1));
        limitedItems.add(new LimitedShopItem("Bread Recipe", "A recipe to make Bread.", 100, 1));
        limitedItems.add(new LimitedShopItem("Tortilla Recipe", "A recipe to make Tortilla.", 100, 1));
        limitedItems.add(new LimitedShopItem("Pizza Recipe", "A recipe to make Pizza.", 150, 1));
        limitedItems.add(new LimitedShopItem("Maki Roll Recipe", "A recipe to make Maki Roll.", 300, 1));
        limitedItems.add(new LimitedShopItem("Triple Shot Espresso Recipe", "A recipe to make Triple Shot Espresso.", 5000, 1));
        limitedItems.add(new LimitedShopItem("Cookie Recipe", "A recipe to make Cookie.", 300, 1));

         */


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