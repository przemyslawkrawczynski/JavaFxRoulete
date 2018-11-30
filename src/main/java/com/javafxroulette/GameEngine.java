package com.javafxroulette;

import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameEngine {
    int userAmount;
    Text actualAmountBox;
    Beting beting;
    Player player;
    HashMap<int[], int[]> betingOptionList;

    public GameEngine(Text actualAmountBox, Player player, Beting beting){
        this.actualAmountBox = actualAmountBox;
        this.player = player;
        this.beting = beting;
    }

    public void play(int drawNumber) {
        ArrayList<int[]> userBetList = beting.getBetList();
        betingOptionList = beting.getBetingOptionList();
        int userAmount = player.getUserAmount();

        if (userBetList.size() > 0) {

            for(int j=0; j<userBetList.size(); j++) {

                int bid = userBetList.get(j)[0];
                int x = userBetList.get(j)[1];
                int y = userBetList.get(j)[2];

                boolean numberWin = false;

                for (Map.Entry<int[], int[]> entry : betingOptionList.entrySet()) {
                    if (x == entry.getKey()[0] && y == entry.getKey()[1]) {
                        for (int i = 0; i < entry.getValue().length; i++) {
                            if (entry.getValue()[i] == drawNumber) {
                                numberWin = true;
                            }
                        }
                    }
                }

                if (numberWin) {
                    System.out.println("Wygrałeś zakład -> " + (j + 1) + " | " + x + " " + y + " | Stawka " + getWinSize(x,y));
                    userAmount = userAmount + (bid * getWinSize(x,y));
          //          actualAmountBox.setText("" + userAmount);
                    player.setAmount(userAmount);
                } else {
                    System.out.println("Przegrałeś zakład -> " + (j + 1) + " | " + x + " " + y + " | Stawka " + getWinSize(x,y));
                    userAmount = userAmount - bid;
             //       actualAmountBox.setText("" + userAmount);
                    player.setAmount(userAmount);
                }
            }
            beting.clearBetList();
        } else {
            System.out.println("Nie obstawiłeś zakładu..");
        }
    }

    public int getWinSize(int posX, int posY) {
        int winSize = 0;

        for(Map.Entry<int[], int[]> entry : betingOptionList.entrySet()) {

            int x = entry.getKey()[0];
            int y = entry.getKey()[1];
            int stawka = entry.getKey()[2];

            if(entry.getKey()[0] == posX && entry.getKey()[1] == posY) {
                winSize = entry.getKey()[2];
            }
        }
        return winSize;
    }

    public int getUserAmount() {
        return userAmount;
    }
}

