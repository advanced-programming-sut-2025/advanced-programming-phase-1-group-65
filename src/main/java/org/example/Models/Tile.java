package org.example.Models;

import org.example.Models.Enums.TileType;


public class Tile {
    public TileType type;
    private NPC npc;

    public Tile(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public Tile clone() {
        Tile clonedTile = new Tile(this.type);
        clonedTile.setNpc(this.npc);
        return clonedTile;
    }

    public String getSymbol() {
        switch (type) {
            case WALL:
                return "W";
            case SHACK:
                return "C";
            case LAKE:
                return "L";
            case EMPTY:
                return ".";
            case GREENHOUSE:
                return "G";
            case QUARRY:
                return "Q";
            case TREE:
                return "T";
            case BLACKSMITH:
                return "B";
            case JOJAMART:
                return "J";
            case GENERALSTORE:
                return "S";
            case CARPENTERSHOP:
                return "P";
            case FISHSHOP:
                return "F";
            case RANCH:
                return "M";
            case STARDROPSALOON:
                return "A";
            case PLAYER:
                return "@";
            case ROCK:
                return "R";
            case FORAGING:
                return "#";
            case FERTILE:
                return "O";

            case SEBASTIAN_HOUSE:
                return "1";
            case ABIGAIL_HOUSE:
                return "2";
            case HARVEY_HOUSE:
                return "3";
            case LEAH_HOUSE:
                return "4";
            case ROBIN_HOUSE:
                return "5";
            case SEBASTIAN_NPC:
                return "🧑‍🎤";
            case ABIGAIL_NPC:
                return "👩‍🎤";
            case HARVEY_NPC:
                return "👨‍⚕️";
            case LEAH_NPC:
                return "👩‍🎨";
            case ROBIN_NPC:
                return "👷‍♀️";

            default:
                return "?";
        }
    }
}