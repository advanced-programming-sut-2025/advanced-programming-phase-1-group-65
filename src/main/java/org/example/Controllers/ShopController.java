package org.example.Controllers;

import org.example.Models.*;
import org.example.Models.Enums.TileType;
public class ShopController {

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
        switch (tile.getType()) {
            case BLACKSMITH: return new Blacksmith();
            case GENERALSTORE: return new GeneralStore();
            case JOJAMART: return new JojaMart();
            case CARPENTERSHOP: return new CarpenterShop();
            case FISHSHOP: return new FishShop();
            case RANCH: return new Ranch();
            case STARDROPSALOON: return new StardropSaloon();
            default: return null;
        }
    }
}