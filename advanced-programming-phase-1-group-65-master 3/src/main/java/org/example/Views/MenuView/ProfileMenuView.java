package org.example.Views.MenuView;

import org.example.Controllers.MenuController.ProfileMenuController;
import org.example.Models.Enums.ProfileMenuCommands;
import org.example.Models.Enums.Menu;
import org.example.Models.App;  // این خط را اضافه کنید
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenuView implements AppMenu {
    private ProfileMenuController controller = new ProfileMenuController();

    @Override
    public void check(Scanner scanner) {

        System.out.println("Welcome to Profile Menu!");
        System.out.println("Commands: ");
        System.out.println("change username -u <username>");
        System.out.println("change nickname -n <nickname>");
        System.out.println("change email -e <email>");
        System.out.println("change password -o <old_password> -p <new_password>");
        System.out.println("user info");
        System.out.println("user logout");

        while (true) {
            String input = scanner.nextLine();
            Matcher matcher;

            if ((matcher = ProfileMenuCommands.CHANGE_USERNAME.matcher(input)) != null) {
                String newUsername = matcher.group(1);
                controller.changeUsername(newUsername);
            }
            else if ((matcher = ProfileMenuCommands.CHANGE_NICKNAME.matcher(input)) != null) {
                String newNickname = matcher.group(1);
                controller.changeNickname(newNickname);
            }
            else if ((matcher = ProfileMenuCommands.CHANGE_EMAIL.matcher(input)) != null) {
                String newEmail = matcher.group(1);
                controller.changeEmail(newEmail);
            }
            else if ((matcher = ProfileMenuCommands.CHANGE_PASSWORD.matcher(input)) != null) {
                String oldPassword = matcher.group(1);
                String newPassword = matcher.group(2);
                controller.changePassword(oldPassword, newPassword);
            }
            else if ((matcher = ProfileMenuCommands.SHOW_CURRENT_MENU.matcher(input)) != null && matcher.matches()) {
                System.out.println("You are currently in the Profile Menu.");
            }

            else if ((matcher = ProfileMenuCommands.USER_INFO.matcher(input)) != null && matcher.matches()) {
              controller.displayUserInfo();
            }
            else if ((matcher = ProfileMenuCommands.USER_LOGOUT.matcher(input)) != null && matcher.matches()) {
                App.setCurrentMenu(Menu.LOGINREGISTERMENU);
                System.out.println("You are currently in the Login/Register Menu.");
                break ;
            }
            else {
                System.out.println("Invalid input! Please enter a valid command.");
            }
        }
    }
}