package com.example.parkingbg;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.parkingbg.model.User;
import com.example.parkingbg.viewModel.UserViewModel;

import java.util.List;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 25-11-2019
 */
public class ViewReceipt extends AppCompatActivity {
    UserViewModel userViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //this.referWidgets();
        userViewModel = new UserViewModel(getApplication());
        userViewModel.getAllUsers().observe(ViewReceipt.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users) {
                    Log.e("SignIn", user.toString());
                }
            }
        });
    }


}

