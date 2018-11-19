package com.javafxroulette.roulette;

import com.javafxroulette.allshapes.PlayNumbers;
import com.javafxroulette.allshapes.PlayRectangles;
import com.javafxroulette.allshapes.TextInRectangle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistoryBoxView {


    public void historyBoxChangeValue(ArrayList<Integer> drawnNumbers, HashMap<Integer,String> numberColorList, HBox place) {

        place.getChildren().clear();

        TextInRectangle tr = new TextInRectangle();

        if (drawnNumbers.size() < 7) {
            for (int i = 0; i < drawnNumbers.size(); i++) {
                String number = Integer.toString(drawnNumbers.get(i));
                Rectangle drawnRectangle = new Rectangle();
                drawnRectangle.setWidth(38);
                drawnRectangle.setHeight(25);
                drawnRectangle.setStrokeWidth(2);
                drawnRectangle.setArcWidth(15.0);
                drawnRectangle.setArcHeight(15.0);
                drawnRectangle.setStroke(Color.WHITE);
                Text numberText = new Text();

                if (drawnNumbers.get(i) == 0) {
                    drawnRectangle.setFill(Color.rgb(72, 156, 70));
                    numberText = tr.createTextHorizontally("0", 12.0);

                } else {

                    String numberColor = numberColorList.get(drawnNumbers.get(i));

                    if (numberColor.equals("Red")) {
                        drawnRectangle.setFill(Color.rgb(227, 30, 37));
                    } else {
                        drawnRectangle.setFill(Color.rgb(66, 66, 66));
                    }

                    numberText = tr.createTextHorizontally(number, 12.0);
                }
                StackPane sp = new StackPane();
                sp.getChildren().addAll(drawnRectangle, numberText);
                place.getChildren().add(sp);
            }

        } else {
            for (int i = (drawnNumbers.size() - 7); i < drawnNumbers.size(); i++) {
                String number = Integer.toString(drawnNumbers.get(i));
                Rectangle drawnRectangle = new Rectangle();
                drawnRectangle.setWidth(38);
                drawnRectangle.setHeight(25);
                drawnRectangle.setStrokeWidth(2);
                drawnRectangle.setArcWidth(15.0);
                drawnRectangle.setArcHeight(15.0);
                drawnRectangle.setStroke(Color.WHITE);
                Text numberText = new Text();

                if (drawnNumbers.get(i) == 0) {
                    drawnRectangle.setFill(Color.rgb(72, 156, 70));
                    numberText = tr.createTextHorizontally("0", 12.0);
                } else {

                    String numberColor = numberColorList.get(drawnNumbers.get(i));

                    if (numberColor.equals("Red")) {
                        drawnRectangle.setFill(Color.rgb(227, 30, 37));
                    } else {
                        drawnRectangle.setFill(Color.rgb(66, 66, 66));
                    }

                    numberText = tr.createTextHorizontally(number, 12.0);
                }
                StackPane sp = new StackPane();
                sp.getChildren().addAll(drawnRectangle, numberText);
                place.getChildren().add(sp);
            }
        }
    }
}
