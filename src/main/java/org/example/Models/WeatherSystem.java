package org.example.Models;

import org.example.Models.Enums.WeatherType;
import java.util.Random;

public class WeatherSystem {
    private WeatherType todayWeather;
    private WeatherType tomorrowWeather;
    private final Random random = new Random();
    private String currentSeason;

    public WeatherSystem(String initialSeason) {
        this.currentSeason = initialSeason;
        this.todayWeather = WeatherType.SUNNY;
        generateTomorrowWeather();
    }

    public WeatherType getTodayWeather() {
        return todayWeather;
    }

    public WeatherType getTomorrowWeather() {
        return tomorrowWeather;
    }


    public void advanceDay() {
        this.todayWeather = tomorrowWeather;
        generateTomorrowWeather();
    }


    private void generateTomorrowWeather() {
        int roll = random.nextInt(100);

        switch (currentSeason.toLowerCase()) {
            case "spring":
                if (roll < 50) {
                    tomorrowWeather = WeatherType.SUNNY;
                } else if (roll < 80) {
                    tomorrowWeather = WeatherType.RAIN;
                } else {
                    tomorrowWeather = WeatherType.STORM;
                }
                break;
            case "summer":
                if (roll < 60) {
                    tomorrowWeather = WeatherType.SUNNY;
                } else if (roll < 90) {
                    tomorrowWeather = WeatherType.RAIN;
                } else {
                    tomorrowWeather = WeatherType.STORM;
                }
                break;
            case "autumn":
                if (roll < 40) {
                    tomorrowWeather = WeatherType.SUNNY;
                } else if (roll < 75) {
                    tomorrowWeather = WeatherType.RAIN;
                } else {
                    tomorrowWeather = WeatherType.STORM;
                }
                break;
            case "winter":
                if (roll < 30) {
                    tomorrowWeather = WeatherType.SUNNY;
                } else {
                    tomorrowWeather = WeatherType.SNOW;
                }
                break;
            default:
                tomorrowWeather = WeatherType.SUNNY;
        }
    }

    public void setTomorrowWeather(WeatherType weather) {
        this.tomorrowWeather = weather;
    }


}