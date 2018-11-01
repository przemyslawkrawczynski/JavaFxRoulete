package com.javafxroulette.allshapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayRectangles {

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
            rectangle.setFill(Color.rgb(72,156,70));
            rectangle.setStroke(Color.WHITE);
            rectangle.setStrokeWidth(4.0);

        }

        return rectangle;

    }
}
