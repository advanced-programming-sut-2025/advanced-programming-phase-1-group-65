package org.example.Controllers.ShopController;

import com.badlogic.gdx.graphics.Texture;
import org.example.Controllers.BuildingController.BuildingController;
import org.example.Controllers.GameController.GameController;
import org.example.Models.*;
import org.example.Models.Enums.*;
import org.example.Models.Shops.*;

import java.util.HashMap;
import java.util.Map;

public class ShopController {
    public interface ShopMessageListener {
        void showMessage(String message);
    }

    private ShopMessageListener messageListener;

    public void setMessageListener(ShopMessageListener listener) {
        this.messageListener = listener;
    }

    private void notifyPlayer(String message) {
        if (messageListener != null) {
            messageListener.showMessage(message);

        }
    }
    public interface PurchaseListener {
        void onPurchaseCompleted();
    }

    private PurchaseListener purchaseListener;

    public void setPurchaseListener(PurchaseListener listener) {
        this.purchaseListener = listener;
    }



    private final Map<TileType, Shop> shopCache = new HashMap<>();

    public boolean isPlayerInShop(Game game) {
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
            return;
        }
        showProductsByTileType(game);
    }

    public void showAllAvailableProducts(Game game) {
        if (!isPlayerInShop(game)) {
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

    public Shop getNearbyShop(Game game) {
        int playerX = game.currentPlayer.PositionX;
        int playerY = game.currentPlayer.PositionY;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = playerX + j;
                int newY = playerY + i;

                if (newX >= 0 && newY >= 0 && newY < game.Map.size() && newX < game.Map.get(0).size()) {
                    Tile tile = game.Map.get(newY).get(newX);
                    if (isShopTile(tile)) {
                        return getShopFromTile(tile,game);
                    }
                }
            }
        }
        return null;
    }

    private Shop getShopFromTile(Tile tile,Game game) {
        TileType type = tile.getType();
        GameClock gameClock = game.gameClock;
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
    public Building hasFreeSpace(Game game, AnimalType animalType) {
        if(animalType.equals(AnimalType.CHICKEN)){
            for (Building building :game.currentPlayer.playerBuildings){
                if(building.hasSpace() && (building.name.equalsIgnoreCase("Coop") || building.name.equalsIgnoreCase("Big Coop") || building.name.equalsIgnoreCase("Deluxe Coop"))) {
                    return building;
                }
            }
            return null;
        }
        else if(animalType.equals(AnimalType.DUCK)){
            for (Building building :game.currentPlayer.playerBuildings){
                if(building.hasSpace() && ( building.name.equalsIgnoreCase("Big Coop") || building.name.equalsIgnoreCase("Deluxe Coop"))) {
                    return building;
                }
            }
            return null;
        }
        else if(animalType.equals(AnimalType.RABBIT)){
            for (Building building :game.currentPlayer.playerBuildings){
                if(building.hasSpace() && (building.name.equalsIgnoreCase("Deluxe Coop"))) {
                    return building;
                }
            }
            return null;
        }
        else if(animalType.equals(AnimalType.DINOSAUR)){
            for (Building building :game.currentPlayer.playerBuildings){
                if(building.hasSpace() && ( building.name.equalsIgnoreCase("Big Coop") )) {
                    return building;
                }
            }
            return null;
        }
        else if(animalType.equals(AnimalType.COW)){
            for (Building building :game.currentPlayer.playerBuildings){
                if(building.hasSpace() && ( building.name.equalsIgnoreCase("Big Barn") || building.name.equalsIgnoreCase("Deluxe Barn") || building.name.equalsIgnoreCase("Barn"))) {
                    return building;
                }
            }
            return null;
        }
        else if(animalType.equals(AnimalType.GOAT)){
            for (Building building :game.currentPlayer.playerBuildings){
                if(building.hasSpace() && ( building.name.equalsIgnoreCase("Big Barn") || building.name.equalsIgnoreCase("Deluxe Barn"))) {
                    return building;
                }
            }
            return null;
        }
        else if(animalType.equals(AnimalType.SHEEP)){
            for (Building building :game.currentPlayer.playerBuildings){
                if(building.hasSpace() && (  building.name.equalsIgnoreCase("Deluxe Barn"))) {
                    return building;
                }
            }
            return null;
        }
        else if(animalType.equals(AnimalType.PIG)){
            for (Building building :game.currentPlayer.playerBuildings){
                if(building.hasSpace() && ( building.name.equalsIgnoreCase("Deluxe Barn") )) {
                    return building;
                }
            }
            return null;
        }
        return null;

    }
    public void BuyAnimal(Game game, String animalName, String animalNickName , GameController controller) {



        for(Animal animal : game.AllAnimalInfo){
            if(animal.name.equalsIgnoreCase(animalName)){
                Animal newAnimal = new Animal(animal);
                newAnimal.name = animalNickName;
                if(animal.price > game.currentPlayer.money){
                    System.out.println("You are not enough money to buy this animal.");
                    return;
                }
                if(hasFreeSpace(game,newAnimal.animalType)==null){
                    System.out.println("You need to free space for this animal.");
                    notifyPlayer("You need to free space for this animal.");
                    game.currentPlayer.money += animal.price;
                    //game.gameScreen.showMessage("You need to free space for this animal.");
                    return;
                }
                Building building = hasFreeSpace(game,newAnimal.animalType);
                building.animals.add(newAnimal);
                System.out.println("You bought the animal successfully.");
                game.gameScreen.showMessage("You bought the animal successfully.");
            }
        }
    }
    public void purchaseItem(Game game, String productName, int count,GameController controller , String NickName) {
        BuildingController buildingController = new BuildingController();
        if (count <= 0) {
            notifyPlayer("Invalid quantity specified.");
            return;
        }

        if (!isPlayerInShop(game)) {
            notifyPlayer("You must be next to a shop to buy items.");
            return;
        }

        Shop shop = getNearbyShop(game);
        if (shop == null) {
            notifyPlayer("No valid shop nearby.");
            return;
        }

        for (ShopItem item : shop.getUnlimitedItems()) {
            if (item.getName().equalsIgnoreCase(productName)) {
                int totalPrice = item.getPrice() * count;
                if (game.currentPlayer.money < totalPrice) {
                    notifyPlayer("You don't have enough money.");
                    return;
                }

                game.currentPlayer.money -= totalPrice;
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
                else if(item.subtype==ItemSubType.IRON || item.subtype == ItemSubType.GOLD
                        || item.subtype == ItemSubType.COOPER || item.subtype == ItemSubType.COAL) {
                    for (Rock rock : game.AllRocksInfo){
                        if (rock.Mineral.name.equalsIgnoreCase(item.name)) {
                            Material shopRock = rock.Mineral;
                            shopRock.Count = count;
                            System.out.println(shopRock.name);
                            controller.AddItem(game,shopRock);
                            break;
                        }
                    }
                }
                else if(item.subtype == ItemSubType.FERTILIZER){
                    if(item.name.equalsIgnoreCase("deluxe retaining soil")) {
                        Material fertilizer = new Material(count,ItemSubType.FERTILIZER,"deluxe retaining soil",150);
                        fertilizer.texture = new Texture("Material/Deluxe_Retaining_Soil.png");
                        fertilizer.Count = count;
                        controller.AddItem(game,fertilizer);
                    }
                    if(item.name.equalsIgnoreCase("speed-gro")) {
                        Material fertilizer = new Material(count,ItemSubType.FERTILIZER,"speed-gro",100);
                        fertilizer.texture = new Texture("Material/Speed-Gro.png");

                        fertilizer.Count = count;
                        controller.AddItem(game,fertilizer);
                    }
                }
                else if(item.subtype == ItemSubType.BARN || item.subtype == ItemSubType.COOP){
                    if(!buildingController.canBuild(item.name, game,controller)) {
                        System.out.println("You don't have the materials for this building.");
                        return;
                    }
                    if(item.subtype == ItemSubType.COOP) {
                        Item building = new Item(ItemType.MATERIAL,ItemSubType.COOP,1, item.name, item.getPrice());
                        controller.AddItem(game,building);
                    }
                    else if(item.subtype == ItemSubType.BARN) {
                        Item building = new Item(ItemType.MATERIAL,ItemSubType.BARN,1, item.name, item.getPrice());
                        controller.AddItem(game,building);
                    }
                }
                notifyPlayer("You bought " + count + " x " + item.getName() + " for " + totalPrice + "g.");
                return;
            }
        }

        for (LimitedShopItem item : shop.getLimitedItems()) {
            if (item.getName().equalsIgnoreCase(productName)) {
                int remaining = item.getDailyLimit() - item.getPurchasedToday();
                if (remaining <= 0) {
                    notifyPlayer("This item has reached its daily purchase limit.");
                    return;
                }

                if (count > remaining) {
                    notifyPlayer("Only " + remaining + " of this item can be bought today.");
                    return;
                }

                int totalPrice = item.getPrice() * count;
                if (game.currentPlayer.money < totalPrice) {
                    notifyPlayer("You don't have enough money.");
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
                else if(item.subtype==ItemSubType.IRON || item.subtype == ItemSubType.GOLD
                || item.subtype == ItemSubType.COOPER || item.subtype == ItemSubType.COAL) {
                    for (Rock rock : game.AllRocksInfo){
                        if (rock.Mineral.name.equalsIgnoreCase(item.name)) {
                            Material shopRock = rock.Mineral;
                            shopRock.Count = count;
                            System.out.println(shopRock.name);
                            controller.AddItem(game,shopRock);
                            break;
                        }
                    }
                }
                else if(item.subtype == ItemSubType.FERTILIZER){
                    if(item.name.equalsIgnoreCase("deluxe retaining soil")) {
                        Material fertilizer = new Material(count,ItemSubType.FERTILIZER,"deluxe retaining soil",150);
                        fertilizer.texture = new Texture("Material/Deluxe_Retaining_Soil.png");
                        fertilizer.Count = count;
                        controller.AddItem(game,fertilizer);
                    }
                    if(item.name.equalsIgnoreCase("speed-gro")) {
                        Material fertilizer = new Material(count,ItemSubType.FERTILIZER,"speed-gro",100);
                        fertilizer.texture = new Texture("Material/Speed-Gro.png");
                        fertilizer.Count = count;
                        controller.AddItem(game,fertilizer);
                    }
                }
                else if (item.subtype == ItemSubType.WOOD) {
                Material Wood=    new Material(count,ItemSubType.WOOD,"wood",10);
                   Wood.texture = new Texture("Material/Wood.png");
                   Wood.Count = count;
                   controller.AddItem(game,Wood);
                }
                else if(item.subtype == ItemSubType.BARN || item.subtype == ItemSubType.COOP){
                    if(!buildingController.canBuild(item.name, game,controller)) {
                        notifyPlayer("You don't have the materials for this building.");
                        return;
                    }
                    buildingController.looseMaterial(item.name, game,controller);
                    if(item.subtype == ItemSubType.COOP) {
                        Item building = new Item(ItemType.MATERIAL,ItemSubType.COOP,1, item.name, item.getPrice());
                        building.texture = new Texture("Building/Coop.png");
                        controller.AddItem(game,building);
                    }
                    else if(item.subtype == ItemSubType.BARN) {
                        Item building = new Item(ItemType.MATERIAL,ItemSubType.BARN,1, item.name, item.getPrice());
                        building.texture = new Texture("Building/Barn.png");
                        controller.AddItem(game,building);
                    }

                }
                else if (item.type == ItemType.RECIPE){
                    for (Recipe recipe : game.AllRecipes){
                        if (item.name.equalsIgnoreCase(recipe.name)) {
                            game.currentPlayer.KnownRecipes.add(recipe);
                            game.gameScreen.kitchenUI.rebuildUI();
                        }
                    }
                }
                else if (item.subtype == ItemSubType.RABBIT || item.subtype == ItemSubType.CHICKEN
                || item.subtype == ItemSubType.COW || item.subtype == ItemSubType.GOAT ||
                item.subtype == ItemSubType.DUCK || item.subtype == ItemSubType.PIG || item.subtype == ItemSubType.DINOSAUR
                || item.subtype == ItemSubType.SHEEP){
                    BuyAnimal(game , item.name, NickName, controller);

                }

                //notifyPlayer("You bought " + count + " x " + item.getName() + " for " + totalPrice + "g.");
                if (purchaseListener != null) {
                    purchaseListener.onPurchaseCompleted();
                }
                return;
            }
        }

        notifyPlayer("Item not found in this shop.");
    }
}
