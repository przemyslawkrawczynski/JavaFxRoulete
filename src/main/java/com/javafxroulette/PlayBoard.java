package com.javafxroulette;


import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayBoard {

    private ArrayList<Rectangle> rectangles = new ArrayList();
    private List<StackPane> stackPanesList = new ArrayList();
    // Creating List with information about numbers [number]  | [position kolumn] | [position row] | [1 (Red), 2 (Black), 0 (Green)]

    public GridPane createGrid(Beting betingList) {

        GridPane grid = new GridPane();

        PlayRectangles pr = new PlayRectangles();
        TextInRectangle tr = new TextInRectangle();
        StackPanePlayRectangle sp = new StackPanePlayRectangle();
        PlayNumbers pn = new PlayNumbers();
        pn.createNumbers();

        BetOption betOption = new BetOption(pn);
        betOption.generateBetOption();
        HashMap<int[], int[]> betingOptionList = betOption.getBetOptionList();
        ArrayList<int[]> numberInformationList = pn.getNumberInformationList();

        Rectangle[][] rectangleArray = new Rectangle[24][5];
        StackPane[][] stackPaneArray = new StackPane[24][5];


        // Adding first column, and insert number 0 position

        Text zeroText = new Text("0");
        zeroText.setFill(Color.WHITE);
        zeroText.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.BOLD, 40));
        zeroText.setRotate(270);

        Rectangle zeroRectangle = pr.createRectangle(56.0,223.0,15.0,"Green");
        zeroRectangle.setId("0");
        StackPane zeroGroup = sp.createStackPaneGreen("0", zeroRectangle);
        zeroGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (betingList.isUserChooseBid()) {
                    zeroRectangle.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(0,0);
                } else {
                    System.out.println("Wybierz stawkę..");
                }
            }
        });

        zeroGroup.getChildren().addAll(zeroRectangle, zeroText);


        grid.getChildren().add(zeroGroup);
        GridPane.setConstraints(zeroGroup, 0, 0,1,5);

        // Creating numbers rectangle, adding them to correct positions

        for (int i = 1; i < 24; i++) {
            for (int j = 0; j < 5; j++)
                if ((i % 2 != 0 ) && (j == 0 || j % 2 == 0)) {
                    int rectangleColumn = i;
                    int rectangleRow = j;

                    int actualNumber;
                    int[] checkedNumber;

                    for (int k = 0; k < numberInformationList.size(); k++) {

                        checkedNumber = numberInformationList.get(k);
                        if (rectangleColumn == checkedNumber[2] && rectangleRow == checkedNumber[3]) {
                            actualNumber = checkedNumber[0];

                            //Color
                            String color = pn.checkColor(actualNumber);

                            Rectangle r = pr.createRectangle(56.0, 70.0, 15.0, color);
                            StackPane s;

                            if (color.equals("Red")) {
                                s = sp.createStackPaneRed(Integer.toString(actualNumber), r);
                            } else {
                                s = sp.createStackPaneBlack(Integer.toString(actualNumber), r);
                            }

                            Text text = new Text();
                            text.setText(Integer.toString(actualNumber));
                            text.setFill(Color.WHITE);
                            text.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.BOLD, 38));
                            text.setRotate(270);

                            s.getChildren().addAll(r, text);

                            rectangleArray[i][j] = r;
                            stackPaneArray[i][j] = s;

                            int xPos = checkedNumber[2];
                            int yPos = checkedNumber[3];

                            s.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    if (betingList.isUserChooseBid()) {
                                        r.setStroke(Color.rgb(218, 125, 19));
                                        betingList.setBet(xPos,yPos);
                                    } else {
                                        System.out.println("Wybierz stawkę..");
                                    }
                                }
                            });

                            grid.getChildren().add(s);
                            GridPane.setConstraints(s, i, j, 1, 1);
                        }
                    }
                } else {
                    StackPane s = new StackPane();
                    Circle c = new Circle(1);

                    stackPaneArray[i][j] = s;
                    s.setId("F:" + i +j);
                    c.setFill(Color.rgb(218, 149, 19));

                    s.getChildren().add(c);
                    grid.getChildren().add(s);
                    GridPane.setConstraints(s, i, j, 1, 1);
                }
        }

// Setting EventHandler method od Mouse Exited, Entered, Clicked

        for (int i = 1; i < 24; i++) {
            for (int j = 0; j < 5; j++) {
                if ((j == 0 || j == 4 || j == 2) && (i % 2 == 0)) {
                    Rectangle rectangleLeft = rectangleArray[i - 1][j];
                    Rectangle rectangleRight = rectangleArray[i + 1][j];

                    StackPane s = stackPaneArray[i][j];

                    String numberLeft = stackPaneArray[i - 1][j].getId();
                    String numberRight = stackPaneArray[i + 1][j].getId();

                    // Declaration bet Value when user clicked this StackPane

                    int[] position = new int[3];
                    int[] values = new int[2];

                    position[0] = i;
                    position[1] = j;

                    // Declaration win amount
                    position[2] = 18;


                    values[0] = Integer.parseInt(numberLeft);
                    values[1] = Integer.parseInt(numberRight);

                    betingOptionList.put(position, values);

                    // Define action

                    s.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            rectangleLeft.setFill(Color.rgb(218, 125, 19));
                            rectangleRight.setFill(Color.rgb(218, 125, 19));
                        }
                    });

                    s.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (pn.checkColor(Integer.parseInt(numberLeft)).equals("Red")) {
                                rectangleLeft.setFill(Color.rgb(227, 30, 37));
                            } else {
                                rectangleLeft.setFill(Color.rgb(66,66,66));
                            }

                            if (pn.checkColor(Integer.parseInt(numberRight)).equals("Red")) {
                                rectangleRight.setFill(Color.rgb(227, 30, 37));
                            } else {
                                rectangleRight.setFill(Color.rgb(66,66,66));
                            }
                        }
                    });

                    s.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (betingList.isUserChooseBid()) {
                                rectangleLeft.setStroke(Color.rgb(218, 125, 19));
                                rectangleRight.setStroke(Color.rgb(218, 125, 19));
                                betingList.setBet(position[0],position[1]);
                            } else {
                                System.out.println("Wybierz stawke...");
                            }
                        }
                    });


                } else if (( j== 1 || j == 3) && (i % 2 != 0 )) {
                    Rectangle rectangleUp = rectangleArray[i][j - 1];
                    Rectangle rectangleDown = rectangleArray[i][j + 1];

                    StackPane s = stackPaneArray[i][j];

                    String numberUp = stackPaneArray[i][j - 1].getId();
                    String numberDown = stackPaneArray[i][j +1].getId();

                    int[] position = new int[3];
                    int[] values = new int[2];

                    position[0] = i;
                    position[1] = j;

                    // Declare win aomount
                    position[2] = 18;

                    values[0] = Integer.parseInt(numberUp);
                    values[1] = Integer.parseInt(numberDown);

                    betingOptionList.put(position, values);

                    s.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            rectangleUp.setFill(Color.rgb(126, 65, 0));
                            rectangleDown.setFill(Color.rgb(126, 65, 0));
                        }
                    });
                    s.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            if (pn.checkColor(Integer.parseInt(numberUp)).equals("Red")) {
                                rectangleUp.setFill(Color.rgb(227, 30, 37));
                            } else {
                                rectangleUp.setFill(Color.rgb(66,66,66));
                            }

                            if (pn.checkColor(Integer.parseInt(numberDown)).equals("Red")) {
                                rectangleDown.setFill(Color.rgb(227, 30, 37));
                            } else {
                                rectangleDown.setFill(Color.rgb(66,66,66));
                            }
                        }
                    });
                    s.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(betingList.isUserChooseBid()) {
                                rectangleUp.setStroke(Color.rgb(218, 125, 19));
                                rectangleDown.setStroke(Color.rgb(218, 125, 19));
                                betingList.setBet(position[0],position[1]);
                            } else {
                                System.out.println("Wybierz stawkę...");
                            }
                        }
                    });

                } else if ( (i % 2 == 0) && (j % 2 != 0)) {
                    Rectangle rectangleUpLeft = rectangleArray[i-1][j-1];
                    Rectangle rectangleUpRight = rectangleArray[i+1][j-1];
                    Rectangle rectangleDownLeft = rectangleArray[i-1][j+1];
                    Rectangle rectangleDownRigt = rectangleArray[i+1][j+1];

                    StackPane s = stackPaneArray[i][j];

                    int stackPaneUpLeft = Integer.parseInt(stackPaneArray[i-1][j-1].getId());
                    int stackPaneUpRight = Integer.parseInt(stackPaneArray[i+1][j-1].getId());
                    int stackPaneDownLeft = Integer.parseInt(stackPaneArray[i-1][j+1].getId());
                    int stackPaneDownRight = Integer.parseInt(stackPaneArray[i+1][j+1].getId());

                    // Declaration bet Value when user clicked this StackPane

                    int[] position = new int[3];
                    int[] values = new int[4];

                    position[0] = i;
                    position[1] = j;
                    position[2] = 8;

                    values[0] = stackPaneUpLeft;
                    values[2] = stackPaneUpRight;
                    values[1] = stackPaneDownLeft;
                    values[3] = stackPaneDownRight;

                    betingOptionList.put(position, values);

                    // Define action



                    s.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            rectangleUpLeft.setFill(Color.rgb(100, 5, 8));
                            rectangleUpRight.setFill(Color.rgb(100, 5, 8));
                            rectangleDownLeft.setFill(Color.rgb(100, 5, 8));
                            rectangleDownRigt.setFill(Color.rgb(100, 5, 8));
                        }
                    });

                    s.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            if(pn.checkColor(stackPaneUpLeft).equals("Red")) {
                                rectangleUpLeft.setFill(Color.rgb(227, 30, 37));
                            } else {
                                rectangleUpLeft.setFill(Color.rgb(66,66,66));
                            }

                            if(pn.checkColor(stackPaneUpRight).equals("Red")) {
                                rectangleUpRight.setFill(Color.rgb(227, 30, 37));
                            } else {
                                rectangleUpRight.setFill(Color.rgb(66,66,66));
                            }

                            if(pn.checkColor(stackPaneDownLeft).equals("Red")) {
                                rectangleDownLeft.setFill(Color.rgb(227, 30, 37));
                            } else {
                                rectangleDownLeft.setFill(Color.rgb(66,66,66));
                            }

                            if(pn.checkColor(stackPaneDownRight).equals("Red")) {
                                rectangleDownRigt.setFill(Color.rgb(227, 30, 37));
                            } else {
                                rectangleDownRigt.setFill(Color.rgb(66,66,66));
                            }

                        }
                    });

                    s.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(betingList.isUserChooseBid()) {
                                rectangleUpLeft.setStroke(Color.rgb(218, 125, 19));
                                rectangleUpRight.setStroke(Color.rgb(218, 125, 19));
                                rectangleDownLeft.setStroke(Color.rgb(218, 125, 19));
                                rectangleDownRigt.setStroke(Color.rgb(218, 125, 19));
                                betingList.setBet(position[0],position[1]);
                            } else {
                                System.out.println("Wybierz stawkę...");
                            }
                        }
                    });
                }
            }
        }

        // Adding (2:1) Bet position i last column


        Text bet21Text1 = tr.createText("2 TO 1", 20.0);
        Text bet21Text2 = tr.createText("2 TO 1", 20.0);
        Text bet21Text3 = tr.createText("2 TO 1", 20.0);

        Rectangle bet21firstRow = pr.createRectangle(56.0,70.0,15.0,"Green");
        Rectangle bet21secondRow = pr.createRectangle(56.0,70.0,15.0,"Green");
        Rectangle bet21thirdRow = pr.createRectangle(56.0,70.0,15.0,"Green");

        StackPane bet21FR = sp.createStackPaneGreen("21FR",bet21firstRow);
        StackPane bet21Sec = sp.createStackPaneGreen("21SC", bet21secondRow);
        StackPane bet21Thr = sp.createStackPaneGreen("21TH", bet21thirdRow);

        bet21FR.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(betingList.isUserChooseBid()) {
                    bet21firstRow.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(25,0);
                }

            }
        });

        bet21Sec.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(betingList.isUserChooseBid()) {
                    bet21secondRow.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(25,2);
                }
            }
        });

        bet21Thr.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(betingList.isUserChooseBid()) {
                    bet21thirdRow.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(25,4);
                }
            }
        });

        bet21FR.getChildren().addAll(bet21firstRow, bet21Text1);
        bet21Sec.getChildren().addAll(bet21secondRow, bet21Text2);
        bet21Thr.getChildren().addAll(bet21thirdRow, bet21Text3);




        grid.getChildren().addAll(bet21FR, bet21Sec, bet21Thr);

        GridPane.setConstraints(bet21FR,25,0,1,1);
        GridPane.setConstraints(bet21Sec,25,2,1,1);
        GridPane.setConstraints(bet21Thr,25,4,1,1);

        // Adding 1st 12 / 2nd 12 / 3rd 12 - Bet position i 4 row

        Text bet1stText = tr.createTextHorizontally("1st 12", 38.0);
        Text bet2ndText = tr.createTextHorizontally("2nd 12", 38.0);
        Text bet3rdText = tr.createTextHorizontally("3rd 12", 38.0);


        Rectangle bet1st12 = pr.createRectangle(242.0,70.0,15.0,"Green");
        Rectangle bet2nd12 = pr.createRectangle(242.0,70.0,15.0,"Green");
        Rectangle bet3rd12 = pr.createRectangle(242.0,70.0,15.0,"Green");


        StackPane bet1st = sp.createStackPaneGreen("1st12", bet1st12);
        StackPane bet2nd = sp.createStackPaneGreen("2nd12", bet2nd12);
        StackPane bet3rd = sp.createStackPaneGreen("3rd12", bet3rd12);

        bet1st.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(betingList.isUserChooseBid()) {
                    bet1st12.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(1,6);
                }
            }
        });

        bet2nd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(betingList.isUserChooseBid()) {
                    bet2nd12.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(9,6);
                }
            }
        });

        bet3rd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(betingList.isUserChooseBid()) {
                    bet3rd12.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(17,0);
                }
            }
        });

        bet1st.getChildren().addAll(bet1st12, bet1stText);
        bet2nd.getChildren().addAll(bet2nd12, bet2ndText);
        bet3rd.getChildren().addAll(bet3rd12, bet3rdText);

        grid.getChildren().addAll(bet1st, bet2nd, bet3rd);
        GridPane.setConstraints(bet1st, 1, 6,7,1);
        GridPane.setConstraints(bet2nd, 9, 6,7,1);
        GridPane.setConstraints(bet3rd, 17, 6,7,1);

        // Adding Bets (1to18, Even, Red, Black, Odd, 19to39) position - 5 row


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

        StackPane bet1to18Sp = sp.createStackPaneGreen("Bet1to18",bet1to18);
        bet1to18Sp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (betingList.isUserChooseBid()) {
                    bet1to18.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(1,7);
                } else {
                    System.out.println("Wybierz stawkę..");
                }
    }
        });

        StackPane betEvenSp = sp.createStackPaneGreen("BetEven", betEven);
        betEvenSp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (betingList.isUserChooseBid()) {
                    betEven.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(1,7);
                } else {
                    System.out.println("Wybierz stawkę..");
                }
            }
        });

        StackPane betRedSp = sp.createStackPaneRed("BetRed", betRed);

        betRedSp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (betingList.isUserChooseBid()) {
                    betRed.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(9,7);
                } else {
                    System.out.println("Wybierz stawkę..");
                }
            }
        });

        StackPane betBlackSp = sp.createStackPaneBlack("BetBlack", betBlack);
        betBlackSp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (betingList.isUserChooseBid()) {
                    betBlack.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(13,7);
                } else {
                    System.out.println("Wybierz stawkę..");
                }
            }
        });

        StackPane betOddSp = sp.createStackPaneGreen("BetOdd", betOdd);
        betOddSp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (betingList.isUserChooseBid()) {
                    betOdd.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(17,7);
                } else {
                    System.out.println("Wybierz stawkę..");
                }
            }
        });

        StackPane bet19to36Sp = sp.createStackPaneGreen("Bet19to36", bet19to36);
        bet19to36Sp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (betingList.isUserChooseBid()) {
                    bet19to36.setStroke(Color.rgb(218, 125, 19));
                    betingList.setBet(21,7);
                } else {
                    System.out.println("Wybierz stawkę..");
                }
            }
        });

        bet1to18Sp.getChildren().addAll(bet1to18, bet1to18Text);
        betEvenSp.getChildren().addAll(betEven, betEvenText);
        betRedSp.getChildren().addAll(betRed, betRedText);
        betBlackSp.getChildren().addAll(betBlack, betBlackText);
        betOddSp.getChildren().addAll(betOdd, betOddText);
        bet19to36Sp.getChildren().addAll(bet19to36, bet19to36Text);


        grid.getChildren().addAll(bet1to18Sp, betEvenSp, betRedSp, betBlackSp, betOddSp, bet19to36Sp);
        GridPane.setConstraints(bet1to18Sp, 1, 7,3,1);
        GridPane.setConstraints(betEvenSp, 5, 7,3,1);
        GridPane.setConstraints(betRedSp, 9, 7,3,1);
        GridPane.setConstraints(betBlackSp, 13, 7,3,1);
        GridPane.setConstraints(betOddSp, 17, 7,3,1);
        GridPane.setConstraints(bet19to36Sp, 21, 7,3,1);


        betingList.setBetingOptionList(betingOptionList);


        grid.setAlignment(Pos.CENTER);
        grid.setVgap(1);

        return grid;
    }


}