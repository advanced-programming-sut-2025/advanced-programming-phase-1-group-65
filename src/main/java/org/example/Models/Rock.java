package org.example.Models;

import org.example.Models.Enums.TileType;

public class Rock extends Tile {
    public Rock(Material Mineral) {
        super(TileType.ROCK);
        this.Mineral = Mineral;

    }
    public Rock(Rock Other){
        super(TileType.ROCK);
        this.Mineral = Other.Mineral;
    }
   public Material Mineral;

}
