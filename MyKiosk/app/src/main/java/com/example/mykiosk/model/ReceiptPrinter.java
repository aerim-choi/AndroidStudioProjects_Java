package com.example.mykiosk.model;

import java.util.ArrayList;

public class ReceiptPrinter {
    String totalAmount;    //결제금액
    ArrayList<Order> orderList; //결제메뉴
    int orderNumber; //주문번호


    public ReceiptPrinter(String totalAmount, ArrayList<Order> orderList, int orderNumber) {
        this.totalAmount = totalAmount;
        this.orderList = orderList;
        this.orderNumber = orderNumber;
    }

    public String printReceipt(){
        String receipt="";
        for(int i=0;i<orderList.size();i++){
            receipt+=orderList.get(i).getOrderMenu()+" "+orderList.get(i).getOrderQuantity()+'\n';
        }
        receipt+="총 주문금액 "+Order.getTotalPrice(orderList);
        return receipt;
    }

}