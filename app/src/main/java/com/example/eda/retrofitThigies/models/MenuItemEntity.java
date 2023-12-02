package com.example.eda.retrofitThigies.models;

public class MenuItemEntity{
    private int id;
    private int quantity;
    private double price;
    private String name;
    private String pictureUrl;

    public MenuItemEntity(int id, int quantity, double price, String name, String pictureUrl) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "MenuItemEntity{" +
                "\n\tid=" + id +
                ", \n\tquantity=" + quantity +
                ", \n\tprice=" + price +
                ", \n\tname='" + name + '\'' +
                ", \n\tpictureUrl='" + pictureUrl + "\'\n" +
                "}\n";
    }
}