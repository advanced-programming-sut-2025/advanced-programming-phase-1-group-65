package org.example.Models;

import com.badlogic.gdx.graphics.Texture;
import org.example.Models.Enums.TileType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {
    public Skill farmingSkill;
    public Skill miningSkill;
    public Skill foragingSkill;
    public Skill fishingSkill;
    public List<Skill> skills = new ArrayList<>();

    public TileType lastTileType = TileType.EMPTY;
    public Item CurrentItem = null;
    public boolean GreenHouseFixed = false;
    public Player() {
        farmingSkill = new Skill();
        farmingSkill.texture = new Texture("Skills/Farming_Skill_Icon.png");
        farmingSkill.description = "Earned by harvesting crops and caring for animals. Leveling up increases hoe and watering can proficiency. ";
        miningSkill = new Skill();
        miningSkill.texture = new Texture("Skills/Mining_Skill_Icon.png");
        miningSkill.description = "Increased by breaking rocks with a pickaxe. Leveling up increases pickaxe proficiency. ";
        foragingSkill = new Skill();
        foragingSkill.texture = new Texture("Skills/Foraging_Skill_Icon.png");
        foragingSkill.description = "Earned by gathering foraged items and chopping trees with an axe. Leveling up increases axe proficiency. ";
        fishingSkill = new Skill();
        fishingSkill.texture = new Texture("Skills/Fishing_Skill_Icon.png");
        fishingSkill.description = "Increased by successfully fishing or using crab pots. Leveling up increases fishing rod proficiency. ";
        npcFriendships = new HashMap<>();
        skills.add(farmingSkill);
        skills.add(miningSkill);
        skills.add(foragingSkill);
        skills.add(fishingSkill);

    }
    public boolean RefrigeratorOpen =false;

    public int FarmNumber;
    public ArrayList<Building> playerBuildings = new ArrayList<>();
    public ArrayList<Item> SoldItems = new ArrayList<>();
    public HashMap<String, Integer> npcFriendships = new HashMap<>();
    public ArrayList<Item> Refrigerator = new ArrayList<>();
    public void gainFarmingXP(int amount) {
        farmingSkill.gainXP(amount);
    }    public List<Integer> completedQuestIds = new ArrayList<>();

    public ArrayList<Recipe> KnownRecipes = new ArrayList<>();
    public int MaxEnergy = 200;
    public void gainMiningXP(int amount) {
        miningSkill.gainXP(amount);
    }

    public void gainForagingXP(int amount) {
        foragingSkill.gainXP(amount);
    }

    public void gainFishingXP(int amount) {
        fishingSkill.gainXP(amount);
    }
    public int PositionX;
    public int PositionY;
    public double Energy = 200;
    public double maxEnergy = 200;
    public boolean Fainted = false;
    public boolean isEating = false;
    public float eatingTimer = 0;
    public List<Item> items = new ArrayList<>();
    public Tool CurrentTool = null;
    public Game game = null;
    public int money = 0;
    public TileType previousTileType = TileType.EMPTY;


    public void setFarmNumber(int FarmNumber) {
        this.FarmNumber = FarmNumber;
    }

    public int getFarmNumber() {
        return FarmNumber;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

}
