package com.example.parkingbg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 14-11-2019
 */
public class Launcher extends AppCompatActivity implements View.OnClickListener {
    Button btn_SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_SignIn = findViewById(R.id.btnSignin);
        btn_SignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignin:
                this.signIn();
                break;

        }

    }
    private void signIn(){
        Intent signInIntent;
        signInIntent = new Intent(this, SignIn.class);
        startActivity(signInIntent);

    }
}
