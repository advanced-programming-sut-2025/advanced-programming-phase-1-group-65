package org.example.Models;

import org.example.Models.Enums.ItemSubType;
import org.example.Models.Enums.ItemType;

public class Tool extends Item {
    public Tool(int level, ItemSubType subtype,String name) {
        super(ItemType.TOOL,subtype,1,name );
        this.level = level;

    }

   public int level;
    public int volume=0;

}
