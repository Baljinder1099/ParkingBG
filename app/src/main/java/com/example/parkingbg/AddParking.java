package com.example.parkingbg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkingbg.model.Parking;
import com.example.parkingbg.model.User;
import com.example.parkingbg.viewModel.UserViewModel;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
                this.createParkingAndReply();

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

        Log.d("AddParking", newParking.toString());

        //reply to previous intent
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, newParking);
        setResult(RESULT_OK, replyIntent);
        finish();

    }

//    private boolean validateData(){
//        boolean allValidations = true;
//
//        if (edt_BuildingCode.getText().toString().isEmpty()){
//            edt_BuildingCode.setError("You must enter Building Code");
//            allValidations = false;
//        }
//
//        if (edtLname.getText().toString().isEmpty()){
//            edtLname.setError("You must enter last name");
//            allValidations = false;
//        }
//
//        if (edtPhone.getText().toString().isEmpty()){
//            edtPhone.setError("You must provide phone number");
//            allValidations = false;
//        }
//
//
//        if (edtEmail.getText().toString().isEmpty()){
//            edtEmail.setError("Email cannot be empty");
//            allValidations = false;
//        }else if (!Utils.isValidEmail(edtEmail.getText().toString())){
//            edtEmail.setError("Please provide valid email address");
//            allValidations = false;
//        }
//
//        if (edtPswd.getText().toString().isEmpty()){
//            edtPswd.setError("Please enter password");
//            allValidations = false;
//        }
//
//        if (edtConfirm.getText().toString().isEmpty()){
//            edtConfirm.setError("You must enter confirm password");
//            allValidations = false;
//        }else if (!edtPswd.getText().toString().equals(edtConfirm.getText().toString())){
//            edtConfirm.setError("Both passwords must be same");
//            allValidations = false;
//        }
//
//        if (edtPlateNo.getText().toString().isEmpty()){
//            edtPlateNo.setError("You must enter plate number");
//            allValidations = false;
//        }
//
//        if (edtCardNo.getText().toString().isEmpty()){
//            edtCardNo.setError("You must enter card number");
//            allValidations = false;
//        }
//
//        if (edtExpiry.getText().toString().isEmpty()){
//            edtExpiry.setError("You must enter expiry date");
//            allValidations = false;
//        }
//
//        if (edtNameOnCard.getText().toString().isEmpty()){
//            edtNameOnCard.setError("You must enter the name on card");
//            allValidations = false;
//        }
//
//        if (edtCvv.getText().toString().isEmpty()){
//            edtCvv.setError("You must enter valid CVV");
//            allValidations = false;
//        }
//
//        return allValidations;
//    }
}
