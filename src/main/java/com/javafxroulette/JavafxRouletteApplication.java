package com.javafxroulette;

import com.javafxroulette.allshapes.PlayRectangles;
import com.javafxroulette.allshapes.StackPanePlayRectangle;
import com.javafxroulette.allshapes.TextInRectangle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class JavafxRouletteApplication extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Text");
        VBox root = new VBox();
        root.setSpacing(15.0);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 350, 250);

        PlayRectangles pr = new PlayRectangles();
        StackPanePlayRectangle sp = new StackPanePlayRectangle();
        TextInRectangle tr = new TextInRectangle();
        HashMap<String, StackPane> spList = new HashMap<>();


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Rectangle[][] rectangleArray = new Rectangle[3][3];

        for(int i=0; i<3; i++) {
            for(int j=0;j<3;j++) {
                if (i != 1 && j != 1) {
                    Rectangle r = pr.createRectangle(76.0, 50.0, 15.0, "Red");
                    StackPane s = new StackPane();
                    spList.put(""+i+j, s);
                    Text text = tr.createTextHorizontally("" + j, 26.0);
                    s.getChildren().addAll(r, text);

                    rectangleArray[i][j] = r;

                    grid.getChildren().add(s);
                    GridPane.setConstraints(s, i, j, 1, 1);
                } else {
                    StackPane s = new StackPane();
                    spList.put(""+i+j, s);
                    Circle c = new Circle(2);
                    c.setFill(Color.rgb(218, 149, 19));

                    s.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            c.setFill(Color.RED);
                        }
                    });
                    s.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            c.setFill(Color.rgb(218, 149, 19));
                        }
                    });


                    s.getChildren().add(c);
                    grid.getChildren().add(s);
                    GridPane.setConstraints(s, i, j, 1, 1);
                }
            }
        }

        StackPane stackPane = spList.get("10");
        stackPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Rectangle rec1 = rectangleArray[0][0];
                Rectangle rec2 = rectangleArray[2][0];

                rec1.setFill(Color.BLUE);
                rec2.setFill(Color.BLUE);
            }
        });

        stackPane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Rectangle rec1 = rectangleArray[0][0];
                Rectangle rec2 = rectangleArray[2][0];

                rec1.setFill(Color.rgb(227, 30, 37));
                rec2.setFill(Color.rgb(227, 30, 37));
            }
        });


        Button playButton = new Button("PLAY");
        playButton.setPrefSize(250, 25);

        root.getChildren().addAll(grid,playButton);

        scene.getStylesheets().add("RouletteCss.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

