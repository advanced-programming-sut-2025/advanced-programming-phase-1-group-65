package org.example.Controllers;

import org.example.Models.*;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.TileType;

import java.util.HashMap;
import java.util.Map;

public class ShopController {

    private final Map<TileType, Shop> shopCache = new HashMap<>();

    private boolean isPlayerInShop(Game game) {
        int playerX = game.currentPlayer.PositionX;
        int playerY = game.currentPlayer.PositionY;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = playerX + j;
                int newY = playerY + i;

                if (newX >= 0 && newY >= 0 && newY < game.Map.size() && newX < game.Map.get(0).size()) {
                    Tile tile = game.Map.get(newY).get(newX);
                    if (isShopTile(tile)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isShopTile(Tile tile) {
        return tile.getType() == TileType.BLACKSMITH ||
                tile.getType() == TileType.JOJAMART ||
                tile.getType() == TileType.GENERALSTORE ||
                tile.getType() == TileType.CARPENTERSHOP ||
                tile.getType() == TileType.FISHSHOP ||
                tile.getType() == TileType.RANCH ||
                tile.getType() == TileType.STARDROPSALOON;
    }

    public void showAllProducts(Game game) {
        if (!isPlayerInShop(game)) {
            System.out.println("You must be next to a shop to view its products.");
            return;
        }
        showProductsByTileType(game);
    }

    public void showAllAvailableProducts(Game game) {
        if (!isPlayerInShop(game)) {
            System.out.println("You must be next to a shop to view available products.");
            return;
        }
        showAvailableProductsByTileType(game);
    }

    private void showProductsByTileType(Game game) {
        Shop shop = getNearbyShop(game);
        if (shop == null) return;

        System.out.println("All Products:");
        for (ShopItem item : shop.getUnlimitedItems()) {
            System.out.println("- " + item.getName() + " | " + item.getDescription() + " | " + item.getPrice() + "g");
        }

        for (LimitedShopItem item : shop.getLimitedItems()) {
            System.out.println("- " + item.getName() + " | " + item.getDescription() +
                    " | " + item.getPrice() + "g | Daily Limit: " + item.getDailyLimit());
        }
    }

    private void showAvailableProductsByTileType(Game game) {
        Shop shop = getNearbyShop(game);
        if (shop == null) return;

        System.out.println("Available Products:");
        for (ShopItem item : shop.getUnlimitedItems()) {
            System.out.println("- " + item.getName() + " | " + item.getDescription() + " | " + item.getPrice() + "g");
        }

        for (LimitedShopItem item : shop.getLimitedItems()) {
            if (item.getPurchasedToday() < item.getDailyLimit()) {
                System.out.println("- " + item.getName() + " | " + item.getDescription() +
                        " | " + item.getPrice() + "g | Remaining today: " +
                        (item.getDailyLimit() - item.getPurchasedToday()));
            }
        }
    }

    private Shop getNearbyShop(Game game) {
        int playerX = game.currentPlayer.PositionX;
        int playerY = game.currentPlayer.PositionY;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = playerX + j;
                int newY = playerY + i;

                if (newX >= 0 && newY >= 0 && newY < game.Map.size() && newX < game.Map.get(0).size()) {
                    Tile tile = game.Map.get(newY).get(newX);
                    if (isShopTile(tile)) {
                        return getShopFromTile(tile);
                    }
                }
            }
        }
        return null;
    }

    private Shop getShopFromTile(Tile tile) {
        TileType type = tile.getType();
        GameClock gameClock = new GameClock();
        if (!shopCache.containsKey(type)) {
            switch (type) {
                case BLACKSMITH: shopCache.put(type, new Blacksmith()); break;
                case GENERALSTORE: shopCache.put(type, new GeneralStore()); break;
                case JOJAMART: shopCache.put(type, new JojaMart(gameClock)); break;
                case CARPENTERSHOP: shopCache.put(type, new CarpenterShop()); break;
                case FISHSHOP: shopCache.put(type, new FishShop()); break;
                case RANCH: shopCache.put(type, new Ranch()); break;
                case STARDROPSALOON: shopCache.put(type, new StardropSaloon()); break;
            }
        }
        return shopCache.get(type);
    }

    public void purchaseItem(Game game, String productName, int count,GameController controller) {
        if (count <= 0) {
            System.out.println("Invalid quantity specified.");
            return;
        }

        if (!isPlayerInShop(game)) {
            System.out.println("You must be next to a shop to buy items.");
            return;
        }

        Shop shop = getNearbyShop(game);
        if (shop == null) {
            System.out.println("No valid shop nearby.");
            return;
        }

        for (ShopItem item : shop.getUnlimitedItems()) {
            if (item.getName().equalsIgnoreCase(productName)) {
                int totalPrice = item.getPrice() * count;
                if (game.currentPlayer.money < totalPrice) {
                    System.out.println("You don't have enough money.");
                    return;
                }

                game.currentPlayer.money -= totalPrice;
                System.out.println("You bought " + count + " x " + item.getName() + " for " + totalPrice + "g.");
                return;
            }
        }

        for (LimitedShopItem item : shop.getLimitedItems()) {
            if (item.getName().equalsIgnoreCase(productName)) {
                int remaining = item.getDailyLimit() - item.getPurchasedToday();
                if (remaining <= 0) {
                    System.out.println("This item has reached its daily purchase limit.");
                    return;
                }

                if (count > remaining) {
                    System.out.println("Only " + remaining + " of this item can be bought today.");
                    return;
                }

                int totalPrice = item.getPrice() * count;
                if (game.currentPlayer.money < totalPrice) {
                    System.out.println("You don't have enough money.");
                    return;
                }

                game.currentPlayer.money -= totalPrice;
                item.increasePurchasedToday(count);
                if(item.subtype == ItemSubType.SEED) {
                    for (Foraging foraging : game.AllCropInfo){
                        if (foraging.Seed.name.equalsIgnoreCase(item.name)) {
                            Material shopseed = foraging.Seed;
                            shopseed.Count = count;
                            controller.AddItem(game,shopseed);
                            break;
                        }
                    }
                }
                else {
                    controller.AddItem(game,item);
                }
                System.out.println("You bought " + count + " x " + item.getName() + " for " + totalPrice + "g.");
                return;
            }
        }

        System.out.println("Item not found in this shop.");
    }
}