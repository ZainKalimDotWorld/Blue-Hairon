package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ThankuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanku);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(ThankuActivity.this, Value_Feedback.class);
                startActivity(mainIntent);
                finish();
            }
        }, 5000);


    }

    public void onBackPressed() {
//        Intent intent = new Intent(Product_Detail.this, ProductsActivity.class);
//        finish();
//        startActivity(intent);
    }
}
