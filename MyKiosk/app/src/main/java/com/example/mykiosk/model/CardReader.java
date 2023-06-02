package com.example.mykiosk.model;

import android.util.Log;

public class CardReader {
    Card card; //사용자 카드
    int payAmount;    //결제금액

    String msg;

    public CardReader(Card card, int payAmount) {
        this.card = card;
        this.payAmount = payAmount;
    }

    boolean readCard(){
        boolean isCardValid= validCard(this.card.getCardNumber());
        boolean isCashLeft = cashLeft(this.card.getCardMoney());
        if(!isCardValid) {
            this.msg="유효하지 않은 카드입니다.";
            return false;
        }
        if(!isCashLeft){
            this.msg="카드 잔액이 부족합니다.";
            return false;
        }
        if(isCardValid && isCashLeft){
            payment(this.card);
            this.msg="결제 완료";
            return true;
        }
        this.msg="카드 리더기 오류";
        return false;
    }

    boolean validCard(int cardNumber){//카드 번호가 유효한지 확인
        if(cardNumber==0){  //카드번호가 0 이면 오류
            return false;
        }
        else return true;
    }
    boolean cashLeft(int cardMoney){ //카드에 잔액이 주문 금액보다 큰지 확인
        if(this.payAmount<=cardMoney){
            return true;
        }else{
            return false;
        }
    }

    public void payment(Card card){ //카드 결제
        int cardMoney=card.getCardMoney();
        card.setCardMoney(cardMoney-payAmount);
    }

    public String getMsg() {
        return msg;
    }
}
