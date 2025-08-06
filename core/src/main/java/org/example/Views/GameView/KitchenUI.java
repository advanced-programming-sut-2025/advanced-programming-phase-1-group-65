package org.example.Views.GameView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.example.Controllers.GameController.GameController;
import org.example.Models.*;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

import java.util.ArrayList;
import java.util.List;


public class KitchenUI extends Stage {
    private final GameController controller;
    private final Player player;
    private final Table inventoryTable;
    private final List<Recipe> displayedItems = new ArrayList<>();
    private final Image background;
    private final Label.LabelStyle labelStyle;
    private Skin skin;

    private static final int CELL_SIZE = 75;
    private static final int COLUMNS = 5;
    private static final int ROWS = 10;
    private static final int GRID_WIDTH = CELL_SIZE * COLUMNS * 2;
    private static final int GRID_HEIGHT = CELL_SIZE * ROWS;
    public Game game;
    private int selectedIndex = 0;
    private int currentPage = 0;

    public KitchenUI(Game game, SpriteBatch batch, GameController controller) {
        super(new ScreenViewport(), batch);
        this.game = game;
        this.player = game.currentPlayer;
        displayedItems.addAll(game.AllRecipes);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        this.controller = controller;
        Texture bgTexture = new Texture(Gdx.files.internal("Inventory.png"));
        background = new Image(bgTexture);
        background.setSize(GRID_WIDTH, GRID_HEIGHT);
        float centerX = (Gdx.graphics.getWidth() - GRID_WIDTH) / 2f;
        float centerY = (Gdx.graphics.getHeight() - GRID_HEIGHT) / 2f;
        background.setPosition(centerX, centerY);
        addActor(background);
        BitmapFont font = new BitmapFont(); // فونت پیش‌فرض
        labelStyle = new Label.LabelStyle(font, Color.WHITE); // رنگ متن سفید
        inventoryTable = new Table();
        inventoryTable.setSize(GRID_WIDTH, GRID_HEIGHT);
        inventoryTable.setPosition(centerX, centerY);
        inventoryTable.top().left();
        addActor(inventoryTable);

        rebuildUI();
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        handleInput();
    }
    private void handleInput() {
        boolean updated = false;
        int totalItems = displayedItems.size();
        int pageSize = COLUMNS * 5;
        // 5 ردیف در صفحه (طبق rebuildUI)

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (selectedIndex < totalItems - 1) {
                selectedIndex++;
                // اگر به انتهای صفحه رسیدیم، صفحه را تغییر دهیم
                if (selectedIndex >= (currentPage + 1) * pageSize && (currentPage + 1) * pageSize < totalItems) {
                    currentPage++;
                }
                updated = true;
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (selectedIndex > 0) {
                selectedIndex--;
                // اگر به ابتدای صفحه رسیدیم و صفحه قبلی وجود دارد
                if (selectedIndex < currentPage * pageSize && currentPage > 0) {
                    currentPage--;
                }
                updated = true;
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (selectedIndex + COLUMNS < totalItems) {
                selectedIndex += COLUMNS;

                // اگر از پایین صفحه فعلی بیرون رفتیم صفحه را تغییر دهیم
                if (selectedIndex >= (currentPage + 1) * pageSize && (currentPage + 1) * pageSize < totalItems) {
                    currentPage++;
                }

                updated = true;
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (selectedIndex - COLUMNS >= 0) {
                selectedIndex -= COLUMNS;

                // اگر از بالا صفحه فعلی بیرون رفتیم صفحه را تغییر دهیم
                if (selectedIndex < currentPage * pageSize && currentPage > 0) {
                    currentPage--;
                }

                updated = true;
            }

        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.gameScreen.RefrigeratorPick = false;
            game.gameScreen.isRefrigeratorOpen =false;
            game.gameScreen.KitchenOpen =false;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (selectedIndex >= 0 && selectedIndex < displayedItems.size()) {
                Recipe recipe = displayedItems.get(selectedIndex);
                controller.PrepareRecipe(game, recipe.name);
            }

        }
        if (updated) {
            rebuildUI();
        }

    }
    public void rebuildUI() {
        inventoryTable.clearChildren();

        // هم‌زمان‌سازی با لیست اصلی یخچال
        displayedItems.clear();
        displayedItems.addAll(game.AllRecipes);

        int pageSize = COLUMNS * 5;
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, displayedItems.size());

        for (int i = start; i < end; i++) {
            Recipe recipe = displayedItems.get(i);
            Image img = new Image(recipe.texture);
            img.setSize(CELL_SIZE, CELL_SIZE);

            // تغییر رنگ اگر recipe در KnownRecipe نبود
            if (!player.KnownRecipes.contains(recipe)) {
                img.setColor(Color.GRAY);
            }

            Stack stack = new Stack();
            stack.setSize(CELL_SIZE, CELL_SIZE);
            stack.add(img);

            if (i == selectedIndex) {
                img.setColor(Color.YELLOW);  // های‌لایت

                String labelText = recipe.name + recipe.description;
                Label itemLabel = new Label(labelText, labelStyle);
                itemLabel.setFontScale(1.5f);
                itemLabel.setColor(Color.WHITE);

                Table labelContainer = new Table();
                labelContainer.top();
                labelContainer.add(itemLabel).padTop(5);
                stack.add(labelContainer);
            }

            inventoryTable.add(stack).size(CELL_SIZE, CELL_SIZE).pad(65, 40, -2, 30);

            if ((i - start + 1) % COLUMNS == 0) {
                inventoryTable.row().padTop(10);
            }
        }


        int remainingSlots = pageSize - (end - start);
        for (int i = 0; i < remainingSlots; i++) {
            Actor emptySlot = new Actor();
            emptySlot.setSize(CELL_SIZE, CELL_SIZE);
            inventoryTable.add(emptySlot).size(CELL_SIZE, CELL_SIZE).pad(65,40,-2,30);

            if ((i + (end - start) + 1) % COLUMNS == 0) {
                inventoryTable.row().padTop(10);
            }
        }
    }


}
