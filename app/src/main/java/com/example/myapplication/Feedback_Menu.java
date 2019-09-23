package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Feedback_Menu extends AppCompatActivity {

    EditText customer_info,customer_info2;
    Button home,next;
    String email_validation,contact_validation;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback__menu);


        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(Feedback_Menu.this,R.color.colorback));


        customer_info = findViewById(R.id.customer_info);
        customer_info2 = findViewById(R.id.customer_info2);
        home=findViewById(R.id.home);
        next=findViewById(R.id.next);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent( Feedback_Menu.this , Value_Feedback.class);
                startActivity(intent);
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_validation = customer_info.getText().toString();
                contact_validation = customer_info2.getText().toString();

                if (email_validation.equals("") && contact_validation.equals("")) {
                    SweetAlertDialog pDialog = new SweetAlertDialog(Feedback_Menu.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Fill Up The Fields..!!");
                    pDialog.setCancelable(true);
                    pDialog.show();
                    return;
                } else if (contact_validation.equals("")) {
                    SweetAlertDialog pDialog = new SweetAlertDialog(Feedback_Menu.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Contact Number Is Required..!!");
                    pDialog.setCancelable(true);
                    pDialog.show();
                    return;
                }
                else
                {
                    customer_info.setText("");
                    customer_info2.setText("");

                    Intent intent = new Intent( Feedback_Menu.this , Questions_Screen.class);
                    startActivity(intent);
                }
            }
        });
    }
}
