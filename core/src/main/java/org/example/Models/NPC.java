package org.example.Models;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class NPC {
    private String name;
    private int x, y;
    private int friendshipLevel = 0;
    private Set<Integer> daysTalked;
    private Map<Integer, Quest> friendshipQuests;
    private Map<String, List<String>> dialogues;
    private Set<String> favoriteItems = new HashSet<>();
    private int lastGiftDay = -1;
    private int friendshipPoints = 0;

    public NPC(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.friendshipLevel = 0;
        this.daysTalked = new HashSet<>();
        this.dialogues = new HashMap<>();
        this.friendshipQuests = new HashMap<>();
        loadDefaultDialogues();


    }

    public String getName() {
        return name;
    }

    public int getFriendshipLevel() {
        return friendshipLevel;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getLastGiftDay() {
        return lastGiftDay;
    }
    public void setFavoriteItems(Set<String> favoriteItems) {
        this.favoriteItems = favoriteItems;
    }
    public void setLastGiftDay(int lastGiftDay) {
        this.lastGiftDay = lastGiftDay;
    }


    public void setFriendshipQuests(Map<Integer, Quest> quests) {
        this.friendshipQuests = quests;
    }
    public void addFriendshipQuest(int friendshipLevel, Quest quest) {
        friendshipQuests.put(friendshipLevel, quest);
    }

    public Map<Integer, Quest> getFriendshipQuests() {
        return friendshipQuests;
    }
    public void setFriendshipPoints(int points) {
        this.friendshipPoints = Math.min(799, points);
    }

    public void setFriendshipLevel(int level) {
        this.friendshipLevel = Math.min(3, level);
    }


    public Set<String> getFavoriteItems() {
        return favoriteItems;
    }
    public void increaseFriendship(int amount) {
        friendshipPoints = Math.min(799, friendshipPoints + amount);
        friendshipLevel = Math.min(3, friendshipPoints / 200);
    }
    private void loadDefaultDialogues() {
        // --- Spring Morning Sunny ---
        dialogues.put("morning_spring_sunny_low", Arrays.asList(
                "Good morning, stranger!",
                "Lovely spring morning, isn't it?",
                "Smell that? Flowers are blooming.",
                "I'm still getting used to your face.",
                "Don’t step on the tulips, please."
        ));
        dialogues.put("morning_spring_sunny_high", Arrays.asList(
                "Good morning, dear friend!",
                "You're glowing like the spring sun!",
                "Want to help me pick some fresh herbs?",
                "This weather puts me in such a good mood!",
                "We should picnic sometime!"
        ));

        // --- Summer Afternoon Rain ---


        // --- Fall Evening Sunny ---
        dialogues.put("evening_fall_sunny_low", Arrays.asList(
                "Chilly evening, isn't it?",
                "Leaves are everywhere... again.",
                "Never seen you around before at this hour.",
                "Watch your step—wet leaves are slippery!"
        ));
        dialogues.put("evening_fall_sunny_high", Arrays.asList(
                "These golden skies remind me of you.",
                "Let’s walk and hear the leaves crunch.",
                "Want to share stories under the stars?",
                "I like this time of year—peaceful and deep."
        ));
        // Summer - Morning
        dialogues.put("morning_summer_sunny_low", Arrays.asList(
                "Hot already? Summer mornings are rough.",
                "Don’t talk to me until I’ve had some iced tea.",
                "Who are you again?"
        ));
        dialogues.put("morning_summer_sunny_neutral", Arrays.asList(
                "Beautiful day, isn't it?",
                "I might hit the beach later.",
                "Don’t forget sunscreen!"
        ));
        dialogues.put("morning_summer_sunny_high", Arrays.asList(
                "Good morning, sunshine!",
                "With you around, summer feels cooler.",
                "Let’s take a walk before it gets too hot."
        ));

        dialogues.put("morning_summer_rain_low", Arrays.asList(
                "Rain in summer? That’s unexpected.",
                "You’re new around here, huh?"
        ));
        dialogues.put("morning_summer_rain_neutral", Arrays.asList(
                "Rainy mornings are rare in summer.",
                "Good day to stay inside and relax.",
                "The rain makes the garden happy."
        ));
        dialogues.put("morning_summer_rain_high", Arrays.asList(
                "Glad you’re here despite the rain.",
                "Perfect morning for some tea and chat.",
                "Rain can’t dampen our spirits!"
        ));

        dialogues.put("morning_summer_storm_low", Arrays.asList(
                "Stormy mornings are the worst.",
                "I didn’t sign up for this weather.",
                "Stay safe out there, stranger."
        ));
        dialogues.put("morning_summer_storm_neutral", Arrays.asList(
                "Storm’s coming, better prepare.",
                "I hope this passes quickly.",
                "Stay inside, it’s dangerous out."
        ));
        dialogues.put("morning_summer_storm_high", Arrays.asList(
                "Even storms can’t stop us from talking!",
                "You bring sunshine to the darkest days.",
                "Let’s wait out the storm together."
        ));


        dialogues.put("afternoon_summer_sunny_low", Arrays.asList(
                "The sun is blazing today.",
                "You look like you can handle the heat.",
                "Who’s this again?"
        ));
        dialogues.put("afternoon_summer_sunny_neutral", Arrays.asList(
                "Perfect weather for a swim.",
                "Summer afternoons are my favorite.",
                "Stay hydrated out there."
        ));
        dialogues.put("afternoon_summer_sunny_high", Arrays.asList(
                "Nothing beats summer with you around.",
                "Let’s grab some ice cream later!",
                "You brighten up this hot day."
        ));

        dialogues.put("afternoon_summer_rain_low", Arrays.asList(
                "Rain on a summer afternoon? Strange.",
                "Not the weather I hoped for.",
                "You’re new, aren’t you?"
        ));
        dialogues.put("afternoon_summer_rain_neutral", Arrays.asList(
                "A sudden shower to cool things down.",
                "Better stay dry for now.",
                "Rainy days can be peaceful."
        ));
        dialogues.put("afternoon_summer_rain_high", Arrays.asList(
                "I love how you make rainy days better.",
                "Perfect time for a warm drink and chat.",
                "You always lift my mood."
        ));

        dialogues.put("afternoon_summer_storm_low", Arrays.asList(
                "Storms can ruin a good day.",
                "Not a fan of this weather.",
                "Be careful out there."
        ));
        dialogues.put("afternoon_summer_storm_neutral", Arrays.asList(
                "The storm’s rolling in fast.",
                "I hope it’s over soon.",
                "Stay safe inside."
        ));
        dialogues.put("afternoon_summer_storm_high", Arrays.asList(
                "Storms are no match for our friendship.",
                "I’m glad you’re here with me.",
                "Let’s enjoy this moment despite the weather."
        ));


        dialogues.put("evening_summer_sunny_low", Arrays.asList(
                "Long summer days make me tired.",
                "I’m not used to you yet.",
                "Stay cool tonight."
        ));
        dialogues.put("evening_summer_sunny_neutral", Arrays.asList(
                "Summer evenings are peaceful.",
                "Perfect time to watch the sunset.",
                "Glad you stopped by."
        ));
        dialogues.put("evening_summer_sunny_high", Arrays.asList(
                "I always look forward to summer evenings with you.",
                "Let’s watch the stars together.",
                "You make summer nights magical."
        ));

        dialogues.put("evening_summer_rain_low", Arrays.asList(
                "Rainy nights can be gloomy.",
                "I don’t know you well yet.",
                "Stay dry out there."
        ));
        dialogues.put("evening_summer_rain_neutral", Arrays.asList(
                "The rain soothes the summer heat.",
                "Good night, friend.",
                "Hope tomorrow’s sunnier."
        ));
        dialogues.put("evening_summer_rain_high", Arrays.asList(
                "Rain or shine, I’m glad you’re here.",
                "Let’s enjoy the sound of rain together.",
                "You make every night better."
        ));

        dialogues.put("evening_summer_storm_low", Arrays.asList(
                "Stormy nights are scary.",
                "I don’t know if I trust you yet.",
                "Be careful tonight."
        ));
        dialogues.put("evening_summer_storm_neutral", Arrays.asList(
                "The storm is fierce tonight.",
                "Better stay inside.",
                "I hope it passes soon."
        ));
        dialogues.put("evening_summer_storm_high", Arrays.asList(
                "Even a stormy night can’t keep me from talking to you.",
                "You’re a light in the darkness.",
                "Stay safe, my friend."
        ));


        dialogues.put("evening_winter_snow_low", Arrays.asList(
                "So cold... why are we even out here?",
                "My fingers are freezing!",
                "You again? Brave soul to wander in snow.",
                "Don’t slip on the ice!"
        ));
        dialogues.put("evening_winter_snow_high", Arrays.asList(
                "You warm up the coldest evenings.",
                "Snowball fight later?",
                "Let’s watch the snowfall together.",
                "I’ve got some cocoa back home... just saying."
        ));

        dialogues.put("afternoon_spring_rain_low", Arrays.asList(
                "Ah, spring rain again...",
                "Great, my socks are soaked.",
                "You new here or just love getting wet?"
        ));
        dialogues.put("afternoon_spring_rain_high", Arrays.asList(
                "Rain’s not so bad with you around.",
                "Let’s find a tree to sit under.",
                "Wanna dance in the rain?"
        ));

        // Winter - Morning - Snow
        dialogues.put("morning_winter_snow_low", Arrays.asList(
                "Brrr, it's freezing this morning.",
                "I hope you dressed warmly.",
                "Snow is beautiful but so cold!"
        ));
        dialogues.put("morning_winter_snow_neutral", Arrays.asList(
                "Good morning! The snow makes everything look magical.",
                "Winter mornings have a special calm, don't they?",
                "Stay cozy out there."
        ));
        dialogues.put("morning_winter_snow_high", Arrays.asList(
                "Morning, friend! Snow days are better with you around.",
                "I'm glad we can share this snowy morning.",
                "Let's enjoy a warm drink later!"
        ));

// Winter - Afternoon - Snow
        dialogues.put("afternoon_winter_snow_low", Arrays.asList(
                "The snow keeps falling... it's chilly out here.",
                "Don't catch a cold!",
                "New face? It's freezing to get to know you."
        ));
        dialogues.put("afternoon_winter_snow_neutral", Arrays.asList(
                "Afternoon snowfalls are so peaceful.",
                "Have you built a snowman yet?",
                "It's nice to have some company on cold days."
        ));
        dialogues.put("afternoon_winter_snow_high", Arrays.asList(
                "Afternoon! Ready for a snowball fight?",
                "Snowy days with friends are the best.",
                "Let's warm up by the fire later."
        ));

// Winter - Evening - Snow
        dialogues.put("evening_winter_snow_low", Arrays.asList(
                "Cold night, stay warm.",
                "Winter nights can be harsh.",
                "My toes are frozen... and so is my mood."
        ));
        dialogues.put("evening_winter_snow_neutral", Arrays.asList(
                "Evening snow is so quiet and calming.",
                "Hope you have a warm place to go.",
                "The stars sparkle beautifully tonight."
        ));
        dialogues.put("evening_winter_snow_high", Arrays.asList(
                "You warm up the coldest evenings.",
                "Snowball fight later?",
                "You bring warmth, even in winter."
        ));
        // Winter - Morning - Sunny
        dialogues.put("morning_winter_sunny_low", Arrays.asList(
                "A sunny winter morning, not too bad!",
                "I’m surprised to see the sun today.",
                "Cold but clear skies, that's winter for you."
        ));
        dialogues.put("morning_winter_sunny_neutral", Arrays.asList(
                "Good morning! The sun makes winter feel a bit warmer.",
                "A bright start to a chilly day.",
                "Hope you enjoy the sunshine!"
        ));
        dialogues.put("morning_winter_sunny_high", Arrays.asList(
                "Morning, friend! Winter sun suits you well.",
                "Let's make the most of this bright cold day.",
                "Sunshine and friendship — perfect combo!"
        ));

// Winter - Afternoon - Sunny
        dialogues.put("afternoon_winter_sunny_low", Arrays.asList(
                "Sunshine can be deceiving, it's still cold.",
                "Keep warm even if the sun is out.",
                "First time seeing you around here."
        ));
        dialogues.put("afternoon_winter_sunny_neutral", Arrays.asList(
                "The sun’s out and the snow is sparkling.",
                "Winter afternoons like this are rare.",
                "Glad you're here to enjoy it too."
        ));
        dialogues.put("afternoon_winter_sunny_high", Arrays.asList(
                "Afternoon sunshine with you is a blessing.",
                "Let’s take a walk while the sun’s still out.",
                "You make this cold day feel warmer."
        ));

// Winter - Evening - Sunny
        dialogues.put("evening_winter_sunny_low", Arrays.asList(
                "The sun's setting early in winter.",
                "Evenings get cold fast, stay close to fire.",
                "Haven't seen you before around here."
        ));
        dialogues.put("evening_winter_sunny_neutral", Arrays.asList(
                "The sunset paints a beautiful winter sky.",
                "Nice to see you before the night gets colder.",
                "Winter evenings can be peaceful."
        ));
        dialogues.put("evening_winter_sunny_high", Arrays.asList(
                "The winter sun sets, but our friendship stays bright.",
                "Glad to have you here this evening.",
                "Let’s enjoy this calm, crisp evening together."
        ));
        dialogues.put("default", Arrays.asList(
                "Hello there!",
                "Nice to see you.",
                "We meet again.",
                "Need something?",
                "The weather’s been strange lately, hasn’t it?"
        ));
    }

    private String getTimeOfDay(int hour) {
        if (hour >= 9 && hour < 12) return "morning";
        else if (hour >= 12 && hour < 17) return "afternoon";
        else return "evening";
    }

    private String getFriendshipCategory() {
        if (friendshipPoints < 70) return "low";
        else if (friendshipPoints < 100) return "neutral";
        else return "high";
    }

    public boolean isPlayerNearby(int playerX, int playerY) {
        int dx = Math.abs(playerX - x);
        int dy = Math.abs(playerY - y);
        return dx <= 1 && dy <= 1;
    }

    public String meet(int currentDay, int hour, String currentSeason, String currentWeather, int playerX, int playerY) {
        if (!isPlayerNearby(playerX, playerY)) {
            return "You are too far from " + name + " to talk.";
        }

        if (!daysTalked.contains(currentDay)) {
            increaseFriendship(20);
            daysTalked.add(currentDay);
        }

        String timeOfDay = getTimeOfDay(hour);
        String friendshipCategory = getFriendshipCategory();

        String key = timeOfDay + "_" + currentSeason.toLowerCase() + "_" + currentWeather.toLowerCase() + "_" + friendshipCategory;
        List<String> possibleDialogues = dialogues.getOrDefault(key, dialogues.get("default"));

        Random rand = new Random();
        return possibleDialogues.get(rand.nextInt(possibleDialogues.size()));
    }

}
