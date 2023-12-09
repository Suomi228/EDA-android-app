package com.example.eda.RoomDataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cartDB")
public class Cart {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "foodName")
    public String foodName;

    @ColumnInfo(name = "foodPrice")
    public String foodPrice;

    @ColumnInfo(name = "quantity")
    public String quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}
