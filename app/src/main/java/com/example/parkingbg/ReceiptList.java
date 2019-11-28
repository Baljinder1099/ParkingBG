package com.example.parkingbg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.parkingbg.model.Parking;
import com.example.parkingbg.model.User;
import com.example.parkingbg.viewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 27-11-2019
 */
public class ReceiptList extends AppCompatActivity {
    ListView lstParkings;
    ArrayAdapter parkingAdapter;
    ArrayList<Parking> parkingArrayList;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_list);
        parkingArrayList = new ArrayList<Parking>();
        lstParkings = findViewById(R.id.lstParking);


        userViewModel = new UserViewModel(getApplication());

        userViewModel.getAllParkings().observe(ReceiptList.this, new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                for (Parking parking : parkings) {
                    Log.e("MainActivity", parking.toString());
                    parkingArrayList.add(parking);
                }
            }

        });
        //fruitAdapter.notifyDataSetChanged();
        parkingAdapter = new ParkingAdapter(ReceiptList.this, parkingArrayList);
        lstParkings.setAdapter(parkingAdapter);

        this.listViewListener();

    }

//    private void populateParkingList() {
//        parkingArrayList = new ArrayList<Parking>();
//        parkingArrayList = userViewModel.getAllParkings().getValue();
//
//        for (Parking parking: allParkings) {
//            parkingArrayList.add(parking);
//        }
//
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.itm_add_fruit:
//                this.addFruit();
//                return true;
////                break;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
    private void listViewListener(){
        lstParkings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent addParkingIntent = new Intent(getApplication(), ViewReceipt.class);
//                startActivity(addParkingIntent);
                //Toast.makeText(ReceiptList.this, title, Toast.LENGTH_LONG).show();
            }
        });


    }
}
