package org.example.Views.MenuView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.example.Controllers.MenuController.GameMenuController;
import org.example.Main;
import org.example.Models.App;
import org.example.Models.User;
import org.example.Models.Enums.Menu;
import org.example.Views.AppMenu;
import org.example.Views.GameView.GameSetupView;

public class GameMenuView extends ScreenAdapter implements AppMenu {
    private Stage stage;
    private Skin skin;
    private Table table;
    private Label messageLabel;

    private TextField user2Field;
    private TextField user3Field;
    private TextButton addPlayersButton;
    private TextButton startGameButton;
    private TextButton loadGameButton;
    private TextButton backButton;

    private GameMenuController controller;

    public GameMenuView() {
        controller = new GameMenuController();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("Game Menu", skin);
        titleLabel.setFontScale(2f);
        table.add(titleLabel).colspan(2).padBottom(20);
        table.row();

        if (App.getCurrentUser() == null) {
            messageLabel = new Label("You must log in first to access the Game Menu.", skin);
            messageLabel.setColor(Color.RED);
            table.add(messageLabel).colspan(2).padBottom(20);
            table.row();

            TextButton backToLoginButton = new TextButton("Back to Login/Register Menu", skin);
            table.add(backToLoginButton).colspan(2).width(800).center().padBottom(10);
            table.row();

            backToLoginButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    App.setCurrentMenu(Menu.LOGINREGISTERMENU);
                    Main.getInstance().setScreen(new LoginRegisterMenuView());
                }
            });

            return;
        }

        User user1 = App.getCurrentUser();

        table.add(new Label("Logged-in User: " + user1.getUsername(), skin)).colspan(2).padBottom(20);
        table.row();

        user2Field = new TextField("", skin);
        user3Field = new TextField("", skin);

        table.add(new Label("Second Player Username:", skin)).left().pad(5);
        table.add(user2Field).width(300).pad(5);
        table.row();

        table.add(new Label("Third Player Username:", skin)).left().pad(5);
        table.add(user3Field).width(300).pad(5);
        table.row();

        addPlayersButton = new TextButton("Add Players", skin);
        startGameButton = new TextButton("Start Game", skin);
        startGameButton.setDisabled(true);

        table.add(addPlayersButton).colspan(2).width(400).padTop(10);
        table.row();
        table.add(startGameButton).colspan(2).width(400).padTop(10);
        table.row();

        loadGameButton = new TextButton("Load Previous Game", skin);
        table.add(loadGameButton).colspan(2).width(600).padTop(20);
        table.row();

        backButton = new TextButton("Back to Main Menu", skin);
        table.add(backButton).colspan(2).width(700).padTop(10);
        table.row();

        messageLabel = new Label("", skin);
        table.add(messageLabel).colspan(2).padTop(20);
        table.row();

        addPlayersButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String username2 = user2Field.getText().trim();
                String username3 = user3Field.getText().trim();

                String result = controller.checkPlayers(user1.getUsername(), username2, username3);
                messageLabel.setText(result);
                if (result.equals("Players validated. Ready to start.")) {
                    messageLabel.setColor(Color.GREEN);
                    startGameButton.setDisabled(false);
                } else {
                    messageLabel.setColor(Color.RED);
                    startGameButton.setDisabled(true);
                }
            }
        });

        startGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String username2 = user2Field.getText().trim();
                String username3 = user3Field.getText().trim();

                Main.getInstance().setScreen(new GameSetupView(
                        App.getCurrentUser().getUsername(),
                        username2,
                        username3,
                        controller
                ));
            }
        });

        loadGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (user1.player.game == null) {
                    messageLabel.setText("You don't have an active game.");
                    messageLabel.setColor(Color.RED);
                } else {
                    user1.player.game.MainPlayer = user1.player;
                    user1.player.game.currentPlayer = user1.player;
                    user1.player.game.GameRun(); // اجرای بازی
                }
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getInstance().setScreen(new MainMenuView());
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void check(java.util.Scanner scanner) {
        // Not used in graphical mode
    }
}
