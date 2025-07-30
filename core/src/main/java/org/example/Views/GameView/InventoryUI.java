package org.example.Views.GameView;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.example.Models.Game;
import org.example.Models.Item;
import org.example.Models.Player;

import java.util.List;

public class InventoryUI extends Stage {
    private final Player player;
    private final Table inventoryTable;

    public InventoryUI(Game game, SpriteBatch batch) {
        super(new ScreenViewport(), batch);
        this.player = game.currentPlayer;
        inventoryTable = new Table();
        inventoryTable.setFillParent(true);
        inventoryTable.center();

        rebuildUI();
        addActor(inventoryTable);
    }

    public void rebuildUI() {
        inventoryTable.clear();
        List<Item> items = player.items;

        int columns = 5; // مثلاً 5 ستون مثل Stardew
        for (int i = 0; i < items.size(); i++) {
            final Item item = items.get(i);
            Image img = new Image(item.getTexture()); // فرض بر اینکه آیتم texture دارد
            inventoryTable.add(img).size(32, 32).pad(5);

            if ((i + 1) % columns == 0)
                inventoryTable.row();
        }
    }
}

