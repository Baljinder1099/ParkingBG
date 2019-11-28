package com.example.parkingbg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 14-11-2019
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_Add_Parking , btn_Receipt, btn_Receipt_List, btn_Nearby_Facilities, btn_Update, btn_Manual, btn_Support, btn_Sign_Out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.referWidgets();



    }

    private void referWidgets(){
        btn_Add_Parking = findViewById(R.id.btnAddParking);
        btn_Add_Parking.setOnClickListener(this);

        btn_Receipt = findViewById(R.id.btnReceipt);
        btn_Receipt.setOnClickListener(this);

        btn_Receipt_List = findViewById(R.id.btnReceiptList);
        btn_Receipt_List.setOnClickListener(this);

        btn_Nearby_Facilities = findViewById(R.id.btnNearbyFacilities);
        btn_Nearby_Facilities.setOnClickListener(this);

        btn_Update = findViewById(R.id.btnUpdate);
        btn_Update.setOnClickListener(this);

        btn_Manual = findViewById(R.id.btnManual);
        btn_Manual.setOnClickListener(this);

        btn_Support = findViewById(R.id.btnSupport);
        btn_Support.setOnClickListener(this);

        btn_Sign_Out = findViewById(R.id.btnSignOut);
        btn_Sign_Out.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddParking:
                this.addParking();
                break;
        }

        switch (view.getId()) {
            case R.id.btnReceipt:
                this.receipt();
                break;
        }
        switch (view.getId()) {
            case R.id.btnReceiptList:
                this.receiptList();
                break;
        }
        switch (view.getId()) {
            case R.id.btnNearbyFacilities:
                this.nearbyFacilities();
                break;
        }
        switch (view.getId()) {
            case R.id.btnUpdate:
                this.update();
                break;
        }
        switch (view.getId()) {
            case R.id.btnManual:
                this.manual();
                break;
        }
        switch (view.getId()) {
            case R.id.btnSupport:
                this.support();
                break;
        }
        switch (view.getId()) {
            case R.id.btnSignOut:
                this.signOut();
                break;
        }

    }
    private void addParking(){
        Intent addParkingIntent = new Intent(this, AddParking.class);
        startActivity(addParkingIntent);

    }
    private void receipt(){
        Intent addParkingIntent = new Intent(this, ViewReceipt.class);
        startActivity(addParkingIntent);

    }
    private void receiptList(){
        Intent viewReceiptListIntent = new Intent(this, ReceiptList.class);
        startActivity(viewReceiptListIntent);
    }
    private void nearbyFacilities(){

    }
    private void update(){

    }
    private void manual(){

    }
    private void support(){

    }
    private void signOut(){
        Intent signOut = new Intent(this, Launcher.class);
        startActivity(signOut);
    }
}
