package org.example.Models.Shops;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;
import org.example.Models.LimitedShopItem;
import org.example.Models.Shop;
import org.example.Models.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class GeneralStore implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    @Override
    public String getName() {
        return "Pierre's General Store";
    }

    public GeneralStore() {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();







        unlimitedItems.add(new ShopItem("Parsnip Seeds", "Plant these in spring. Takes 4 days to grow.", 20, ItemType.MATERIAL, ItemSubType.SEED));
        unlimitedItems.add(new ShopItem("Bean Starter", "Spring crop. Grows on a trellis. Takes 10 days to mature.", 60,ItemType.MATERIAL,ItemSubType.SEED));
        unlimitedItems.add(new ShopItem("Cauliflower Seeds", "Plant in spring. Takes 12 days to grow.", 80,ItemType.MATERIAL,ItemSubType.SEED));
        unlimitedItems.add(new ShopItem("Potato Seeds", "Plant in spring. Takes 6 days to grow, and may yield multiple potatoes.", 50,ItemType.MATERIAL,ItemSubType.SEED));
        unlimitedItems.add(new ShopItem("Speed-Gro", "Improves soil quality a little, increasing your chance to grow quality crops.", 100,ItemType.MATERIAL,ItemSubType.FERTILIZER));
        limitedItems.add(new LimitedShopItem("Backpack Upgrade", "Increases inventory space by 12 slots.", 2000, 1,ItemType.MATERIAL,ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("Deluxe Retaining Soil", "Greatly improves soil quality.", 150, 3, ItemType.MATERIAL,ItemSubType.FERTILIZER));




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