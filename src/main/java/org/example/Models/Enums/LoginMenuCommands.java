package org.example.Models.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    LOGIN("^login -u (\\S+) -p (\\S+)( --stay-logged-in)?$", true),
    BACK("^menu enter signupmenu$", false),
    MENUSHOW("^menu show$", false),
    EXIT("Exit"  , false);

    private final String pattern;
    private final boolean isRegex;

    LoginMenuCommands(String pattern, boolean isRegex) {
        this.pattern = pattern;
        this.isRegex = isRegex;
    }

    public Matcher matcher(String input) {
        if (isRegex) {
            Matcher matcher = Pattern.compile(this.pattern).matcher(input);
            if (matcher.matches()) return matcher;
            return null;
        } else {
            if (input.equalsIgnoreCase(this.pattern.replace("^", "").replace("$", ""))) {
                return Pattern.compile(this.pattern).matcher(input);
            }
            return null;
        }
    }
}