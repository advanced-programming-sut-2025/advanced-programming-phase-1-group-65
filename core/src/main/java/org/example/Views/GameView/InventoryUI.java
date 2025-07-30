package org.example.Views.GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.example.Models.Enums.ItemType;
import org.example.Models.Game;
import org.example.Models.Item;
import org.example.Models.Player;

import java.util.ArrayList;
import java.util.List;

public class InventoryUI extends Stage {
    private final Player player;
    private final Table inventoryTable;
    private final List<Item> displayedItems = new ArrayList<>();
    private final Image background;

    private static final int CELL_SIZE = 75;
    private static final int COLUMNS = 5;
    private static final int ROWS = 10;
    private static final int GRID_WIDTH = CELL_SIZE * COLUMNS * 2;
    private static final int GRID_HEIGHT = CELL_SIZE * ROWS;

    private int selectedIndex = 0;
    private int currentPage = 0;

    public InventoryUI(Game game, SpriteBatch batch) {
        super(new ScreenViewport(), batch);
        this.player = game.currentPlayer;
        displayedItems.addAll(player.items);

        Texture bgTexture = new Texture(Gdx.files.internal("Inventory.png"));
        background = new Image(bgTexture);
        background.setSize(GRID_WIDTH, GRID_HEIGHT);
        float centerX = (Gdx.graphics.getWidth() - GRID_WIDTH) / 2f;
        float centerY = (Gdx.graphics.getHeight() - GRID_HEIGHT) / 2f;
        background.setPosition(centerX, centerY);
        addActor(background);

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
        int pageSize = COLUMNS * 5;  // 5 ردیف در صفحه (طبق rebuildUI)

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

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // وقتی Enter زده شد، اگر آیتم انتخاب شده Tool بود انتخابش کن
            if (selectedIndex >= 0 && selectedIndex < displayedItems.size()) {
                Item selectedItem = displayedItems.get(selectedIndex);
                if (selectedItem.type == ItemType.TOOL) {
                    player.CurrentTool = (org.example.Models.Tool) selectedItem;
                    System.out.println("Current tool selected: " + selectedItem.getName());
                }
            }
        }

        if (updated) {
            rebuildUI();
        }


        // **حالا selectedIndex را به محدوده صفحه جدید محدود کنیم**
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, totalItems);
        if (selectedIndex < start) selectedIndex = start;
        if (selectedIndex >= end) selectedIndex = end - 1;

        if (updated) {
            rebuildUI();
        }
    }

    public void rebuildUI() {
        inventoryTable.clearChildren();

        int pageSize = COLUMNS * 5;
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, displayedItems.size());

        for (int i = start; i < end; i++) {
            Item item = displayedItems.get(i);
            Image img = new Image(item.getTexture());
            img.setSize(CELL_SIZE, CELL_SIZE);

            if (i == selectedIndex) {
                img.setColor(Color.YELLOW);
            }

            inventoryTable.add(img).size(CELL_SIZE, CELL_SIZE).pad(65,40,-2,30);

            if ((i - start + 1) % COLUMNS == 0) {
                inventoryTable.row().padTop(10);
            }
        }

        // پر کردن خانه‌های خالی برای حفظ ساختار جدول
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


    public void showToolsOnly() {
        displayedItems.clear();
        for (Item item : player.items) {
            if (item.type == ItemType.TOOL) {
                displayedItems.add(item);
            }
        }
        currentPage = 0;
        selectedIndex = 0;
        rebuildUI();
    }

    public void showAllItems() {
        displayedItems.clear();
        displayedItems.addAll(player.items);
        currentPage = 0;
        selectedIndex = 0;
        rebuildUI();
    }
}
