package com.example.parkingbg.db;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.parkingbg.model.User;

import java.util.List;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 14-11-2019
 */
public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user_table WHERE email = :email")
    void deleteUserByEmail(String email);

    @Query("SELECT * FROM user_table ORDER BY email ASC")
    LiveData<List<User>> getAllUsers();
}
