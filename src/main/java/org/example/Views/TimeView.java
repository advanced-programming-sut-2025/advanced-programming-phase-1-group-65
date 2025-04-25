package org.example.Views;

public class TimeView {
    int time;
    int date;
    int DaysOfWeek;
    public TimeView(int time, int date, int DaysOfWeek) {
        this.time = time;
        this.date = date;
        this.DaysOfWeek = DaysOfWeek;
    }
    public int getTime() {
        return time;
    }
    public int getDate() {
        return date;
    }
    public int getDaysOfWeek() {
        return DaysOfWeek;

    }
    public void setTime(int time) {
        this.time = time;
    }
    public void setDate(int date) {
        this.date = date;
    }
    public void setDaysOfWeek(int DaysOfWeek) {
        this.DaysOfWeek = DaysOfWeek;
    }

}
