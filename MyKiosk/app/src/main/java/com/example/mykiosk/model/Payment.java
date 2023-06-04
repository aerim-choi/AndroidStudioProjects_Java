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
    String paymentResultMsg;

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


    public boolean pay(int payAmount){
        boolean isPay;

        if(this.paymentType.equals("쿠폰")) { //쿠폰 결제
            Log.d("paymentType", this.paymentType);

            CouponReader couponReader=new CouponReader(coupon,payAmount);
            isPay = couponReader.readCoupon();
            if(isPay) {
                this.totalAmount = totalAmount - payAmount;
            }
            this.paymentResultMsg=couponReader.getMsg();
            return isPay;
        }else{ //카드 결제
            Log.d("paymentType", this.paymentType);
            CardReader cardReader=new CardReader(card,payAmount);
            isPay = cardReader.readCard();
            if(isPay){
                this.totalAmount=totalAmount-payAmount;
            }
            this.paymentResultMsg=cardReader.getMsg();
            return isPay;
        }
    }
    public String getPaymentResultMsg(){
        return this.paymentResultMsg;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

}

