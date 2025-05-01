package org.example.Controllers;

import org.example.Models.Player;

public class EnergyController extends Player {
    public int CurrentEnergy;
    public boolean Fainted;
    public int EnergyGiven(int energyGiven){
        CurrentEnergy += energyGiven;
        return CurrentEnergy;
    }
    public int EnergyTaken(int energyTaken){
        CurrentEnergy -= energyTaken;
        return CurrentEnergy;
    }


}
