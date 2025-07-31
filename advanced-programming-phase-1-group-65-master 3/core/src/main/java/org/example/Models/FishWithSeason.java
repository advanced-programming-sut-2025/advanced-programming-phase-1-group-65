package org.example.Models;

public class FishWithSeason {
    public Food fish;
    public String season;

    public FishWithSeason(Food fish, String season) {
        this.fish = fish;
        this.season = season;
    }
    public FishWithSeason(FishWithSeason Other){
        fish = Other.fish;
        season = Other.season;
    }
}
