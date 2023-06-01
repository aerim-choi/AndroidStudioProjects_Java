package com.example.mykiosk.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Order implements Parcelable {

    private int orderQuantity;
    private String orderMenu;
    private String unitAmount;
    private String totalAmount;

    public Order(int orderQuantity, String orderMenu, String unitAmount) {
        this.orderQuantity = orderQuantity;
        this.orderMenu = orderMenu;
        this.unitAmount = unitAmount;
        this.totalAmount = String.valueOf(Integer.parseInt(unitAmount) * orderQuantity);
    }

    protected Order(Parcel in) {
        orderQuantity = in.readInt();
        orderMenu = in.readString();
        unitAmount = in.readString();
        totalAmount = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(orderQuantity);
        dest.writeString(orderMenu);
        dest.writeString(unitAmount);
        dest.writeString(totalAmount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public String getOrderMenu() {
        return orderMenu;
    }

    public String getUnitAmount() {
        return unitAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }
    public static String getTotalQuantity(ArrayList<Order> orderList) {
        int totalQuantity = 0;
        for (Order order : orderList) {
            totalQuantity += order.getOrderQuantity();
        }
        return String.valueOf(totalQuantity);
    }

    public static String getTotalPrice(ArrayList<Order> orderList) {
        Integer totalPrice = 0;
        for (Order order : orderList) {
            totalPrice += Integer.parseInt(order.getTotalAmount());
        }
        return String.valueOf(totalPrice);
    }

}
