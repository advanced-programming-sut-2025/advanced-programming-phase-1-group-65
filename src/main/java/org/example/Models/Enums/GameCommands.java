package org.example.Models.Enums;

import java.util.regex.Matcher;

public enum GameCommands {
    EXIT("^exit game$"),
    NEXTTURN("^next turn$"),
    WALK("^walk -l (\\d+),(\\d+)$"),
    PRINTMAP(""),
    HELPMAP("^help reading map$"),
    SEASON("^season$"),
    DATE("^date$"),
    TIME("^time$"),
    DATETIME("^datetime$"),
    DAYOFWEEK("^day of the week$"),
    CHEAT_ADVANCE_TIME("^cheat advance time (\\d+)h$"),
    CHEAT_ADVANCE_DATE("^cheat advance date (\\d+)d$"),
    ENERGY_SHOW("^energy show$"),
    ENERGY_SET_CHEAT("^energy set -v (\\d+)$"),
    ENERGY_UNLIMITED("^energy unlimited$"),
    WEATHER_SHOW("^weather$"),
    WEATHER_FORECAST("^weather forecast$"),
    CHEAT_WEATHER_SET("^cheat weather set (SUNNY|RAIN|STORM|SNOW)$"),
    CHEAT_THOR("^cheat Thor -l (\\d+),(\\d+)$"),
    SHOW_CURRENT_TOOL("^tool show current$"),
    TOOL_EQUIP("^tools equip ([\\w.-]+)$"),
    USE_TOOL("^tools use -d (0|1|-1),(0|1|-1)$"),
    TOOLS_AVAILABLE("^tools show available"),
    CHEAT_ADD_MONEY("^cheat add (\\d+) dollars$"),
    SHOW_ALL_PRODUCTS("^show all products$"),
    SHOW_ALL_AVAILABLE_PRODUCTS("^show all available products$"),
    PURCHASE_ITEM("^purchase\\s+(.+?)(?:\\s+-n\\s+(\\d+))?$"),
    CRAFTINFO("^craftinfo\\s+-n\\s+(.+)$"),
    SHOWINVNETORY("show inventory"),
    PLANTING("^plant -s\\s+(.+) -d (0|1|-1),(0|1|-1)$"),
    CHEATWATER("^cheat water$"),
    HOWMUCHWATER("^howmuch water$"),
    SHOWPLANT("^show plant -l (\\d+),(\\d+)$"),
    FISHING("^fishing\\s+-p\\s+(.+)$"),
    TRASHCAN("^inventory trash\\s+(.+?)(?:\\s+-n\\s+(\\d+))?"),
    FERTILIZE("^fertilize\\s+-f\\s+(.+) -d (0|1|-1),(0|1|-1)$");

    private final String pattern;

    GameCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher matcher(String input) {
        Matcher matcher = java.util.regex.Pattern.compile(this.pattern).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}