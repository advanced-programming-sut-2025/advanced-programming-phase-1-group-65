package org.example.Models;

import org.example.Models.Enums.TileType;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public int PositionX;
    public int PositionY;
    public double Energy = 200;
    public boolean Fainted = false;
    public int FarmingSkill = 0;
    public int MiningSkill = 0;
    public int ForagingSkill = 0;
    public int FishingSkill = 0;
    int FarmNumber;
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