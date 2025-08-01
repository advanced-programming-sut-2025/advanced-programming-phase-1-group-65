package org.example.Views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.example.Models.Enums.TileType;
import org.example.Models.Rock;
import org.example.Models.Tile;
import org.example.Models.Trees;

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
    private Texture QuarryTileTexture;
    private TextureRegion QuarryTileRegion;
    private Texture DiamondTileTexture;
    private TextureRegion DiamondTileRegion;
    private Texture GoldTileTexture;
    private TextureRegion GoldTileRegion;
    private Texture CoalTileTexture;
    private TextureRegion CoalTileRegion;
    private Texture CopperTileTexture;
    private TextureRegion CopperTileRegion;
    private Texture IronTileTexture;
    private TextureRegion IronTileRegion;
    private Texture IriduimTileTexture;
    private TextureRegion IriduimTileRegion;
    private Texture WildTreeTexture;
    private TextureRegion WildTreeRegion;

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
        QuarryTileTexture = new Texture("map/Quarry_Boulder.png");
        QuarryTileRegion = new TextureRegion(QuarryTileTexture);
        DiamondTileTexture = new Texture("Material/Diamond_Node.png");
        DiamondTileRegion = new TextureRegion(DiamondTileTexture);
        GoldTileTexture = new Texture("Material/Gold_Node.png");
        GoldTileRegion = new TextureRegion(GoldTileTexture);
        CoalTileTexture = new Texture("Material/Coal_Node_Quarry_01.png");
        CoalTileRegion = new TextureRegion(CoalTileTexture);
        CopperTileTexture = new Texture("Material/Copper_Node.png");
        CopperTileRegion = new TextureRegion(CopperTileTexture);
        IronTileTexture = new Texture("Material/Iron_Node.png");
        IronTileRegion = new TextureRegion(IronTileTexture);
        IriduimTileTexture = new Texture("Material/Iridium_Node.png");
        IriduimTileRegion = new TextureRegion(IriduimTileTexture);
        WildTreeTexture = new Texture("Trees/Wild_Tree.png");
        WildTreeRegion = new TextureRegion(WildTreeTexture);
        // همینطور تکسچرهای دیگه رو بارگذاری کن
    }

    public void render(SpriteBatch batch) {
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(0).size(); x++) {
                Tile tile = map.get(y).get(x);

                // 1. ابتدا پس‌زمینه‌ی پایه رو برای همه رسم کن
                batch.draw(outdoorTileRegion, x * tileSize, y * tileSize, tileSize, tileSize);

                // 2. حالا اگر tile تصویر خاص خودش رو داره، اون رو رسم کن
                TextureRegion regionToDraw = getTextureForTile(tile);

                // outdoorTileRegion رو دوباره نکش، چون همین الان کشیدی
                if (regionToDraw != null && regionToDraw != outdoorTileRegion) {
                    batch.draw(regionToDraw, x * tileSize, y * tileSize, tileSize, tileSize);
                }
            }
        }
    }



    private TextureRegion getTextureForTile(Tile tile) {
        if (tile instanceof Rock) {
            Rock rock = (Rock) tile;
            return switch (rock.Mineral.name) {
                case "Diamond" -> DiamondTileRegion;
                case "Gold Ore" -> GoldTileRegion;
                case "Coal " -> CoalTileRegion;
                case "Iron Ore" -> IronTileRegion;
                case "Iriduim Ore" -> IriduimTileRegion;
                case "Copper Ore" -> QuarryTileRegion;
                default -> null;
            };
        }
        if (tile instanceof Trees) {
            Trees tree = (Trees) tile;
            return switch (tree.name) {
                case "Wild" -> WildTreeRegion;
                default -> null;
            };

        }
        return switch (tile.type) {
            case EMPTY -> outdoorTileRegion;
            case WALL -> WallTileRegion;
            case FERTILE -> FertileTileRegion;
            case QUARRY -> QuarryTileRegion;
            case LAKE -> LakeTileRegion;
            // case های دیگر برای هر نوع Tile
            // مثلا WALL -> wallTextureRegion
            default -> null;
        };
    }

    public void dispose() {
        outdoorTileTexture.dispose();
        LakeTileTexture.dispose();
        WallTileTexture.dispose();
        FertileTileTexture.dispose();
        QuarryTileTexture.dispose();
        DiamondTileTexture.dispose();
        GoldTileTexture.dispose();
        CoalTileTexture.dispose();
        CopperTileTexture.dispose();
        IronTileTexture.dispose();
        IriduimTileTexture.dispose();
        WildTreeTexture.dispose();
    }

}
