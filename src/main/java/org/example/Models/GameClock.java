package org.example.Models;

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

    public void advanceTimeByOneHour() {
        hour++;
        if (hour >= 24) {
            hour = 0;
            advanceDay();
        }
    }

    public void advanceTimeByOneDay() {
        advanceDay();
    }

    private void advanceDay() {
        day++;
        if (day > 28) {
            day = 1;
            currentSeasonIndex = (currentSeasonIndex + 1) % 4;
            if (currentSeasonIndex == 0) {
                year++;
            }
            System.out.println("Season has changed to: " + getCurrentSeason());
        }
    }
}