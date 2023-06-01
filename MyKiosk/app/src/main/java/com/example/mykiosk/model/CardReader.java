package com.example.mykiosk.model;

import android.util.Log;

public class CardReader {
    Card card; //사용자 카드
    int totalAmount;    //결제금액


    public CardReader(Card card, int totalAmount) {
        this.card = card;
        this.totalAmount = totalAmount;
    }

    String readCard(){
        boolean isCardValid= validCard(this.card.getCardNumber());
        boolean isCashLeft = cashLeft(this.card.getCardMoney());
        if(!isCardValid) {return "유효하지 않은 카드";}
        if(!isCashLeft){return "카드 잔액 부족";}
        if(isCardValid && isCashLeft){
            payment(this.card);
            return "결제 성공";
        }
        return "결제 실패";
    }

    boolean validCard(int cardNumber){//카드 번호가 유효한지 확인
        if(cardNumber==0){  //카드번호가 0 이면 오류
            return false;
        }
        else return true;
    }
    boolean cashLeft(int cardMoney){ //카드에 잔액이 주문 금액보다 큰지 확인
        if(this.totalAmount<=cardMoney){
            return true;
        }else{
            return false;
        }
    }

    void payment(Card card){ //카드 결제
        int cardMoney=card.getCardMoney();
        card.setCardMoney(cardMoney-totalAmount);
    }



}
