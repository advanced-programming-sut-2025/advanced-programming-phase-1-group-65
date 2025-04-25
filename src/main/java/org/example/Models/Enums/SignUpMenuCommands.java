package org.example.Models.Enums;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignUpMenuCommands {
    // add regexes
    REGISTER("^register -u (\\S+) -p (\\S+) (\\S+) -n (\\S+) -e (\\S+) -g (\\S+)$"),
    Exit("EXIT"),
    LOGINENTERY("menu enter loginmenu"),
    MENUSHOW("menu show"),
    REGISTERRANDOM("REGISTER RANDOM");



    private final String pattern;
    SignUpMenuCommands(String pattern) {
        this.pattern = pattern;

    }
    public Matcher matcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if(matcher.matches()) {
            return matcher;
        }
        return null;
    }


}
