package org.example.Models.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    EXIT("^exit game$"),
    NEXTTURN("^next turn$"),
    WALK(""),
    PRINTMAP(""),
    HELPMAP("^help reading map$"),;


    private final String pattern;
    GameCommands(String pattern) {
        this.pattern = pattern;
    }
    public Matcher matcher(String input) {
        Matcher matcher = java.util.regex.Pattern.compile(this.pattern).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
