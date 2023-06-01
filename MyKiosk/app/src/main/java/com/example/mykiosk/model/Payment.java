package com.example.mykiosk.model;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

public class Payment {

    public static int orderNumber=0;
    Coupon coupon;
    Card card;
    String paymentType;
    int totalAmount;
    boolean isTakeout;
    String msg;

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


    public boolean pay(){
        boolean isPay;

        if(this.paymentType.equals("쿠폰")) { //쿠폰 결제
            Log.d("paymentType", this.paymentType);
            CouponReader couponReader=new CouponReader(coupon,totalAmount);
            isPay = couponReader.readCoupon();
            this.msg=couponReader.getMsg();
            return isPay;

        }else{ //카드 결제
            Log.d("paymentType", this.paymentType);
            CardReader cardReader=new CardReader(card,totalAmount);
            isPay = cardReader.readCard();
            this.msg=cardReader.getMsg();
            return isPay;
        }
    }
    public String displayPrompt(){
        return this.msg;
    }

//    public String payfinish(){
//        ReceiptPrinter receiptPrinter=new ReceiptPrinter(totalAmount,orderList,orderNumber)
//    }
}

