package org.example.Controllers.MenuController;

import org.example.Models.App;
import org.example.Models.User;
import org.example.Models.Enums.Menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuController {

    private boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9-]+$");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public String changeUsername(String newUsername) {
        User currentUser = App.getCurrentUser();
        if (currentUser == null) return "No user is currently logged in.";

        if (currentUser.getUsername().equals(newUsername)) return "The username is the same as the current one.";

        if (!isValidUsername(newUsername)) return "Invalid username. Only letters, numbers, and hyphens are allowed.";

        for (User user : App.getUsers()) {
            if (user.getUsername().equals(newUsername)) return "Username already taken. Try a different one.";
        }

        currentUser.setUsername(newUsername);
        return "Username changed successfully.";
    }

    public String changeEmail(String newEmail) {
        User currentUser = App.getCurrentUser();
        if (currentUser == null) return "No user is currently logged in.";

        if (currentUser.getEmail().equals(newEmail)) return "The email is the same as the current one.";

        if (!isValidEmail(newEmail)) return "Invalid email format.";

        for (User user : App.getUsers()) {
            if (user.getEmail().equals(newEmail)) return "Email already taken. Try a different one.";
        }

        currentUser.setEmail(newEmail);
        return "Email changed successfully.";
    }

    public String changeNickname(String newNickname) {
        User currentUser = App.getCurrentUser();
        if (currentUser == null) return "No user is currently logged in.";

        if (currentUser.getNickname().equals(newNickname)) return "The nickname is the same as the current one.";

        currentUser.setNickname(newNickname);
        return "Nickname changed successfully.";
    }

    public String changeAvatar(String avatarPath) {
        User currentUser = App.getCurrentUser();
        if (currentUser == null) return "No user is currently logged in.";

        currentUser.setAvatarPath(avatarPath);
        return "Avatar changed to: " + avatarPath;
    }

    public String logout() {
        App.setCurrentUser(null);
        App.setCurrentMenu(Menu.LOGINMENU);
        return "You have logged out.";
    }
}
