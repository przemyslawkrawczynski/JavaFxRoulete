package com.javafxroulette;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Chip {

    public StackPane createChip(String amount, Color color) {

        TextInRectangle tr = new TextInRectangle();

        Circle chipBGstroke = new Circle(39, Color.WHITE);
        chipBGstroke.setEffect(new DropShadow());

        Circle chipBG = new Circle(36, color);
        chipBG.setStroke(color);
        chipBG.setStrokeWidth(4);
        chipBG.setFill(Color.WHITE);

        Rectangle rec1  = new Rectangle();
        rec1.setFill(color);
        rec1.setWidth(70);
        rec1.setHeight(9);

        Rectangle rec2  = new Rectangle();
        rec2.setFill(color);
        rec2.setWidth(70);
        rec2.setHeight(9);
        rec2.setRotate(30);

        Rectangle rec3  = new Rectangle();
        rec3.setFill(color);
        rec3.setWidth(70);
        rec3.setHeight(9);
        rec3.setRotate(60);

        Rectangle rec4  = new Rectangle();
        rec4.setFill(color);
        rec4.setWidth(70);
        rec4.setHeight(9);
        rec4.setRotate(90);

        Rectangle rec5  = new Rectangle();
        rec5.setFill(color);
        rec5.setWidth(70);
        rec5.setHeight(9);
        rec5.setRotate(120);

        Rectangle rec6  = new Rectangle();
        rec6.setFill(color);
        rec6.setWidth(70);
        rec6.setHeight(9);
        rec6.setRotate(150);

        Circle chipIn = new Circle(21, color);
        chipIn.setStroke(color);
        chipIn.setStrokeWidth(4);

        Circle chipInText = new Circle(18, color);
        chipInText.setStroke(Color.WHITE);
        chipInText.setStrokeWidth(2);

        Text chipTxt = tr.createTextHorizontally(amount, 19.0);

        StackPane chip = new StackPane();
        chip.getChildren().addAll(chipBGstroke, chipBG, rec1, rec2, rec3, rec4, rec5, rec6, chipIn,chipInText, chipTxt);

        chip.setOnMouseEntered(e -> chipBGstroke.setFill(Color.rgb(218, 149, 19)));
        chip.setOnMouseExited(e -> chipBGstroke.setFill(Color.WHITE));

        return chip;

    }
}
