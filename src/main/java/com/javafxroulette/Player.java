package com.javafxroulette;

import javafx.scene.text.Text;

public class Player {
    private String name = "Username";
    private int amount = 0;
    private Text userNameText;
    private Text userAmount;

    public int getUserAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public void setAmount(int am) {
        amount = am;
        changeUserAmountText(Integer.toString(am));
    }

    public void setUserName(String userName) {
        name = userName;
        changeUserNameText(name);
    }

    public void setUserNameText(Text text) { userNameText = text; }

    public void changeUserNameText(String text) {
        userNameText.setText(text);
    }

    public void setUserAmountText(Text text) {
        userAmount = text;
    }

    public void changeUserAmountText(String text) {
        userAmount.setText(text);
    }
}
