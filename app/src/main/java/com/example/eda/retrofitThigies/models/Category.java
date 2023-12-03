package com.example.eda.retrofitThigies.models;

public class Category{
    private int id;
    private String category;

    private String categoryName;

    public Category(int id, String category, String categoryName) {
        this.id = id;
        this.category = category;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
