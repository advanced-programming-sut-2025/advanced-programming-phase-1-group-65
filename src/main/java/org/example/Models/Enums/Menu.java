package org.example.Models.Enums;

import org.example.Views.AppMenu;
import org.example.Views.LoginMenuView;
import org.example.Views.SignUpMenuView;

import java.util.Scanner;

public enum Menu {
    SIGNUPMENU(new SignUpMenuView()),
    LOGINMENU(new LoginMenuView());
    private final AppMenu appMenu;
    Menu(AppMenu appMenu) {
        this.appMenu = appMenu;
    }
    public void checkCommands(Scanner scanner) {
        this.appMenu.check(scanner);
    }
}
