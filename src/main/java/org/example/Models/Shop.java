package org.example.Models;

import java.util.List;

public interface Shop {
    List<ShopItem> getUnlimitedItems();
    List<LimitedShopItem> getLimitedItems();
    void resetDailyLimits();
}