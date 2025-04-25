package org.example.Views;

import org.example.Controllers.LoginMenuController;

import java.util.Scanner;

public class LoginMenuView implements AppMenu{
    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
    }
}
