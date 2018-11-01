package com.javafxroulette.allshapes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TextInRectangle {

    public Text createText(String text, Double size) {
        Text textFill = new Text();
        textFill.setText(text);
        textFill.setFill(Color.WHITE);
        textFill.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.BOLD, size));
        textFill.setRotate(270);
        return textFill;
    }

    public Text createTextHorizontally(String text, Double size) {
        Text textFill = new Text();
        textFill.setText(text);
        textFill.setFill(Color.WHITE);
        textFill.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.BOLD, size));
        return textFill;
    }

    public Text createTextHistoryBox(String text, Double size, Color color) {
        Text textFill = new Text();
        textFill.setText(text);
        textFill.setFill(color);
        textFill.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.BOLD, size));
        return textFill;
    }
}
