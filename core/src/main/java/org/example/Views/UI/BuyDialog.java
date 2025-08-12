package org.example.Views.UI;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;
import org.example.Controllers.ShopController.ShopController;
import org.example.Models.ShopItem;
import org.example.Models.Game;
import org.example.Controllers.GameController.GameController;

public class BuyDialog extends Dialog {
    private int count = 1;
    private Label countLabel;
    private ShopItem item;
    private ShopController shopController;
    private Game game;
    private GameController gameController;

    public BuyDialog(Skin skin, ShopItem item, ShopController shopController, Game game, GameController gameController) {
        super("Buy: " + item.getName(), skin);
        this.item = item;
        this.shopController = shopController;
        this.game = game;
        this.gameController = gameController;

        countLabel = new Label(String.valueOf(count), skin);
        countLabel.setAlignment(Align.center);

        TextButton plusButton = new TextButton("+", skin);
        TextButton minusButton = new TextButton("-", skin);
        TextButton buyButton = new TextButton("Buy", skin);
        TextButton cancelButton = new TextButton("Cancel", skin);

        plusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                count++;
                countLabel.setText(String.valueOf(count));
            }
        });

        minusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (count > 1) {
                    count--;
                    countLabel.setText(String.valueOf(count));
                }
            }
        });

        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                shopController.purchaseItem(game, item.getName(), count, gameController ,"");

                hide();
            }
        });

        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
            }
        });

        // Layout of the dialog content
        getContentTable().add(new Label(item.getName(), skin)).colspan(3).padBottom(10).row();
        getContentTable().add(new Label("Description:", skin)).colspan(3).padBottom(5).row();
        getContentTable().add(new Label(item.getDescription(), skin)).colspan(3).padBottom(15).row();

        getContentTable().add(minusButton).width(50).height(50);
        getContentTable().add(countLabel).width(50).height(50);
        getContentTable().add(plusButton).width(50).height(50).row();

        getContentTable().add(new Label("Total Price: " + (item.getPrice() * count) + "g", skin)).colspan(3).padTop(15).row();

        getButtonTable().add(buyButton).width(120).pad(10);
        getButtonTable().add(cancelButton).width(120).pad(10);
    }
}
