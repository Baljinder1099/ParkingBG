package com.example.parkingbg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 14-11-2019
 */
public class SignIn extends AppCompatActivity implements View.OnClickListener {

    EditText edtUsername;
    EditText edtPassword;
    Button btnSignIn;
    TextView txtSignUp;
    Switch swtRemember;
    TextView txtForgetPassword;

    String username = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        this.referWidgets();
    }

    void referWidgets(){
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

        txtSignUp = findViewById(R.id.txtSignUp);
        txtSignUp.setOnClickListener(this);

        swtRemember = findViewById(R.id.swtRemember);
        txtForgetPassword = findViewById(R.id.txtForgetPassword);
        txtForgetPassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignIn:
                //signin operation
                this.signIn();
                break;
            case R.id.txtSignUp:
                //sign up operation
                this.signUp();
                break;
            case R.id.txtForgetPassword:
                //forget password operation
                this.resetPassword();
                break;
        }

    }
}
