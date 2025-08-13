//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.Views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import org.example.Models.Game;

public class PlayerAnimation {
    private final Animation<TextureRegion>[] walkAnimations = new Animation[4];
    private float stateTime = 0.0F;
    private Animation<TextureRegion> eatingAnimation;
    private Animation<TextureRegion> faintingAnimation;


    public Game game;
    public PlayerAnimation(float frameDuration , Game game) {
        this.game = game;
        this.walkAnimations[PlayerAnimation.Direction.DOWN.ordinal()] = this.loadDirectionAnimation("player/d", frameDuration);
        this.walkAnimations[PlayerAnimation.Direction.LEFT.ordinal()] = this.loadDirectionAnimation("player/l", frameDuration);
        this.walkAnimations[PlayerAnimation.Direction.UP.ordinal()] = this.loadDirectionAnimation("player/u", frameDuration);
        this.walkAnimations[PlayerAnimation.Direction.RIGHT.ordinal()] = this.loadDirectionAnimation("player/r", frameDuration);
        TextureRegion[] eatingFrames = new TextureRegion[2];
        TextureRegion[] faintingFrames = new TextureRegion[4];
        eatingFrames[0] = new TextureRegion(new Texture("player/Eating1.png"));
        eatingFrames[1] = new TextureRegion(new Texture("player/Eating2.png"));
        faintingFrames[0] = new TextureRegion(new Texture("player/f1.png"));
        faintingFrames[1] = new TextureRegion(new Texture("player/f2.png"));
        faintingFrames[2] = new TextureRegion(new Texture("player/f3.png"));
        faintingFrames[3] = new TextureRegion(new Texture("player/f4.png"));

        eatingAnimation = new Animation<>(0.2f, eatingFrames);
        eatingAnimation.setPlayMode(PlayMode.NORMAL);
        faintingAnimation = new Animation<>(0.2f, faintingFrames);
        faintingAnimation.setPlayMode(PlayMode.NORMAL);

    }

    private Animation<TextureRegion> loadDirectionAnimation(String basePath, float frameDuration) {
        TextureRegion[] frames = new TextureRegion[3];

        for(int i = 0; i < 3; ++i) {
            Texture texture = new Texture(basePath + (i + 1) + ".png");
            frames[i] = new TextureRegion(texture);
        }

        Animation<TextureRegion> animation = new Animation(frameDuration, frames);
        animation.setPlayMode(PlayMode.LOOP);
        return animation;
    }

    public TextureRegion getCurrentFrame(Direction dir, float delta) {
        if (game.currentPlayer.isEating) {
            game.currentPlayer.eatingTimer += delta;
            TextureRegion frame = eatingAnimation.getKeyFrame(game.currentPlayer.eatingTimer);

            if (eatingAnimation.isAnimationFinished(game.currentPlayer.eatingTimer)) {
                game.currentPlayer.isEating = false; // برگرد به حالت عادی بعد از خوردن
                game.currentPlayer.eatingTimer = 0f;
            }
            return frame;
        }
        if (game.currentPlayer.Fainted){
            game.currentPlayer.faintingTimer += delta;
            TextureRegion frame = faintingAnimation.getKeyFrame(game.currentPlayer.faintingTimer);
            if (faintingAnimation.isAnimationFinished(game.currentPlayer.faintingTimer)) {
                game.currentPlayer.faintingTimer = 0f;
                game.gameScreen.controller.processNextTurn(game);
            }
            return frame;
        }

        if (game.gameScreen.walking) {
            stateTime += delta;
            return walkAnimations[dir.ordinal()].getKeyFrame(stateTime);
        } else {
            return walkAnimations[dir.ordinal()].getKeyFrames()[0];
        }
    }


    public static enum Direction {
        DOWN,
        LEFT,
        UP,
        RIGHT;
    }
    public void startEating() {
        game.currentPlayer.isEating = true;
        game.currentPlayer.eatingTimer = 0f;
    }

}
