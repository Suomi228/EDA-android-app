package com.example.eda.menuStuff;

public class GridViewDomain {
    private String title;
    private String picture;
    private int fee;
    private int numberInCart;

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public GridViewDomain(String title, String picture, int fee, int numberInCart) {
        this.title = title;
        this.picture = picture;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }

    public GridViewDomain(String title, String picture, int fee){
        this.title = title;
        this.picture = picture;
        this.fee = fee;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
