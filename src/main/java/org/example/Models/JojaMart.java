package org.example.Models;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

import java.util.ArrayList;
import java.util.List;
public class JojaMart implements Shop {
    private List<ShopItem> unlimitedItems;
    private List<LimitedShopItem> limitedItems;

    public JojaMart(GameClock gameClock) {
        unlimitedItems = new ArrayList<>();
        limitedItems = new ArrayList<>();


        unlimitedItems.add(new ShopItem("Joja Cola", "The flagship product of Joja corporation.", 75,ItemType.FOOD,ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("Ancient Seed", "Could these still grow?", 500, 1,ItemType.MATERIAL,ItemSubType.SEED));
        unlimitedItems.add(new ShopItem("Grass Starter", "Place this on your farm to start a new patch of grass.", 125,ItemType.FOOD,ItemSubType.UNKNOWN));
        unlimitedItems.add(new ShopItem("Sugar", "Adds sweetness to pastries and candies. Too much can be unhealthy.", 125,ItemType.FOOD,ItemSubType.UNKNOWN));
        unlimitedItems.add(new ShopItem("Wheat Flour", "A common cooking ingredient made from crushed wheat seeds.", 125,ItemType.FOOD,ItemSubType.UNKNOWN));
        unlimitedItems.add(new ShopItem("Rice", "A basic grain often served under vegetables.", 250,ItemType.FOOD,ItemSubType.FRUIT));

        String season = gameClock.getCurrentSeason();

        switch (season) {
            case "Spring":
                // آیتم‌های محدود به فصل بهار
                limitedItems.add(new LimitedShopItem("Parsnip Seeds", "Plant these in the spring. Takes 4 days to mature.", 25, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Bean Starter", "Plant these in the spring. Takes 10 days to mature, but keeps producing after that. Grows on a trellis.", 75, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Cauliflower Seeds", "Plant these in the spring. Takes 12 days to produce a large cauliflower.", 100, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Potato Seeds", "Plant these in the spring. Takes 6 days to mature, and has a chance of yielding multiple potatoes at harvest.", 62, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Strawberry Seeds", "Plant these in spring. Takes 8 days to mature, and keeps producing strawberries after that.", 100, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Tulip Bulb", "Plant in spring. Takes 6 days to produce a colorful flower. Assorted colors.", 25, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Kale Seeds", "Plant these in the spring. Takes 6 days to mature. Harvest with the scythe.", 87, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Coffee Beans", "Plant in summer or spring. Takes 10 days to grow, then produces coffee Beans every other day.", 200, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Carrot Seeds", "Plant in the spring. Takes 3 days to grow.", 5, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Rhubarb Seeds", "Plant these in the spring. Takes 13 days to mature.", 100, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Jazz Seeds", "Plant in spring. Takes 7 days to produce a blue puffball flower.", 37, 1,ItemType.MATERIAL,ItemSubType.SEED));
                break;

            case "Summer":
                // آیتم‌های محدود به فصل تابستان
                limitedItems.add(new LimitedShopItem("Tomato Seeds", "Plant these in the summer. Takes 11 days to mature, and continues to produce after first harvest.", 62, 1, ItemType.FOOD, ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Pepper Seeds", "Plant these in the summer. Takes 5 days to mature, and continues to produce after first harvest.", 50, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Summer Squash Seeds", "Plant in the summer. Takes 6 days to grow, and continues to produce after first harvest.", 10, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Radish Seeds", "Plant these in the summer. Takes 6 days to mature.", 50, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Melon Seeds", "Plant these in the summer. Takes 12 days to mature.", 100, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Hops Starter", "Plant these in the summer. Takes 11 days to grow, but keeps producing after that. Grows on a trellis.", 75, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Poppy Seeds", "Plant in summer. Produces a bright red flower in 7 days.", 125, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Starfruit Seeds", "Plant these in the summer. Takes 13 days to mature.", 400, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Sunflower Seeds", "Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest.", 125, 1,ItemType.MATERIAL,ItemSubType.SEED));
                break;

            case "Fall":
                // آیتم‌های محدود به فصل پاییز
                limitedItems.add(new LimitedShopItem("Corn Seeds", "Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest.", 187, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Eggplant Seeds", "Plant these in the fall. Takes 5 days to mature, and continues to produce after first harvest.", 25, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Pumpkin Seeds", "Plant these in the fall. Takes 13 days to mature.", 125, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Amaranth Seeds", "Plant these in the fall. Takes 7 days to grow. Harvest with the scythe.", 87, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Beet Seeds", "Plant these in the fall. Takes 6 days to mature.", 20, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Cranberry Seeds", "Plant these in the fall. Takes 7 days to mature, and continues to produce after first harvest.", 300, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Fairy Seeds", "Plant in fall. Takes 12 days to produce a mysterious flower. Assorted Colors.", 250, 1,ItemType.MATERIAL,ItemSubType.SEED));
                limitedItems.add(new LimitedShopItem("Rare Seed", "Sow in fall. Takes all season to grow.", 1000, 1,ItemType.MATERIAL,ItemSubType.SEED));
                break;

            case "Winter":
                // آیتم‌های محدود به فصل زمستان
                limitedItems.add(new LimitedShopItem("Powdermelon Seeds", "This special melon grows in the winter. Takes 7 days to grow.", 20, 1,ItemType.MATERIAL,ItemSubType.SEED));
                break;
        }

        // آیتم‌های محدود به طور ثابت
        limitedItems.add(new LimitedShopItem("Joja Membership", "Grants access to special Joja benefits.", 5000, 1,ItemType.MATERIAL,ItemSubType.UNKNOWN));
        limitedItems.add(new LimitedShopItem("Battery Pack", "Used to power some machines.", 2500, 2,ItemType.MATERIAL,ItemSubType.UNKNOWN));
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