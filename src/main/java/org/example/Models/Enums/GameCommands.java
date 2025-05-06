package org.example.Models.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    EXIT("^exit game$"),
    NEXTTURN("^next turn$"),
    WALK("^walk -l (\\d+),(\\d+)"),
    PRINTMAP(""),
    HELPMAP("^help reading map$"),

    SEASON("^season$"),
    DATE("^date$"),
    TIME("^time$"),
    DATETIME("^datetime$"),
    DAYOFWEEK("^day of the week$"),
    CHEAT_ADVANCE_TIME("^cheat advance time (\\d+)h$"),
    CHEAT_ADVANCE_DATE("^cheat advance date (\\d+)d$");

    private final String pattern;

    GameCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher matcher(String input) {
        Matcher matcher = java.util.regex.Pattern.compile(this.pattern).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
