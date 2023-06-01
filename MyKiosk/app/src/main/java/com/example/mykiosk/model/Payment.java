package com.example.mykiosk.model;

import android.util.Log;

public class Payment {
    static int orderNumber=0;
    Coupon coupon;
    Card card;
    String paymentType;
    int totalAmount;
    boolean isTakeout;

    public Payment(Coupon coupon, int totalAmount, boolean isTakeout) {
        this.orderNumber++;
        this.coupon = coupon;
        this.totalAmount = totalAmount;
        this.isTakeout = isTakeout;
        this.paymentType="쿠폰";
    }
    public Payment(Card card, int totalAmount, boolean isTakeout) {
        this.orderNumber++;
        this.card = card;
        this.totalAmount = totalAmount;
        this.isTakeout = isTakeout;
        this.paymentType="카드";
    }


    public String pay(){
        String isPay="";
        if(this.paymentType.equals("쿠폰")) { //쿠폰 결제
            return "쿠폰";

        }else{ //카드 결제
            Log.d("paymentType", this.paymentType);
            CardReader cardReader=new CardReader(card,totalAmount);
            isPay = cardReader.readCard();
            return isPay;
        }
    }
}
