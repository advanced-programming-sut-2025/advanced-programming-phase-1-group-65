package org.example.Controllers.BuildingController;

import org.example.Controllers.GameController.GameController;
import org.example.Models.Building;
import org.example.Models.Enums.BuildingType;
import org.example.Models.Enums.TileType;
import org.example.Models.Game;
import org.example.Models.Tile;

public class BuildingController {
    //public Building Build(Game game){

    //}
    public Building getBuilding( String name,int startX, int startY,Game game){

        switch (name){
            case "Coop":
                Building Coop = new Building(name,startX,startY,6,3,4,BuildingType.COOP);
                return Coop;
            case "Big Coop":
                Building BigCoop = new Building(name,startX,startY,6,3,8,BuildingType.COOP);
                return BigCoop;
                case "Deluxe Coop":
                    Building DeluxeCoop = new Building(name,startX,startY,6,3,12,BuildingType.COOP);
                    return DeluxeCoop;
            case "Barn":
                Building Barn = new Building(name,startX,startY,7,4,4,BuildingType.BARN);
                return Barn;
                case "Big Barn":
                    Building BigBarn = new Building(name,startX,startY,7,4,8,BuildingType.BARN);
                    return BigBarn;
                    case "Deluxe Barn":
                        Building DeluxeBarn = new Building(name,startX,startY,7,4,12,BuildingType.BARN);
                        return DeluxeBarn;
        }
        return null;

    }
    public void Build(String name, Game game, int startX, int startY, GameController controller) {
        if(getBuilding(name,startX,startY,game) == null){
            System.out.println("Building dose not exist");
            return;
        }
        Building building = getBuilding(name,startX,startY,game);
        for (int i = 0; i < building.height; i++) {
            for (int j = 0; j < building.width; j++) {
                int x = startX + j;
                int y = startY + i;
                if (x >= game.Map.get(0).size() || y >= game.Map.size()) {
                    System.out.println("Out of bounds");
                    return;
                }
                if (!game.Map.get(y).get(x).type.equals(TileType.EMPTY)) {
                    System.out.println("Space is not empty");
                    return;
                }
            }
        }
        game.currentPlayer.playerBuildings.add(building);
        for (int i = 0; i < building.height; i++) {
            for (int j = 0; j < building.width; j++) {
                int x = startX + j;
                int y = startY + i;
                Tile barnTile = new Tile(TileType.BUILDING);
                barnTile.parentBuilding = building;
                game.Map.get(y).set(x, barnTile);
            }
        }
        controller.removeItem(game,name,1);
        System.out.println("Building created successfully");

    }
    public void looseMaterial(String name,Game game,GameController controller){
        switch(name){
            case "Coop":
                controller.removeItem(game,"Wood",300);
                controller.removeItem(game,"Stone",100);
                break;
            case "Big Coop":
                controller.removeItem(game,"Wood",400);
                controller.removeItem(game,"Stone",150);
                break;
            case "Deluxe Coop":
                controller.removeItem(game,"Wood",500);
                controller.removeItem(game,"Stone",200);
                break;
            case "Barn":
                controller.removeItem(game,"Wood",350);
                controller.removeItem(game,"Stone",150);
                break;
                case "Big Barn":
                    controller.removeItem(game,"Wood",450);
                    controller.removeItem(game,"Stone",200);
                    break;
                    case "Deluxe Barn":
                        controller.removeItem(game,"Wood",550);
                        controller.removeItem(game,"Stone",200);
                        break;
        }
    }
    public boolean canBuild(String name,Game game,GameController controller){
        switch (name){
            case "Coop":
                return HasItems(game,controller,300,100);
            case "Big Coop":
                    return HasItems(game,controller,400,150);
                    case "Deluxe Coop":
                        return HasItems(game,controller,500,200);
            case "Barn":
                return HasItems(game,controller,350,150);
                case "Big Barn":
                    return HasItems(game,controller,450,200);
                    case "Deluxe Barn":
                        return HasItems(game,controller,550,200);



        }
        return false;
    }
    public boolean HasItems(Game game,GameController controller, int wood, int stone){
        if(controller.HasItem(game,"Wood",wood) && controller.HasItem(game,"Stone",stone)){
            return true;
        }
        return false;
    }

}
