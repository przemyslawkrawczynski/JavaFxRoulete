package com.javafxroulette.roulette;

import com.javafxroulette.allshapes.PlayBoard;
import com.javafxroulette.allshapes.TextInRectangle;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;


public class Roulette extends Application {
    public int drawNumber = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image bgImage = new Image("file:D:\\javafx-roulette\\src\\main\\resources\\templates\\scene.jpg");
        Image rouletteImg = new Image("file:D:\\javafx-roulette\\src\\main\\resources\\templates\\kolo.png");
        Image lgView = new Image("file:D:\\javafx-roulette\\src\\main\\resources\\templates\\roulete.png");

        // Seting global Integer. We will use him in engine play.

        TextInRectangle tr = new TextInRectangle();

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        // VBox right from TOP <roulete img/ PlayBoard (Grid) / User Stering>

        VBox vboxRight = new VBox();
        vboxRight.setAlignment(Pos.TOP_RIGHT);
        vboxRight.setSpacing(50);

        ImageView logoView = new ImageView(lgView);

        GridPane grid = (new PlayBoard()).createGrid();

        HBox down = new HBox();
        down.setAlignment(Pos.CENTER);
        down.setSpacing(50);

        VBox downLeft = new VBox();
        Text lastNumber = tr.createTextHorizontally("Last number:", 21.0);

        Rectangle historyBox = new Rectangle(250, 75);
        historyBox.setFill(Color.rgb(66, 66, 66, 0.65));
        historyBox.setStroke(Color.rgb(218, 125, 19));
        historyBox.setStrokeWidth(1.0);

        downLeft.getChildren().addAll(lastNumber, historyBox);

        Rectangle userChoicePlace = new Rectangle(400, 100);
        userChoicePlace.setFill(Color.rgb(218, 149, 19, 0.55));
        userChoicePlace.setStroke(Color.rgb(218, 125, 19));
        userChoicePlace.setStrokeWidth(1.0);

        down.getChildren().addAll(downLeft, userChoicePlace);

        vboxRight.getChildren().addAll(logoView, grid, down);

        // Here starts left side od general Scene
        // VBox Left from the top Roulette Circle <Stack Pane>, History Box, Button

        VBox vBoxLeft = new VBox();
        vBoxLeft.setSpacing(25);
        vBoxLeft.setAlignment(Pos.CENTER);

        // Top - Left Scene
        StackPane top = new StackPane();

        ImageView rouletteView = new ImageView(rouletteImg);

        Circle numberBox = new Circle(70);
        numberBox.setFill(Color.rgb(66, 66, 66));
        numberBox.setStroke(Color.rgb(218, 125, 19));
        numberBox.setStrokeWidth(3.0);

        Text numberText = tr.createTextHorizontally("33", 45.0);

        top.getChildren().addAll(rouletteView, numberBox, numberText);

        Button playButton = new Button("PLAY");
        playButton.setPrefSize(250, 50);
        playButton.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.BOLD, 35));
        playButton.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-padding: 10 20 10 20;\n" +
                "    -fx-border-color: WHITE;\n" +
                "    -fx-border-width: 2px;\n");


        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Random random = new Random();
                numberText.setText((""));

                LocalTime time = LocalTime.now();
                LocalTime timeAfter = time.plusSeconds(2);
                LocalTime checkedTime = time.plusNanos(250000000);

                while(time.isBefore(timeAfter)) {
                    time = LocalTime.now();
                    if (time.equals(checkedTime)) {
                        int number = random.nextInt(37);
                        numberText.setText(("" + number));
                        checkedTime = checkedTime.plusNanos(200000000);
                        drawNumber = number;
                    }
                }

                RotateTransition tr = new RotateTransition();
                tr.setByAngle(360);
                tr.setDuration(Duration.seconds(3));
                tr.setRate(2);
                tr.setNode(rouletteView);
                // tr.setCycleCount(3);

                tr.play();

            }
        });



        // Adding all elements of VBoxLeft

        vBoxLeft.getChildren().addAll(top, playButton);

        HBox root = new HBox();
        root.getChildren().addAll(vBoxLeft, vboxRight);
        root.setBackground(background);
        root.setAlignment(Pos.CENTER);


        Scene scene = new Scene(root, 1400, 700, Color.WHITE);

        primaryStage.setTitle("Roulette");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
