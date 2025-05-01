package org.example.Models.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignUpMenuCommands {

    REGISTER("^register -u (\\S+) -p (\\S+) (\\S+) -n (\\S+) -e (\\S+) -g (\\S+)$"),
    LOGINENTERY("menu enter loginmenu"),
    REGISTERRANDOM("REGISTER RANDOM"),
    SHOW_CURRENT_MENU("^show current menu$"),
    MENU_EXIT("^menu exit$");

    private final String pattern;

    SignUpMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher matcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
