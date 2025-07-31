package org.example.Models.Enums;

import org.example.Views.AppMenu;
import org.example.Views.MenuView.GameMenuView;
import org.example.Views.MenuView.LoginMenuView;
import org.example.Views.MenuView.SignUpMenuView;
import org.example.Views.MenuView.MainMenuView;
import org.example.Views.MenuView.ProfileMenuView;
import org.example.Views.MenuView.LoginRegisterMenuView;

import java.util.Scanner;

public enum Menu {

    LOGINREGISTERMENU(new LoginRegisterMenuView()),
    SIGNUPMENU(new SignUpMenuView()),
    LOGINMENU(new LoginMenuView()),
    GAMEMENU(new GameMenuView()),
    MAINMENU(new MainMenuView()),
    PROFILEMENU(new ProfileMenuView());

    private final AppMenu appMenu;

    Menu(AppMenu appMenu) {
        this.appMenu = appMenu;
    }

    public void checkCommands(Scanner scanner) {
        this.appMenu.check(scanner);
    }
}
