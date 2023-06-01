package com.example.mykiosk.model;

import android.util.Log;

public class CouponReader {
    Coupon coupon; //쿠폰
    int totalAmount;//결제금액
    String msg;
    public CouponReader(Coupon coupon, int totalAmount) {
        this.coupon=coupon;
        this.totalAmount=totalAmount;
    }

    boolean readCoupon(){
        boolean isCouponValid= validCoupon(this.coupon.getBarcode());
        boolean isCashLeft = cashLeft(this.coupon.getCouponMoney());
        if(!isCouponValid) {
            this.msg="유효하지 않은 쿠폰입니다.";
            return false;
        }
        if(!isCashLeft){
            this.msg="쿠폰 잔액이 부족합니다.";
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
    boolean cashLeft(int couponMoney){ //쿠폰금액이 주문 금액보다 작거나 같은지 확인. 쿠폰 금액이 더 클 경우 사용불가
        if(this.totalAmount<=couponMoney){
            return true;
        }else{
            return false;
        }
    }

    void payment(Coupon coupon){ //쿠폰 사용
        int couponMoney=coupon.getCouponMoney();
        coupon.setCouponMoney(couponMoney-totalAmount);
    }
    public String getMsg() {
        return msg;
    }
}

