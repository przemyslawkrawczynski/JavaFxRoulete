package com.javafxroulette;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class StartPanel {

    private StackPane startPanelStackPane = new StackPane();
    private GridPane startPanel = new GridPane();
    private String userName;
    private int amount = 0;

    public StackPane createStartWindow(StackPane root, Player player) {

        startPanel.setStyle("-fx-border-color: white; -fx-background-color: rgb(66,66,66,0.95); ");
        startPanel.setPrefSize(800,550);
        startPanel.setAlignment(Pos.CENTER);
        startPanel.setVgap(4);
        startPanel.setHgap(4);


        Label userNameLabel = new Label("Username");
        userNameLabel.setAlignment(Pos.CENTER_RIGHT);
        userNameLabel.setTextFill(Color.WHITE);
        userNameLabel.setPrefSize(120,30);

        TextField userNameText = new TextField("Select Username");
        userNameText.setPrefSize(120,30);
        userNameText.setStyle( "-fx-border-color: grey; -fx-border-width: 1 1 1 1;-fx-border-radius:5; -fx-background-color: transparent; -fx-text-fill: white");

        startPanel.getChildren().addAll(userNameLabel, userNameText);
        GridPane.setConstraints(userNameLabel, 0,0,1,1);
        GridPane.setConstraints(userNameText, 1,0,1,1);

        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.WHITE);
        errorLabel.setPrefSize(200,30);

        GridPane.setConstraints(errorLabel, 1,4,1,2);


        Label chooseAmount = new Label("Select amount");
        chooseAmount.setAlignment(Pos.CENTER_RIGHT);
        chooseAmount.setTextFill(Color.WHITE);
        chooseAmount.setPrefSize(120,30);

        ChoiceBox insertAmount = new ChoiceBox();
        insertAmount.getItems().addAll("1000", "2000","5000", "10000");
        insertAmount.setStyle( "-fx-border-color: grey; -fx-border-width: 1 1 1 1;-fx-border-radius:5; -fx-background-color: transparent; -fx-text-fill: white");
        insertAmount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.setAmount(Integer.parseInt(insertAmount.getValue().toString()));
                System.out.println(player.getUserAmount());
            }
        });

        startPanel.getChildren().addAll(chooseAmount,insertAmount,errorLabel);
        GridPane.setConstraints(chooseAmount, 0,1,1,1);
        GridPane.setConstraints(insertAmount, 1,1,1,1);


        Button startButton = new Button("Star Game");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userName = userNameText.getText();
                if (!userName.equals("Select Username")) {
                    System.out.println(userNameText.getText());
                    root.getChildren().removeAll(startPanelStackPane);
                    player.setUserName(userName);
                    System.out.println(player.getUserAmount());
                } else {
                    errorLabel.setText("Please insert Username.");
                }
            }
        });


        startPanel.getChildren().add(startButton);
        GridPane.setConstraints(startButton, 1,2);

        startPanelStackPane.getChildren().addAll(startPanel);

        return startPanelStackPane;
    }
}
