package org.example.Views.MenuView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.example.Main;
import org.example.Models.App;
import org.example.Models.Enums.Menu;
import org.example.Views.AppMenu;

public class MainMenuView extends ScreenAdapter implements AppMenu {

    private Stage stage;
    private Skin skin;
    private Table table;

    private Label titleLabel;
    private Label infoLabel;
    private TextButton loginRegisterMenuButton;
    private TextButton profileMenuButton;
    private TextButton gameMenuButton;
    private Label messageLabel;

    public MainMenuView() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        titleLabel = new Label("Welcome to the Main Menu!", skin);
        titleLabel.setFontScale(2f);
        table.add(titleLabel).colspan(2).padBottom(30);
        table.row();

        infoLabel = new Label("Select a menu to enter:", skin);
        table.add(infoLabel).colspan(2).padBottom(20);
        table.row();

        loginRegisterMenuButton = new TextButton("Back to Login/Register Menu", skin);
        profileMenuButton = new TextButton("Profile Menu", skin);
        gameMenuButton = new TextButton("Game Menu", skin);

        table.add(profileMenuButton).width(500).pad(10);
        table.add(gameMenuButton).width(500).pad(10);
        table.row();

        table.add(loginRegisterMenuButton).colspan(2).width(800).pad(10);
        table.row();

        messageLabel = new Label("", skin);
        table.add(messageLabel).colspan(2).padTop(30);

        loginRegisterMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.setCurrentMenu(Menu.LOGINREGISTERMENU);
                Main.getInstance().setScreen(new LoginRegisterMenuView());
            }
        });

        profileMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.setCurrentMenu(Menu.PROFILEMENU);
                Main.getInstance().setScreen(new ProfileMenuView());

            }
        });

        gameMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.setCurrentMenu(Menu.GAMEMENU);
                Main.getInstance().setScreen(new GameMenuView());

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
    }
}
