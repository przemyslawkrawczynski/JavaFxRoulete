package com.javafxroulette;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayRectangles extends Rectangle{

    public Rectangle createRectangle(Double width, Double height, Double roundCorner, String color) {

        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setArcWidth(roundCorner);
        rectangle.setArcHeight(roundCorner);

        if (color.equals("Red")) {
            rectangle.setFill(Color.rgb(227, 30, 37));
            rectangle.setStroke(Color.WHITE);
            rectangle.setStrokeWidth(4.0);

        } else if (color.equals("Black")) {
            rectangle.setFill(Color.rgb(66,66,66));
            rectangle.setStroke(Color.WHITE);
            rectangle.setStrokeWidth(4.0);

        } else if (color.equals("Green")) {
            rectangle.setFill(Color.rgb(49,98,49));
            rectangle.setStroke(Color.WHITE);
            rectangle.setStrokeWidth(4.0);
        }
        return rectangle;
    }

}