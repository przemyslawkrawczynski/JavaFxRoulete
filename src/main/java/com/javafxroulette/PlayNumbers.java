package com.javafxroulette;

import java.util.ArrayList;

public class PlayNumbers {
    private ArrayList<int[]> numberInformationList = new ArrayList<>();

    public void createNumbers() {


        // Define Red and Black numbers

        for (int i = 1; i < 37; i++) {

            if (i == 2 || i == 4 || i == 6 || i == 8 || i == 10 || i == 11 || i == 13 || i == 15 || i == 17 || i == 20 || i == 22 || i == 24 || i == 26 || i == 28 || i == 29 || i == 33 || i == 31 || i == 35) {
                int[] actualNumber = new int[4];
                actualNumber[0] = i;
                actualNumber[1] = 2;
                numberInformationList.add(actualNumber);

            } else {

                int[] actualNumber = new int[4];
                actualNumber[0] = i;
                actualNumber[1] = 1;

                numberInformationList.add(actualNumber);
            }
        }

// Setting correct position on GridPane

        for (int j = 0; j < 5; j++) {

            int first = 0;

            if (j == 0 || (j % 2 == 0)) {

                if (j == 0) {
                    first = 3;
                }
                if (j == 2) {
                    first = 2;
                }
                if (j == 4) {
                    first = 1;
                }

                for (int i = 1; i < 24; i++) {
                    if ((i==1)) {
                        int[] actualNumber = numberInformationList.get(first - 1);
                        actualNumber[2] = i;
                        actualNumber[3] = j;

                        first = first + 3;


                    } else if (i % 2 != 0) {
                        int[] actualNumber = numberInformationList.get(first - 1);
                        actualNumber[2] = i;
                        actualNumber[3] = j;

                        first = first + 3;
                    }
                }
//
            }
        }
    }

    public void showNumbers() {
        System.out.println("Numer | Kolor | Kolumna | Wiersz");
        for (int i = 0; i < numberInformationList.size(); i++) {
            int[] aN = numberInformationList.get(i);

            String color;
            if (aN[1] == 1) {
                color = "Red";
            } else  {
                color = "Black";
            }

            System.out.println(aN[0] + " | " + color + " | " + aN[2] + " | " + aN[3]);
        }
    }

    public String checkColor(int number) {
        String result = "Please give correct number <0-36>";
        if (number > 0 && number < 37) {
            int[] num = numberInformationList.get(number - 1);
            if (num[1] == 2) {
                result =  "Black";
            } else {
                result = "Red";
            }
        } else if (number == 0) {
            result = "Green";
        }
        return result;
    }

    public ArrayList<Integer> getColorList(String color) {
        ArrayList<Integer> blackList = new ArrayList<>();
        ArrayList<Integer> redlist = new ArrayList<>();

        for(int i=1; i<37; i++) {
            if (checkColor(i).equals("Red")) {
                redlist.add(i);
            } else {
                blackList.add(i);
            }
        }

        if (color.equals("Red")) {
            return redlist;
        } else {
            return blackList;
        }
    }

    public int getI(int num) {
        int column = 0;
        for (int i=0; i < numberInformationList.size(); i++) {
            int[] numberInfo = numberInformationList.get(i);
            int number = numberInfo[0];
            if (num == number) {
                column = numberInfo[2];
            }
        }
        return column;
    }

    public int getJ(int num) {
        int row = 0;
        for (int i=0; i < numberInformationList.size(); i++) {
            int[] numberInfo = numberInformationList.get(i);
            int number = numberInfo[0];
            if (num == number) {
                row = numberInfo[3];
            }
        }
        return row;
    }

    public int getNumberOnPosition(int x, int y) {
        int num = 0;
        for(int i=0; i < numberInformationList.size(); i++) {
            int[] numberInfo = numberInformationList.get(i);
            if (numberInfo[2] == x && numberInfo[3] == y) {
                num = numberInfo[0];
            }
        }
        return num;
    }

    public ArrayList getNumberInformationList() {
        return numberInformationList;
    }
}