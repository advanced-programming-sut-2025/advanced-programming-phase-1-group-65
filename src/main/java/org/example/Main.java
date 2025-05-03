package org.example;

import org.example.Models.App;
import org.example.Models.Tile;
import org.example.Views.AppView;
import org.example.Models.Map;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*Map m = new Map();
        ArrayList<ArrayList<Tile>> map = m.buildMap();
        m.printMap(map);
*/
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                App.getCurrentMenu().checkCommands(scanner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}