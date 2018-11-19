package com.javafxroulette.roulette;

import java.util.List;

public class GameEngine {
    private List redNumberList;
    private List blackNumberList;
    private int balance;

    public GameEngine(List redNumberList, List blackNumberList, int balance) {
        this.redNumberList = redNumberList;
        this.blackNumberList = blackNumberList;
        this.balance = balance;
    }

}
