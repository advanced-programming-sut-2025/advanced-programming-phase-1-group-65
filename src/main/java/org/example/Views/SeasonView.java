package org.example.Views;

public class SeasonView extends TimeView{
    SeasonView(int time, int date, int DaysOfWeek) {
        super(time, date, DaysOfWeek);
    }
    String season;
    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    
}
