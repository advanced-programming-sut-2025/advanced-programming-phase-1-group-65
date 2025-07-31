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
    private final Map<TileType, Texture> textureMap = new HashMap<>();
    private final Map<TileType, TextureRegion> regionMap = new HashMap<>();

    // تکسچرهای بزرگ برای ساختمان‌ها
    private final Texture greenhouseBigTexture;
    private final Texture lakeBigTexture;
   // private final Texture blacksmithBigTexture;
   // private final Texture generalStoreBigTexture;
   // private final Texture carpentersShopBigTexture;
    //private final Texture fishShopBigTexture;
    //private final Texture ranchBigTexture;
    private final Texture stardropSaloonBigTexture;
    private final Texture jojaMartBigTexture;

    public MapRenderer(ArrayList<ArrayList<Tile>> map) {
        this.map = map;
        this.loadTextures();

        // بارگذاری تکسچرهای بزرگ
        this.greenhouseBigTexture = new Texture("map/GreenhouseBig.png");
        this.lakeBigTexture = new Texture("map/LakeBig.png");
       // this.blacksmithBigTexture = new Texture("map/BlacksmithBig.png");
        //this.generalStoreBigTexture = new Texture("map/GeneralStoreBig.png");
        //this.carpentersShopBigTexture = new Texture("map/CarpentersShopBig.png");
        //this.fishShopBigTexture = new Texture("map/FishShopBig.png");
        //this.ranchBigTexture = new Texture("map/RanchBig.png");
        this.stardropSaloonBigTexture = new Texture("map/StardropSaloonBig.png");
        this.jojaMartBigTexture = new Texture("map/JojaMartBig.png");

        regionMap.put(TileType.GREENHOUSE, new TextureRegion(greenhouseBigTexture));
        regionMap.put(TileType.LAKE, new TextureRegion(lakeBigTexture));
        //regionMap.put(TileType.BLACKSMITH, new TextureRegion(blacksmithBigTexture));
        //regionMap.put(TileType.GENERALSTORE, new TextureRegion(generalStoreBigTexture));
        //regionMap.put(TileType.CARPENTERSHOP, new TextureRegion(carpentersShopBigTexture));
        //regionMap.put(TileType.FISHSHOP, new TextureRegion(fishShopBigTexture));
        //regionMap.put(TileType.RANCH, new TextureRegion(ranchBigTexture));
        regionMap.put(TileType.STARDROPSALOON, new TextureRegion(stardropSaloonBigTexture));
        regionMap.put(TileType.JOJAMART, new TextureRegion(jojaMartBigTexture));
    }

    private void loadTextures() {
        loadTileTexture(TileType.EMPTY, "map/OutDoorTile.png");
        loadTileTexture(TileType.WALL, "map/Hardwood_Fence.png");
        loadTileTexture(TileType.PLAYER, "map/OutDoorTile.png");
        // اگر نیاز بود بقیه تکسچرهای کوچک را هم اینجا اضافه کن
    }

    private void loadTileTexture(TileType type, String path) {
        Texture tex = new Texture(path);
        textureMap.put(type, tex);
        regionMap.put(type, new TextureRegion(tex));
    }

    public void render(SpriteBatch batch) {
        int rows = map.size();
        int cols = map.get(0).size();
        boolean[][] drawn = new boolean[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (drawn[y][x]) continue;

                Tile tile = map.get(y).get(x);
                TileType type = tile.getType();

                // لیست TileType هایی که ناحیه بزرگ دارند
                boolean isBigTile = type == TileType.GREENHOUSE || type == TileType.LAKE
                        || type == TileType.BLACKSMITH
                        || type == TileType.GENERALSTORE
                        || type == TileType.CARPENTERSHOP
                        || type == TileType.FISHSHOP
                        || type == TileType.RANCH
                        || type == TileType.STARDROPSALOON
                        || type == TileType.JOJAMART;

                if (isBigTile) {
                    TextureRegion baseRegion = regionMap.get(TileType.EMPTY);
                    TextureRegion bigRegion = regionMap.get(type);
                    if (baseRegion == null || bigRegion == null) continue;

                    // پیدا کردن عرض ناحیه
                    int width = 1;
                    while (x + width < cols &&
                            map.get(y).get(x + width).getType() == type &&
                            !drawn[y][x + width]) {
                        width++;
                    }

                    // پیدا کردن ارتفاع ناحیه
                    int height = 1;
                    boolean canExpandHeight = true;
                    while (y + height < rows && canExpandHeight) {
                        for (int i = 0; i < width; i++) {
                            if (map.get(y + height).get(x + i).getType() != type
                                    || drawn[y + height][x + i]) {
                                canExpandHeight = false;
                                break;
                            }
                        }
                        if (canExpandHeight) height++;
                    }

                    // کشیدن پس‌زمینه EMPTY زیر ناحیه
                    for (int dy = 0; dy < height; dy++) {
                        for (int dx = 0; dx < width; dx++) {
                            batch.draw(baseRegion, (x + dx) * tileSize, (y + dy) * tileSize, tileSize, tileSize);
                            drawn[y + dy][x + dx] = true;
                        }
                    }

                    // رسم تصویر بزرگ ساختمان
                    batch.draw(bigRegion,
                            x * tileSize, y * tileSize,
                            width * tileSize, height * tileSize);

                } else {
                    // رسم تایل‌های معمولی
                    TextureRegion region = regionMap.get(type);
                    if (region != null) {
                        batch.draw(region, x * tileSize, y * tileSize, tileSize, tileSize);
                    }
                }
            }
        }
    }

    public void dispose() {
        for (Texture tex : textureMap.values()) {
            tex.dispose();
        }
        greenhouseBigTexture.dispose();
        lakeBigTexture.dispose();
        //blacksmithBigTexture.dispose();
        //generalStoreBigTexture.dispose();
        //carpentersShopBigTexture.dispose();
        //fishShopBigTexture.dispose();
        //ranchBigTexture.dispose();
        stardropSaloonBigTexture.dispose();
        jojaMartBigTexture.dispose();
    }
}