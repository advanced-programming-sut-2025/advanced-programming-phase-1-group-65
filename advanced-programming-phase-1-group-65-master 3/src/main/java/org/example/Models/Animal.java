package org.example.Models;

import org.example.Models.Enums.AnimalType;
import org.example.Models.Enums.TileType;

import java.util.ArrayList;

public class Animal extends Tile{
    public String name;
    public int FriendSheepPoint;
    public AnimalType animalType;

    public Animal(String name, AnimalType animalType,int ProductionCycle,int price) {
        super(TileType.ANIMAL);
        this.name = name;
        this.animalType = animalType;
        this.foodProduct = new ArrayList<>();
        this.materialProduct = new ArrayList<>();
        this.ProductionCycle = ProductionCycle;
        this.price = price;
    }
    public int posX;
    public int posY;
    public boolean FedToday = false;
    public ArrayList<Food> foodProduct ;
    public ArrayList<Material> materialProduct ;
    public int ProductionCycle;
    public boolean Inside = true;
    public boolean petToday = false;
    public int price;
    public int day=0;
    public boolean isHarvestable = false;
    public Food FinalFoodProduct;
    public Material FinalMaterialProduct;
    public Animal (Animal Other){
        super(TileType.ANIMAL);
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
