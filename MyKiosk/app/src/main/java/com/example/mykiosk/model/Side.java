package com.example.mykiosk.model;

public class Side implements Menu{
    private String name;
    private int image;
    private String price;

    public Side(String name, int image, String price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    @Override
    public void showMenu() {

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
