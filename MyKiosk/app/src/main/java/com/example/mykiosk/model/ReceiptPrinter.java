package com.example.mykiosk.model;

import java.util.ArrayList;

public class ReceiptPrinter {
    int totalAmount;    //결제금액
    ArrayList<Order> orderList; //결제메뉴
    int orderNumber; //주문번호


    public ReceiptPrinter(int totalAmount, ArrayList<Order> orderList, int orderNumber) {
        this.totalAmount = totalAmount;
        this.orderList = orderList;
        this.orderNumber = orderNumber;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String printReceipt(){
        String str="";
        for(int i=0;i<orderList.size();i++){
            str+=orderList.get(i).getOrderMenu()+" "+orderList.get(i).getOrderQuantity()+'\n';
        }
        str+="총 주문금액 "+Order.getTotalPrice(orderList);
        return str;
    }

}