package org.example.Models.Shops;

import org.example.Models.LimitedShopItem;
import org.example.Models.Shop;
import org.example.Models.ShopItem;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

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

        unlimitedItems.add(new ShopItem("Beer", "Drink in moderation.", 400, ItemType.FOOD, ItemSubType.UNKNOWN));
        unlimitedItems.add(new ShopItem("Salad", "A healthy garden salad.", 220,ItemType.FOOD,ItemSubType.UNKNOWN));
        unlimitedItems.add(new ShopItem("Bread", "A crusty baguette.", 120,ItemType.FOOD,ItemSubType.UNKNOWN));
        unlimitedItems.add(new ShopItem("Spaghetti", "An old favorite.", 240,ItemType.FOOD,ItemSubType.UNKNOWN));
        unlimitedItems.add(new ShopItem("Pizza", "It's popular for all the right reasons.", 600,ItemType.FOOD,ItemSubType.UNKNOWN));
        unlimitedItems.add(new ShopItem("Coffee", "It smells delicious. This is sure to give you a boost.", 300,ItemType.FOOD,ItemSubType.UNKNOWN));

        limitedItems.add(new LimitedShopItem("Hash Browns", "A recipe to make Hashbrowns.", 50, 1,ItemType.RECIPE, ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("Omelet", "A recipe to make Omelet.", 100, 1,ItemType.RECIPE, ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("Pancakes ", "A recipe to make Pancakes.", 100, 1,ItemType.RECIPE, ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("Bread", "A recipe to make Bread.", 100, 1,ItemType.RECIPE, ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("Tortilla", "A recipe to make Tortilla.", 100, 1,ItemType.RECIPE, ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("Pizza", "A recipe to make Pizza.", 150, 1,ItemType.RECIPE, ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("Maki Roll", "A recipe to make Maki Roll.", 300, 1,ItemType.RECIPE, ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("Triple Shot Espresso", "A recipe to make Triple Shot Espresso.", 5000, 1,ItemType.RECIPE, ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("Cookie", "A recipe to make Cookie.", 300, 1,ItemType.RECIPE, ItemSubType.UNKNOWN));




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
