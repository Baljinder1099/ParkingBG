package com.example.parkingbg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkingbg.model.User;
import com.example.parkingbg.viewModel.UserViewModel;

/**
 * ParkingBG created by gursharansandhu
 * Student ID : 991544576
 * on 14-11-2019
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String password;
    String plateNo;
    String cardNo;
    String expiry;
    String nameOnCard;
    String cvv;

    EditText edtFname;
    EditText edtLname;
    EditText edtPhone;
    EditText edtEmail;
    EditText edtPswd;
    EditText edtConfirm;
    EditText edtPlateNo;
    EditText edtCardNo;
    EditText edtExpiry;
    EditText edtNameOnCard;
    EditText edtCvv;

    Button btnSubmit;

    UserViewModel userViewModel;
    public static final String EXTRA_REPLY = "com.example.parkingbg.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.referWidgets();
    }

    private void referWidgets(){
        edtFname = findViewById(R.id.edt_firstname);
        edtLname = findViewById(R.id.edt_lastname);
        edtPhone = findViewById(R.id.edt_phone);
        edtEmail = findViewById(R.id.edt_email);
        edtPswd = findViewById(R.id.edt_password);
        edtConfirm = findViewById(R.id.edt_confirm_password);
        edtPlateNo = findViewById(R.id.edt_plateNo);
        edtCardNo = findViewById(R.id.edt_cardNo);
        edtExpiry = findViewById(R.id.edt_expiry);
        edtNameOnCard = findViewById(R.id.edt_NameOnCard);
        edtCvv = findViewById(R.id.edt_cvv);


        btnSubmit = findViewById(R.id.btn_Submit);
        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Submit:
                if (this.validateData()){
                    this.createUserAndReply();
                }
                break;
        }
    }

    private void createUserAndReply(){
        firstName = edtFname.getText().toString();
        lastName = edtLname.getText().toString();
        phoneNumber = edtPhone.getText().toString();
        email = edtEmail.getText().toString();
        password = edtPswd.getText().toString();
        plateNo = edtPlateNo.getText().toString();
        cardNo = edtCardNo.getText().toString();
        expiry = edtExpiry.getText().toString();
        nameOnCard = edtNameOnCard.getText().toString();
        cvv = edtCvv.getText().toString();

        User newUser = new User(firstName, lastName, phoneNumber, email, password,
                plateNo, cardNo, expiry, nameOnCard, cvv);
        //userViewModel.insert(newUser);

        Log.d("SignUpActivity", newUser.toString());

        //reply to previous intent
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, newUser);
        setResult(RESULT_OK, replyIntent);
        finish();

    }

    private boolean validateData(){
        boolean allValidations = true;

        if (edtFname.getText().toString().isEmpty()){
            edtFname.setError("You must enter first name");
            allValidations = false;
        }

        if (edtLname.getText().toString().isEmpty()){
            edtLname.setError("You must enter last name");
            allValidations = false;
        }

        if (edtPhone.getText().toString().isEmpty()){
            edtPhone.setError("You must provide phone number");
            allValidations = false;
        }


        if (edtEmail.getText().toString().isEmpty()){
            edtEmail.setError("Email cannot be empty");
            allValidations = false;
        }else if (!Utils.isValidEmail(edtEmail.getText().toString())){
            edtEmail.setError("Please provide valid email address");
            allValidations = false;
        }

        if (edtPswd.getText().toString().isEmpty()){
            edtPswd.setError("Please enter password");
            allValidations = false;
        }

        if (edtConfirm.getText().toString().isEmpty()){
            edtConfirm.setError("You must enter confirm password");
            allValidations = false;
        }else if (!edtPswd.getText().toString().equals(edtConfirm.getText().toString())){
            edtConfirm.setError("Both passwords must be same");
            allValidations = false;
        }

        if (edtPlateNo.getText().toString().isEmpty()){
            edtPlateNo.setError("You must enter plate number");
            allValidations = false;
        }

        if (edtCardNo.getText().toString().isEmpty()){
            edtCardNo.setError("You must enter card number");
            allValidations = false;
        }

        if (edtExpiry.getText().toString().isEmpty()){
            edtExpiry.setError("You must enter expiry date");
            allValidations = false;
        }

        if (edtNameOnCard.getText().toString().isEmpty()){
            edtNameOnCard.setError("You must enter the name on card");
            allValidations = false;
        }

        if (edtCvv.getText().toString().isEmpty()){
            edtCvv.setError("You must enter valid CVV");
            allValidations = false;
        }

        return allValidations;
    }




}
