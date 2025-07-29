package org.example.Models.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    SHOW_CURRENT_MENU("^show current menu$"),
    CHANGE_USERNAME("^change username -u (\\S+)$"),
    CHANGE_PASSWORD("^change password -o (\\S+) -p (\\S+)$"),
    CHANGE_EMAIL("^change email -e (\\S+)$"),
    CHANGE_NICKNAME("^change nickname -n (\\S+)$"),
    MENU_ENTER("^menu enter (\\w+)$"),
    USER_INFO("^user info$"),
    USER_LOGOUT("^user logout$");

    private final String pattern;

    ProfileMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher matcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
