package org.example.Models;

import org.example.Models.Enums.BuildingType;

import java.util.ArrayList;

public class Building {
    public ArrayList<Animal> animals= new ArrayList<>();
    public Building(String name,int startX,int startY,int width,int height,int Capacity,BuildingType type) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.name = name;
        this.Capacity = Capacity;
    }
    public boolean hasSpace() {
        return animals.size() < Capacity;
    }
    public int Capacity;
    public String name;
    public int startX;
    public int startY;
    public int width;
    public int height;
    public BuildingType type;


}
