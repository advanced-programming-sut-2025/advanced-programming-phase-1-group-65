package org.example;

import org.example.Models.App;
import org.example.Views.AppView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                App.getCurrentMenu().checkCommands(scanner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}