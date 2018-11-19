package com.javafxroulette.allshapes;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class StackPanePlayRectangle {

    public StackPane createStackPaneRed(String id, Rectangle rectangle) {
        StackPane stackPane = new StackPane();
        stackPane.setId(id);

        stackPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setFill(Color.rgb(218, 125, 19));
            }
        });

        stackPane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setFill(Color.rgb(227, 30, 37));
            }
        });

        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setFill(Color.rgb(218, 125, 19));
                stackPane.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        rectangle.setFill(Color.rgb(218, 125, 19));
                    }
                });
            }
        });
        return stackPane;
    }

    public StackPane createStackPaneBlack(String id, Rectangle rectangle) {
        StackPane stackPane = new StackPane();

        stackPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setFill(Color.rgb(218, 125, 19));
            }
        });

        stackPane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setFill(Color.rgb(66,66,66));
            }
        });
        return stackPane;
    }

    public StackPane createStackPaneGreen(String id, Rectangle rectangle) {
        StackPane stackPane = new StackPane();

        stackPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setFill(Color.rgb(218, 125, 19));
            }
        });
        stackPane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setFill(Color.rgb(72,156,70));
            }
        });
        return stackPane;
    }
}
