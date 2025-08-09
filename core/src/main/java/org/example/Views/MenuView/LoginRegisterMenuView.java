package org.example.Views.MenuView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.example.Main;
import org.example.Models.App;
import org.example.Models.Enums.Menu;
import org.example.Views.AppMenu;

import java.util.Scanner;

public class LoginRegisterMenuView extends ScreenAdapter implements AppMenu {

    private Stage stage;
    private Skin skin;
    private Label infoLabel;

    public LoginRegisterMenuView() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        Table table = new Table();
        table.setFillParent(true);
        Texture background = new Texture(Gdx.files.internal("background.png"));
        Image backgroundImage = new Image(background);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);
        stage.addActor(table);

        Label welcomeLabel = new Label("Welcome to the Login/Register Menu!", skin);
        Label commandLabel = new Label("Select a menu:", skin);

        infoLabel = new Label("", skin);
        infoLabel.setColor(1, 0, 0, 1);

        TextButton loginMenuBtn = new TextButton("Login Menu", skin);
        TextButton signupMenuBtn = new TextButton("Signup Menu", skin);
        TextButton mainMenuBtn = new TextButton("Main Menu", skin);
        TextButton exitBtn = new TextButton("Exit", skin);

        loginMenuBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.setCurrentMenu(Menu.LOGINMENU);
                Main.getInstance().setScreen(new LoginMenuView());
            }
        });

        signupMenuBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.setCurrentMenu(Menu.SIGNUPMENU);
                Main.getInstance().setScreen(new SignUpMenuView());
            }
        });

        mainMenuBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.setCurrentMenu(Menu.MAINMENU);
                Main.getInstance().setScreen(new MainMenuView());

            }
        });

        exitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        table.add(welcomeLabel).colspan(2).padBottom(20);
        table.row();
        table.add(commandLabel).colspan(2).padBottom(10);
        table.row();
        table.add(loginMenuBtn).width(400).pad(5);
        table.add(signupMenuBtn).width(400).pad(5);
        table.row();
        table.add(mainMenuBtn).width(400).pad(5);
        table.add(exitBtn).width(400).pad(5);
        table.row();
        table.add(infoLabel).colspan(2).padTop(20);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    @Override
    public void check(Scanner scanner) {
        // کد متد
    }
}
