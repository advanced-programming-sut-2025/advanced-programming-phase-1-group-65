package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class GeneralStore implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    public GeneralStore() {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();

        unlimitedItems.add(new ShopItem("Parsnip Seeds", "Plant these in spring. Takes 4 days to grow.", 20));
        unlimitedItems.add(new ShopItem("Bean Starter", "Spring crop. Grows on a trellis. Takes 10 days to mature.", 60));
        unlimitedItems.add(new ShopItem("Cauliflower Seeds", "Plant in spring. Takes 12 days to grow.", 80));
        unlimitedItems.add(new ShopItem("Potato Seeds", "Plant in spring. Takes 6 days to grow, and may yield multiple potatoes.", 50));
        unlimitedItems.add(new ShopItem("Fertilizer", "Improves soil quality a little, increasing your chance to grow quality crops.", 100));

        limitedItems.add(new LimitedShopItem("Backpack Upgrade", "Increases inventory space by 12 slots.", 2000, 1));
        limitedItems.add(new LimitedShopItem("Quality Fertilizer Pack", "Greatly improves soil quality.", 150, 3));
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