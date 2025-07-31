package org.example.Views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.example.Models.Enums.TileType;
import org.example.Models.Tile;

import java.util.ArrayList;

public class MapRenderer {
    private final ArrayList<ArrayList<Tile>> map;
    private final int tileSize = 16;

    private Texture outdoorTileTexture;
    private TextureRegion outdoorTileRegion;
    private Texture LakeTileTexture;
    private TextureRegion LakeTileRegion;
    private Texture WallTileTexture;
    private TextureRegion WallTileRegion;
    private Texture FertileTileTexture;
    private TextureRegion FertileTileRegion;

    // سایر تکسچرها مثل treeTexture, wallTexture و ...

    public MapRenderer(ArrayList<ArrayList<Tile>> map) {
        this.map = map;

        // بارگذاری تکسچر (یکبار)
        outdoorTileTexture = new Texture("map/OutDoorTile.png");
        outdoorTileRegion = new TextureRegion(outdoorTileTexture);
        LakeTileTexture = new Texture("map/LakeTile.jpg");
        LakeTileRegion = new TextureRegion(LakeTileTexture);
        WallTileTexture = new Texture("map/Hardwood_Fence.png");
        WallTileRegion = new TextureRegion(WallTileTexture);
        FertileTileTexture = new Texture("map/Dirt Hoed.png");
        FertileTileRegion = new TextureRegion(FertileTileTexture);
        // همینطور تکسچرهای دیگه رو بارگذاری کن
    }

    public void render(SpriteBatch batch) {
        int tileSize = 16;
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(0).size(); x++) {
                Tile tile = map.get(y).get(x);
                TextureRegion regionToDraw = getTextureForTile(tile.getType());

                if (regionToDraw != null) {
                    // توجه کن y معکوس حساب میشه یا نه
                    batch.draw(regionToDraw, x * tileSize, y * tileSize, tileSize, tileSize);
                }
            }
        }
    }


    private TextureRegion getTextureForTile(TileType type) {
        return switch (type) {
            case EMPTY -> outdoorTileRegion;
            case PLAYER -> LakeTileRegion;
            case WALL -> WallTileRegion;
            case FERTILE -> FertileTileRegion;
            // case های دیگر برای هر نوع Tile
            // مثلا WALL -> wallTextureRegion
            default -> null;
        };
    }

    public void dispose() {
        outdoorTileTexture.dispose();
        // dispose سایر تکسچرها
    }
}
