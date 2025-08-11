package org.example.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.example.Models.*;

import org.example.Models.Game;
import org.example.Models.Rock;
import org.example.Models.Tile;
import org.example.Models.Enums.TileType;
import org.example.Models.Trees;
import com.badlogic.gdx.math.MathUtils;
public class MapRenderer {
    public Game game;
    private final ArrayList<ArrayList<Tile>> map;
    private final int tileSize = 16;
    private final Map<TileType, Texture> textureMap = new HashMap<>();
    private final Map<TileType, TextureRegion> regionMap = new HashMap<>();

    // تکسچرهای بزرگ برای ساختمان‌ها
    private  Texture greenhousebroken;
   // private final Texture greenhousefixed;
    private final Texture lakeBigTexture;
    private final Texture ShedTexture;
     private final Texture blacksmithBigTexture;
     private final Texture generalStoreBigTexture;
    private final Texture carpentersShopBigTexture;
    private final Texture fishShopBigTexture;
    private final Texture ranchBigTexture;
    private final Texture stardropSaloonBigTexture;
    private final Texture jojaMartBigTexture;
    private final Texture coopTexture;
    private final Texture barnTexture;
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
    private Texture ShippingTileTexture;
    private TextureRegion ShippingTileRegion;
    private Texture lightningTexture;

    private final ArrayList<Vector2> lightningPositions = new ArrayList<>();
    private boolean showLightning = false;
    private float lightningTimer = 0f;
    private final float lightningDuration = 1.0f;
    private CameraShake cameraShake;
    private Texture outdoorTileNightTexture;
    private TextureRegion outdoorTileNightRegion;
    private boolean showMiniMap = false;
    private boolean isNight = false;

    public MapRenderer(ArrayList<ArrayList<Tile>> map, Game game) {
        this.map = map;
        this.game = game;
        this.loadTextures();
        cameraShake = new CameraShake();
        this.greenhousebroken = new Texture("map/GreenHouseBroken.png");
        this.lakeBigTexture = new Texture("map/LakeBig.png");
        this.blacksmithBigTexture = new Texture("map/BlacksmithBig.png");
        this.generalStoreBigTexture = new Texture("map/GeneralStoreBig.png");
        this.carpentersShopBigTexture = new Texture("map/CarpentersShopBig.png");
        this.fishShopBigTexture = new Texture("map/FishShopBig.png");
        this.ranchBigTexture = new Texture("map/RanchBig.png");
        this.stardropSaloonBigTexture = new Texture("map/StardropSaloonBig.png");
        this.jojaMartBigTexture = new Texture("map/JojaMartBig.png");
        this.coopTexture = new Texture("Building/Coop.png");
        this.barnTexture = new Texture("Building/Barn.png");
        ShedTexture = new Texture("map/Shed.png");

        regionMap.put(TileType.LAKE, new TextureRegion(lakeBigTexture));
        regionMap.put(TileType.SHACK , new TextureRegion(ShedTexture));
        regionMap.put(TileType.BLACKSMITH, new TextureRegion(blacksmithBigTexture));
        regionMap.put(TileType.GENERALSTORE, new TextureRegion(generalStoreBigTexture));
        regionMap.put(TileType.CARPENTERSHOP, new TextureRegion(carpentersShopBigTexture));
        regionMap.put(TileType.FISHSHOP, new TextureRegion(fishShopBigTexture));
        regionMap.put(TileType.RANCH, new TextureRegion(ranchBigTexture));
        regionMap.put(TileType.STARDROPSALOON, new TextureRegion(stardropSaloonBigTexture));
        regionMap.put(TileType.JOJAMART, new TextureRegion(jojaMartBigTexture));
        regionMap.put(TileType.COOP, new TextureRegion(coopTexture));
        regionMap.put(TileType.BARN, new TextureRegion(barnTexture));
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
        ShippingTileTexture = new Texture("Map/ShippingBin.jpg");
        ShippingTileRegion = new TextureRegion(ShippingTileTexture);
        outdoorTileNightTexture = new Texture("map/OutDoorTileNight.png");
        outdoorTileNightRegion = new TextureRegion(outdoorTileNightTexture);
        lightningTexture = new Texture("map/lightning.png");

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
            if (tree.name.equalsIgnoreCase("Wild")) {
                return WildTreeRegion;
            } else {
                return tree.isHarvestable ? new TextureRegion(tree.texture2) : new TextureRegion(tree.texture1);
            }
        }
        if (tile instanceof Foraging){
            Foraging foraging = (Foraging) tile;
            return foraging.isHarvestable ? new TextureRegion(foraging.texture2) : new TextureRegion(foraging.texture1);


        }
        return switch (tile.type) {
            case EMPTY -> outdoorTileRegion;
            case WALL -> WallTileRegion;
            case FERTILE -> FertileTileRegion;
            case QUARRY -> QuarryTileRegion;
            case SHIPPINGBIN ->  ShippingTileRegion;
            default -> null;
        };
    }

    public void update(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            showLightning = true;
            lightningTimer = 0f;
            cameraShake.shake(lightningDuration, 5f);

            lightningPositions.clear();
            int count = MathUtils.random(3, 5);

            float screenWidth = Gdx.graphics.getWidth();
            float lightningWidth = 64f;

            for (int i = 0; i < count; i++) {
                float x = MathUtils.random(0, screenWidth - lightningWidth);
                float y = Gdx.graphics.getHeight() - 256f;
                lightningPositions.add(new Vector2(x, y));
            }
        }

        if (showLightning) {
            lightningTimer += delta;
            if (lightningTimer >= lightningDuration) {
                showLightning = false;
                lightningPositions.clear();
            }
        }

        cameraShake.update(delta);
    }
    public void setNight(boolean night) {
        this.isNight = night;
    }
    public void render(SpriteBatch batch) {
        updateGreenhouseTexture();

        int rows = map.size();
        int cols = map.get(0).size();
        boolean[][] drawn = new boolean[rows][cols];

        Vector2 shakeOffset = cameraShake.update(Gdx.graphics.getDeltaTime());

        com.badlogic.gdx.math.Matrix4 originalProj = batch.getProjectionMatrix().cpy();

        com.badlogic.gdx.math.Matrix4 shakenProj = new com.badlogic.gdx.math.Matrix4(originalProj);
        shakenProj.translate(shakeOffset.x, shakeOffset.y, 0);
        batch.end();
        batch.setProjectionMatrix(shakenProj);

        batch.begin();


        TextureRegion currentOutdoorRegion = isNight ? outdoorTileNightRegion : outdoorTileRegion;

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
                    || type == TileType.JOJAMART
                    || type == TileType.SHACK
                    || type == TileType.COOP
                    || type == TileType.BARN;

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
                            batch.draw(currentOutdoorRegion, (x + dx) * tileSize, (y + dy) * tileSize, tileSize, tileSize);
                            drawn[y + dy][x + dx] = true;
                        }
                    }

                    // رسم تصویر بزرگ ساختمان
                    batch.draw(bigRegion,
                        x * tileSize, y * tileSize,
                        width * tileSize, height * tileSize);

                } else {
                    // برای کاشی‌های معمولی از currentOutdoorRegion استفاده می‌کنیم
                    batch.draw(currentOutdoorRegion, x * tileSize, y * tileSize, tileSize, tileSize);

                    TextureRegion region = getTextureForTile(tile);

                    // اگر منطقه مربوط به کاشی خالی (EMPTY) است، آن را با currentOutdoorRegion جایگزین کن
                    if (region == outdoorTileRegion) {
                        region = currentOutdoorRegion;
                    }

                    if (region != null) {
                        if (tile instanceof Foraging) {
                            // کوچک‌تر کشیدن Foraging، مثلاً نصف اندازه و مرکز شده
                            int smallSize = tileSize / 3;
                            float offsetX = x * tileSize + (tileSize - smallSize) / 2f;
                            float offsetY = y * tileSize;
                            batch.draw(region, offsetX, offsetY, smallSize, smallSize);
                        }

                        else {
                            batch.draw(region, x * tileSize, y * tileSize, tileSize, tileSize);
                        }
                    }
                }

            }
        }
        batch.end();

        batch.setProjectionMatrix(new com.badlogic.gdx.math.Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        batch.begin();
        if (showLightning) {
            float lightningWidth = 300;
            float lightningHeight = 900;
            for (Vector2 pos : lightningPositions) {
                batch.draw(lightningTexture, pos.x, pos.y-450, lightningWidth, lightningHeight);
            }
        }
        batch.end();

        batch.setProjectionMatrix(originalProj);
        batch.begin();
    }
    private void updateGreenhouseTexture() {
        if (game.currentPlayer.GreenHouseFixed) {
            greenhousebroken = new Texture("map/GreenHouseFixed.png");
            regionMap.put(TileType.GREENHOUSE, new TextureRegion(greenhousebroken));

        } else {
            greenhousebroken = new Texture("map/GreenHouseBroken.png");
            regionMap.put(TileType.GREENHOUSE, new TextureRegion(greenhousebroken));

        }
    }




    public void dispose() {
        for (Texture tex : textureMap.values()) {
            tex.dispose();
        }
        lakeBigTexture.dispose();
        greenhousebroken.dispose();
        blacksmithBigTexture.dispose();
        generalStoreBigTexture.dispose();
        carpentersShopBigTexture.dispose();
        fishShopBigTexture.dispose();
        ranchBigTexture.dispose();
        stardropSaloonBigTexture.dispose();
        jojaMartBigTexture.dispose();
        ShedTexture.dispose();

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

        lightningTexture.dispose();
    }
}
