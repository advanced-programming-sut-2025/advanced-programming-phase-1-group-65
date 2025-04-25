package org.example.Views;

import org.example.Controllers.SignUpMenuController;
import org.example.Models.App;
import org.example.Models.Enums.Menu;
import org.example.Models.Enums.SignUpMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SignUpMenuView implements AppMenu{
    private final SignUpMenuController controller= new SignUpMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();

        Matcher matcher;
        if((matcher = SignUpMenuCommands.REGISTER.matcher(input)) != null){
            System.out.println(controller.registerUser(matcher.group(1).trim(),
                    matcher.group(2).trim(),
                    matcher.group(3).trim(),
                    matcher.group(5).trim(),
                    matcher.group(4).trim(),
                    matcher.group(6).trim()));
            //  گروه های مچر برن به کنترلر و چک بشن

        }
        else if ((matcher = SignUpMenuCommands.Exit.matcher(input)) != null){
            System.out.println("Goodbye");
            System.exit(0);
        }
        else if ((matcher=SignUpMenuCommands.LOGINENTERY.matcher(input))!= null){
            App.setCurrentMenu(Menu.LOGINMENU);
            System.out.println("You are now in login menu");
        }
        else if ((matcher=SignUpMenuCommands.REGISTERRANDOM.matcher(input))!= null){
            // رمز رندوم بده سوال امنیتی بپرس
        }
        else {
            System.out.println("Invalid input! You are in SignUpMenu");
        }
    }
}
