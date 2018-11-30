package com.javafxroulette;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class UserChoiceBox {

    TextInRectangle text = new TextInRectangle();

    public void addToUserChoiceBox(int bid, int posX, int posY, VBox userChoiceBox, PlayNumbers pn, HashMap<int[], int[]> betingOptionList) {
        HBox bidLine = new HBox();
        bidLine.setSpacing(3);
        bidLine.setAlignment(Pos.CENTER_LEFT);
        Text bidText = text.createTextHorizontally("$" + bid + " - ", 14.0);
        bidText.setFill(Color.WHITE);

        bidLine.getChildren().add(bidText);

        Text number = new Text();

        for (Map.Entry<int[], int[]> entry: betingOptionList.entrySet()){
            if(posX == entry.getKey()[0] && posY == entry.getKey()[1]) {
                int[] value = entry.getValue();
                for(int i=0; i < value.length; i++) {

                    int actualNumber = value[i];

                    Rectangle betRectangle = new Rectangle();
                    betRectangle.setWidth(38);
                    betRectangle.setHeight(25);
                    betRectangle.setStrokeWidth(2);
                    betRectangle.setArcWidth(15.0);
                    betRectangle.setArcHeight(15.0);
                    betRectangle.setStroke(Color.WHITE);

                    if (actualNumber == 0) {
                        betRectangle.setFill(Color.rgb(72, 156, 70));
                        number = text.createTextHorizontally("0", 12.0);

                    } else {

                        String numberColor = pn.checkColor(actualNumber);

                        if (numberColor.equals("Red")) {
                            betRectangle.setFill(Color.rgb(227, 30, 37));
                        } else {
                            betRectangle.setFill(Color.rgb(66, 66, 66));
                        }

                        number = text.createTextHorizontally(""+actualNumber, 12.0);
                    }

                    StackPane bidSp = new StackPane();
                    bidSp.getChildren().addAll(betRectangle, number);
                    bidLine.getChildren().add(bidSp);
                }
            }

        }

        userChoiceBox.getChildren().addAll(bidLine);
    }
}
