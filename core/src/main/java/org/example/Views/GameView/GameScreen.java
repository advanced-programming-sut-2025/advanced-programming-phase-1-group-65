package org.example.Views.GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import org.example.Controllers.GameController.GameController;
import org.example.Models.Game;
import org.example.Views.MapRenderer;
import org.example.Views.PlayerAnimation;
import org.example.Views.PlayerAnimation.Direction;

public class GameScreen implements Screen {
    private final Game game;
    private final GameController controller = new GameController();
    private OrthographicCamera camera;
    private final MapRenderer mapRenderer;
    private SpriteBatch batch;
    private PlayerAnimation playerAnim;
    private PlayerAnimation.Direction currentDirection;
    private Texture clockTexture;
    private BitmapFont font;
    private float timeAccumulator;
    private final float timePerGameHour;

    public GameScreen(Game game) {
        this.currentDirection = Direction.DOWN;
        this.timeAccumulator = 0.0F;
        this.timePerGameHour = 5.0F;
        this.game = game;
        this.mapRenderer = new MapRenderer(game.Map);
    }

    @Override
    public void show() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera(320.0F, 240.0F);
        this.camera.zoom = 1.7F;
        this.camera.update();
        this.playerAnim = new PlayerAnimation(0.2F);
        this.clockTexture = new Texture(Gdx.files.internal("ui/Clock.png"));
        this.font = new BitmapFont();
        this.font.getData().setScale(1.5F);
    }

    @Override
    public void render(float delta) {
        this.timeAccumulator += delta;
        if (this.timeAccumulator >= this.timePerGameHour) {
            this.timeAccumulator -= this.timePerGameHour;
            this.game.gameClock.advanceTimeByOneHour(this.game, this.controller);
        }

        this.handleInput();
        this.updateCamera();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(16384);

        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();

        this.mapRenderer.render(this.batch);

        TextureRegion frame = this.playerAnim.getCurrentFrame(this.currentDirection, delta);
        int px = this.game.currentPlayer.PositionX;
        int py = this.game.currentPlayer.PositionY;
        int tileSize = 16;
        this.batch.draw(frame, px * tileSize, py * tileSize, tileSize, tileSize);

        this.batch.end();

        this.drawClockUI();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            this.controller.Walk(this.game, 'w');
            this.currentDirection = Direction.UP;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            this.controller.Walk(this.game, 's');
            this.currentDirection = Direction.DOWN;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            this.controller.Walk(this.game, 'a');
            this.currentDirection = Direction.LEFT;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            this.controller.Walk(this.game, 'd');
            this.currentDirection = Direction.RIGHT;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            this.game.gameClock.advanceTimeByOneHour(this.game, this.controller);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            this.controller.processNextTurn(this.game);
        }
    }

    private void updateCamera() {
        int tileSize = 16;
        float px = this.game.currentPlayer.PositionX * tileSize + tileSize / 2f;
        float py = this.game.currentPlayer.PositionY * tileSize + tileSize / 2f;
        this.camera.position.set(px, py, 0);
        this.camera.update();
    }

    private void drawClockUI() {
        this.batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.batch.begin();

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float clockWidth = this.clockTexture.getWidth();
        float clockHeight = this.clockTexture.getHeight();
        float clockX = screenWidth - clockWidth - 10f;
        float clockY = screenHeight - clockHeight - 10f;

        this.batch.draw(this.clockTexture, clockX, clockY, clockWidth, clockHeight);

        this.font.setColor(0.1f, 0.1f, 0.1f, 1f);
        int hour = this.game.gameClock.getHour();
        String ampm = hour >= 12 ? "PM" : "AM";
        int displayHour = hour % 12;
        if (displayHour == 0) {
            displayHour = 12;
        }

        String time = String.format("%02d:00 %s", displayHour, ampm);
        String day = this.game.gameClock.getDayOfWeek().substring(0, 3) + ". " + this.game.gameClock.getDay() + " - " + this.game.gameClock.getCurrentSeason();
        String money = "$" + this.game.currentPlayer.money;

        this.font.draw(this.batch, day, clockX + 120f, clockY + clockHeight - 20f);
        this.font.draw(this.batch, time, clockX + 130f, clockY + clockHeight - 115f);
        this.font.draw(this.batch, money, clockX + clockWidth - 120f, clockY + 42f);

        this.batch.end();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        this.batch.dispose();
        this.clockTexture.dispose();
        this.font.dispose();
        this.mapRenderer.dispose();
    }
}