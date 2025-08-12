package org.example.Views.GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.example.Models.Enums.ItemSubType;

import static org.example.Models.Enums.ItemSubType.COOP;
import static org.example.Models.Enums.ItemSubType.SEED;

public class DirectionInputProcessor extends InputAdapter {
    private final GameScreen gameScreen;

    public DirectionInputProcessor(GameScreen screen) {
        this.gameScreen = screen;
    }

    @Override

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT && gameScreen.selectingDirection) {
            int tileX = (int)(gameScreen.camera.unproject(new com.badlogic.gdx.math.Vector3(screenX, screenY, 0)).x / 16);
            int tileY = (int)(gameScreen.camera.unproject(new com.badlogic.gdx.math.Vector3(screenX, screenY, 0)).y / 16);

            int playerX = gameScreen.game.currentPlayer.PositionX;
            int playerY = gameScreen.game.currentPlayer.PositionY;

            int dx = tileX - playerX;
            int dy = playerY - tileY;

            if (Math.abs(dx) <= 1 && Math.abs(dy) <= 1 && !(dx == 0 && dy == 0)) {

                // ابزار انتخاب شده باشد → UseTool
                if (gameScreen.game.currentPlayer.CurrentTool != null) {
                    gameScreen.controller.UseTool(gameScreen.game, dx, dy);
                }

                // بذر انتخاب شده باشد → Plant
                else if (gameScreen.game.currentPlayer.CurrentItem != null &&
                    gameScreen.game.currentPlayer.CurrentItem.subtype == ItemSubType.SEED) {
                    String seedName = gameScreen.game.currentPlayer.CurrentItem.name;
                    gameScreen.controller.Plant(gameScreen.game, dx, dy, seedName);
                }
                else if (gameScreen.game.currentPlayer.CurrentItem.subtype == ItemSubType.BARN || gameScreen.game.currentPlayer.CurrentItem.subtype == ItemSubType.COOP) {
                        String building = gameScreen.game.currentPlayer.CurrentItem.name;
                        gameScreen.buildingController.Build(building , gameScreen.game ,playerX+dx,playerY-dy,gameScreen.controller);

                }
                else if (gameScreen.game.currentPlayer.CurrentItem.subtype == ItemSubType.FERTILIZER) {
                    String fertilizerName = gameScreen.game.currentPlayer.CurrentItem.name;
                    gameScreen.controller.Fertilize(gameScreen.game, dx, dy, fertilizerName);
                }

                gameScreen.selectingDirection = false;
                return true;
            }
        }
        return false;
    }


}
