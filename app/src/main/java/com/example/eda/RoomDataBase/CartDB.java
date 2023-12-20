package com.example.eda.RoomDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cart.class}, version = 1)
public abstract class CartDB extends RoomDatabase {

    public abstract CartDao korzinaDao();

    private static CartDB INSTANCE;

    public static CartDB getInstance(Context context){

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            CartDB.class,
                            "CartDB"
                    )
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}