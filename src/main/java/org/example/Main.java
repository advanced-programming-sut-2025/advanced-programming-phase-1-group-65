package org.example;

import org.example.Models.App;
import org.example.Models.Tile;
import org.example.Views.AppView;
import org.example.Models.Map;

import java.util.ArrayList;
import java.util.Scanner;

/*

menu enter signupmenu
register -u Arya -p Arya@90579 Arya@90579 -n Arya -e AryaHosseini@gmail.com -g male
1

green
menu enter signupmenu
register -u Ilia -p Ilia@85858 Ilia@85858 -n Ilia -e IliaFaraj@gmail.com -g male
1

blue
menu enter signupmenu
register -u Amir -p Amir@12345 Amir@12345 -n Amir -e Amirasss@gmail.com -g male
1

pink
login -u Arya -p Arya@90579

menu enter Gamemenu

game new -u Arya Amir Ilia


*/
public class Main {
    public static void main(String[] args) {
        /*Map m = new Map();
        ArrayList<ArrayList<Tile>> map = m.buildMap();
        m.printMap(map);
*/
        Scanner scanner = new Scanner(System.in);
        App.checkStayLoggedIn();

        try {
            while (true) {
                App.getCurrentMenu().checkCommands(scanner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}