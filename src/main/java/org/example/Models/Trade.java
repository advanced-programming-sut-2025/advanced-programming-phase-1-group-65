package org.example.Models;

public class Trade {
    private String item;
    private int price;
    private String trader;

    public Trade(String item, int price, String trader) {
        this.item = item;
        this.price = price;
        this.trader = trader;
    }
}
