package org.example.Views.GameView;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import org.example.Controllers.BuildingController.BuildingController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.example.Controllers.GameController.GameController;
import org.example.Main;
import org.example.Models.*;
import org.example.Models.Enums.ItemType;
import org.example.Models.Enums.TileType;
import org.example.Models.Enums.WeatherType;
import org.example.Controllers.NPCController.NPCController;
import org.example.Controllers.ShopController.ShopController;
import org.example.Models.Game;
import org.example.Models.NPC;
import org.example.Views.MapRenderer;
import org.example.Views.MenuView.LoginRegisterMenuView;
import org.example.Views.PlayerAnimation;
import org.example.Views.UI.ShopUI;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.badlogic.gdx.utils.TimeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameScreen implements Screen {
    final Game game;
    OrthographicCamera camera;
    public GameController controller = new GameController();
    public InventoryUI inventoryUI;
    public ShopUI shopUI; // اضافه کردن فروشگاه
    public RefrigeratorUI refrigeratorUI;
    boolean isInventoryOpen = false;
    boolean isShopOpen = false;  // وضعیت باز بودن فروشگاه
    private Skin skin;
    public boolean selectingDirection = false;
    private final MapRenderer mapRenderer;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    public PlayerAnimation playerAnim;
    private PlayerAnimation.Direction currentDirection;
    private Texture clockTexture;
    private BitmapFont font;
    private float timeAccumulator;
    private final float timePerGameHour;
    private Stage uiStage;
    public boolean isRefrigeratorOpen = false;
    public boolean RefrigeratorPick = false;
    public KitchenUI kitchenUI;
    public boolean KitchenOpen = false;
    public InputMultiplexer multiplexer;
    private boolean isMiniMapOpen = false;
    private ShopController shopController;
    private Texture npcIconTexture;
    private OrthographicCamera miniMapCamera;
    private BitmapFont miniMapFont;
    public boolean isShippingBinOpen = false;
    private boolean isSkillScreenOpen = false;
    private ArrayList<Rectangle> skillIconBounds = new ArrayList<>() ;
    public boolean walking= false;
    private Animation<TextureRegion> rainAnimation;
    private ArrayList<RainDrop> rainDrops;
    public BuildingController buildingController;
    List<String> npcNames = Arrays.asList("abigail", "sebastian", "harvey", "leah", "robin");
    long lastUpdateTime = 0;
    String currentNpcName = "abigail";
    Random random = new Random();
    private boolean isDialogOpen = false;
    private String currentDialogText = "";
    private float npcIconX, npcIconY, npcIconWidth, npcIconHeight;
    private NPC nearbyNpc;
    private NPCController npcController;
    private float dialogTimer = 0f;
    private final float DIALOG_DURATION = 2.5f;
    private Texture dialogBackgroundTexture;
    public boolean isBackPackOpen = false;

    public GameScreen(Game game) {
        this.game = game;
        this.mapRenderer = new MapRenderer(game.Map , game);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        this.currentDirection = PlayerAnimation.Direction.DOWN;
        this.timeAccumulator = 0.0F;
        this.timePerGameHour = 5.0F;

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        inventoryUI = new InventoryUI(game, batch, controller);
       // gameController = new GameController();
        shopController = new ShopController();
        shopUI = new ShopUI(game,controller);
        buildingController = new BuildingController();

        refrigeratorUI = new RefrigeratorUI(game, batch, controller);
        kitchenUI  = new KitchenUI(game, batch, controller);
        camera = new OrthographicCamera(320, 160);
        camera.zoom = 1f;
        camera.update();
        uiStage = new Stage(new ScreenViewport());
        uiStage = new Stage(new ScreenViewport());
        DirectionInputProcessor directionInput = new DirectionInputProcessor(this);

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiStage);            // UI Stage اول باشه
        multiplexer.addProcessor(directionInput);     // بعد ورودی سفارشی
        multiplexer.addProcessor(inventoryUI);
        multiplexer.addProcessor(shopUI.getStage());
        Gdx.input.setInputProcessor(multiplexer);

        miniMapCamera = new OrthographicCamera(320, 160);
        miniMapCamera.zoom = 5f;
        miniMapCamera.update();


        this.playerAnim = new PlayerAnimation(0.2F , game);
        this.clockTexture = new Texture(Gdx.files.internal("ui/Clock.png"));
        this.font = new BitmapFont();
        this.font.getData().setScale(1.5F);

        this.miniMapFont = new BitmapFont();
        this.miniMapFont.getData().setScale(2f);
        this.miniMapFont.setColor(1f, 1f, 1f, 1f);
        this.npcIconTexture = new Texture(Gdx.files.internal("ui/npc_icon.png"));
        dialogBackgroundTexture = new Texture(Gdx.files.internal("ui/dialog_bg.png"));
        Texture rainTexture = new Texture("weather/rain.png");
        TextureRegion[][] tmp = TextureRegion.split(rainTexture, rainTexture.getWidth() / 4, rainTexture.getHeight());
        TextureRegion[] frames = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            frames[i] = tmp[0][i];
        }
        rainAnimation = new Animation<>(0.2f, frames);

        // ایجاد لیست قطره ها
        rainDrops = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            float x = (float)(Math.random() * Gdx.graphics.getWidth());
            float y = (float)(Math.random() * Gdx.graphics.getHeight());
            float speed = 100 + (float)(Math.random() * 100);
            rainDrops.add(new RainDrop(x, y, speed, rainAnimation));
        }
    }



    @Override
    public void render(float delta) {

        if (!isMiniMapOpen && !isInventoryOpen && !isShopOpen) {
            this.timeAccumulator += delta;
            if (this.timeAccumulator >= 20.0f) {
                this.timeAccumulator -= 20.0F;
                this.game.gameClock.advanceTimeByOneHour(this.game, this.controller);
            }
        }
        if (game.currentPlayer.Energy <= 0){
            game.currentPlayer.Fainted = true;
            //controller.processNextTurn(game);
        }

        handleInput();

        if (!isMiniMapOpen && !isShopOpen) {
            updateCamera();
            mapRenderer.update(delta);
            camera.update();
        } else if (isMiniMapOpen) {
            updateMiniMapCamera();
            miniMapCamera.update();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (isMiniMapOpen) {
            batch.setProjectionMatrix(miniMapCamera.combined);
            batch.begin();
            mapRenderer.render(batch);

            TextureRegion frame = this.playerAnim.getCurrentFrame(this.currentDirection, delta);
            int px = this.game.currentPlayer.PositionX;
            int py = this.game.currentPlayer.PositionY;
            int playerSize = 8;
            int tileSize = 16;
            int offset = (tileSize - playerSize) / 2;
            batch.draw(frame, (float)(px * tileSize + offset), (float)(py * tileSize ), (float)playerSize, (float)playerSize);
            batch.end();

            batch.setProjectionMatrix((new Matrix4()).setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            batch.begin();
            String msg = "Mini Map - Press M to return to game";
            miniMapFont.draw(batch, msg, Gdx.graphics.getWidth() / 2f - 150, Gdx.graphics.getHeight() - 20);
            batch.end();
        } else {
            batch.setProjectionMatrix(camera.combined);
            batch.begin();
            mapRenderer.render(batch);

            NPCController npcController = new NPCController(game);
            long now = TimeUtils.millis();
            if (now - lastUpdateTime > 20000) {
                int index = random.nextInt(npcNames.size());
                currentNpcName = npcNames.get(index);
                lastUpdateTime = now;
            }
            if (game.currentPlayer.friedEggBuffActive) {
                game.currentPlayer.friedEggBuffTime -= Gdx.graphics.getDeltaTime();
                if (game.currentPlayer.friedEggBuffTime < 0) {
                    game.currentPlayer.friedEggBuffTime = 0;
                }
            }



            NPC nearbyNpc = npcController.findNearbyNPCByName(currentNpcName);
            if (nearbyNpc != null) {
                int tileSize = 16;
                float iconSize = 10f;
                npcIconX = nearbyNpc.getX() * tileSize + 3;
                npcIconY = nearbyNpc.getY() * tileSize + tileSize;
                npcIconWidth = iconSize;
                npcIconHeight = iconSize;
                batch.draw(npcIconTexture, npcIconX, npcIconY, iconSize, iconSize);

            }
            TextureRegion frame = this.playerAnim.getCurrentFrame(this.currentDirection, delta);


            int px = this.game.currentPlayer.PositionX;
            int py = this.game.currentPlayer.PositionY;
            int playerSize = 10;
            int tileSize = 16;
            int offset = (tileSize - playerSize) / 2;
            this.batch.draw(frame, (float)(px * tileSize + offset), (float)(py * tileSize ), (float)playerSize, (float)playerSize);
            if (this.game.currentPlayer.CurrentTool != null) {

                TextureRegion toolTexture = new TextureRegion(this.game.currentPlayer.CurrentTool.texture);

                // تنظیم مختصات ابزار روی بازیکن؛ می‌تونی اعداد را با توجه به جایگاهی که می‌خوای اصلاح کنی
                float toolX = px * tileSize + offset + 7;  // کمی به راست
                float toolY = py * tileSize +2;           // کمی بالاتر از پایین بازیکن
                int toolSize = 8;  // اندازه ابزار

                batch.draw(toolTexture, toolX, toolY, toolSize, toolSize);
            }


            batch.end();
            // تغییر پروجکشن به مختصات صفحه
            batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            batch.begin();

            if (game.currentPlayer.friedEggBuffActive) {
                int iconSize = 70;
                int x = 70;
                int y = Gdx.graphics.getHeight() - 70;

                // رسم آیکون Buff
                batch.draw(new Texture("Skills/Energy_Buff.png"), x, y - iconSize, iconSize, iconSize);

                // رسم زمان باقی‌مانده Buff
                font.draw(batch, (int)game.currentPlayer.friedEggBuffTime + "s", x + iconSize + 10, y - 10);
            }

            batch.end();

            this.drawClockUI();

            if (isInventoryOpen) {
                inventoryUI.act(delta);
                inventoryUI.draw();
            }
            if (RefrigeratorPick){
                refrigeratorUI.act(delta);
                refrigeratorUI.draw();
            }
            if (KitchenOpen){
                kitchenUI.act(delta);
                kitchenUI.draw();
            }

            if (isSkillScreenOpen) {
                skillIconBounds.clear();  // هر بار قبل از پر کردن، خالی کن

                batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
                batch.begin();

                int x = 50;
                int y = Gdx.graphics.getHeight() - 50;

                int iconSize = 64;
                int spacingY = 80;

                for (Skill skill : game.currentPlayer.skills) {
                    batch.draw(skill.texture, x, y - iconSize, iconSize, iconSize);
                    skillIconBounds.add(new Rectangle(x, y - iconSize, iconSize, iconSize));

                    font.draw(batch, "Level: " + skill.getLevel(), x + iconSize + 10, y - 10);
                    font.draw(batch, "XP to next: " + skill.getXP(skill.level), x + iconSize + 10, y - 30);

                    y -= spacingY;
                }

                batch.end();

                int mouseX = Gdx.input.getX();
                int mouseY = Gdx.input.getY();
                float mouseYCorrected = Gdx.graphics.getHeight() - mouseY;

                for (int i = 0; i < skillIconBounds.size(); i++) {
                    Rectangle rect = skillIconBounds.get(i);
                    if (rect.contains(mouseX, mouseYCorrected)) {
                        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                        shapeRenderer.setColor(1, 1, 0, 0.5f);
                        float tooltipX = rect.x + rect.width + 10;
                        float tooltipY = rect.y + rect.height;
                        float tooltipWidth = 400;
                        float tooltipHeight = 120;
                        shapeRenderer.rect(tooltipX, tooltipY - tooltipHeight, tooltipWidth, tooltipHeight);
                        shapeRenderer.end();

                        batch.begin();
                        font.draw(batch, game.currentPlayer.skills.get(i).description, tooltipX + 5, tooltipY - 10, tooltipWidth - 10, Align.left, true);
                        batch.end();

                        break;
                    }
                }


            }






            float barWidth = 30; // عرض نوار
            float barHeight = 200; // ارتفاع کل نوار
            float x2 = Gdx.graphics.getWidth() - barWidth - 50; // 20 پیکسل فاصله از سمت راست صفحه
            float y2 = 500; // فاصله از پایین صفحه

// درصد انرژی
            float energyPercent = (float)(game.currentPlayer.Energy / game.currentPlayer.maxEnergy);
            if (energyPercent > 1) energyPercent = 1;
            if (energyPercent < 0) energyPercent = 0;

// رسم پس‌زمینه نوار (مثلاً خاکستری روشن)
            // --- آماده‌سازی پروجکشن صفحه‌ای برای UI ---
            Matrix4 uiProj = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.setProjectionMatrix(uiProj);

// فعال کردن blending برای آلفا (اگر لازم است)
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

// رسم پس‌زمینه نوار
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(x2, y2, barWidth, barHeight);

// رسم مقدار انرژی (پر شده)
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.rect(x2, y2, barWidth, barHeight * energyPercent);
            shapeRenderer.end();

            Gdx.gl.glDisable(GL20.GL_BLEND);

// متن عددی انرژی — حتماً batch با همان پروجکشن صفحه‌ای باشد
            batch.setProjectionMatrix(uiProj);
            batch.begin();
            font.draw(batch, (int)game.currentPlayer.Energy + " / " + (int)game.currentPlayer.maxEnergy, x2 - 40, y2 + barHeight / 2);
            batch.end();

// در انتها اگر قرار است دوباره world را با batch بر اساس دوربین رسم کنی،
// projection آن را به camera.combined برگردان (یا هر جا که لازم است).
            batch.setProjectionMatrix(camera.combined);
// تنظیم پروجکشن UI
            batch.setProjectionMatrix(uiProj);
            batch.begin();

            int padding = 10;
            int iconSize = 60; // اندازه آیکون ابزار/آیتم
            int textOffsetX = iconSize + 10;
            int startY = Gdx.graphics.getHeight() - padding; // شروع از بالا صفحه

// نمایش ابزار فعلی
            if (game.currentPlayer.CurrentTool != null && game.currentPlayer.CurrentTool.texture != null) {
                batch.draw(game.currentPlayer.CurrentTool.texture, padding, startY - iconSize, iconSize, iconSize);
            }

// نمایش آیتم فعلی (پایین‌تر از ابزار)
            int itemY = startY - iconSize - 40;
            if (game.currentPlayer.CurrentItem != null && game.currentPlayer.CurrentItem.texture != null) {
                batch.draw(game.currentPlayer.CurrentItem.texture, padding, itemY - iconSize, iconSize, iconSize);
            }

            batch.end();
            if (game.weatherSystem.getTodayWeather() == WeatherType.RAIN) {
                batch.begin();
                for (RainDrop drop : rainDrops) {
                    drop.update(delta, Gdx.graphics.getHeight());
                    drop.render(batch);
                }
                batch.end();
            }

            if (selectingDirection) {
                // فعال کردن شفافیت
                Gdx.gl.glEnable(GL20.GL_BLEND);
                Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

                shapeRenderer.setProjectionMatrix(camera.combined);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(1, 1, 0, 0.5f);

                int playerX = game.currentPlayer.PositionX;
                int playerY = game.currentPlayer.PositionY;

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        // خونه‌ی وسط رو نکش
                        if (dx == 0 && dy == 0) continue;

                        int tileX = playerX + dx;
                        int tileY = playerY + dy;

                        shapeRenderer.rect(
                            tileX * tileSize,
                            tileY * tileSize,
                            tileSize,
                            tileSize
                        );
                    }
                }

                shapeRenderer.end();
                Gdx.gl.glDisable(GL20.GL_BLEND);
            }

            uiStage.act(delta);
            uiStage.draw();
            Gdx.input.setInputProcessor(multiplexer);
            if (shopUI.isVisible()) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                shopUI.act(delta);
                shopUI.draw();
            }
        }
        if (isDialogOpen) {
            if (isDialogOpen) {
                dialogTimer += delta;
                if (dialogTimer >= DIALOG_DURATION) {
                    isDialogOpen = false;
                }
            }
            batch.begin();
            float dialogX = 60;
            float dialogY = 60;
            float dialogWidth = Gdx.graphics.getWidth() - 120;
            float dialogHeight = 100;

            batch.draw(dialogBackgroundTexture, dialogX, dialogY, dialogWidth, dialogHeight);

            font.setColor(1, 1, 1, 1);
            font.draw(batch, currentDialogText, dialogX + 20, dialogY + dialogHeight - 30);
            batch.end();
        }


    }

    @Override
    public void resize(int i, int i1) {

    }
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


    private void handleInput() {
        if (isMiniMapOpen) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
                isMiniMapOpen = false;
                camera.zoom = 1f;  // برگردوندن زوم دوربین بازی به حالت اولیه
                camera.update();
                Gdx.input.setInputProcessor(multiplexer);
            }
            return;
        }
        if (Gdx.input.justTouched()) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();
            Vector3 worldCoordinates = camera.unproject(new Vector3(mouseX, mouseY, 0));

            if (worldCoordinates.x >= npcIconX && worldCoordinates.x <= npcIconX + npcIconWidth
                    && worldCoordinates.y >= npcIconY && worldCoordinates.y <= npcIconY + npcIconHeight) {
                npcController = new NPCController(game);
                nearbyNpc = npcController.findNearbyNPCByName(currentNpcName);
                if (nearbyNpc != null) {
                    currentDialogText = npcController.talkToNPCByName(nearbyNpc.getName());
                    isDialogOpen = true;
                    dialogTimer = 0f;
                }


            } else {
                isDialogOpen = false;
            }
        }




        if (!isInventoryOpen && !isRefrigeratorOpen && !isShopOpen) {
            if (Gdx.input.isKeyJustPressed(51)) { // W
                this.controller.Walk(this.game, 'w');
                game.currentPlayer.Energy -= 1;
                this.currentDirection = PlayerAnimation.Direction.UP;
                walking = true;
            } else if (Gdx.input.isKeyJustPressed(47)) { // S
                this.controller.Walk(this.game, 's');
                game.currentPlayer.Energy -= 1;
                this.currentDirection = PlayerAnimation.Direction.DOWN;
                walking = true;
            } else if (Gdx.input.isKeyJustPressed(29)) { // A
                this.controller.Walk(this.game, 'a');
                game.currentPlayer.Energy -= 1;
                this.currentDirection = PlayerAnimation.Direction.LEFT;
                walking = true;
            } else if (Gdx.input.isKeyJustPressed(32)) { // D
                this.controller.Walk(this.game, 'd');
                game.currentPlayer.Energy -= 1;
                this.currentDirection = PlayerAnimation.Direction.RIGHT;
                walking = true;
            }
            else if (Gdx.input.isKeyJustPressed(Input.Keys.G) && !isShopOpen) {
                game.currentPlayer.GreenHouseFixed =true;
            }
            else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !isShopOpen) {
                this.controller.processNextTurn(this.game);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && !isRefrigeratorOpen && !isShopOpen) {
            isInventoryOpen = !isInventoryOpen;
            isSkillScreenOpen= false;
            if (isInventoryOpen) {
                inventoryUI.rebuildUI();
                Gdx.input.setInputProcessor(multiplexer); // ✅ ورودی به این مرحله بره
            } else {
                Gdx.input.setInputProcessor(multiplexer); // ✅ غیرفعال‌سازی، یا اگه Stage دیگه‌ای دارید تنظیمش کنید
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.T) && !isInventoryOpen && !isSkillScreenOpen) {
            game.gameClock.advanceTimeByOneHour(game , controller);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            isShopOpen = !isShopOpen;
            if (isShopOpen) {
                shopUI.show();
                Gdx.input.setInputProcessor(multiplexer);
            } else {
                this.show();
                Gdx.input.setInputProcessor(multiplexer);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.T) && isInventoryOpen) {
            inventoryUI.showToolsOnly();
            isBackPackOpen = !isBackPackOpen;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.C) && !isInventoryOpen && !isShopOpen) {
            selectingDirection = !selectingDirection;
            // می‌تونی اینجا مثلا یک صدای کوچک هم پخش کنی یا پیام بده
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S) && isInventoryOpen) {
            isSkillScreenOpen = !isSkillScreenOpen;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.M) && !isInventoryOpen && !isShopOpen) {
            isMiniMapOpen = !isMiniMapOpen;
            if (isMiniMapOpen) {

                updateMiniMapCamera();
            } else {

                camera.zoom = 1.5f;
                camera.update();
            }
            Gdx.input.setInputProcessor(multiplexer);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E) && !isShopOpen){
            game.currentPlayer.Energy =200;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q) && !isShopOpen){
            ShowSetting();
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            int tileSize = 16;
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();

            // تبدیل مختصات صفحه به مختصات جهان
            Vector3 worldCoords = camera.unproject(new Vector3(mouseX, mouseY, 0));
            int tileX = (int)(worldCoords.x / tileSize);
            int tileY = (int)(worldCoords.y / tileSize);

            if (game.Map.get(tileY).get(tileX).type.equals(TileType.SHACK)) {
                isRefrigeratorOpen = true;
                showKitchenMenu(tileX, tileY);
                if (RefrigeratorPick){
                    refrigeratorUI.rebuildUI();
                }
            }
            if (game.Map.get(tileY).get(tileX).type.equals(TileType.SHIPPINGBIN)) {
                isShippingBinOpen = true;
                isInventoryOpen = true;
            }
            if (game.Map.get(tileY).get(tileX).type.equals(TileType.TREE)) {
                 Trees trees =  (Trees)game.Map.get(tileY).get(tileX);
                 showMessage(controller.CraftInfo(game,trees.name));

            }
            if (game.Map.get(tileY).get(tileX).type.equals(TileType.FORAGING)) {
                Foraging foraging =  (Foraging) game.Map.get(tileY).get(tileX);
                showMessage(controller.CraftInfo(game,foraging.name));

            }
            if (game.Map.get(tileY).get(tileX).type.equals(TileType.BARN)
            || game.Map.get(tileY).get(tileX).type.equals(TileType.COOP)){
                showBarnMenu(tileX, tileY);
            }
            if (game.Map.get(tileY).get(tileX).type.equals(TileType.ANIMAL)){
                showAnimalMenu(tileX, tileY);
            }
            if (game.Map.get(tileY).get(tileX).type.equals(TileType.GREENHOUSE)){
                showGreenHouseMenu(tileX, tileY);
            }
        }


    }
    private void showGreenHouseMenu(int tileX, int tileY) {
        Dialog dialog = new Dialog("GreenHouse", skin) {
            @Override
            protected void result(Object object) {
                switch (object.toString()) {
                    case "Fix":
                        controller.GreenHouse(game);
                        break;


                    case "cancel":
                        break;
                }
            }
        };

        dialog.text("What do you want to do?");
        dialog.button("Fix GreenHouse", "Fix");

        dialog.button("Cancel", "cancel");

        dialog.show(uiStage);
    }
    private void showAnimalMenu (int tileX, int tileY) {
        Animal animal = null;

        for(Building building:game.currentPlayer.playerBuildings){
            for(Animal Target:building.animals){
                if (!Target.Inside){
                    animal = Target;
                }
            }
        }


        Animal finalAnimal = animal;
        Dialog dialog = new Dialog("Animal Options", skin) {
            String name;

            @Override
            protected void result(Object object) {

                switch (object.toString()) {
                    case "Pet":
                        controller.Pet(game , finalAnimal.name , finalAnimal.posX , finalAnimal.posY );
                        break;
                    case "Feed Animal":
                         controller.FeedAnimal(game , finalAnimal.name );

                       //  controller.RefrigeratorPick(game, "Apple"); // یا SelectBox در نسخه بعدی
                        break;
                    case "Show Info":
                       String Message = controller.AnimalsShow(game , finalAnimal.name);
                       game.gameScreen.showMessage(Message);
                        // controller.PrepareRecipe(game, "Fried Egg");
                        break;
                    case "cancel":
                        break;
                }
            }
        };

        dialog.text("What do you want to do?");
        dialog.button("Pet", "Pet");
        dialog.button("Feed", "Feed Animal");
        dialog.button("Show Info", "Show Info");
        dialog.button("Cancel", "cancel");

        dialog.show(uiStage);
    }
    private void showBarnMenu (int tileX, int tileY) {
        Dialog dialog = new Dialog("Animal Options", skin) {
            String name;
            @Override
            protected void result(Object object) {
                switch (object.toString()) {
                    case "Shepherd":
                         askForName(uiStage,skin, "" ,name ->{
                                     controller.Shepherd(game , name ,game.currentPlayer.PositionX+1 , game.currentPlayer.PositionY+1);

                                 });
                        break;
                    case "Collect Produce":
                            askForName(uiStage,skin, "" ,name ->{
                                controller.CollectProduce(game , name);
                            });
                        //  controller.RefrigeratorPick(game, "Apple"); // یا SelectBox در نسخه بعدی
                        break;
                    case "Sell Animal":
                        askForName(uiStage,skin, "" ,name ->{
                            controller.SellAnimal(game , name);
                        });
                        // controller.PrepareRecipe(game, "Fried Egg");
                        break;

                    case "cancel":
                        break;
                }
            }
        };

        dialog.text("What do you want to do?");
        dialog.button("Shepherd Animals", "Shepherd");
        dialog.button("Collect Produce", "Collect Produce");
        dialog.button("Sell Animal" , "Sell Animal");
        dialog.button("Cancel", "cancel");

        dialog.show(uiStage);
    }
    public void askForName(Stage stage, Skin skin, String title, Consumer<String> callback) {
        Dialog dialog = new Dialog(title, skin);

        TextField nameField = new TextField("", skin);
        nameField.setMessageText("Enter name...");

        TextButton okButton = new TextButton("OK", skin);
        TextButton cancelButton = new TextButton("Cancel", skin);

        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String enteredName = nameField.getText().trim();
                callback.accept(enteredName); // نتیجه رو برگردون
                dialog.hide();
            }
        });

        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
            }
        });

        dialog.getContentTable().add(new Label("Name:", skin)).pad(10);
        dialog.getContentTable().add(nameField).width(200).pad(10).row();
        dialog.getButtonTable().add(okButton).pad(5);
        dialog.getButtonTable().add(cancelButton).pad(5);

        dialog.show(stage);
    }

    private void showKitchenMenu(int tileX, int tileY) {
        System.out.println("showKitchenMenu");
        Dialog dialog = new Dialog("Kitchen Options", skin) {
            @Override
            protected void result(Object object) {
                switch (object.toString()) {
                    case "put":
                        isInventoryOpen = !isInventoryOpen;
                        break;
                    case "pick":
                        RefrigeratorPick = !RefrigeratorPick;
                        //  controller.RefrigeratorPick(game, "Apple"); // یا SelectBox در نسخه بعدی
                        break;
                    case "cook":
                        KitchenOpen = !KitchenOpen;
                        // controller.PrepareRecipe(game, "Fried Egg");
                        break;
                    case "cancel":
                        isRefrigeratorOpen = !isRefrigeratorOpen;
                        break;
                }
            }
        };

        dialog.text("What do you want to do?");
        dialog.button("Put Food in Refrigerator", "put");
        dialog.button("Pick Food from Refrigerator", "pick");
        dialog.button("Cook Food", "cook");
        dialog.button("Cancel", "cancel");

        dialog.show(uiStage);
    }
    private void ShowSetting() {
        Dialog dialog = new Dialog("Setting", skin) {
            @Override
            protected void result(Object object) {
                switch (object.toString()) {
                    case "Exit":
                        Main.getInstance().setScreen(new LoginRegisterMenuView());

                        break;

                    case "cancel":

                        break;
                }
            }
        };

        dialog.text("What do you want to do?");
        dialog.button("Exit Game", "Exit");
        dialog.button("Cancel", "cancel");

        dialog.show(uiStage);
    }




    private void drawClockUI() {
        this.batch.setProjectionMatrix((new Matrix4()).setToOrtho2D(0.0F, 0.0F, (float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight()));
        this.batch.begin();
        float screenWidth = (float)Gdx.graphics.getWidth();
        float screenHeight = (float)Gdx.graphics.getHeight();
        float clockWidth = (float)this.clockTexture.getWidth();
        float clockHeight = (float)this.clockTexture.getHeight();
        float clockX = screenWidth - clockWidth - 10.0F;
        float clockY = screenHeight - clockHeight - 10.0F;
        this.batch.draw(this.clockTexture, clockX, clockY, clockWidth, clockHeight);
        this.font.setColor(0.1F, 0.1F, 0.1F, 1.0F);
        int hour = this.game.gameClock.getHour();
        mapRenderer.setNight(hour >= 18 || hour < 6);
        String ampm = hour >= 12 ? "PM" : "AM";
        int displayHour = hour % 12;
        if (displayHour == 0) {
            displayHour = 12;
        }

        String time = String.format("%02d:00 %s", displayHour, ampm);
        String var10000 = this.game.gameClock.getDayOfWeek().substring(0, 3);
        String day = var10000 + ". " + this.game.gameClock.getDay() + " - " + this.game.gameClock.getCurrentSeason();
        String money = "$" + this.game.currentPlayer.money;
        this.font.draw(this.batch, day, clockX + 120.0F, clockY + clockHeight - 20.0F);
        this.font.draw(this.batch, time, clockX + 130.0F, clockY + clockHeight - 115.0F);
        this.font.draw(this.batch, money, clockX + clockWidth - 120.0F, clockY + 42.0F);
        this.batch.end();
    }





    private void updateCamera() {
        int tileSize = 16;
        float playerX = game.currentPlayer.PositionX * tileSize + tileSize / 2f;
        float playerY = game.currentPlayer.PositionY * tileSize + tileSize / 2f;

        float cameraHalfWidth = camera.viewportWidth / 2f;
        float cameraHalfHeight = camera.viewportHeight / 2f;

        float mapWidth = game.Map.get(0).size() * tileSize;
        float mapHeight = game.Map.size() * tileSize;

        float camX = Math.min(Math.max(playerX, cameraHalfWidth), mapWidth - cameraHalfWidth);
        float camY = Math.min(Math.max(playerY, cameraHalfHeight), mapHeight - cameraHalfHeight);

        camera.position.set(camX, camY, 0);
    }

    private void updateMiniMapCamera() {
        int tileSize = 16;
        int mapWidthInTiles = game.Map.get(0).size();
        int mapHeightInTiles = game.Map.size();

        float mapWidth = mapWidthInTiles * tileSize;
        float mapHeight = mapHeightInTiles * tileSize;

        float centerX = mapWidth / 2f;
        float centerY = mapHeight / 2f;

        float viewportWidth = mapWidth * 2f;
        float viewportHeight = mapHeight * 2f;

        float screenRatio = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        float mapRatio = viewportWidth / viewportHeight;

        if (screenRatio > mapRatio) {
            viewportWidth = viewportHeight * screenRatio;
        } else {
            viewportHeight = viewportWidth / screenRatio;
        }

        miniMapCamera.viewportWidth = viewportWidth;
        miniMapCamera.viewportHeight = viewportHeight;

        miniMapCamera.zoom = 0.55f;

        miniMapCamera.position.set(centerX, centerY, 0);
        miniMapCamera.update();
    }

    public void showMessage(String message) {
        Dialog dialog = new Dialog("\n", skin) {
            @Override
            protected void result(Object object) {
                // وقتی دکمه OK زده شد اینجا اجرا میشه
                if ("ok".equals(object)) {
                    this.hide(); // دیالوگ را ببند
                }
            }
        };

        dialog.text(message);
        dialog.button("OK", "ok");

        dialog.show(uiStage);
    }
    }





