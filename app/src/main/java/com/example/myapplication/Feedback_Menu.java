package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
                    loadServey();
                }
            }
        });
    }



    public void  loadServey()
    {
        String makeUrl = "http://api.surveymenu.dwtdemo.com/api/en/feedback/"+MainActivity.value7+"/question";
        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.GET, makeUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("HttpClient", "success! response: " + response.toString());
                try {
                    JSONObject mRes = response.getJSONObject("data");

                    Intent i = new Intent(getApplicationContext(),Questions_Screen.class);
                    i.putExtra("mylist",  mRes.getString("questions"));
                    i.putExtra("FeedbackId", MainActivity.value7);
                    i.putExtra("token", MainActivity.value2);



                    i.putExtra("email", email_validation);
                    i.putExtra("phone", contact_validation);



                    startActivity(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer "+MainActivity.value2);
                headers.put("Accept", "*/*");
                headers.put("Cache-Control", "no-cache");

                headers.put("Host", "api.surveymenu.dwtdemo.com");
                headers.put("Accept-Encoding", "gzip, deflate");
                headers.put("cache-control", "no-cache");

                return headers;
            }
        };

        Singleton.getInstance(this).getRequestQueue().add(sr);
    }






}
