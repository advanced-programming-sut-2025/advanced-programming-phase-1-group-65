package org.example.Models;

import java.util.List;

public interface Shop {
    String getName();
    List<ShopItem> getUnlimitedItems();
    List<LimitedShopItem> getLimitedItems();
    void resetDailyLimits();
}
