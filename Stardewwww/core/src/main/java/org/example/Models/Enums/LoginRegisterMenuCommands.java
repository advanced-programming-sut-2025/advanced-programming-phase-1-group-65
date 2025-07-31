package org.example.Models.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginRegisterMenuCommands {
    MENU_ENTER("^menu enter (\\w+)$"),
    MENU_EXIT("^menu exit$"),
    SHOW_CURRENT_MENU("^show current menu$");

    private final String pattern;

    LoginRegisterMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher matcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
