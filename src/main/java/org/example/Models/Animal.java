package org.example.Models;

import org.example.Models.Enums.AnimalType;

public class Animal {
    public String name;
    public int FriendSheepPoint;
    public AnimalType animalType;

    public Animal(String name, AnimalType animalType) {
        this.name = name;
        this.animalType = animalType;
    }
    public boolean FedToday = false;
}
