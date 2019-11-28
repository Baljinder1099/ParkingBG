package com.example.parkingbg;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * ParkingBG created by gursharansandhu
 * Student ID : 991544576
 * on 2019-11-27
 */
public class SupportActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnCall;
    Button btnEmail;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(this);

        btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCall:
                this.makeCall();
                break;
            case R.id.btnEmail:
                this.sendEmail();
                break;
        }
    }

    private void makeCall(){
        //create an intent service to perform call operation
        Intent callIntent = new Intent(Intent.ACTION_CALL);

        //which number do you want to call
        callIntent.setData(Uri.parse("tel:1234567890"));

        ActivityCompat.requestPermissions(SupportActivity.this,
                new String[]{Manifest.permission.CALL_PHONE}, 1);
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Log.e("SupportActivity", "Call permission not granted");
            return;
        }

        startActivity(callIntent);

    }

    private void sendEmail(){

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        //set the type of content in email
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"gursharansandhu809@gmail.com"});

        //subject
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"test email");

        //email content
        emailIntent.putExtra(Intent.EXTRA_TEXT, "This is a text message send it to your own email address");

        emailIntent.setType("message/rfc822");

        //provide options for email services available on device
        startActivity(Intent.createChooser(emailIntent,"Please select email client"));

    }
}

