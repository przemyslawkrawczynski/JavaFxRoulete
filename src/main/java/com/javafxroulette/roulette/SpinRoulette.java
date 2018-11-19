package com.javafxroulette.roulette;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SpinRoulette extends Thread {

    private int[] drawNumbers;
    private Text text;
    private HBox place;
    private ArrayList<Integer> lastDrawnNumbers;
    private HashMap<Integer, String> correctColorsList;
    private HistoryBoxView hb;

    public SpinRoulette(Text text, int[] drawNumbers) {
        this.drawNumbers = drawNumbers;
        this.text = text;
    }

    @Override
    public void run() {

        Random random = new Random();
        LocalTime time = LocalTime.now();
        LocalTime timeAfter = time.plusSeconds(2);
        LocalTime checkedTime = time.plusNanos(250000000);
        int num = 0;

        while(time.isBefore(timeAfter)) {

            if(num == 5) { break; }

            time = LocalTime.now();
            if (time.equals(checkedTime)) {
                int number = drawNumbers[num];
                checkedTime = checkedTime.plusNanos(200000000);
                changeCircleFill(number, text);
        //        System.out.println(number);
                num++;

            }

        }

    }

    public void changeCircleFill(int num, Text text) {
        String number = Integer.toString(num);
        text.setText(number);
    }
}
