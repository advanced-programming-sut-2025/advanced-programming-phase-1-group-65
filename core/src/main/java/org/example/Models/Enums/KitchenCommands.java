package org.example.Models.Enums;

import java.util.regex.Matcher;

public enum KitchenCommands {
    EXIT("exit kitchen"),
    REFRIGERATORPUT("^cooking refrigerator put (.+)$"),
    REFRIGERATORPICK("^cooking refrigerator pick (.+)$"),
    SHOWRECIPE("^cooking show recipes"),
    COOKINGPREPARE("^cooking prepare (.+)");










    private final String pattern;

    KitchenCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher matcher(String input) {
        Matcher matcher = java.util.regex.Pattern.compile(this.pattern).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
