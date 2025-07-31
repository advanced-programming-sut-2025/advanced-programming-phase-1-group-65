package org.example.Models;

public class Skill {

    public int level = 0;
    public int xp = 0;

    public void gainXP(int amount) {
        if (level >= 4) return;

        xp += amount;
        while (level < 4 && xp >= getRequiredXP(level)) {
            xp -= getRequiredXP(level);
            level++;
            System.out.println("Skill leveled up to level " + level);
        }
    }

    private int getRequiredXP(int level) {
        return 50 + level * 100;
    }

    public int getLevel() {
        return level;
    }

    public int getXP() {
        return xp;
    }

}
