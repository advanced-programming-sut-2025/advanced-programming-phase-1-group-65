package org.example.Models;

import org.example.Models.Enums.TileSubType;
import org.example.Models.Enums.TileType;

public class Rock extends Tile {
    TileSubType subType;
    public Rock(TileSubType subType) {
        super(TileType.ROCK);
        this.subType = subType;
    }
}
