package org.example.Controllers;

import org.example.Models.Enums.TileType;
import org.example.Models.Game;
import org.example.Models.Player;
import org.example.Models.Tile;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GameController {
    private static final int[][] DIRECTIONS ={
            {-1,0},//up
            { 1,0},//down
            { 0,-1},//left
            { 0,1},//right
    };
    private boolean[][] visited;
    private int minsteps=Integer.MAX_VALUE;
    public String Walk(int destx, int desty, Game game) {
        Scanner temp = new Scanner(System.in);
        int height = 114;
        int width = 140;
        boolean[][] visited = new boolean[height][width];
        int[][] distance = new int[height][width];

        int startX = game.currentPlayer.PositionX;
        int startY = game.currentPlayer.PositionY;

        if (!game.Map.get(desty).get(destx).type.equals(TileType.EMPTY)) {
            return "You can not walk to this position";
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startY][startX] = true;
        distance[startY][startX] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == destx && y == desty) {
                break;
            }

            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newY >= 0 && newX < width && newY < height
                        && !visited[newY][newX]
                        && (game.Map.get(newY).get(newX).type.equals(TileType.EMPTY)
                        || game.Map.get(newY).get(newX).type.equals(TileType.PLAYER))) {

                    visited[newY][newX] = true;
                    distance[newY][newX] = distance[y][x] + 1;
                    queue.add(new int[]{newX, newY});
                }
            }
        }

        int minsteps = distance[desty][destx];
        if (!visited[desty][destx]) {
            return "You can not walk to this position";
        }

        int energyNeeded = minsteps / 20;
        if (energyNeeded > game.currentPlayer.Energy) {
            return "You don't have enough energy";
        }

        System.out.println("You need " + energyNeeded + " energy to walk to this position.\nDo you wish to proceed? (y/n)");
        String ch = temp.nextLine();

        if (ch.equalsIgnoreCase("y") || ch.equalsIgnoreCase("yes")) {
            int prevX = game.currentPlayer.PositionX;
            int prevY = game.currentPlayer.PositionY;

            game.currentPlayer.Energy -= energyNeeded;
            game.currentPlayer.PositionX = destx;
            game.currentPlayer.PositionY = desty;

            game.Map.get(prevY).set(prevX, new Tile(TileType.EMPTY));
            game.Map.get(desty).set(destx, new Tile(TileType.PLAYER));

            return "You have reached your destination";
        }

        return "Command cancelled";
    }



}
