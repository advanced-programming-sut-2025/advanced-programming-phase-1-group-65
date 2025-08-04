package org.example.Views.GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.example.Controllers.MenuController.GameMenuController;
import org.example.Main;
import org.example.Models.Game;  // فراموش نکنید ایمپورت کنید

public class GameSetupView extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private Table table;

    private TextField map1Field;
    private TextField map2Field;
    private TextField map3Field;
    private TextButton startButton;
    private Label messageLabel;

    private String username1, username2, username3;
    private GameMenuController controller;

    public GameSetupView(String username1, String username2, String username3, GameMenuController controller) {
        this.username1 = username1;
        this.username2 = username2;
        this.username3 = username3;
        this.controller = controller;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label title = new Label("Choose Map Numbers (1 to 4)", skin);
        title.setFontScale(1.5f);
        table.add(title).colspan(2).padBottom(20);
        table.row();

        map1Field = new TextField("", skin);
        map2Field = new TextField("", skin);
        map3Field = new TextField("", skin);

        table.add(new Label("Map for " + username1 + ":", skin)).left().pad(5);
        table.add(map1Field).width(100).pad(5);
        table.row();

        table.add(new Label("Map for " + username2 + ":", skin)).left().pad(5);
        table.add(map2Field).width(100).pad(5);
        table.row();

        table.add(new Label("Map for " + username3 + ":", skin)).left().pad(5);
        table.add(map3Field).width(100).pad(5);
        table.row();

        startButton = new TextButton("Start Game", skin);
        table.add(startButton).colspan(2).width(300).padTop(20);
        table.row();

        messageLabel = new Label("", skin);
        table.add(messageLabel).colspan(2).padTop(20);
        table.row();

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    int m1 = Integer.parseInt(map1Field.getText().trim());
                    int m2 = Integer.parseInt(map2Field.getText().trim());
                    int m3 = Integer.parseInt(map3Field.getText().trim());

                    if (m1 < 1 || m1 > 4 || m2 < 1 || m2 > 4 || m3 < 1 || m3 > 4) {
                        messageLabel.setText("Map numbers must be between 1 and 4.");
                        messageLabel.setColor(Color.RED);
                        return;
                    }

                    if (m1 == m2 || m1 == m3 || m2 == m3) {
                        messageLabel.setText("Map numbers must be different.");
                        messageLabel.setColor(Color.RED);
                        return;
                    }

                    int[] maps = new int[]{m1, m2, m3};

                    String result = controller.newGame(username1, username2, username3, maps);
                    messageLabel.setText(result);

                    if (result.equals("New game was successfully created")) {
                        messageLabel.setColor(Color.GREEN);
                        Game game = controller.getCurrentGame();
                        game.currentPlayer = game.user1.player;
                        game.GameRun();
                        Main.getInstance().setScreen(new GameScreen(game));
                    } else {
                        messageLabel.setColor(Color.RED);
                    }
                } catch (NumberFormatException e) {
                    messageLabel.setText("Please enter valid map numbers (1 to 4).");
                    messageLabel.setColor(Color.RED);
                }
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
}