package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Ranch implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    public Ranch() {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();


       /* unlimitedItems.add(new ShopItem("Hay", "Dried grass used as animal food.", 50));

        limitedItems.add(new LimitedShopItem("Milk Pail", "Gather milk from your animals.", 1000, 1));
        limitedItems.add(new LimitedShopItem("Shears", "Used to collect wool from sheep.", 1000, 1));
        limitedItems.add(new LimitedShopItem("Rabbit", "These are wooly rabbits! They shed precious wool every few days. Lives in the coop.", 8000 , 2));
        limitedItems.add(new LimitedShopItem("Chicken", "Well cared-for chickens lay eggs every day. Lives in the coop.", 800, 2));
        limitedItems.add(new LimitedShopItem("Cow", "Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn.", 1500, 2));
        limitedItems.add(new LimitedShopItem("Goat", "Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn.", 4000, 2));
        limitedItems.add(new LimitedShopItem("Duck", "Happy lay duck eggs every other day. Lives in the coop.", 1200, 2));
        limitedItems.add(new LimitedShopItem("Pig", "These pigs are trained to find truffles! Lives in the barn.", 16000, 2));
        limitedItems.add(new LimitedShopItem("Dinosaur", "The Dinosaur is a farm animal that lives in a Big Coop.", 14000, 2));
        limitedItems.add(new LimitedShopItem("Sheep", "Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn.", 8000, 2));

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