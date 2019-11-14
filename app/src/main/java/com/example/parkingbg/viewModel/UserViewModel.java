package com.example.parkingbg.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.parkingbg.db.UserRepository;
import com.example.parkingbg.model.User;

import java.util.List;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 14-11-2019
 */
public class UserViewModel extends AndroidViewModel {
    private LiveData<List<User>> allUsers;
    private UserRepository userRepository;
    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }
    public void insert(User user){
        userRepository.insert(user);
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }
}
