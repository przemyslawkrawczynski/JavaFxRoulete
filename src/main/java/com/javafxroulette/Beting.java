package com.javafxroulette;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.HashMap;

public class Beting {

    ArrayList<int[]> bL = new ArrayList<>();
    UserChoiceBox ub = new UserChoiceBox();
    private HashMap<int[], int[]> betingOptionList = new HashMap<>();
    private boolean userChooseBid = false;
    private int actualBet = 0;
    private Text betText;
    private PlayNumbers playNumbers;
    private VBox userChoiceBox;

    public void addBid(int bet, Text text) {
        actualBet = actualBet + bet;
        this.betText = text;
        betText.setText("Actual Bid - $" + actualBet);
        userChooseBid = true;
    }

    public void setBet(int xPos, int yPos) {

        userChooseBid = false;
        int[] bet = new int[3];
        bet[0] = getActualBet();
        bet[1] = xPos;
        bet[2] = yPos;

        bL.add(bet);
        betText.setText("- Choose Next Bid or Spin -");
        actualBet = 0;

    }

    public ArrayList<int[]> getBetList() {
        return bL;
    }

    public boolean isUserChooseBid() {
        return userChooseBid;
    }

    public void clearBetList() {
        bL.clear();
    }

    public int getActualBet() {
        return actualBet;
    }

    public HashMap<int[], int[]> getBetingOptionList() {
        return betingOptionList;
    }

    public void setBetingOptionList(HashMap betList) { betingOptionList = betList; }

    public void setPlayNumbers(PlayNumbers pn) { playNumbers = pn; }

}

