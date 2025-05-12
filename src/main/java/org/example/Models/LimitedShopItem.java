package org.example.Models;

public class LimitedShopItem {
    private String name;
    private int price;
    private int purchaseCount;
    private int dailyLimit;
    private String description;

    public LimitedShopItem(String name, String description, int price, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.purchaseCount = 0;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public void resetPurchaseCount() {
        this.purchaseCount = 0;
    }


    public void incrementPurchaseCount() {
        if (purchaseCount < dailyLimit) {
            purchaseCount++;
        }
    }

    public int getPurchaseCount() {
        return purchaseCount;
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
    public int getPurchasedToday() {
        return purchaseCount;
    }
}