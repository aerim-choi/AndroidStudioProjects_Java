package com.example.mykiosk.model;

import android.util.Log;

public class CouponReader {
    Coupon coupon; //쿠폰
    int payAmount;//결제금액
    String msg;
    public CouponReader(Coupon coupon, int payAmount) {
        this.coupon=coupon;
        this.payAmount=payAmount;
    }

    public boolean readCoupon(){
        boolean isCouponValid= validCoupon(this.coupon.getBarcode());
        boolean isCashLeft = cashLeft(this.coupon.getCouponMoney());
        if(!isCouponValid) {
            this.msg="유효하지 않은 쿠폰입니다.";
            return false;
        }
        if(!isCashLeft){
            this.msg="쿠폰 잔액이 없습니다.";
            return false;
        }
        if(isCouponValid && isCashLeft){
            payment(this.coupon);
            this.msg="결제 완료";
            return true;

        }
        this.msg="쿠폰 리더기 오류";
        return false;
    }

    boolean validCoupon(int barcode){//바코드 읽기
        if(barcode==0){  //바코드 읽기 실패
            return false;
        }
        else return true;
    }
    boolean cashLeft(int couponMoney){
        if(couponMoney>=payAmount) {
            return true;

        }else{
            return false;
        }
    }

    void payment(Coupon coupon){ //쿠폰 사용
        int couponMoney=coupon.getCouponMoney();
        coupon.setCouponMoney(couponMoney-payAmount);
    }
    public String getMsg() {
        return msg;
    }
}

