package org.example.Models.Enums;

public enum AnimalType {
    HEN("Coop"),DUCK("Big coop"),RABBIT("Deluxe Coop")
    ,DINOSAUR("Big Coop"), COW("Barn"),GOAT("Big Barn"),SHEEP("Deluxe Barn"),
    PIG("Deluxe Barn");
    String PlaceToLive;
    AnimalType(String PlaceToLive){
        this.PlaceToLive = PlaceToLive;
    }

}
