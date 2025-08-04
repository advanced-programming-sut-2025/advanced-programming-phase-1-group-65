package org.example.Views.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.example.Controllers.ShopController.ShopController;
import org.example.Models.Game;
import org.example.Models.ShopItem;
import org.example.Models.LimitedShopItem;

import java.util.List;

public class ShopUI implements InputProcessor {

    private final Game game;
    private final ShopController shopController;

    private Stage stage;
    private Skin skin;
    private Table rootTable;
    private Label messageLabel;

    private boolean isVisible = false;

    private SelectBox<String> filterSelectBox;
    private static final String FILTER_ALL = "All Products";
    private static final String FILTER_AVAILABLE = "Available Products Only";

    private Table itemsTable;
    private ScrollPane scrollPane;
    private int focusedButtonIndex = 0;
    private java.util.List<TextButton> buyButtons = new java.util.ArrayList<>();

    public ShopUI(Game game) {
        this.game = game;
        this.shopController = new ShopController();

        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        initFilterSelectBox();
        buildUI();
    }

    private void initFilterSelectBox() {
        filterSelectBox = new SelectBox<>(skin);
        filterSelectBox.setItems(FILTER_ALL, FILTER_AVAILABLE);
        filterSelectBox.setSelected(FILTER_ALL);

        // Disable mouse interaction (اختیاری)
        filterSelectBox.setTouchable(Touchable.disabled);
    }

    private void buildUI() {
        rootTable.clear();

        Label title = new Label("Shop Menu", skin);
        title.setFontScale(2f);
        rootTable.add(title).colspan(3).padBottom(20);
        rootTable.row();

        if (!shopController.isPlayerInShop(game)) {
            messageLabel = new Label("You must be next to a shop to open the shop menu.", skin);
            messageLabel.setColor(1, 0, 0, 1);
            rootTable.add(messageLabel).colspan(3).padBottom(20);
            return;
        }

        rootTable.add(new Label("Filter Products: ", skin)).left().padBottom(15);
        rootTable.add(filterSelectBox).left().padBottom(15).colspan(2);
        rootTable.row();

        buyButtons.clear();
        focusedButtonIndex = 0;

        itemsTable = new Table();
        List<ShopItem> unlimitedItems = shopController.getNearbyShop(game).getUnlimitedItems();
        List<LimitedShopItem> limitedItems = shopController.getNearbyShop(game).getLimitedItems();

        Label unlimitedLabel = new Label("Unlimited Items:", skin);
        unlimitedLabel.setAlignment(Align.left);
        itemsTable.add(unlimitedLabel).left().padBottom(10).colspan(2);
        itemsTable.row();

        for (ShopItem item : unlimitedItems) {
            if (filterSelectBox.getSelected().equals(FILTER_AVAILABLE) && !item.isAvailable()) {
                continue;
            }

            Label itemLabel = new Label(item.getName() + " - " + item.getPrice() + "g", skin);
            itemLabel.setAlignment(Align.left);
            if (!item.isAvailable()) itemLabel.setColor(0.5f, 0.5f, 0.5f, 1f);

            TextButton buyButton = new TextButton("Buy", skin);
            buyButton.setName(item.getName());

            buyButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    purchaseItem(item);
                }
            });

            buyButtons.add(buyButton);

            itemsTable.add(itemLabel).left().padBottom(5).expandX();
            itemsTable.add(buyButton).right().padBottom(5);
            itemsTable.row();
        }

        itemsTable.row();

        Label limitedLabel = new Label("Limited Items:", skin);
        limitedLabel.setAlignment(Align.left);
        itemsTable.add(limitedLabel).left().padBottom(10).colspan(2);
        itemsTable.row();

        for (LimitedShopItem item : limitedItems) {
            if (filterSelectBox.getSelected().equals(FILTER_AVAILABLE) && !item.isAvailable()) {
                continue;
            }

            int remaining = item.getDailyLimit() - item.getPurchasedToday();
            Label itemLabel = new Label(item.getName() + " - " + item.getPrice() + "g (Remaining today: " + remaining + ")", skin);
            itemLabel.setAlignment(Align.left);
            if (!item.isAvailable()) itemLabel.setColor(0.5f, 0.5f, 0.5f, 1f);

            TextButton buyButton = new TextButton("Buy", skin);
            buyButton.setName(item.getName());

            buyButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    purchaseLimitedItem(item);
                }
            });

            buyButtons.add(buyButton);

            itemsTable.add(itemLabel).left().padBottom(5).expandX();
            itemsTable.add(buyButton).right().padBottom(5);
            itemsTable.row();
        }

        if (scrollPane == null) {
            scrollPane = new ScrollPane(itemsTable, skin);
            scrollPane.setFadeScrollBars(false);
            scrollPane.setScrollingDisabled(true, false);
            scrollPane.setForceScroll(false, true);
            scrollPane.setOverscroll(false, false);
        } else {
            scrollPane.setWidget(itemsTable);
        }

        rootTable.add(scrollPane).colspan(3).fill().expand().padBottom(10);
        rootTable.row();

        messageLabel = new Label("Press Z to close shop | Use UP/DOWN to select Buy | ENTER to purchase", skin);
        messageLabel.setAlignment(Align.center);
        rootTable.add(messageLabel).colspan(3).padTop(10);

        updateButtonFocus();
    }

    private void updateButtonFocus() {
        for (int i = 0; i < buyButtons.size(); i++) {
            TextButton btn = buyButtons.get(i);
            if (i == focusedButtonIndex) {
                btn.setColor(1, 1, 0, 1); // رنگ زرد برای فوکوس
                btn.getLabel().setFontScale(1.2f);
            } else {
                btn.setColor(1, 1, 1, 1);
                btn.getLabel().setFontScale(1f);
            }
        }
        // اسکرول به سمت دکمه فوکوس شده
        if (!buyButtons.isEmpty()) {
            Actor focused = buyButtons.get(focusedButtonIndex);
            scrollPane.scrollTo(focused.getX(), focused.getY(), focused.getWidth(), focused.getHeight());
        }
    }

    private void purchaseItem(ShopItem item) {
        System.out.println("Purchased: " + item.getName());
        // اینجا می‌تونی کد خرید خودت رو بنویسی

        // بعد از خرید UI رو آپدیت کن
        buildUI();
    }

    private void purchaseLimitedItem(LimitedShopItem item) {
        System.out.println("Purchased limited: " + item.getName());
        // کد خرید محدود

        buildUI();
    }

    public void show() {
        if (!shopController.isPlayerInShop(game)) {
            System.out.println("You must be next to a shop to open the shop menu.");
            return;
        }
        isVisible = true;
        buildUI();
        Gdx.input.setInputProcessor(this);
    }

    public void hide() {
        isVisible = false;
        Gdx.input.setInputProcessor(null);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void act(float delta) {
        if (isVisible) stage.act(delta);
    }

    public void draw() {
        if (isVisible) stage.draw();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.Z || keycode == Input.Keys.ESCAPE) {
            hide();
            return true;
        }

        if (keycode == Input.Keys.UP) {
            if (!buyButtons.isEmpty()) {
                focusedButtonIndex--;
                if (focusedButtonIndex < 0) focusedButtonIndex = buyButtons.size() - 1;
                updateButtonFocus();
                return true;
            }
        } else if (keycode == Input.Keys.DOWN) {
            if (!buyButtons.isEmpty()) {
                focusedButtonIndex++;
                if (focusedButtonIndex >= buyButtons.size()) focusedButtonIndex = 0;
                updateButtonFocus();
                return true;
            }
        } else if (keycode == Input.Keys.ENTER) {
            if (!buyButtons.isEmpty()) {
                // simulate button click to trigger listener
                buyButtons.get(focusedButtonIndex).toggle();
                buyButtons.get(focusedButtonIndex).toggle();
                return true;
            }
        }

        // تغییر فیلتر با چپ و راست
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT) {
            int currentIndex = filterSelectBox.getSelectedIndex();
            int itemCount = filterSelectBox.getItems().size;

            int nextIndex = (keycode == Input.Keys.RIGHT)
                    ? (currentIndex + 1) % itemCount
                    : (currentIndex - 1 + itemCount) % itemCount;

            filterSelectBox.setSelectedIndex(nextIndex);
            buildUI();
            return true;
        }

        return false;
    }

    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char character) { return false; }
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }
    @Override public boolean touchCancelled(int pointer, int button, int x, int y) { return false; }
}