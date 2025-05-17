package org.example.Models.Shops;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;
import org.example.Models.LimitedShopItem;
import org.example.Models.Shop;
import org.example.Models.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class CarpenterShop implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    @Override
    public String getName() {
        return "Carpenter's Shop";
    }

    public CarpenterShop() {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();

       unlimitedItems.add(new ShopItem("Wood", "Basic material used for crafting and building.", 10, ItemType.MATERIAL, ItemSubType.WOOD));
        unlimitedItems.add(new ShopItem("Stone", "Common material used in construction.", 20,ItemType.MATERIAL,ItemSubType.STONE));
        unlimitedItems.add(new ShopItem("Basic Window", "A decorative window for your house.", 200,ItemType.MATERIAL,ItemSubType.UNKNOWN));
       unlimitedItems.add(new ShopItem("Wooden Floor", "Used to decorate your house.", 100,ItemType.MATERIAL,ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("House Upgrade", "Expand your house with a kitchen.", 10000, 1,ItemType.MATERIAL,ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("Barn", "Houses 4 barn animals requires 350 wood , 150 stone", 6000, 1,ItemType.MATERIAL,ItemSubType.BARN));
        limitedItems.add(new LimitedShopItem("Big Barn", "Houses 8 barn animals requires 450 wood , 200 stone.", 12000, 1,ItemType.MATERIAL,ItemSubType.BARN));
        limitedItems.add(new LimitedShopItem("Deluxe Barn","Houses 12 barn animals requires 550 wood , 300 stone",25000,1,ItemType.MATERIAL,ItemSubType.BARN));
        limitedItems.add(new LimitedShopItem("Coop", "Houses 4 coop animals requires 300 wood , 100 stone.", 4000, 1,ItemType.MATERIAL,ItemSubType.COOP));
        limitedItems.add(new LimitedShopItem("Big Coop", "Houses 8 coop animals requires 400 wood , 150 stone.", 10000, 1,ItemType.MATERIAL,ItemSubType.COOP));
        limitedItems.add(new LimitedShopItem("Deluxe Coop","Houses 12 coop animals requires 500 wood , 200 stone",20000,1,ItemType.MATERIAL,ItemSubType.COOP));



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