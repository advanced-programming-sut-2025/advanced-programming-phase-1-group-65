package org.example.Models.Shops;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;
import org.example.Models.LimitedShopItem;
import org.example.Models.Shop;
import org.example.Models.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class Ranch implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    @Override
    public String getName() {
        return "Marnie's Ranch";
    }

    public Ranch() {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();


        unlimitedItems.add(new ShopItem("Hay", "Dried grass used as animal food.", 50, ItemType.MATERIAL, ItemSubType.HAY));
        limitedItems.add(new LimitedShopItem("Rabbit", "These are wooly rabbits! They shed precious wool every few days. Lives in the coop.", 8000 , 2,ItemType.MATERIAL,ItemSubType.RABBIT));
        limitedItems.add(new LimitedShopItem("Chicken", "Well cared-for chickens lay eggs every day. Lives in the coop.", 800, 2,ItemType.MATERIAL,ItemSubType.CHICKEN));
        limitedItems.add(new LimitedShopItem("Cow", "Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn.", 1500, 2,ItemType.MATERIAL,ItemSubType.COW));
        limitedItems.add(new LimitedShopItem("Goat", "Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn.", 4000, 2,ItemType.MATERIAL,ItemSubType.GOAT));
        limitedItems.add(new LimitedShopItem("Duck", "Happy lay duck eggs every other day. Lives in the coop.", 1200, 2,ItemType.MATERIAL,ItemSubType.DUCK));
        limitedItems.add(new LimitedShopItem("Pig", "These pigs are trained to find truffles! Lives in the barn.", 16000, 2,ItemType.MATERIAL,ItemSubType.PIG));
        limitedItems.add(new LimitedShopItem("Dinosaur", "The Dinosaur is a farm animal that lives in a Big Coop.", 14000, 2,ItemType.MATERIAL,ItemSubType.DINOSAUR));
        limitedItems.add(new LimitedShopItem("Sheep", "Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn.", 8000, 2,ItemType.MATERIAL,ItemSubType.SHEEP));


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
