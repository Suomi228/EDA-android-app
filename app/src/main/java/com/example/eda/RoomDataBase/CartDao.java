package com.example.eda.RoomDataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface CartDao {

    @Query("SELECT * from cartDB")
    List<Cart> getAllFood();

    @Insert()
    void insert(Cart... carts);

    @Update
    void update(Cart cart);

    @Delete()
    void delete(Cart cart);



}
