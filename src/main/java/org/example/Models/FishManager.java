package org.example.Models;

import org.example.Models.Enums.ItemSubType;

import java.util.ArrayList;

public class FishManager {
    public static ArrayList<FishWithSeason> allFish = new ArrayList<>();

    public static void createAllFish() {
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Salmon", 25, 75, true), "Fall"));
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Sardine", 20, 40, true), "Fall"));
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Shad", 22, 60, true), "Fall"));
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Blue Discus", 30, 120, true), "Fall"));

        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Midnight Carp", 35, 150, true), "Winter"));
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Squid", 28, 80, true), "Winter"));
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Tuna", 32, 100, true), "Winter"));
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Perch", 21, 55, true), "Winter"));

        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Flounder", 30, 100, true), "Spring"));
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Lionfish", 27, 100, true), "Spring"));
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Herring", 18, 30, true), "Spring"));
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Ghostfish", 20, 45, true), "Spring"));

        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Tilapia", 25, 75, true), "Summer"));
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Dorado", 33, 100, true), "Summer"));
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Sunfish", 15, 30, true), "Summer"));
        allFish.add(new FishWithSeason(new Food(1, ItemSubType.FISH, "Rainbow Trout", 22, 65, true), "Summer"));
    }
}
