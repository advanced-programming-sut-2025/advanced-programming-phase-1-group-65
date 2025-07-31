package org.example.Views.GameView;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class DirectionInputProcessor extends InputAdapter {
    private final GameScreen gameScreen;

    public DirectionInputProcessor(GameScreen screen) {
        this.gameScreen = screen;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT && gameScreen.selectingDirection) {
            // تبدیل screenX, screenY به موقعیت روی نقشه
            int tileX = (int)(gameScreen.camera.unproject(new com.badlogic.gdx.math.Vector3(screenX, screenY, 0)).x / 16);
            int tileY = (int)(gameScreen.camera.unproject(new com.badlogic.gdx.math.Vector3(screenX, screenY, 0)).y / 16);

            int playerX = gameScreen.game.currentPlayer.PositionX;
            int playerY = gameScreen.game.currentPlayer.PositionY;

            int dx = tileX - playerX;
            int dy = playerY - tileY;

            // فقط 8 جهت اطراف بازیکن مجاز است (dx, dy باید در [-1,0,1] باشد و نه هر دو صفر)
            if (Math.abs(dx) <= 1 && Math.abs(dy) <= 1 && !(dx == 0 && dy == 0)) {
                // فراخوانی متد UseTool در کنترلر
                gameScreen.controller.UseTool(gameScreen.game, dx, dy);
                gameScreen.selectingDirection = false;
                return true;
            }
        }
        return false;
    }



}
