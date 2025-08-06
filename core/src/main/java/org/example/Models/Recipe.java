package org.example.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
    public Texture texture;
}
