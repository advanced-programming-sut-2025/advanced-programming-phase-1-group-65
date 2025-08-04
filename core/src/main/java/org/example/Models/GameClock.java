package org.example.Models;

import org.example.Controllers.GameController.GameController;

public class GameClock {
    private int hour;
    private int day;
    private int currentSeasonIndex;
    private int year;

    private static final String[] seasons = {"Spring", "Summer", "Fall", "Winter"};

    public GameClock() {
        this.hour = 9;
        this.day = 1;
        this.currentSeasonIndex = 0;
        this.year = 1;
    }
    public int getDay() {
        return day;
    }
    public int getCurrentSeasonIndex(){
        return this.currentSeasonIndex+1;
    }

    public String getFormattedTime() {
        return String.format("%02d:00", hour);
    }

    public String getFormattedDate() {
        return String.format("Day %d of %s, Year %d", day, seasons[currentSeasonIndex], year);
    }

    public String getFormattedDateTime() {
        return getFormattedDate() + " - " + getFormattedTime();
    }

    public String getDayOfWeek() {

        String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int totalDays = ((year - 1) * 4 * 28) + (currentSeasonIndex * 28) + (day - 1);
        return weekDays[totalDays % 7];
    }

    public String getCurrentSeason() {
        return seasons[currentSeasonIndex];
    }

    public void advanceTimeByOneHour(Game game,GameController controller) {
        hour++;
        if (hour >= 22) {
            hour = 9;
            advanceDay(game,controller);
            game.weatherSystem.advanceDay();
        }
    }

    public int getHour() {
        return hour;
    }

    public void advanceTimeByOneDay(Game game,GameController controller) {
        advanceDay(game,controller);
    }

    private void advanceDay(Game game, GameController controller) {
        if (game.user1.player.Fainted){
            game.user1.player.Energy = 150;
            game.user1.player.Fainted = false;
        }
        if (game.user2.player.Fainted){
            game.user2.player.Energy = 150;
            game.user2.player.Fainted = false;
        }
        if (game.user3.player.Fainted){
            game.user3.player.Energy = 150;
            game.user3.player.Fainted = false;
        }
        game.map.GenerateRandomForagingDaily(game.Map,game);
        game.map.GenerateRandomRockDaily(game.Map,game);
        controller.TendToCropsDaily(game);
        controller.TendToTreesDaily(game);
        controller.TendToShippingBins(game);
    //    controller.GoHome(game);
        controller.TendToAnimalsDaily(game);
        day++;
        hour=9;
        if (day > 28) {
            day = 1;
            currentSeasonIndex = (currentSeasonIndex + 1) % 4;
            if (currentSeasonIndex == 0) {
                year++;
            }
            String newSeason = getCurrentSeason();
            System.out.println("Season has changed to: " + newSeason);
            game.weatherSystem.setCurrentSeason(newSeason);
        }
        if(game.weatherSystem.getTodayWeatherName().equalsIgnoreCase("rain")){
            controller.CheatWater(game);
        }
    }
    
}
