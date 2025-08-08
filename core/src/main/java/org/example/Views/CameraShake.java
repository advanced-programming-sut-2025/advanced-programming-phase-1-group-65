package org.example.Views;

import com.badlogic.gdx.math.Vector2;

public class CameraShake {
    private float duration;
    private float time;
    private float intensity;
    private boolean active;

    public void shake(float duration, float intensity) {
        this.duration = duration;
        this.intensity = intensity;
        this.time = 0;
        this.active = true;
    }

    public Vector2 update(float delta) {
        if (!active) return new Vector2(0, 0);

        time += delta;
        if (time >= duration) {
            active = false;
            return new Vector2(0, 0);
        }

        float x = (float) (Math.random() * 2 - 1) * intensity;
        float y = (float) (Math.random() * 2 - 1) * intensity;
        return new Vector2(x, y);
    }

    public boolean isActive() {
        return active;
    }
}