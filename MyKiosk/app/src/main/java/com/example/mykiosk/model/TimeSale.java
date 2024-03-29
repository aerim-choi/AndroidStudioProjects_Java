package com.example.mykiosk.model;

public class TimeSale implements Menu {
    private String name;
    private int image;
    private String price;

    public TimeSale(String name, int image, String price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getMenuName() {
        return name;
    }

    public int getMenuImage() {
        return image;
    }

    public String getMenuPrice() {
        return price;
    }
}
