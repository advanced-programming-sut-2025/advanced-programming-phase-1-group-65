package org.example.Controllers;

public class GameMenuController extends MainMenuController{
    String[] usernames = new String[3] ;
    public void NewGame(String Username_1, String Username_2, String Username_3 ) {
        usernames[0] = Username_1;
        usernames[1] = Username_2;
        usernames[2] = Username_3;
        return;
    }
}
