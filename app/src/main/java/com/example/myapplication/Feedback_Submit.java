package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Feedback_Submit extends AppCompatActivity {


    EditText customer_info;
    Button signin;
    String ki;
    int o;
    String ik;
    MyData[] myData;
//    int[] ratings;

String emails_intent_final;
int contact_intent_final;
    int go;
    int[] ratings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback__submit);



        ratings = getIntent().getIntArrayExtra("Total_Things_Data");
        o=  getIntent().getIntExtra("Total_Things" , 0);

        String go = getIntent().getStringExtra("stock_list");


        emails_intent_final = getIntent().getStringExtra("Email_Value");
        contact_intent_final = getIntent().getIntExtra("Contact_Value" , 0);

        Gson gson = new Gson();
        myData = gson.fromJson(go, MyData[].class);
//        ratings = new int[myData.length];
        Log.d("Array22" , ""+ratings);





        customer_info = findViewById(R.id.customer_info);
        signin = findViewById(R.id.signin);






        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitResults(ratings);
            }
        });


    }

    public void submitResults(int[] ratings )
    {

        ki = customer_info.getText().toString();
        JSONObject level1 = new JSONObject();         // Included object
        try {

            level1.put("feedback_id", MainActivity.value7);
            level1.put("lang", "en");
            JSONObject level3 = new JSONObject();
            level3.put("email",emails_intent_final);
            level3.put("contact",contact_intent_final);
            level1.put("customer",level3);
            JSONArray myArray = new JSONArray();
            for(int i = 0; i< o; i++)
            {


                JSONObject level2 = new JSONObject();
                level2.put("question_id",myData[i].getId());
                Log.e("Questiion_ID", myData[i].getId().toString());
                level2.put("response_id",ratings[i]);
                Log.e("Rating", String.valueOf(ratings[i]));
                myArray.put(level2);
            }
            level1.put("Rating",myArray);
            level1.put("comment",ki);




        } catch (JSONException e) {
            Log.e("Json Error", e.toString());
        }

        Log.e("MyResult Request",level1.toString());
        String myUrl = "http://api.surveymenu.dwtdemo.com/api/feedback/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, myUrl, level1, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Feedback_Submit.this, response.toString(), Toast.LENGTH_SHORT).show();
                Log.e("Success",response.toString());

                Intent intent = new Intent(Feedback_Submit.this , ThankuActivity.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Feedback_Submit.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("Error",error.toString());

            }
        }){

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer "+MainActivity.value2);
                Log.e("Token", MainActivity.value2);
                //headers.put("Content-Type", "application/json");
                headers.put("User-Agent", "PostmanRuntime/7.17.1");
                headers.put("Accept", "*/*");
                headers.put("Cache-Control", "no-cache");
                headers.put("Postman-Token", "6d6adfd9-12e3-4860-8cc2-ff458425702e,a4278764-20f9-4756-9edc-f5f1ceb629ec");
                headers.put("Host", "api.surveymenu.dwtdemo.com");
                headers.put("Accept-Encoding", "gzip, deflate");
                headers.put("Connection", "keep-alive");
                headers.put("cache-control", "no-cache");




                return headers;
            }};
        Singleton.getInstance(this).getRequestQueue().add(jsonObjectRequest);

    }




    }

