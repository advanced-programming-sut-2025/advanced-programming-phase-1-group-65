package org.example.Views.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.example.Controllers.GameController.GameController;
import org.example.Controllers.ShopController.ShopController;
import org.example.Models.Game;
import org.example.Models.ShopItem;
import org.example.Models.LimitedShopItem;

import java.util.List;

public class ShopUI {
    private final GameController gameController;
    private final Game game;
    private final ShopController shopController;
    private Label notificationLabel;
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
    private java.util.List<TextButton> buyButtons = new java.util.ArrayList<>();

    public ShopUI(Game game, GameController gameController) {
        this.game = game;
        this.shopController = new ShopController();
        this.gameController = gameController;

        shopController.setMessageListener(new ShopController.ShopMessageListener() {
            @Override
            public void showMessage(String message) {
                Gdx.app.postRunnable(() -> setNotification(message));
            }
        });

        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        notificationLabel = new Label("", skin);
        notificationLabel.setColor(1, 1, 0, 1);
        notificationLabel.setAlignment(Align.center);

        rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        initFilterSelectBox();
        buildUI();

        rootTable.row();
        rootTable.add(notificationLabel).colspan(3).padTop(10);

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.Z)  {
                    hide();
                    return true;
                }
                return false;
            }
        });

        filterSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buildUI();
            }
        });
    }

    private void initFilterSelectBox() {
        filterSelectBox = new SelectBox<>(skin);
        filterSelectBox.setItems(FILTER_ALL, FILTER_AVAILABLE);
        filterSelectBox.setSelected(FILTER_ALL);
    }

    private void buildUI() {
        rootTable.clear();
        shopController.setPurchaseListener(() -> {
            Gdx.app.postRunnable(() -> {
                buildUI();
            });
        });

        Label title = new Label("Shop Menu", skin);
        title.setFontScale(2f);
        rootTable.add(title).colspan(3).padBottom(20);
        rootTable.row();

        if (!shopController.isPlayerInShop(game)) {
            messageLabel = new Label("You must be next to a shop to open the shop menu.", skin);
            messageLabel.setColor(1, 0, 0, 1);
            rootTable.add(messageLabel).colspan(3).padBottom(20);

            rootTable.row();
            rootTable.add(notificationLabel).colspan(3).padTop(10);

            return;
        }

        rootTable.add(new Label("Filter Products: ", skin)).left().padBottom(15);
        rootTable.add(filterSelectBox).left().padBottom(15).colspan(2);
        rootTable.row();

        buyButtons.clear();

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
                    BuyDialog dialog = new BuyDialog(skin, item, shopController, game, gameController);
                    dialog.show(stage);
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
                    BuyLimitedDialog dialog = new BuyLimitedDialog(skin, item, shopController, game, gameController);
                    dialog.show(stage);
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
        } else {
            scrollPane.setWidget(itemsTable);
        }

        rootTable.add(scrollPane).colspan(3).fill().expand().padBottom(10);
        rootTable.row();

        messageLabel = new Label("Click to Buy | Scroll to navigate | Press Z to close", skin);
        messageLabel.setAlignment(Align.center);
        rootTable.add(messageLabel).colspan(3).padTop(10);

        // نمایش notificationLabel پایین UI
        rootTable.row();
        rootTable.add(notificationLabel).colspan(3).padTop(10);
    }

    public void show() {
        if (!shopController.isPlayerInShop(game)) {
            return;
        }
        isVisible = true;
        buildUI();
        Gdx.input.setInputProcessor(stage);
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

    public Stage getStage() {
        return stage;
    }

    public void setNotification(String message) {
        notificationLabel.setText(message);
    }
}