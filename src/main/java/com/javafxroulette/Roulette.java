package com.javafxroulette;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Roulette extends Application {

    private int drawNumber = 0;
    private ArrayList<Integer> lastDrawnNumbers = new ArrayList();
    private int userAmount;
    private String userName;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) {
        Image bgImage = new Image("templates/scene2.jpg");
        Image rouletteImg = new Image("templates/kolo.png");
        Image lgView = new Image("templates/roulete.png");

        // Implements Class
        PlayRectangles pr = new PlayRectangles();
        StackPanePlayRectangle sp = new StackPanePlayRectangle();
        TextInRectangle tr = new TextInRectangle();
        Chip chip = new Chip();
        PlayNumbers pn = new PlayNumbers();
        pn.createNumbers();
        HistoryBoxView historyBox = new HistoryBoxView();
        Beting betList = new Beting();
        betList.setPlayNumbers(pn);

        // Background image
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        VBox vboxRight = new VBox();
        vboxRight.setAlignment(Pos.TOP_RIGHT);
        vboxRight.setSpacing(10);

        // Top Left Box
        HBox upLeft = new HBox();
        upLeft.setPadding(new Insets(15,15,15,15));
        upLeft.setAlignment(Pos.BOTTOM_RIGHT);

        ImageView logoView = new ImageView(lgView);

        // Bid Box
        HBox bidHb = new HBox();
        bidHb.setId("amount");
        bidHb.setMaxHeight(10);
        bidHb.setPrefWidth(350);
        bidHb.setAlignment(Pos.CENTER);
        Text bidEticket = tr.createTextHorizontally("", 18.0);
        bidEticket.setStyle("-fx-effect: dropshadow( one-pass-box , black , 0, 0.0 , 0 , -1 );");
        Text bidValue = tr.createTextHorizontally("- Choose Bid -", 18.0);
        bidValue.setStyle("-fx-effect: dropshadow( one-pass-box , black , 0, 0.0 , 0 , -1 );");
        bidHb.getChildren().addAll(bidEticket, bidValue);

        //Amount Box
        Player player = new Player();
        userAmount = player.getUserAmount();
        HBox amountHb = new HBox();
        amountHb.setId("amount");
        amountHb.setMaxHeight(10);
        Text amountEticket = tr.createTextHorizontally("Amount   $", 18.0);
        amountEticket.setStyle("-fx-effect: dropshadow( one-pass-box , black , 0, 0.0 , 0 , -1 );");
        Text amount = tr.createTextHorizontally("" + userAmount, 18.0);
        amount.setStyle("-fx-effect: dropshadow( one-pass-box , black , 0, 0.0 , 0 , -1 );");
        player.setUserAmountText(amount);

        HBox uNameHb = new HBox();
        uNameHb.setId("amount");
        uNameHb.setMaxHeight(10);

        Text uName = tr.createTextHorizontally("Username", 18.0);
        uName.setStyle("-fx-effect: dropshadow( one-pass-box , black , 0, 0.0 , 0 , -1 );");
        player.setUserNameText(uName);

        uNameHb.getChildren().add(uName);
        amountHb.getChildren().addAll(amountEticket, amount);
        upLeft.getChildren().addAll(uNameHb, bidHb, amountHb);

        PlayBoard pb = new PlayBoard();
        GridPane grid = pb.createGrid(betList);

        HBox down = new HBox();
        down.setSpacing(10);

        // Define chip place and Buttons
        HBox downLeft = new HBox();
        downLeft.setStyle("-fx-border-color: white ; -fx-background-color: rgb(66,66,66,0.75); ");
        downLeft.setSpacing(5);
        downLeft.setPadding(new Insets(10,10,10,10));
        downLeft.setAlignment(Pos.TOP_CENTER);

        VBox betingButtons = new VBox();
        betingButtons.setSpacing(5);
        betingButtons.setAlignment(Pos.CENTER);

        Button clearButton = new Button();
        clearButton.setId("button-bet");
        clearButton.setText("Clear bet");
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                betList.clearBetList();
                vboxRight.getChildren().remove(2);
                vboxRight.getChildren().add(2, pb.createGrid(betList));
            }
        });

        Button lastBetButton = new Button();
        lastBetButton.setId("button-bet");
        lastBetButton.setText("Last Bet");

        Button someBetButton = new Button();
        someBetButton.setId("button-bet");
        someBetButton.setText("Some Bet");

        betingButtons.getChildren().addAll(clearButton, lastBetButton, someBetButton);

        HBox chipAll = new HBox();
        chipAll.setSpacing(4);

        StackPane chip1Dolar = chip.createChip("1", Color.rgb(66,66,66));
        chip1Dolar.setOnMouseClicked(e-> betList.addBid(1, bidValue));

        StackPane chip5Dolar = chip.createChip("5", Color.rgb(189,14,6));
        chip5Dolar.setOnMouseClicked(e-> betList.addBid(5, bidValue));

        StackPane chip25Dolar = chip.createChip("25", Color.rgb(0,110,34));
        chip25Dolar.setOnMouseClicked(e-> betList.addBid(25, bidValue));

        StackPane chip50Dolar = chip.createChip("50", Color.rgb(21,82,221));
        chip50Dolar.setOnMouseClicked(e-> betList.addBid(50, bidValue));


        chipAll.getChildren().addAll(chip1Dolar,new Separator(Orientation.VERTICAL),chip5Dolar,new Separator(Orientation.VERTICAL), chip25Dolar,new Separator(Orientation.VERTICAL), chip50Dolar,new Separator(Orientation.VERTICAL));
        downLeft.getChildren().addAll(chipAll,betingButtons);

        // Place where we will show user Bets

//        VBox userChoice = new VBox();
//        userChoice.setSpacing(3);
//        betList.setUserChoiceBox(userChoice);
//        Text actualBetText = tr.createTextHorizontally("Hello!", 16.0);
//        player.setUserNameText(actualBetText);
//        actualBetText.setFill(Color.WHITE);
//        userChoice.getChildren().addAll(actualBetText);


        down.getChildren().addAll(downLeft);
        vboxRight.getChildren().addAll(logoView, upLeft, grid, down);

        // Here starts left side od general Scene
        // VBox Left from the top Roulette Circle <Stack Pane>, History Box, Button

        VBox vBoxLeft = new VBox();

        vBoxLeft.setSpacing(15);
        vBoxLeft.setAlignment(Pos.CENTER);

        // Top - Left Scene
        VBox lnVbox = new VBox();

        Text lastNumber = tr.createTextHorizontally("Last numbers", 18.0);
        HBox LNBox = new HBox();
        LNBox.setAlignment(Pos.TOP_CENTER);
        LNBox.setSpacing(2);
        LNBox.setStyle("-fx-border-color: white; -fx-background-color: rgb(66,66,66,0.55); ");

        LNBox.setPrefSize(282, 50);
        lnVbox.getChildren().addAll(lastNumber, LNBox);

        StackPane top = new StackPane();
        ImageView rouletteView = new ImageView(rouletteImg);

        Circle numberBox = new Circle(60);
        numberBox.setFill(Color.rgb(66, 66, 66));
        numberBox.setStroke(Color.rgb(218, 125, 19));
        numberBox.setStrokeWidth(3.0);

        Text numberText = tr.createTextHorizontally("Start!", 32.0);
        top.getChildren().addAll(rouletteView, numberBox, numberText);

        // Creating object necessary to game

        GameEngine game = new GameEngine(amount, player, betList);

        Button playButton = new Button("SPIN");
        playButton.setPrefSize(250, 50);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                numberText.setText((""));

                int[] drawNumbers = new int[5];
                for (int i=0; i<5; i++) {
                    Random random = new Random();
                    drawNumber = random.nextInt(37);
                    drawNumbers[i] = drawNumber;
                }

                lastDrawnNumbers.add(drawNumber);

                Thread spin = new SpinRoulette(numberText, drawNumbers);
                spin.start();

                RotateTransition tr = new RotateTransition();
                tr.setByAngle(360);
                tr.setDuration(Duration.seconds(3));
                tr.setRate(2);
                tr.setNode(rouletteView);
                tr.play();

                historyBox.historyBoxChangeValue(lastDrawnNumbers, LNBox, pn) ;
                game.play(drawNumber);

                vboxRight.getChildren().remove(2);
                vboxRight.getChildren().add(2, pb.createGrid(betList));
            }
        });

        // Adding all elements of VBoxLeft
        vBoxLeft.getChildren().addAll(lnVbox, top, playButton);

        StackPane rootSP = new StackPane();
        HBox root = new HBox();
        root.setSpacing(15);
        root.getChildren().addAll(vBoxLeft, vboxRight);
        root.setBackground(background);
        root.setAlignment(Pos.CENTER);

        // First time window user name / Scores

        StartPanel startPanel = new StartPanel();
        StackPane startPanelSP = startPanel.createStartWindow(rootSP, player);

        rootSP.getChildren().addAll(root, startPanelSP);

        Scene scene = new Scene(rootSP, 1400, 700, Color.WHITE);
        scene.getStylesheets().add("RouletteCss.css");

        primaryStage.setTitle("Roulette");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}