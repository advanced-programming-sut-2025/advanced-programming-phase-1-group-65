package org.example.Models;
public class NPC {
    private String name;
    private boolean givesQuest;
    private int FriendShipLevel;
    private boolean Married;
    private String[] Dialogues;
    private String[] FavoriteItems;



    public NPC(String name, boolean givesQuest) {
        this.name = name;
        this.givesQuest = givesQuest;
    }
}
