package org.example.Views;

public class WeatherView extends SeasonView{
    String Weather;
    WeatherView(int time, int date, int DaysOfWeek) {
        super(time, date, DaysOfWeek);
    }
    public String getWeather(String Weather) {
        return Weather;
    }
    public void setWeather(String Weather) {
        this.Weather = Weather;
    }

}
