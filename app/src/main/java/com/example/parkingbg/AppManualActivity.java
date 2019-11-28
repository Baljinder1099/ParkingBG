package com.example.parkingbg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AppManualActivity extends AppCompatActivity {

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        //final WebView mWebView = root.findViewById(R.id.webView);

        final WebView mWebView = findViewById(R.id.webView);

        mWebView.getSettings().setJavaScriptEnabled(true);

        //to open the web page in Application fragment and not in browser
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e("activity_manual", error.getDescription().toString());
            }
        });

//        mWebView.loadUrl("http://www.google.com/search?q=SheridanCollege");
        mWebView.loadUrl("file:///android_asset/html/app_manual.html");



    }




}
