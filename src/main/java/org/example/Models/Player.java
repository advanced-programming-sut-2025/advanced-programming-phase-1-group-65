package org.example.Models;

import org.example.Models.Enums.SkillType;
import org.example.Models.Enums.TileType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {
    public Skill farmingSkill;
    public Skill miningSkill;
    public Skill foragingSkill;
    public Skill fishingSkill;

    public Player() {
        farmingSkill = new Skill();
        miningSkill = new Skill();
        foragingSkill = new Skill();
        fishingSkill = new Skill();
        npcFriendships = new HashMap<>();
    }
    public ArrayList<Building> playerBuildings = new ArrayList<>();
    public int FarmNumber;
    public ArrayList<Item> SoldItems = new ArrayList<>();
    public HashMap<String, Integer> npcFriendships = new HashMap<>();
    public void gainFarmingXP(int amount) {
        farmingSkill.gainXP(amount);
    }

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
    public boolean Fainted = false;

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