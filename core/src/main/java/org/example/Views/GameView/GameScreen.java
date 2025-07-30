package org.example.Views.GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.example.Controllers.GameController.GameController;
import org.example.Models.Enums.ItemType;
import org.example.Models.Game;
import org.example.Models.Item;
import org.example.Views.MapRenderer;

public class GameScreen implements Screen {
    private final Game game;
    GameController controller = new GameController();
    private OrthographicCamera camera;
    private InventoryUI inventoryUI;
    private boolean isInventoryOpen = false;



    private final MapRenderer mapRenderer;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    public GameScreen(Game game) {
        this.game = game;
        // وقتی mapRenderer از جنس خودت هست نیازی به پاس دادنش نیست
        this.mapRenderer = new MapRenderer(game.Map); // اینجا باید map رو بدی
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        inventoryUI = new InventoryUI(game, batch); // ✅ این خطو اضافه کن

        camera = new OrthographicCamera(320, 240);
        camera.zoom = 1f; // یا هر بزرگنمایی دلخواه
        camera.update();
    }

    @Override
    public void render(float delta) {
        handleInput();
        updateCamera();

        camera.update();
        batch.setProjectionMatrix(camera.combined);


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        mapRenderer.render(batch);
        batch.end();
        if (isInventoryOpen) {
            inventoryUI.act(delta);
            inventoryUI.draw();
        }
    }
    private void handleInput() {
        if (!isInventoryOpen) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.W))
                controller.Walk(game, 'w');
            if (Gdx.input.isKeyJustPressed(Input.Keys.S))
                controller.Walk(game,'s');
            if (Gdx.input.isKeyJustPressed(Input.Keys.A))
                controller.Walk(game,'a');
            if (Gdx.input.isKeyJustPressed(Input.Keys.D))
                controller.Walk(game,'d');
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            isInventoryOpen = !isInventoryOpen;
            if (isInventoryOpen) {
                inventoryUI.rebuildUI();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.T) && isInventoryOpen) {
            inventoryUI.showToolsOnly();
        }

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
