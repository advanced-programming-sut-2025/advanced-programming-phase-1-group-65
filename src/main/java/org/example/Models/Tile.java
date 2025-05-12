package org.example.Models;

import org.example.Models.Enums.TileType;

public class Tile {
    public TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }
    public char getSymbol() {
        switch (type) {
            case WALL:
                return 'W';
            case SHACK:
                return 'C';
            case LAKE:
                return 'L';
            case EMPTY:
                return '.';
            case GREENHOUSE:
                return 'G';
            case QUARRY:
                return 'Q';
            case TREE:
                return 'T';
            case BLACKSMITH:
                return 'B';
            case JOJAMART:
                return 'J';
            case GENERALSTORE:
                return 'S';
            case CARPENTERSHOP:
                return 'P';
            case FISHSHOP:
                return 'F';
            case RANCH:
                return 'M';
            case STARDROPSALOON:
                return 'A';
            case PLAYER:
                return '@';
            case ROCK:
                return 'R';
            case FORAGING:
                return '#';
            default:
                return '?';
        }
    }
}