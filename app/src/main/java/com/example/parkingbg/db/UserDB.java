package com.example.parkingbg.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.parkingbg.model.Parking;
import com.example.parkingbg.model.User;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 14-11-2019
 */
@Database(entities = {User.class, Parking.class}, version = 2, exportSchema = false)

public abstract class UserDB extends RoomDatabase {
    public abstract UserDao userDao();

    private static volatile UserDB INSTANCE;

    public static UserDB getINSTANCE(final Context context) {
        if (INSTANCE == null){
            synchronized (UserDB.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        UserDB.class, "parking_room_database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }
}
