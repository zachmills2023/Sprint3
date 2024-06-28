package com.example;

public class Stock {
    private String symbol;
    private double price;
    private double high52Week;

    public Stock(String symbol, double price, double high52Week) {
        this.symbol = symbol;
        this.price = price;
        this.high52Week = high52Week;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getHigh52Week() {
        return high52Week;
    }

    public void setHigh52Week(double high52Week) {
        this.high52Week = high52Week;
    }
}
