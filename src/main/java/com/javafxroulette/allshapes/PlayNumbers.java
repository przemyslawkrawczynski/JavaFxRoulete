package com.javafxroulette.allshapes;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PlayNumbers {
    private List redNumberList;
    private List blackNumberList;

    public PlayNumbers(List redNumberList, List blackNumberList) {
        this.redNumberList = redNumberList;
        this.blackNumberList = blackNumberList;
    }

    public String checkNumberColor(int number) {
        String result = "0";
        if (0 != number) {
            for (int i = 0; i < redNumberList.size(); i++) {
                if (redNumberList.get(i).equals(number)) {
                    result = "Red";
                } else {
                    result = "Black";
                }

            }
        }
        return result;
    }
}
