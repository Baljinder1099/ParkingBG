package com.example.parkingbg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.parkingbg.model.User;
import com.example.parkingbg.viewModel.UserViewModel;

import java.util.List;

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

    UserViewModel userViewModel;

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final int SIGN_UP_REQUEST_CODE = 1;
    public  static final String USER_PREF = "com.example.parkingbg.userpref";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userViewModel = new UserViewModel(getApplication());
        this.referWidgets();
        userViewModel = new UserViewModel(getApplication());
        userViewModel.getAllUsers().observe(SignIn.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users){
                    Log.e("SignIn", user.toString());
                }
            }
        });


        this.getRememberedData();

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

    void signIn(){
        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();

//        if (username.equals("test") && password.equals("test")){
        if (this.authenticateUser(username, password)){

            if (swtRemember.isChecked()){
                this.rememberData();
            }else{
                this.forgetData();
            }
            //login successful

            Toast.makeText(this, "Login successful",Toast.LENGTH_LONG).show();
            this.openHomeActivity();
        }else{
            //login unsuccessful
            Toast.makeText(this, "Incorrect Username/Password ! Try again.",Toast.LENGTH_LONG).show();
        }
    }

    void resetPassword(){

    }

    void signUp(){
        Intent signUpIntent = new Intent(SignIn.this, SignUpActivity.class);
        startActivityForResult(signUpIntent, SIGN_UP_REQUEST_CODE);
    }

    private boolean authenticateUser(String username, String password){
        List<User> allUsers = userViewModel.getAllUsers().getValue();

        for(User user: allUsers){
            if (user.getEmail().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }

    private void rememberData(){
        SharedPreferences sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);

        sp.edit().putString(USERNAME, edtUsername.getText().toString()).commit();
        sp.edit().putString(PASSWORD, edtPassword.getText().toString()).commit();
    }

    private void forgetData(){
        SharedPreferences sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        //to delete all preferences
        sp.edit().clear().commit();

        //to delete specific preference
//        sp.edit().remove(PASSWORD).commit();
    }

    void openHomeActivity(){
        Intent mainIntent = new Intent(SignIn.this, HomeActivity.class);
        startActivity(mainIntent);
    }

    private void getRememberedData(){
        SharedPreferences sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);

        edtUsername.setText(sp.getString(USERNAME, ""));
        edtPassword.setText(sp.getString(PASSWORD, ""));
    }
}
