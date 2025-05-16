package org.example.Models;

import org.example.Models.Enums.TileType;

import java.util.ArrayList;

public class Tile {
    public TileType type;
    public Tile(TileType type) {
        this.type = type;
    }
    public Building parentBuilding = null;
    public TileType getType() {
        return type;
    }
    public Tile clone() {
        return new Tile(this.type); // یا اطلاعات کامل‌تر
    }
   public char getSymbol() {
        switch (type) {
            case WALL:
                return 'W';
                case SHACK:
                    return 'C';
                    case LAKE:
                        return 'L';
                        case EMPTY:
                            return '.';
            case GREENHOUSE:
                return 'G';
            case QUARRY:
                return 'Q';
                case TREE:
                    return 'T';
                    case BLACKSMITH:
                        return 'B';
                        case JOJAMART:
                            return 'J';
                            case GENERALSTORE:
                                return 'S';
                                case CARPENTERSHOP:
                                    return 'P';
                                    case FISHSHOP:
                                        return 'F';
                                        case RANCH:
                                            return 'M';
                                            case STARDROPSALOON:
                                                return 'A';
            case PLAYER:
                return '@';
            case ROCK:
                return 'R';
                case FORAGING:
                    return '#';
            case FERTILE:
                return 'O';
            case SHIPPINGBIN:
                return '&';
            case BUILDING:
                return '=';
            case ANIMAL:
                return 'U';


                        default:
                            return '?';
        }

    }
    public boolean deluxeFed=false;
    public boolean speedFed=false;
    public ArrayList<Item> Items = new ArrayList<>();
    public int FarmNumber=1;

}
