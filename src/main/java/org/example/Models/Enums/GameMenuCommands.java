package org.example.Models.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    NEWGAME("^game new -u ([\\w.-]+) ([\\w.-]+) ([\\w.-]+)$"),
    LOADGAME(""),
    EXIT("");

    public Matcher matcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
    private final String pattern;

    GameMenuCommands(String pattern) {
        this.pattern = pattern;
    }
}
