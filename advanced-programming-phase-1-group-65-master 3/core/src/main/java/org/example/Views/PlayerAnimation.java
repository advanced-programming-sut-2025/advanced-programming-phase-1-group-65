//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.Views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

public class PlayerAnimation {
    private final Animation<TextureRegion>[] walkAnimations = new Animation[4];
    private float stateTime = 0.0F;

    public PlayerAnimation(float frameDuration) {
        this.walkAnimations[PlayerAnimation.Direction.DOWN.ordinal()] = this.loadDirectionAnimation("player/d", frameDuration);
        this.walkAnimations[PlayerAnimation.Direction.LEFT.ordinal()] = this.loadDirectionAnimation("player/l", frameDuration);
        this.walkAnimations[PlayerAnimation.Direction.UP.ordinal()] = this.loadDirectionAnimation("player/u", frameDuration);
        this.walkAnimations[PlayerAnimation.Direction.RIGHT.ordinal()] = this.loadDirectionAnimation("player/r", frameDuration);
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
        this.stateTime += delta;
        return (TextureRegion)this.walkAnimations[dir.ordinal()].getKeyFrame(this.stateTime);
    }

    public static enum Direction {
        DOWN,
        LEFT,
        UP,
        RIGHT;
    }
}
