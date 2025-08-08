package org.example.Models;

import com.badlogic.gdx.graphics.Texture;

public class Skill {
    public Texture texture;
    public int level = 0;
    public int xp = 0;
    public String description = " this is a discription";

    public void gainXP(int amount) {
        if (level >= 4) return;

        xp += amount;
        while (level < 4 && xp >= getRequiredXP(level)) {
            xp -= getRequiredXP(level);
            level++;
            System.out.println("Skill leveled up to level " + level);
        }
    }

    public int getRequiredXP(int level) {
        return 50 + level * 100;
    }

    public int getLevel() {
        return level;
    }

    public int getXP() {
        return xp;
    }

}
