package org.example.Models;

import com.badlogic.gdx.graphics.Texture;
import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

public class ShopItem extends Item {
    private String description;
    private boolean available;  // وضعیت موجود بودن کالا

    // سازنده با 6 پارامتر
    public ShopItem(String name, String description, int price, ItemType type, ItemSubType subType, boolean available) {
        super(type, subType, 1, name, price);
        this.description = description;
        this.available = available;
    }

    // سازنده با 5 پارامتر (مقدار پیش‌فرض available = true)
    public ShopItem(String name, String description, int price, ItemType type, ItemSubType subType) {
        this(name, description, price, type, subType, true);
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}