package org.example.Models.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    LOGIN("^login -u (\\S+) -p (\\S+)( -stay-logged-in)?$"),
    SHOW_CURRENT_MENU("^show current menu$"),
    MENU_EXIT("^menu exit$"),
    FORGOTPASSWORD("^forgot-password -u (\\S+)$"),
    MENU_ENTER("^menu enter (\\w+)$"), BACK("Back");

    private final String pattern;

    LoginMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher matcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}