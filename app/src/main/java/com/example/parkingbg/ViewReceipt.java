package com.example.parkingbg;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.parkingbg.model.Parking;
import com.example.parkingbg.model.User;
import com.example.parkingbg.viewModel.UserViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 25-11-2019
 */
public class ViewReceipt extends AppCompatActivity {
    UserViewModel userViewModel;


    TextView tvBuildingCode, tvNoOfHours, tvPlate, tvSuitNo, tvDateTime, tvParking;
    String carPlateNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receipt);
        carPlateNo = this.getIntent().getStringExtra("EXTRA_CARPLATE");
        this.referWidgets();





    }
    private void referWidgets(){
        tvBuildingCode = findViewById(R.id.txt_BuildingCode);
        tvNoOfHours = findViewById(R.id.txt_Hours);
        tvPlate = findViewById(R.id.txt_Plate);
        tvSuitNo = findViewById(R.id.txt_SuitNo);
        tvDateTime = findViewById(R.id.txt_DateTime);
        tvParking = findViewById(R.id.txt_Parking);
        this.viewParking();

    }

    private void viewParking(){
//        Parking parking = new Parking();
//        parking = userViewModel.fetchParkingFromId(id);
        userViewModel = new UserViewModel(getApplication());
        userViewModel.getAllParkings().observe(ViewReceipt.this, new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                for (Parking parking : parkings) {
                    Log.e("ViewReceipt", parking.toString());
                }
            }

        });
        List<Parking> allParkings;
        allParkings = userViewModel.getAllParkings().getValue();
        //Id = Integer.parseInt(id);

        for (Parking parking: allParkings) {

            if (parking.getCarPlate().equals(carPlateNo)){
                Toast.makeText(this, parking.getId(),Toast.LENGTH_LONG).show();
            }


        }

    }


}

