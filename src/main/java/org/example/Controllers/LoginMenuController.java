package org.example.Controllers;
import org.example.Models.App;
import org.example.Models.Enums.Menu;
import org.example.Models.User;

public class LoginMenuController extends RegisterMenuController {
    public boolean login(String username, String password) {
        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
               return true;

            }

        }
        return false;
    }


}
