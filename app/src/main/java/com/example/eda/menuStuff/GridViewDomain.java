package com.example.eda.menuStuff;

public class GridViewDomain {
    private String title;
    private String picture;
    public GridViewDomain(String title, String picture){
        this.title = title;
        this.picture = picture;
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
