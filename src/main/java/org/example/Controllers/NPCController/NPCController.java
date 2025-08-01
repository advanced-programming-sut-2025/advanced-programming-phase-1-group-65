package org.example.Controllers.NPCController;

import org.example.Controllers.GameController.GameController;
import org.example.Models.*;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;
import org.example.Models.Enums.TileType;

import java.util.*;
import java.util.Map;

public class NPCController {

    private Game game;
    private GameController controller;

    public NPCController(Game game) {
        this.game = game;
        this.controller = controller;
    }

    public NPC findNearbyNPCByName(String npcName) {
        int px = game.currentPlayer.PositionX;
        int py = game.currentPlayer.PositionY;

        ArrayList<ArrayList<Tile>> map = game.Map;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = px + dx;
                int ny = py + dy;

                if (nx >= 0 && ny >= 0 && ny < map.size() && nx < map.get(ny).size()) {
                    Tile tile = map.get(ny).get(nx);
                    if (isNPCTile(tile.getType())) {
                        String tileNPCName = getNameByTileType(tile.getType());
                        if (tileNPCName.equalsIgnoreCase(npcName)) {
                            return getNPCByTileType(tile.getType(), nx, ny);
                        }
                    }
                }
            }
        }
        return null;
    }

    private boolean isNPCTile(TileType type) {
        switch (type) {
            case SEBASTIAN_NPC:
            case ABIGAIL_NPC:
            case HARVEY_NPC:
            case LEAH_NPC:
            case ROBIN_NPC:
                return true;
            default:
                return false;
        }
    }

    private String getNameByTileType(TileType type) {
        switch (type) {
            case SEBASTIAN_NPC:
                return "Sebastian";
            case ABIGAIL_NPC:
                return "Abigail";
            case HARVEY_NPC:
                return "Harvey";
            case LEAH_NPC:
                return "Leah";
            case ROBIN_NPC:
                return "Robin";
            default:
                return "";
        }
    }

    private NPC getNPCByTileType(TileType type, int x, int y) {
        String name = getNameByTileType(type);
        NPC npc = new NPC(name, x, y);
        initializeNPCData(npc);
        return npc;
    }

    private void initializeNPCData(NPC npc) {
        Map<Integer, Quest> quests = new HashMap<>();

        switch (npc.getName().toLowerCase()) {
            case "sebastian":
                npc.setFavoriteItems(new HashSet<>(Arrays.asList("Wool", "Pumpkin Pie", "Pizza")));

                Quest q1 = new Quest(1, "Deliver 50 Iron", "Deliver 50 Iron to receive 2 Diamonds", 0);
                q1.addRequiredItem("Iron", 50);
                q1.addRewardItem("Diamond", 2);

                Quest q2 = new Quest(2, "Deliver Pumpkin Pie", "Deliver Pumpkin Pie to receive 5000 Gold", 1);
                q2.addRequiredItem("Pumpkin Pie", 1);
                q2.addRewardItem("Gold", 5000);

                Quest q3 = new Quest(3, "Deliver 150 Stone", "Deliver 150 Stone to receive 50 Quartz", 2);
                q3.addRequiredItem("Stone", 150);
                q3.addRewardItem("Quartz", 50);

                Quest q4 = new Quest(4, "Deliver 1 Gold Bar", "Deliver 1 Gold Bar to gain 1 Friendship Level", 3);
                q4.addRequiredItem("Gold Bar", 1);
                q4.addRewardItem("FriendshipLevel", 1);

                quests.put(0, q1);
                quests.put(1, q2);
                quests.put(2, q3);
                quests.put(3, q4);
                break;

            case "abigail":
                npc.setFavoriteItems(new HashSet<>(Arrays.asList("Stone", "Iron Ore", "Coffee")));

                Quest aq1 = new Quest(5, "Deliver 150 Stone", "Deliver 150 Stone to receive 50 Quartz", 0);
                aq1.addRequiredItem("Stone", 150);
                aq1.addRewardItem("Quartz", 50);

                Quest aq2 = new Quest(6, "Deliver 1 Lazy Pumpkin", "Deliver 1 Lazy Pumpkin to receive 500 Gold", 1);
                aq2.addRequiredItem("Lazy Pumpkin", 1);
                aq2.addRewardItem("Gold", 500);

                Quest aq3 = new Quest(7, "Deliver 50 Wheat and 1 Iridium Sprinkler", "Deliver 50 Wheat and 1 Iridium Sprinkler to receive 750 Gold", 2);
                aq3.addRequiredItem("Wheat", 50);
                aq3.addRequiredItem("Iridium Sprinkler", 1);
                aq3.addRewardItem("Gold", 750);

                Quest aq4 = new Quest(8, "Deliver 12 Favorite Flowers", "Deliver 12 Favorite Flowers to receive 750 Gold", 3);
                aq4.addRequiredItem("Favorite Flower", 12);
                aq4.addRewardItem("Gold", 750);

                quests.put(0, aq1);
                quests.put(1, aq2);
                quests.put(2, aq3);
                quests.put(3, aq4);
                break;

            case "harvey":
                npc.setFavoriteItems(new HashSet<>(Arrays.asList("Coffee", "Grape", "Wine")));

                Quest hq1 = new Quest(9, "Deliver 1 Healthy Salmon", "Deliver 1 Healthy Salmon to gain 1 Friendship Level", 0);
                hq1.addRequiredItem("Healthy Salmon", 1);
                hq1.addRewardItem("FriendshipLevel", 1);

                Quest hq2 = new Quest(10, "Deliver 1 Bottle of Wine and 5 Salads", "Deliver 1 Bottle of Wine and 5 Salads", 1);
                hq2.addRequiredItem("Wine", 1);
                hq2.addRequiredItem("Salad", 5);

                Quest hq3 = new Quest(11, "Deliver 10 Hard Wood", "Deliver 10 Hard Wood to receive 500 Gold", 2);
                hq3.addRequiredItem("Hard Wood", 10);
                hq3.addRewardItem("Gold", 500);

                quests.put(0, hq1);
                quests.put(1, hq2);
                quests.put(2, hq3);
                break;

            case "leah":
                npc.setFavoriteItems(new HashSet<>(Arrays.asList("Salad", "Grape", "Wine")));

                Quest lq1 = new Quest(13, "Deliver 1 Cooking Recipe", "Deliver 1 Cooking Recipe (Dinner Salmon)", 0);
                lq1.addRequiredItem("Cooking Recipe - Dinner Salmon", 1);

                Quest lq2 = new Quest(14, "Deliver 1 Healthy Salmon", "Deliver 1 Healthy Salmon", 1);
                lq2.addRequiredItem("Healthy Salmon", 1);

                Quest lq4 = new Quest(16, "Deliver 200 Wood", "Deliver 200 Wood", 3);
                lq4.addRequiredItem("Wood", 200);

                Quest lq5 = new Quest(17, "Deliver 80 Wood", "Deliver 80 Wood to receive 1000 Gold", 4);
                lq5.addRequiredItem("Wood", 80);
                lq5.addRewardItem("Gold", 1000);

                quests.put(0, lq1);
                quests.put(1, lq2);
                quests.put(3, lq4);
                quests.put(4, lq5);
                break;

            case "robin":
                npc.setFavoriteItems(new HashSet<>(Arrays.asList("Spaghetti", "Wood", "Iron Bar")));

                Quest rq1 = new Quest(17, "Deliver 10 Iron Bars", "Deliver 10 Iron Bars to receive 3 Beehouses", 0);
                rq1.addRequiredItem("Iron Bar", 10);
                rq1.addRewardItem("Beehouse", 3);

                Quest rq2 = new Quest(18, "Deliver 1000 Wood", "Deliver 1000 Wood to receive 25000 Gold", 1);
                rq2.addRequiredItem("Wood", 1000);
                rq2.addRewardItem("Gold", 25000);

                quests.put(0, rq1);
                quests.put(1, rq2);
                break;

            default:
                npc.setFavoriteItems(new HashSet<>());
                break;
        }

        npc.setFriendshipQuests(quests);
        npc.setFriendshipLevel(0);
        npc.setLastGiftDay(-1);
    }


    public String talkToNPCByName(String npcName) {
        if (!isValidNPCName(npcName)) {
            return "There is no NPC with the name '" + npcName + "' in the game.";
        }

        NPC npc = findNearbyNPCByName(npcName);
        if (npc == null) {
            return "There is no NPC named '" + npcName + "' nearby.";
        }

        int currentDay = game.gameClock.getDay();
        int hour = game.gameClock.getHour();
        String currentSeason = game.gameClock.getCurrentSeason();
        String currentWeather = game.weatherSystem.getTodayWeatherName();

        return npc.meet(currentDay, hour, currentSeason, currentWeather, npc.getX(), npc.getY());
    }

    public String giftNPC(String npcName, String itemName) {
        if (!isValidNPCName(npcName)) {
            return "There is no NPC with the name '" + npcName + "' in the game.";
        }

        NPC npc = findNearbyNPCByName(npcName);
        if (npc == null) {
            return "There is no NPC named '" + npcName + "' nearby.";
        }

        Player player = game.currentPlayer;

        Item itemToGive = null;
        for (Item item : player.items) {
            if (item.name.equalsIgnoreCase(itemName)) {
                itemToGive = item;
                break;
            }
        }

        if (itemToGive == null) {
            return "You don't have any '" + itemName + "' in your inventory.";
        }

        if (itemToGive instanceof Tool) {
            return "You cannot gift tools.";
        }

        if (npc.getLastGiftDay() == game.gameClock.getDay()) {
            return "You've already gifted " + npcName + " today.";
        }

        itemToGive.Count--;
        if (itemToGive.Count == 0) {
            player.items.remove(itemToGive);
        }

        int friendshipPoints = 50;
        if (npc.getFavoriteItems().contains(itemToGive.name)) {
            friendshipPoints = 200;
        }

        int currentPoints = player.npcFriendships.getOrDefault(npc.getName(), 0);
        int newPoints = currentPoints + friendshipPoints;
        if (newPoints > 799) {
            newPoints = 799;
        }

        player.npcFriendships.put(npc.getName(), newPoints);

        npc.setFriendshipPoints(newPoints);

        int newLevel = newPoints / 200;
        if (newLevel > 3) newLevel = 3;
        npc.setFriendshipLevel(newLevel);

        npc.setLastGiftDay(game.gameClock.getDay());

        return "You gave " + itemName + " to " + npcName + ". Friendship +" + friendshipPoints + ".";
    }

    public String getAllNPCFriendships() {
        StringBuilder sb = new StringBuilder("NPC Friendships:\n");
        for (Map.Entry<String, Integer> entry : game.currentPlayer.npcFriendships.entrySet()) {
            String npcName = entry.getKey();
            int points = entry.getValue();
            int level = points / 200;
            if (level > 3) {
                level = 3;
            }

            sb.append("- ").append(npcName)
                    .append(": ").append(points).append(" points")
                    .append(" (Level ").append(level).append(")\n");
        }
        return sb.toString();
    }

    public String getAvailableQuests() {
        Player player = game.currentPlayer;
        StringBuilder sb = new StringBuilder("Available Quests:\n");

        List<String> allNPCNames = Arrays.asList("Sebastian", "Abigail", "Harvey", "Leah", "Robin");

        boolean anyQuestFound = false;

        for (String npcName : allNPCNames) {
            NPC npc = getNPCWithFriendshipLevel(npcName);

            Map<Integer, Quest> quests = npc.getFriendshipQuests();

            for (int level = 0; level <= npc.getFriendshipLevel(); level++) {
                Quest quest = quests.get(level);
                if (quest == null) continue;

                if (!player.completedQuestIds.contains(quest.getId())) {
                    sb.append("- ").append(npcName)
                            .append(" (Level ").append(level).append("): ")
                            .append(quest.getDescription())
                            .append(" [Quest ID: ").append(quest.getId()).append("]\n");
                    anyQuestFound = true;
                }
            }
        }

        if (!anyQuestFound) {
            return "No quests available right now.";
        }

        return sb.toString();
    }
    public void showAllQuests() {
        System.out.println(getAvailableQuests());
    }
    public void removeItem(Game game, String name, int countToRemove) {
        for (Item item : game.currentPlayer.items) {
            if (item.name.equalsIgnoreCase(name)) {
                if(item.type.equals(ItemType.TOOL)){
                    System.out.println("You can't remove a tool.");
                    return;
                }
                if (item.Count > countToRemove) {
                    item.Count -= countToRemove;
                    System.out.println("You removed "+countToRemove+" from "+name);
                    return;
                } else if (item.Count == countToRemove) {
                    game.currentPlayer.items.remove(item);
                    System.out.println("You removed "+name);
                    return;
                } else {
                    System.out.println("This amount is more than you have.");
                    return ;

                }
            }
        }
        System.out.println("You don't have this item.");
        return;
    }
    public boolean HasItem(Game game , String name,int requiredCount){
        for (Item item : game.currentPlayer.items){
            if(item.name.equalsIgnoreCase(name) && item.Count >= requiredCount){
                return true;
            }
        }
        return false;
    }
    public void AddItem(Game game, Item newitem) {
        for (Item item : game.currentPlayer.items) {
            if(item.name.equalsIgnoreCase(newitem.name) && item.subtype.equals(newitem.subtype)){
                item.Count = item.Count + newitem.Count;
                return;
            }
        }
        game.currentPlayer.items.add(newitem);
        return;
    }
    public String completeQuest(String npcName, int questId) {
        if (!isValidNPCName(npcName)) {
            return "There is no NPC with the name '" + npcName + "' in the game.";
        }
        NPC npc = findNearbyNPCByName(npcName);
        if (npc == null) {
            return "NPC '" + npcName + "' is not nearby to complete the quest.";
        }

        Player player = game.currentPlayer;

        Map<Integer, Quest> quests = npc.getFriendshipQuests();
        Quest quest = null;


        for (Quest q : quests.values()) {
            if (q.getId() == questId) {
                quest = q;
                break;
            }
        }

        if (quest == null) {
            return npcName + " does not have a quest with ID " + questId + ".";
        }

        if (player.completedQuestIds.contains(quest.getId())) {
            return "You have already completed this quest.";
        }

        for (Map.Entry<String, Integer> requiredItem : quest.getRequiredItems().entrySet()) {
            String itemName = requiredItem.getKey();
            int requiredCount = requiredItem.getValue();

            if (!HasItem(game, itemName, requiredCount)) {
                return "You don't have enough '" + itemName + "' to complete the quest.";
            }
        }

        for (Map.Entry<String, Integer> requiredItem : quest.getRequiredItems().entrySet()) {
            String itemName = requiredItem.getKey();
            int requiredCount = requiredItem.getValue();
            removeItem(game, itemName, requiredCount);
        }

        for (Map.Entry<String, Integer> rewardItem : quest.getRewardItems().entrySet()) {
            String rewardName = rewardItem.getKey();
            int rewardCount = rewardItem.getValue();

            if (rewardName.equalsIgnoreCase("FriendshipLevel")) {
                int currentPoints = player.npcFriendships.getOrDefault(npcName, 0);
                int newPoints = currentPoints + (rewardCount * 200);
                if (newPoints > 799) newPoints = 799;

                player.npcFriendships.put(npcName, newPoints);
                npc.setFriendshipPoints(newPoints);

                int newLevel = newPoints / 200;
                if (newLevel > 3) newLevel = 3;
                npc.setFriendshipLevel(newLevel);
            } else if (rewardName.equalsIgnoreCase("Gold")) {
                player.money += rewardCount;
            }
            else if (rewardName.equalsIgnoreCase("Cooking Recipe - Dinner Salmon")) {
                game.currentPlayer.KnownRecipes.add(game.AllRecipes.get(17));
            }
            else {
                ItemType type = ItemType.MATERIAL;
                ItemSubType subtype = ItemSubType.NONE;
                int count = rewardCount;
                String name = rewardName;
                Integer price = 20;

                Item newItem = new Item(type, subtype, count, name, price);
                AddItem(game, newItem);
            }
        }

        player.completedQuestIds.add(quest.getId());

        return "Quest '" + quest.getTitle() + "' completed successfully!";
    }
    public String finishQuestById(int questId) {
        List<String> npcNames = Arrays.asList("Sebastian", "Abigail", "Harvey", "Leah", "Robin");
        for (String npcName : npcNames) {
            NPC npc = getNPCWithFriendshipLevel(npcName);
            Map<Integer, Quest> quests = npc.getFriendshipQuests();

            for (Quest q : quests.values()) {
                if (q.getId() == questId) {
                    return completeQuest(npcName, questId);
                }
            }
        }
        return "Quest with ID " + questId + " not found for any NPC.";
    }
    private boolean isValidNPCName(String npcName) {
        switch (npcName.toLowerCase()) {
            case "sebastian":
            case "abigail":
            case "harvey":
            case "leah":
            case "robin":
                return true;
            default:
                return false;
        }
    }
    private NPC getNPCWithFriendshipLevel(String npcName) {
        NPC npc = new NPC(npcName, -1, -1);
        initializeNPCData(npc);

        int friendshipPoints = game.currentPlayer.npcFriendships.getOrDefault(npcName, 0);
        int friendshipLevel = friendshipPoints / 200;
        if (friendshipLevel > 3) friendshipLevel = 3;

        npc.setFriendshipLevel(friendshipLevel);
        npc.setFriendshipPoints(friendshipPoints);
        return npc;
    }
}
