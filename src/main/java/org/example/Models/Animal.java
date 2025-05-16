package org.example.Models;

import org.example.Models.Enums.AnimalType;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Animal {
    public String name;
    public int FriendSheepPoint;
    public AnimalType animalType;

    public Animal(String name, AnimalType animalType,int ProductionCycle,int price) {
        this.name = name;
        this.animalType = animalType;
        this.foodProduct = new ArrayList<>();
        this.materialProduct = new ArrayList<>();
        this.ProductionCycle = ProductionCycle;
        this.price = price;
    }
    public boolean FedToday = false;
    public ArrayList<Food> foodProduct ;
    public ArrayList<Material> materialProduct ;
    public int ProductionCycle;
    public boolean Inside = true;
    public boolean petToday = false;
    public int price;
    public Animal (Animal Other){
        this.name = Other.name;
        this.FriendSheepPoint = Other.FriendSheepPoint;
        this.animalType = Other.animalType;
        this.foodProduct = Other.foodProduct;
        this.materialProduct = Other.materialProduct;
        FedToday = Other.FedToday;
        this.ProductionCycle = Other.ProductionCycle;
        this.price = Other.price;

    }
}
