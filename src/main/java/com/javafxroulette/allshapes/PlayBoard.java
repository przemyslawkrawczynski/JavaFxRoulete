package com.javafxroulette.allshapes;


import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PlayBoard {
 //   private Image bgImage = new Image("file:D:\\javafx-roulette\\src\\main\\resources\\templates\\scene.jpg");

    private List<Rectangle> rectangles = new ArrayList();

    public GridPane createGrid() {

        GridPane grid = new GridPane();
        grid.setHgap(3);
        grid.setVgap(3);

        PlayRectangles pr = new PlayRectangles();
        TextInRectangle tr = new TextInRectangle();

        // Creating 36 Rectangles in correct colors, adding them to the Rectangle List

        for (int i=1;i<37;i++) {
            Rectangle rectangle = null;

            if(i== 2 || i==4 || i==6 || i==8 || i==10 || i==11 || i==13 || i==15 || i==17 || i== 20 || i==22 || i==24 || i==26 || i==28 || i==29 || i==33 || i==31 || i==35 ) {
                rectangle = pr.createRectangle(56.0,70.0,15.0,"Black");
                rectangles.add(rectangle);

            } else {
                rectangle = pr.createRectangle(56.0,70.0,15.0,"Red");
                rectangles.add(rectangle);
            }
        }

        // Adding first column, and insert number 0 position

        StackPane zeroGroup = new StackPane();

        Text zeroText = new Text("0");
        zeroText.setFill(Color.WHITE);
        zeroText.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.BOLD, 40));
        zeroText.setRotate(270);

        Rectangle zeroRectangle = pr.createRectangle(56.0,219.0,15.0,"Green");

        zeroGroup.getChildren().addAll(zeroRectangle, zeroText);



        grid.getChildren().add(zeroGroup);
        GridPane.setConstraints(zeroGroup, 0, 0,1,3);

        // Set correct positions for rectangles
        for (int i = 0; i < 3; i++) {
            StackPane sp = null;
            int startRectangle;
            if (i == 0) {
                startRectangle = 3;
            } else if (i == 1) {
                startRectangle = 2;
            } else {
                startRectangle = 1;
            }

            for (int j = 0; j < 12; j++) {

                sp = new StackPane();
                Text textFill = new Text();

                textFill.setText(Integer.toString(startRectangle));
                textFill.setFill(Color.WHITE);
                textFill.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.BOLD, 38));
                textFill.setRotate(270);

                Rectangle actRectangle = rectangles.get(startRectangle-1);

                sp.getChildren().addAll(actRectangle,textFill);
                grid.getChildren().add(sp);
                GridPane.setConstraints(sp,j+1,i);

                startRectangle = startRectangle + 3;
            }
        }

        // Adding (2:1) Bet position i last column

        StackPane bet21FR = new StackPane();
        StackPane bet21Sec = new StackPane();
        StackPane bet21Thr = new StackPane();

        Text bet21Text1 = tr.createText("2 TO 1", 20.0);
        Text bet21Text2 = tr.createText("2 TO 1", 20.0);
        Text bet21Text3 = tr.createText("2 TO 1", 20.0);

        Rectangle bet21firstRow = pr.createRectangle(56.0,70.0,15.0,"Green");
        bet21FR.getChildren().addAll(bet21firstRow, bet21Text1);

        Rectangle bet21secondRow = pr.createRectangle(56.0,70.0,15.0,"Green");
        bet21Sec.getChildren().addAll(bet21secondRow, bet21Text2);

        Rectangle bet21thirdRow = pr.createRectangle(56.0,70.0,15.0,"Green");
        bet21Thr.getChildren().addAll(bet21thirdRow, bet21Text3);

        grid.getChildren().addAll(bet21FR, bet21Sec, bet21Thr);
        GridPane.setConstraints(bet21FR,13,0);
        GridPane.setConstraints(bet21Sec,13,1);
        GridPane.setConstraints(bet21Thr,13,2);

        // Adding 1st 12 / 2nd 12 / 3rd 12 - Bet position i 4 row

        StackPane bet1st = new StackPane();
        StackPane bet2nd = new StackPane();
        StackPane bet3rd = new StackPane();

        Text bet1stText = tr.createTextHorizontally("1st 12", 38.0);
        Text bet2ndText = tr.createTextHorizontally("2nd 12", 38.0);
        Text bet3rdText = tr.createTextHorizontally("3rd 12", 38.0);


        Rectangle bet1st12 = pr.createRectangle(242.0,70.0,15.0,"Green");
        Rectangle bet2nd12 = pr.createRectangle(242.0,70.0,15.0,"Green");
        Rectangle bet3rd12 = pr.createRectangle(242.0,70.0,15.0,"Green");

        bet1st.getChildren().addAll(bet1st12, bet1stText);
        bet2nd.getChildren().addAll(bet2nd12, bet2ndText);
        bet3rd.getChildren().addAll(bet3rd12, bet3rdText);

        grid.getChildren().addAll(bet1st, bet2nd, bet3rd);
        GridPane.setConstraints(bet1st, 1, 3,4,1);
        GridPane.setConstraints(bet2nd, 5, 3,4,1);
        GridPane.setConstraints(bet3rd, 9, 3,4,1);

        // Adding Bets (1to18, Even, Red, Black, Odd, 19to39) position - 5 row

        StackPane bet1to18Sp = new StackPane();
        StackPane betEvenSp = new StackPane();
        StackPane betRedSp = new StackPane();
        StackPane betBlackSp = new StackPane();
        StackPane betOddSp = new StackPane();
        StackPane bet19to36Sp = new StackPane();

        Text bet1to18Text = tr.createTextHorizontally("1 to 18", 30.0);
        Text betEvenText = tr.createTextHorizontally("EVEN", 30.0);
        Text betRedText = tr.createTextHorizontally("Red", 30.0);
        Text betBlackText = tr.createTextHorizontally("Black", 30.0);
        Text betOddText = tr.createTextHorizontally("ODD", 30.0);
        Text bet19to36Text = tr.createTextHorizontally("19 to 36", 30.0);

        Rectangle bet1to18 = pr.createRectangle(118.0,70.0,15.0,"Green");
        Rectangle betEven = pr.createRectangle(118.0,70.0,15.0,"Green");
        Rectangle betRed = pr.createRectangle(118.0,70.0,15.0,"Red");
        Rectangle betBlack = pr.createRectangle(118.0,70.0,15.0,"Black");
        Rectangle betOdd = pr.createRectangle(118.0,70.0,15.0,"Green");
        Rectangle bet19to36 = pr.createRectangle(118.0,70.0,15.0,"Green");

        bet1to18Sp.getChildren().addAll(bet1to18, bet1to18Text);
        betEvenSp.getChildren().addAll(betEven, betEvenText);
        betRedSp.getChildren().addAll(betRed, betRedText);
        betBlackSp.getChildren().addAll(betBlack, betBlackText);
        betOddSp.getChildren().addAll(betOdd, betOddText);
        bet19to36Sp.getChildren().addAll(bet19to36, bet19to36Text);


        grid.getChildren().addAll(bet1to18Sp, betEvenSp, betRedSp, betBlackSp, betOddSp, bet19to36Sp);
        GridPane.setConstraints(bet1to18Sp, 1, 4,2,1);
        GridPane.setConstraints(betEvenSp, 3, 4,2,1);
        GridPane.setConstraints(betRedSp, 5, 4,2,1);
        GridPane.setConstraints(betBlackSp, 7, 4,2,1);
        GridPane.setConstraints(betOddSp, 9, 4,2,1);
        GridPane.setConstraints(bet19to36Sp, 11, 4,2,1);



        grid.setAlignment(Pos.CENTER);
     //   grid.setPadding(new Insets(5));
       // grid.setGridLinesVisible(true);
  //      grid.setBackground(background);
        return grid;
    }
}

