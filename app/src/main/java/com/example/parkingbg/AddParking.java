package com.example.parkingbg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.parkingbg.model.Parking;
import com.example.parkingbg.model.User;
import com.example.parkingbg.viewModel.UserViewModel;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.parkingbg.SignUpActivity.EXTRA_REPLY;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 22-11-2019
 */
public class AddParking extends AppCompatActivity implements View.OnClickListener {
    String buildingCode;
    String noOfHours;
    String carPlateNo;
    String suitNo;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String date;
    int parkingCharges;


    EditText edt_BuildingCode;
    EditText edt_noOfHours;
    EditText edt_carPlateNo;
    EditText edt_suitNo;

    Button btn_AddPaking;

    public static final String EXTRA_REPLY = "com.example.parkingbg.REPLY";


    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addparking);

        this.referWidgets();
        userViewModel = new UserViewModel(getApplication());
        userViewModel.getAllParkings().observe(AddParking.this, new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                for (Parking parking : parkings){
                    Log.e("AddParking", parking.toString());
                }
            }
        });
        List<Parking> allParkings = userViewModel.getAllParkings().getValue();

    }

    private void referWidgets(){
        edt_BuildingCode = findViewById(R.id.edtBuildingCode);
        edt_carPlateNo = findViewById(R.id.edtCarPlate);
        edt_noOfHours = findViewById(R.id.edtHours);
        edt_suitNo = findViewById(R.id.edtSuitNo);

        btn_AddPaking = findViewById(R.id.btnAddParking);
        btn_AddPaking.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddParking:
                if (this.validateData()){
                    this.createParkingAndReply();
                }
                break;
        }
    }

    private void createParkingAndReply(){
        buildingCode = edt_BuildingCode.getText().toString();
        int BuildingCode = Integer.parseInt(buildingCode);
        noOfHours = edt_noOfHours.getText().toString();
        float NoOfHours = Float.parseFloat(noOfHours);
        carPlateNo = edt_carPlateNo.getText().toString();
        suitNo = edt_suitNo.getText().toString();


        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        date = simpledateformat.format(calander.getTime());

        if(NoOfHours < 1){
            parkingCharges = 4;
        }else if(NoOfHours <= 3 && NoOfHours > 1){
            parkingCharges = 8;
        }else if(NoOfHours <= 10 && NoOfHours > 3){
            parkingCharges = 12;
        }else {
            parkingCharges = 20;
        }

        Parking newParking = new Parking(BuildingCode, NoOfHours, carPlateNo, suitNo, date, parkingCharges
                );

        //userViewModel.insert(newParking);




        userViewModel.insert(newParking);
        Log.d("AddParking", newParking.toString());

//        int parkingId = newParking.getId();
//        String id = String.valueOf(parkingId);

        //reply to previous intent
        Intent replyIntent = new Intent(this, ViewReceipt.class);
        replyIntent.putExtra(EXTRA_REPLY, newParking);
        setResult(RESULT_OK, replyIntent);
        replyIntent.putExtra("EXTRA_CARPLATE", carPlateNo);
        finish();
        startActivity(replyIntent);



    }

    private boolean validateData() {
        boolean allValidations = true;

        if (edt_BuildingCode.getText().toString().isEmpty()) {
            edt_BuildingCode.setError("You must enter Building Code");
            allValidations = false;
        }

        if (edt_noOfHours.getText().toString().equals("0")) {
            edt_noOfHours.setError("You cannot park for 0 hours");
            allValidations = false;
        }

        if (edt_carPlateNo.getText().toString().isEmpty()) {
            edt_carPlateNo.setError("You must provide car number plate");
            allValidations = false;
        }


        if (edt_suitNo.getText().toString().isEmpty()) {
            edt_suitNo.setError("Suit No cannot be empty");
            allValidations = false;
        }
        return allValidations;
    }



}
