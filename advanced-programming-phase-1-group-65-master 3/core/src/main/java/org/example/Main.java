package org.example;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import org.example.Views.MenuView.LoginRegisterMenuView;

public class Main extends Game {
    private static Main instance;

    public Main() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void create() {
        setScreen(new LoginRegisterMenuView());
    }

    @Override
    public void setScreen(Screen screen) {
        if (getScreen() != null) {
            getScreen().dispose();
        }
        super.setScreen(screen);
    }
}
