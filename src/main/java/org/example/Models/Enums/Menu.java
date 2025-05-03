package org.example.Models.Enums;

import org.example.Views.AppMenu;
import org.example.Views.LoginMenuView;
import org.example.Views.SignUpMenuView;
import org.example.Views.MainMenuView;
import org.example.Views.ProfileMenuView;
import org.example.Views.LoginRegisterMenuView;

import java.util.Scanner;

public enum Menu {

    LOGINREGISTERMENU(new LoginRegisterMenuView()),
    SIGNUPMENU(new SignUpMenuView()),
    LOGINMENU(new LoginMenuView()),
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