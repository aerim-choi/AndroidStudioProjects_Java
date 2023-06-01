package com.example.mykiosk.model;

public class Coupon {
    int barcode;
    int couponMoney;

    public Coupon(int barcode, int couponMoney) {
        this.barcode = barcode;
        this.couponMoney = couponMoney;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public void setCouponMoney(int couponMoney) {
        this.couponMoney = couponMoney;
    }

    public int getBarcode() {
        return barcode;
    }

    public int getCouponMoney() {
        return couponMoney;
    }

}