package org.example.Views.GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.example.Controllers.GameController.GameController;
import org.example.Models.Enums.ItemType;
import org.example.Models.Game;
import org.example.Models.Item;
import org.example.Views.MapRenderer;
import org.example.Views.PlayerAnimation;

public class GameScreen implements Screen {
    final Game game;
    OrthographicCamera camera;
    GameController controller = new GameController();
    public InventoryUI inventoryUI;
    boolean isInventoryOpen = false;
    private Skin skin;
    public boolean selectingDirection = false;
    private final MapRenderer mapRenderer;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private PlayerAnimation playerAnim;
    private PlayerAnimation.Direction currentDirection;
    private Texture clockTexture;
    private BitmapFont font;
    private float timeAccumulator;
    private final float timePerGameHour;


    public GameScreen(Game game) {
        this.game = game;
        // وقتی mapRenderer از جنس خودت هست نیازی به پاس دادنش نیست
        this.mapRenderer = new MapRenderer(game.Map);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        this.currentDirection = PlayerAnimation.Direction.DOWN;
        this.timeAccumulator = 0.0F;
        this.timePerGameHour = 5.0F;
// اینجا باید map رو بدی
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        inventoryUI = new InventoryUI(game, batch, controller);

        camera = new OrthographicCamera(320, 160);
        camera.zoom = 1f;
        camera.update();

        // تنظیم ورودی برای مدیریت جهت انتخابی
        Gdx.input.setInputProcessor(new DirectionInputProcessor(this));
        this.playerAnim = new PlayerAnimation(0.2F);
        this.clockTexture = new Texture(Gdx.files.internal("ui/Clock.png"));
        this.font = new BitmapFont();
        this.font.getData().setScale(1.5F);
    }


    @Override
    public void render(float delta) {
        this.timeAccumulator += delta;
        if (this.timeAccumulator >= 20.0f) {
            this.timeAccumulator -=20.0F;
            this.game.gameClock.advanceTimeByOneHour(this.game, this.controller);
        }

        handleInput();
        updateCamera();

        camera.update();
        batch.setProjectionMatrix(camera.combined);


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        mapRenderer.render(batch);
        TextureRegion frame = this.playerAnim.getCurrentFrame(this.currentDirection, delta);

        if (selectingDirection) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(1, 1, 0, 0.5f); // زرد شفاف

            int playerX = game.currentPlayer.PositionX;
            int playerY = game.currentPlayer.PositionY;
            int tileSize = 16;
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {

                    // فقط 8 خانه‌ی اطراف (نه وسط)


                    int tileX = playerX + dx;
                    int tileY = playerY + dy;
                    if ( tileX== playerX &&tileY == playerY) continue;
                    shapeRenderer.rect(
                        tileX * tileSize,
                        tileY * tileSize,
                        tileSize,
                        tileSize
                    );
                }
            }

            shapeRenderer.end();
        }

        int px = this.game.currentPlayer.PositionX;
        int py = this.game.currentPlayer.PositionY;
        int playerSize = 12;
        int tileSize = 16;
        int offset = (tileSize - playerSize) / 2;
        this.batch.draw(frame, (float)(px * tileSize + offset), (float)(py * tileSize ), (float)playerSize, (float)playerSize);

        batch.end();
        this.drawClockUI();

        if (isInventoryOpen) {
            inventoryUI.act(delta);
            inventoryUI.draw();
        }
    }
    private void handleInput() {
        if (!isInventoryOpen) {
            if (Gdx.input.isKeyJustPressed(51)) {
                this.controller.Walk(this.game, 'w');
                this.currentDirection = PlayerAnimation.Direction.UP;
            } else if (Gdx.input.isKeyJustPressed(47)) {
                this.controller.Walk(this.game, 's');
                this.currentDirection = PlayerAnimation.Direction.DOWN;
            } else if (Gdx.input.isKeyJustPressed(29)) {
                this.controller.Walk(this.game, 'a');
                this.currentDirection = PlayerAnimation.Direction.LEFT;
            } else if (Gdx.input.isKeyJustPressed(32)) {
                this.controller.Walk(this.game, 'd');
                this.currentDirection = PlayerAnimation.Direction.RIGHT;
            } else if (Gdx.input.isKeyJustPressed(48)) {
                this.game.gameClock.advanceTimeByOneHour(this.game, this.controller);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            isInventoryOpen = !isInventoryOpen;
            if (isInventoryOpen) {
                inventoryUI.rebuildUI();
                Gdx.input.setInputProcessor(inventoryUI); // ✅ ورودی به این مرحله بره
            } else {
                Gdx.input.setInputProcessor(new DirectionInputProcessor(this)); // ✅ غیرفعال‌سازی، یا اگه Stage دیگه‌ای دارید تنظیمش کنید
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.T) && isInventoryOpen) {
            inventoryUI.showToolsOnly();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.C) && !isInventoryOpen) {
            selectingDirection = !selectingDirection;
            // می‌تونی اینجا مثلا یک صدای کوچک هم پخش کنی یا پیام بده
        }

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





    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
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





    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
