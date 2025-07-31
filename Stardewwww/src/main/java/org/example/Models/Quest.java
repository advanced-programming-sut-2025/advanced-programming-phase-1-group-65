package org.example.Models;

import java.util.HashMap;
import java.util.Map;

public class Quest {
    private int id;
    private String title;
    private String description;
    private int stage;
    private boolean isCompleted = false;

    private Map<String, Integer> requiredItems = new HashMap<>();
    private Map<String, Integer> rewardItems = new HashMap<>();

    public Quest(int id, String title, String description, int stage) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.stage = stage;
    }
    public String getTitle() {
        return title;
    }
    public void addRequiredItem(String itemName, int quantity) {
        requiredItems.put(itemName, quantity);
    }

    public void addRewardItem(String itemName, int quantity) {
        rewardItems.put(itemName, quantity);
    }


    public int getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getRequiredItems() {
        return requiredItems;
    }

    public Map<String, Integer> getRewardItems() {
        return rewardItems;
    }
}