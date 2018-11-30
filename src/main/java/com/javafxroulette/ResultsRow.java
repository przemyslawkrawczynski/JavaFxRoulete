package com.javafxroulette;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ResultsRow {

    public VBox createResultTable() {
        VBox resultTable = new VBox();
        // Title HBox
        HBox columnName = new HBox();

        StackPane placeSp = new StackPane();
        StackPane userNameSp = new StackPane();
        StackPane userResult = new StackPane();

        Rectangle placeR = new Rectangle(50, 25, Color.rgb(218, 125, 19,0.95));
        Rectangle userNameR = new Rectangle(150, 25, Color.rgb(218, 125, 19,0.95));
        Rectangle userResultR = new Rectangle(150, 25, Color.rgb(218, 125, 19,0.95));

        Text placeText = new Text("Place");
        placeText.setFill(Color.WHITE);
        Text userNameText = new Text("Username");
        userNameText.setFill(Color.WHITE);
        Text userResultText = new Text("Result");
        userResultText.setFill(Color.WHITE);

        placeSp.getChildren().addAll(placeR,placeText);
        userNameSp.getChildren().addAll(userNameR,userNameText);
        userResult.getChildren().addAll(userResultR,userResultText);
        columnName.getChildren().addAll(placeSp,userNameSp,userResult);
        resultTable.getChildren().addAll(columnName);

        for(int i=0;i<10;i++) {
            HBox column = new HBox();

            StackPane spPlac = new StackPane();
            StackPane spUser = new StackPane();
            StackPane spResult = new StackPane();

            Color color;

            if (i % 2 == 0) {
                color = Color.GRAY;
            } else {
                color = Color.rgb(52, 52, 52,0.95);
            }

            Rectangle rPlace = new Rectangle(50, 25, color);
            Rectangle rUser = new Rectangle(150, 25, color);
            Rectangle rResult = new Rectangle(150, 25, color);

            Text placeT = new Text("1");
            placeT.setFill(Color.WHITE);
            Text userNameT = new Text("John");
            userNameT.setFill(Color.WHITE);
            Text userResultT = new Text("$ 52000");
            userResultT.setFill(Color.WHITE);

            spPlac.getChildren().addAll(rPlace,placeT);
            spUser.getChildren().addAll(rUser,userNameT);
            spResult.getChildren().addAll(rResult,userResultT);

            column.getChildren().addAll(spPlac,spUser,spResult);
            resultTable.getChildren().add(column);
        }

        return resultTable;
    }

}
