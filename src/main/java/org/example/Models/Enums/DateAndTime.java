package org.example.Models.Enums;

public enum DateAndTime {
    STARTOFTHEDAY("9 O'Clock AM"),STARTOFTHEYEAR("First Day Of Spring"),
    SATURDAY("Saturday"),SUNDAY("Sunday"),  MONDAY("Monday"), TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"), THURSDAY("THursday"), FRIDAY("Friday"),SPRING("Spring"),
    SUMMER("Summer"), WINTER("Winter"),FALL("Fall")
    ,DAYSOFSEASON("28 Days");
    String Description;
    DateAndTime(String Description) {
        this.Description = Description;

    }
}
