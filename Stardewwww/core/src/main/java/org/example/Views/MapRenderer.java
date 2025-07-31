//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.Views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.example.Models.Tile;
import org.example.Models.Enums.TileType;

public class MapRenderer {
    private final ArrayList<ArrayList<Tile>> map;
    private final int tileSize = 16;
    private final Map<TileType, Texture> textureMap = new HashMap();
    private final Map<TileType, TextureRegion> regionMap = new HashMap();

    public MapRenderer(ArrayList<ArrayList<Tile>> map) {
        this.map = map;
        this.loadTextures();
    }

    private void loadTextures() {
        this.loadTileTexture(TileType.EMPTY, "map/OutDoorTile.png");
        this.loadTileTexture(TileType.LAKE, "map/LakeTile.jpg");
        this.loadTileTexture(TileType.WALL, "map/Hardwood_Fence.png");
        this.loadTileTexture(TileType.PLAYER, "map/OutDoorTile.png");
    }

    private void loadTileTexture(TileType type, String path) {
        Texture tex = new Texture(path);
        this.textureMap.put(type, tex);
        this.regionMap.put(type, new TextureRegion(tex));
    }

    public void render(SpriteBatch batch) {
        for(int y = 0; y < this.map.size(); ++y) {
            for(int x = 0; x < ((ArrayList)this.map.get(0)).size(); ++x) {
                Tile tile = (Tile)((ArrayList)this.map.get(y)).get(x);
                TextureRegion region = (TextureRegion)this.regionMap.get(tile.getType());
                if (region != null) {
                    batch.draw(region, (float)(x * 16), (float)(y * 16), 16.0F, 16.0F);
                }
            }
        }

    }

    public void dispose() {
        for(Texture tex : this.textureMap.values()) {
            tex.dispose();
        }

    }
}
