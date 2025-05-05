package org.example.Models;

public class Player {
    public int PositionX;
    public int PositionY;
    public int Energy=200;


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
