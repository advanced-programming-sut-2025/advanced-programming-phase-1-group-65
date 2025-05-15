package org.example.Controllers;

import org.example.Models.*;
import org.example.Models.Enums.TileType;

import java.util.ArrayList;

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
        return new NPC(name, x, y);
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