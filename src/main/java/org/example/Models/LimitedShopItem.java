package org.example.Models;

public class LimitedShopItem {
    private String name;
    private int price;
    private int dailyLimit;
    private String description;
    private int purchasedToday = 0;

    public LimitedShopItem(String name, String description, int price, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public void resetPurchasedToday() {
        this.purchasedToday = 0;
    }

    public int getPurchasedToday() {
        return purchasedToday;
    }

    public void increasePurchasedToday(int count) {
        this.purchasedToday += count;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}