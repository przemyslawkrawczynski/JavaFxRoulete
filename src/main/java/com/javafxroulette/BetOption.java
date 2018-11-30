package com.javafxroulette;
import java.util.HashMap;

public class BetOption {

    public HashMap<int[], int[]> betOptionList = new HashMap<>();
    PlayNumbers playNumbers;

    public BetOption(PlayNumbers playNumbers) {
        this.playNumbers = playNumbers;
    }

    public void generateBetOption() {

        int[] key = new int[3];
        int[] value = new int[2];

        for (int i = 1; i < 37; i++) {

            int[] numberKey = new int[3];
            int[] numberValue;

            numberKey[0] = playNumbers.getI(i);
            numberKey[1] = playNumbers.getJ(i);
            numberKey[2] = 36;

            numberValue = new int[1];
            numberValue[0] = i;

            betOptionList.put(numberKey, numberValue);

        }


        // Declare 0 position
        key[0] = 0;
        key[1] = 0;
        key[2] = 36;
        value[0] = 0;

        betOptionList.put(key, value);
        generateBetOption12();
        generateBet2to1();
        generateBetOptionEvenOdd();
        generateRedBlack();
        generateBetFirstAndSecond18();
    }

    public void generateBetOption12() {

        // First 12
        int[] keyF12 = new int[3];
        int[] valueF12 = new int[12];

        keyF12[0] = 1;
        keyF12[1] = 6;
        keyF12[2] = 2;

        for(int i=0; i<11; i++) {
            valueF12[i] = i + 1 ;
        }

        betOptionList.put(keyF12, valueF12);

        // Second 12

        int[] keyS12 = new int[3];
        int[] valueS12 = new int[12];

        keyS12[0] = 9;
        keyS12[1] = 6;
        keyS12[2] = 2;

        for(int i=0; i<11; i++) {
            valueS12[i] = i + 12 ;
        }

        betOptionList.put(keyS12, valueS12);

        // Third 12
        int[] keyT12 = new int[3];
        int[] valueT12 = new int[12];

        keyT12[0] = 17;
        keyT12[1] = 1;
        keyT12[2] = 2;

        for(int i=0; i<11; i++) {
            valueT12[i] = i + 24 ;
        }

        betOptionList.put(keyT12, valueT12);

    }

    public void generateBet2to1() {

        // Up 2to1

        int[] key1 = new int[3];
        int[] value1 = new int[12];

        key1[0] = 25;
        key1[1] = 0;
        key1[2] = 2;

        int first = 0;
        for(int i = 0; i<12; i++) {
            first = first + 3;
            value1[i] = first;
        }

        betOptionList.put(key1, value1);

        // Middle 2to1

        int[] key2 = new int[3];
        int[] value2 = new int[12];

        key2[0] = 25;
        key2[1] = 2;
        key2[2] = 2;

        first = 2;
        for(int i = 0; i<12; i++) {
            value2[i] = first;
            first = first + 3;
        }

        betOptionList.put(key2, value2);

        // Down 2to1

        int[] key3 = new int[3];
        int[] value3 = new int[12];

        key3[0] = 25;
        key3[1] = 4;
        key3[2] = 2;

        first = 1;
        for(int i = 0; i<12; i++) {
            value3[i] = first;
            first = first + 3;
        }

        betOptionList.put(key3, value3);

    }

    public void generateBetFirstAndSecond18() {

        int[] keyFirst = new int[3];
        int[] keySecond = new int[3];
        int[] valueFirst = new int[18];
        int[] valueSecond = new int[18];

        keyFirst[0] = 1;
        keyFirst[1] = 7;
        keyFirst[2] = 1;

        keySecond[0] = 22;
        keySecond[1] = 7;
        keySecond[2] = 1;

        int secondFirst = 19;

        for(int i=0; i<18; i++) {
            valueFirst[i] = i + 1;
            valueSecond[i] = secondFirst;
            secondFirst++;
        }

        betOptionList.put(keyFirst, valueFirst);
        betOptionList.put(keySecond, valueSecond);
    }

    public void generateBetOptionEvenOdd() {

        int[] keyOdd = new int[3];
        int[] keyEven = new int[3];
        int[] valueOdd = new int[18];
        int[] valueEven = new int[18];

        keyEven[0] = 5;
        keyEven[1] = 7;
        keyEven[2] = 1;

        keyOdd[0] = 17;
        keyOdd[1] = 7;
        keyOdd[2] = 1;

        int numOdd = 1;
        int numEven = 1;
        for(int i=0; i<18; i++) {
            valueOdd[i] = numOdd;
            valueEven[i] = numEven;

            numOdd = numOdd + 2;
            numEven = numEven + 2;
        }

        betOptionList.put(keyEven, valueEven);
        betOptionList.put(keyOdd, valueOdd);

    }

    public void generateRedBlack() {

        int[] keyRed = new int[3];
        int[] keyBlack = new int[3];
        int[] valueRed = new int[18];
        int[] valueBlack = new int[18];

        keyRed[0] = 9;
        keyRed[1] = 7;
        keyRed[2] = 1;

        keyBlack[0] = 13;
        keyBlack[1] = 7;
        keyBlack[2] = 1;

        for(int i=0; i<18; i++) {
            valueRed[i] = playNumbers.getColorList("Red").get(i);
            valueBlack[i] = playNumbers.getColorList("Black").get(i);
        }

        betOptionList.put(keyRed, valueRed);
        betOptionList.put(keyBlack, valueBlack);

    }

    public void setBetOptionList(int[] position, int[] values) {
        betOptionList.put(position, values);
    }

    public HashMap<int[], int[]> getBetOptionList() {
        return betOptionList;
    }

    public PlayNumbers getPlayNumbers() {
        return playNumbers;
    }
}
