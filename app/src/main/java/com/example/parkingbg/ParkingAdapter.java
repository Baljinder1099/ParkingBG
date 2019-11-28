package com.example.parkingbg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.parkingbg.model.Parking;

import java.util.ArrayList;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 28-11-2019
 */
public class ParkingAdapter extends ArrayAdapter {

    Context context;


    public ParkingAdapter(@NonNull Context context, ArrayList<Parking> parkings) {
        super(context, 0, parkings);
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_parking_entry, parent, false);
        }

        TextView tvBuildingCode = convertView.findViewById(R.id.txt_buildingCode);
        TextView tvDateTime = convertView.findViewById(R.id.txt_dateTime);

        Parking parking = (Parking) getItem(position);

        tvBuildingCode.setText(parking.getBuildingCode());
        tvDateTime.setText(parking.getDateTime());



        return convertView;
    }
}
