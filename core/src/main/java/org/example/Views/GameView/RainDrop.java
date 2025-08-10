package org.example.Views.GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RainDrop {
    float x, y;
    float speed;
    Animation<TextureRegion> animation;
    float stateTime;
    float timer = 0.0f;

    public RainDrop(float x, float y, float speed, Animation<TextureRegion> animation) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.animation = animation;
        this.stateTime = (float)(Math.random() * animation.getAnimationDuration()); // مقدار اولیه تصادفی برای شروع فریم انیمیشن
    }

    public void update(float delta, float screenHeight) {
       // y -= speed * delta;

        timer += delta;
        if (timer > 0.5) {
            y =(float)(Math.random() * Gdx.graphics.getHeight()) ; // دوباره از بالا بیاد
            x = (float)(Math.random() * Gdx.graphics.getWidth()); // مکان جدید
            timer = 0;
            this.stateTime = (float)(Math.random() * animation.getAnimationDuration()); // مقدار اولیه تصادفی برای شروع فریم انیمیشن
        }
        stateTime += delta;

    }

    public void render(SpriteBatch batch) {
        TextureRegion frame = animation.getKeyFrame(stateTime, true);
        batch.draw(frame, x, y, 50, 50); // اندازه کوچک قطره
    }
}
