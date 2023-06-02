package com.example.mykiosk.model;

public class Card {
    int cardNumber;
    int cardMoney;


    public Card(int cardNumber, int cardMoney) {
        this.cardNumber = cardNumber;
        this.cardMoney = cardMoney;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardMoney(int cardMoney) {
        this.cardMoney = cardMoney;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getCardMoney() {
        return cardMoney;
    }
}


