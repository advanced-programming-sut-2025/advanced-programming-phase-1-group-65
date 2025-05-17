package org.example.Models;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

import java.util.ArrayList;

public class Recipe{
    public String name;
    public String description;
    public Recipe(String name,String description) {
        this.name = name;
        this.description = description;
    }
}
