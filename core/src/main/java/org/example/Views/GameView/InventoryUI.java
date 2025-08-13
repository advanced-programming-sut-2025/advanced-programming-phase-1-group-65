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
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;
import org.example.Models.Game;
import org.example.Models.Item;
import org.example.Models.Player;

import java.util.ArrayList;
import java.util.List;


public class InventoryUI extends Stage {
    private final GameController controller;
    private final Table inventoryTable;
    private final List<Item> displayedItems = new ArrayList<>();
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

    public InventoryUI(Game game, SpriteBatch batch, GameController controller) {
        super(new ScreenViewport(), batch);
        this.game = game;
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

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && !game.gameScreen.isRefrigeratorOpen && !game.gameScreen.isShippingBinOpen) {
            // وقتی Enter زده شد، اگر آیتم انتخاب شده Tool بود انتخابش کن
            if (selectedIndex >= 0 && selectedIndex < displayedItems.size()) {
                Item selectedItem = displayedItems.get(selectedIndex);
                if (selectedItem.type == ItemType.TOOL) {
                    game.currentPlayer.CurrentTool = (org.example.Models.Tool) selectedItem;
                    game.currentPlayer.CurrentItem = null;
                    System.out.println("Current tool selected: " + selectedItem.getName());
                    showMessage("You Have Equiped " + selectedItem.getName() );

                }
                else if (selectedItem.subtype == ItemSubType.SEED){
                    game.currentPlayer.CurrentTool = null;
                    game.currentPlayer.CurrentItem =  selectedItem;
                    System.out.println("Current Seed selected: " + selectedItem.getName());
                    showMessage("You Have Equiped " + selectedItem.getName() );
                }
                else if (selectedItem.type == ItemType.FOOD){
                    controller.Eat(game , selectedItem.name);
                    game.gameScreen.isInventoryOpen = false;
                }
                else if (selectedItem.subtype == ItemSubType.BARN || selectedItem.subtype == ItemSubType.COOP) {
                    game.currentPlayer.CurrentTool = null;
                    game.currentPlayer.CurrentItem =  selectedItem;
                }
                else if (selectedItem.subtype == ItemSubType.FERTILIZER){
                    game.currentPlayer.CurrentTool = null;
                    game.currentPlayer.CurrentItem =  selectedItem;
                }
            }

        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && game.gameScreen.isRefrigeratorOpen){
            if (selectedIndex >= 0 && selectedIndex < displayedItems.size()) {
                Item selectedItem = displayedItems.get(selectedIndex);
                System.out.println("selectedIndex: " + selectedIndex);
                System.out.println("selectedItem: " + displayedItems.get(selectedIndex).name);

                game.gameScreen.controller.RefrigeratorPut(game , selectedItem.name);
                game.gameScreen.isInventoryOpen = false;
                game.gameScreen.isRefrigeratorOpen = false;

            }
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && game.gameScreen.isRefrigeratorOpen){
            game.gameScreen.isInventoryOpen = false;
            game.gameScreen.isRefrigeratorOpen = false;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && game.gameScreen.isShippingBinOpen){
            if (selectedIndex >= 0 && selectedIndex < displayedItems.size()) {
                Item selectedItem = displayedItems.get(selectedIndex);

                controller.Sell(selectedItem.name , game, 1);
                game.gameScreen.isShippingBinOpen = false;
                game.gameScreen.isInventoryOpen = false;

            }
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.DEL) ){
            if (selectedIndex >= 0 && selectedIndex < displayedItems.size()) {
                Item selectedItem = displayedItems.get(selectedIndex);
                controller.removeItem(game,selectedItem.name,1);
                updated = true;
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
        displayedItems.clear();
        displayedItems.addAll(game.currentPlayer.items);


        int pageSize = COLUMNS * 5;
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, displayedItems.size());

        for (int i = start; i < end; i++) {
            Item item = displayedItems.get(i);
            Image img = new Image(item.getTexture());
            img.setSize(CELL_SIZE, CELL_SIZE);

            Stack stack = new Stack();
            stack.setSize(CELL_SIZE, CELL_SIZE);
            stack.add(img);

            if (i == selectedIndex) {
                img.setColor(Color.YELLOW);

                String labelText = item.getName() + " x" + item.Count;
                Label itemLabel = new Label(labelText, labelStyle);
                itemLabel.setFontScale(1.5f);
                itemLabel.setColor(Color.WHITE);

                // برای قرار دادن متن بالای آیتم
                Table labelContainer = new Table();
                labelContainer.top(); // متن در بالا قرار بگیرد
                labelContainer.add(itemLabel).padTop(5);

                stack.add(labelContainer);
            }

            inventoryTable.add(stack).size(CELL_SIZE, CELL_SIZE).pad(65, 40, -2, 30);

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
        inventoryTable.clearChildren();
        displayedItems.clear();
        for (Item item : game.currentPlayer.items) {
            if (item.type == ItemType.TOOL){
                displayedItems.add(item);
            }
        }

        int pageSize = COLUMNS * 5;
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, displayedItems.size());

        for (int i = start; i < end; i++) {
            Item item = displayedItems.get(i);
            Image img = new Image(item.getTexture());
            img.setSize(CELL_SIZE, CELL_SIZE);

            Stack stack = new Stack();
            stack.setSize(CELL_SIZE, CELL_SIZE);
            stack.add(img);

            if (i == selectedIndex) {
                img.setColor(Color.YELLOW);

                String labelText = item.getName() + " x" + item.Count;
                Label itemLabel = new Label(labelText, labelStyle);
                itemLabel.setFontScale(1.5f);
                itemLabel.setColor(Color.WHITE);

                // برای قرار دادن متن بالای آیتم
                Table labelContainer = new Table();
                labelContainer.top(); // متن در بالا قرار بگیرد
                labelContainer.add(itemLabel).padTop(5);

                stack.add(labelContainer);
            }

            inventoryTable.add(stack).size(CELL_SIZE, CELL_SIZE).pad(65, 40, -2, 30);

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
    public void showMessage(String message) {
        Table messageTable = new Table();
        messageTable.setFillParent(true);
        messageTable.top(); // متن بالا نمایش داده شود

        Label.LabelStyle messageStyle = new Label.LabelStyle(new BitmapFont(), Color.GOLD);
        Label messageLabel = new Label(message, messageStyle);
        messageLabel.setFontScale(4f);
        messageTable.add(messageLabel).padTop(20);

        addActor(messageTable); // اضافه به stage

        // بعد از 2 ثانیه حذف شود
        addAction(Actions.sequence(
            Actions.delay(2f),
            Actions.run(() -> messageTable.remove())
        ));
    }


    public void showAllItems() {
        displayedItems.clear();
        displayedItems.addAll(game.currentPlayer.items);
        currentPage = 0;
        selectedIndex = 0;
        rebuildUI();
    }
}
