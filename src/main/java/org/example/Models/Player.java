package org.example.Models;

public class Player {
    public int PositionX;
    public int PositionY;
    public int Energy=200;
    public boolean Fainted=false;
    public int FarmingSkill=0;
    public int MiningSkill=0;
    public int HarvestSkill=0;
    public int FishingSkill=0;
    Inventory inventory;
    int FarmNumber;

    public void setFarmNumber(int FarmNumber) {
        this.FarmNumber = FarmNumber;
    }
    public int getFarmNumber() {
        return FarmNumber;
    }

    public Game game= null;
    public void setGame(Game game) {
        this.game = game;
    }

}
