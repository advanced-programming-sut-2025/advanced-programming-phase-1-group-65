package org.example.Models;

import org.example.Models.Enums.TileSubType;
import org.example.Models.Enums.TileType;

public class Rock extends Tile {
    public Rock(Material Mineral) {
        super(TileType.ROCK);

    }
    Material Mineral;

}
