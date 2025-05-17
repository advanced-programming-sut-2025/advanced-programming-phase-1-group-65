package org.example.Controllers.NPCController;

import org.example.Models.*;
import org.example.Models.Enums.TileType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class NPCController {

    private Game game;

    public NPCController(Game game) {
        this.game = game;
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
        switch (npc.getName().toLowerCase()) {
            case "sebastian":
                npc.setFavoriteItems(new HashSet<>(Arrays.asList(
                        "Wool", "Pumpkin Pie", "Pizza"
                )));
                break;
            case "abigail":
                npc.setFavoriteItems(new HashSet<>(Arrays.asList(
                        "Stone", "Iron Ore", "Coffee"
                )));
                break;
            case "harvey":
                npc.setFavoriteItems(new HashSet<>(Arrays.asList(
                        "Coffee", "Grape", "Wine"
                )));
                break;
            case "leah":
                npc.setFavoriteItems(new HashSet<>(Arrays.asList(
                        "Salad", "Grape", "Wine"
                )));
                break;
            case "robin":
                npc.setFavoriteItems(new HashSet<>(Arrays.asList(
                        "Spaghetti", "Wood", "Iron Bar"
                )));
                break;
            default:
                npc.setFavoriteItems(new HashSet<>());
                break;
        }

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
}