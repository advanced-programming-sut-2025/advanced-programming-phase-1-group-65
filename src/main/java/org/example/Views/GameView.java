package org.example.Views;

import org.example.Controllers.GameController;
import org.example.Models.App;
import org.example.Models.Enums.GameCommands;
import org.example.Models.Enums.Menu;
import org.example.Models.Enums.WeatherType;
import org.example.Models.Game;
import org.example.Controllers.ShopController;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameView {
    public void check(Scanner scanner, Game game) {
        GameController controller = new GameController();
        ShopController shopController = new ShopController();
        Matcher matcher;

        while (true) {
            String input = scanner.nextLine();

            if ((matcher = GameCommands.EXIT.matcher(input)) != null) {
                if (game.currentPlayer == game.MainPlayer) {
                    game.timesLoaded++;
                    App.setCurrentMenu(Menu.MAINMENU);
                    break;
                }
                else{
                    System.out.println("You don't have the authority to do this.");
                }

            } else if ((matcher = GameCommands.NEXTTURN.matcher(input)) != null) {
                controller.processNextTurn(game);

            } else if ((matcher = GameCommands.SEASON.matcher(input)) != null) {
                System.out.println("Current season: " + game.gameClock.getCurrentSeason());

            } else if ((matcher = GameCommands.TIME.matcher(input)) != null) {
                System.out.println("Current time: " + game.gameClock.getFormattedTime());

            } else if ((matcher = GameCommands.DATE.matcher(input)) != null) {
                System.out.println("Current date: " + game.gameClock.getFormattedDate());

            } else if ((matcher = GameCommands.DATETIME.matcher(input)) != null) {
                System.out.println("Current datetime: " + game.gameClock.getFormattedDateTime());

            } else if ((matcher = GameCommands.DAYOFWEEK.matcher(input)) != null) {
                System.out.println("Day of week: " + game.gameClock.getDayOfWeek());

            } else if ((matcher = GameCommands.CHEAT_ADVANCE_TIME.matcher(input)) != null) {
                int hours = Integer.parseInt(matcher.group(1));
                controller.processAdvanceHours(game, hours);

            } else if ((matcher = GameCommands.CHEAT_ADVANCE_DATE.matcher(input)) != null) {
                int days = Integer.parseInt(matcher.group(1));
                controller.processAdvanceDays(game, days);

            } else if ((matcher = GameCommands.ENERGY_SHOW.matcher(input)) != null) {
                controller.ShowCurrentEnergy(game);

            } else if ((matcher = GameCommands.ENERGY_SET_CHEAT.matcher(input)) != null) {
                int energy = Integer.parseInt(matcher.group(1));
                controller.Energy_set(game, energy);
                System.out.println("Energy changed successfully.");

            } else if ((matcher = GameCommands.ENERGY_UNLIMITED.matcher(input)) != null) {
                controller.Energy_unlimited(game);
                System.out.println("Energy set to unlimited.");

            } else if (input.equals("print map")) {
                game.map.printMap(game.Map);

            }
            else if((matcher= GameCommands.WALK.matcher(input))!=null){
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                System.out.println(controller.Walk(x,y,game));
            }
            else if ((matcher= GameCommands.TOOL_EQUIP.matcher(input))!=null){
                String name = matcher.group(1);
                System.out.println(controller.EquipTool(game,name));
            }
            else if ((matcher= GameCommands.SHOW_CURRENT_TOOL.matcher(input))!=null){
                System.out.println(controller.ShowCurrentTool(game));
            }
            else if ((matcher = GameCommands.WEATHER_SHOW.matcher(input)) != null) {
                System.out.println("Today's weather: " + game.weatherSystem.getTodayWeather());

            } else if ((matcher = GameCommands.WEATHER_FORECAST.matcher(input)) != null) {
                System.out.println("Tomorrow's weather: " + game.weatherSystem.getTomorrowWeather());

            } else if ((matcher = GameCommands.CHEAT_WEATHER_SET.matcher(input)) != null) {
                String type = matcher.group(1);
                WeatherType weatherType;
                try {
                    weatherType = WeatherType.valueOf(type.toUpperCase());
                    controller.setWeatherCheat(game, weatherType);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid weather type. Please choose from: SUNNY, RAIN, STORM, SNOW.");
                }

            } else if ((matcher = GameCommands.CHEAT_THOR.matcher(input)) != null) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                controller.triggerLightning(game, x, y);
            }
            else if ((matcher = GameCommands.TOOLS_AVAILABLE.matcher(input)) != null) {
                controller.ShowAllTools(game);
            }
            else if ((matcher = GameCommands.USE_TOOL.matcher(input)) != null) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                controller.UseTool(game, x, y);
            }
            else if ((matcher = GameCommands.CHEAT_ADD_MONEY.matcher(input)) != null) {
                int amount = Integer.parseInt(matcher.group(1));
                controller.cheatAddDollars(game, amount);
            }
            else if ((matcher = GameCommands.SHOW_ALL_PRODUCTS.matcher(input)) != null) {
                shopController.showAllProducts(game);
            }
            else if ((matcher = GameCommands.SHOW_ALL_AVAILABLE_PRODUCTS.matcher(input)) != null) {
                shopController.showAllAvailableProducts(game);
            }
            else if ((matcher = GameCommands.PURCHASE_ITEM.matcher(input)) != null) {
                String productName = matcher.group(1).trim();
                int count = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 1;
                shopController.purchaseItem(game, productName, count,controller);
            }
            else if((matcher = GameCommands.CRAFTINFO.matcher(input)) != null) {
                String name = matcher.group(1);
                controller.CraftInfo(game, name);
            }
            else if((matcher = GameCommands.SHOWINVNETORY.matcher(input)) != null) {
                controller.ShowInventory(game);
            }
            else if((matcher = GameCommands.PLANTING.matcher(input)) != null) {
                String name = matcher.group(1);
                int x = Integer.parseInt(matcher.group(2));
                int y = Integer.parseInt(matcher.group(3));
                controller.Plant(game,x,y,name);
            }
            else if((matcher = GameCommands.CHEATWATER.matcher(input)) != null) {
                controller.CheatWater(game);
            }
            else if((matcher = GameCommands.HOWMUCHWATER.matcher(input)) != null) {
                controller.HowMuchWater(game);
            }
            else if((matcher = GameCommands.SHOWPLANT.matcher(input)) != null) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                controller.ShowPlant(game,x,y);
            }
            else if((matcher = GameCommands.HELPMAP.matcher(input)) !=null){
                controller.HelpMap();
            }
            else if ((matcher = GameCommands.FISHING.matcher(input)) != null) {
                String toolName = matcher.group(1);
                controller.Fishing(game, toolName);
            }
            else if((matcher = GameCommands.TRASHCAN.matcher(input)) != null) {
                String itemName = matcher.group(1).trim();
                int count = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 1;
                controller.removeItem(game,itemName,count);
            }
            else if ((matcher = GameCommands.FERTILIZE.matcher(input)) != null) {
                String name = matcher.group(1);
                int x = Integer.parseInt(matcher.group(2));
                int y = Integer.parseInt(matcher.group(3));
                controller.Fertilize(game,x,y,name);
            }
            else if((matcher = GameCommands.SELL.matcher(input)) != null) {
                String name = matcher.group(1);
                int count = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 1;
                controller.Sell(name,game,count);
            }
            else if((matcher = GameCommands.SHOWMONEY.matcher(input)) != null) {
                System.out.println(game.currentPlayer.money);
            }


            else {
                System.out.println("Invalid input");
            }
        }
    }
}