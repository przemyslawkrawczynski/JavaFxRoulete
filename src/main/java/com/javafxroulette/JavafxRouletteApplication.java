package com.javafxroulette;

import com.javafxroulette.allshapes.PlayRectangles;
import com.javafxroulette.allshapes.StackPanePlayRectangle;
import com.javafxroulette.allshapes.TextInRectangle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.time.LocalTime;
import java.util.Random;

public class JavafxRouletteApplication extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Text");
        VBox root = new VBox();

        root.setSpacing(15.0);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 250, 250, Color.WHITE);

        TextInRectangle txt = new TextInRectangle();
        PlayRectangles pr = new PlayRectangles();
        StackPanePlayRectangle stackPanePlayRectangle = new StackPanePlayRectangle();

        Text t1 = txt.createTextHorizontally("Testujemy", 15.0);
        Rectangle r1 = pr.createRectangle(250.0, 50.0, 15.0, "Black");
        StackPane sp = stackPanePlayRectangle.createStackPaneBlack("1", r1);

        sp.getChildren().addAll(r1, t1);

        Rectangle r2 = pr.createRectangle(250.0, 50.0, 15.0, "Black");

        Button playButton = new Button("PLAY");
        playButton.setPrefSize(220, 25);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                r1.setFill(Color.rgb(72, 156, 70));
            }
        });


        root.getChildren().addAll(sp, r2, playButton);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

