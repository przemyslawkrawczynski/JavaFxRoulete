package com.javafxroulette.roulette;

import com.javafxroulette.allshapes.PlayBoard;
import com.javafxroulette.allshapes.TextInRectangle;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Roulette extends Application {
    private int drawNumber = 0;
    private ArrayList<Integer> lastDrawnNumbers = new ArrayList();
    private HashMap<Integer,String> numberCorrectColorList = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image bgImage = new Image("file:D:\\javafx-roulette\\src\\main\\resources\\templates\\scene.jpg");
        Image rouletteImg = new Image("file:D:\\javafx-roulette\\src\\main\\resources\\templates\\kolo.png");
        Image bet1dolar = new Image("file:D:\\javafx-roulette\\src\\main\\resources\\templates\\1dolar.png");
        Image bet5dolar = new Image("file:D:\\javafx-roulette\\src\\main\\resources\\templates\\5dolar.png");
        Image lgView = new Image("file:D:\\javafx-roulette\\src\\main\\resources\\templates\\roulete.png");

        // Setting global Integer. We will use him in engine play.

        TextInRectangle tr = new TextInRectangle();
        HistoryBoxView historyBox = new HistoryBoxView();

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        // VBox right from TOP <roulete img/ PlayBoard (Grid) / User Stering>

        VBox vboxRight = new VBox();
        vboxRight.setAlignment(Pos.TOP_RIGHT);
        vboxRight.setSpacing(50);

        ImageView logoView = new ImageView(lgView);

        PlayBoard pb = new PlayBoard();
        GridPane grid = pb.createGrid();

        // Define list Red and Black Numbers
        numberCorrectColorList = pb.getNumberColorList();

        HBox down = new HBox();
        down.setAlignment(Pos.CENTER);

        ImageView bet1 = new ImageView(bet1dolar);
        ImageView bet5 = new ImageView(bet5dolar);


        Rectangle userChoicePlace = new Rectangle(400, 100);
        userChoicePlace.setFill(Color.rgb(218, 149, 19, 0.55));
        userChoicePlace.setStroke(Color.rgb(218, 125, 19));
        userChoicePlace.setStrokeWidth(1.0);

        down.getChildren().addAll(bet1, bet5, userChoicePlace);

        vboxRight.getChildren().addAll(logoView, grid, down);

        // Here starts left side od general Scene
        // VBox Left from the top Roulette Circle <Stack Pane>, History Box, Button

        VBox vBoxLeft = new VBox();
        vBoxLeft.setSpacing(15);
        vBoxLeft.setAlignment(Pos.CENTER);


        // Top - Left Scene

        Text lastNumber = tr.createTextHorizontally("Last numbers", 18.0);

        HBox LNBox = new HBox();
        LNBox.setAlignment(Pos.TOP_CENTER);
        LNBox.setSpacing(2);
        LNBox.setPrefSize(282, 50);

        StackPane top = new StackPane();

        ImageView rouletteView = new ImageView(rouletteImg);

        Circle numberBox = new Circle(60);
        numberBox.setFill(Color.rgb(66, 66, 66));
        numberBox.setStroke(Color.rgb(218, 125, 19));
        numberBox.setStrokeWidth(3.0);

        Text numberText = tr.createTextHorizontally("Start!", 32.0);

        top.getChildren().addAll(rouletteView, numberBox, numberText);

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

                }
        });



        // Adding all elements of VBoxLeft

        vBoxLeft.getChildren().addAll(lastNumber, LNBox, top, playButton);

        HBox root = new HBox();
        root.getChildren().addAll(vBoxLeft, vboxRight);
        root.setBackground(background);
        root.setAlignment(Pos.CENTER);


        Scene scene = new Scene(root, 1400, 700, Color.WHITE);
        scene.getStylesheets().add("RouletteCss.css");

        primaryStage.setTitle("Roulette");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
