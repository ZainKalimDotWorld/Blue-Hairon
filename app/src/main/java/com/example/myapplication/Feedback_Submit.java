package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.myapplication.Feedback_Menu.swToggle;

public class Feedback_Submit extends AppCompatActivity {


    EditText customer_info;
    Button signin;
    String ki,ki2;
    int o;
    String ik;
    MyData[] myData;
    Button home_btn,signin2;
//    int[] ratings;

String emails_intent_final;
int contact_intent_final;
    int go;
    int[] ratings;
    SweetAlertDialog pdialog;
    boolean bool_value;
String user_Name;
Boolean toggle_state;
    TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int orientation = this.getResources().getConfiguration().orientation;


        if (orientation== Configuration.ORIENTATION_PORTRAIT)
        {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            //portrait
            setContentView(R.layout.activity_feedback__submit_portrait);
            textview = findViewById(R.id.textview);


            ratings = getIntent().getIntArrayExtra("Total_Things_Data");
            o = getIntent().getIntExtra("Total_Things", 0);

            String go = getIntent().getStringExtra("stock_list");


            emails_intent_final = getIntent().getStringExtra("Email_Value");
            contact_intent_final = getIntent().getIntExtra("Contact_Value", 0);

            Gson gson = new Gson();
            myData = gson.fromJson(go, MyData[].class);
//        ratings = new int[myData.length];
            Log.d("Array22", "" + ratings);



//            if(savedInstanceState!=null)
//            {
//                user_Name = savedInstanceState.getString("userName");
//
//                if (customer_info!=null)
//                {
//                    customer_info.setText(user_Name);
//                }


//                toggle_state = savedInstanceState.getBoolean("toggle_state");
////                swToggle.setOn(toggle_state);
//                if (toggle_state)
//                {
//                    setContentView(R.layout.activity_feedback_submit_arabic);
//
//                    customer_info = findViewById(R.id.customer_info);
//                    signin2 = findViewById(R.id.signin2);
//                    home_btn = findViewById(R.id.home_btn);
//
//
//                    home_btn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            Intent intent = new Intent(Feedback_Submit.this, Value_Feedback.class);
//                            startActivity(intent);
//                            finish();
//
//                        }
//                    });
//
//                    signin2.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            ki2 = customer_info.getText().toString();
//                            submitResults2(ratings, ki2);
//                        }
//                    });
//                }
//
//                else
//                {
//                    setContentView(R.layout.activity_feedback__submit_portraits);
//                    customer_info = findViewById(R.id.customer_info);
//                    signin2 = findViewById(R.id.signin2);
//                    home_btn = findViewById(R.id.home_btn);
//
//
//                    home_btn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            Intent intent = new Intent(Feedback_Submit.this, Value_Feedback.class);
//                            startActivity(intent);
//                            finish();
//
//                        }
//                    });
//
//                    signin2.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            ki2 = customer_info.getText().toString();
//                            submitResults2(ratings, ki2);
//                        }
//                    });
//                }
//
//
//            }





            if (swToggle.isOn())
            {
                setContentView(R.layout.activity_feedback_submit_arabic);

                customer_info = findViewById(R.id.customer_info);
                signin2 = findViewById(R.id.signin2);
                home_btn = findViewById(R.id.home_btn);


                home_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Feedback_Submit.this, Value_Feedback.class);
                        startActivity(intent);
                        finish();

                    }
                });

                signin2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ki2 = customer_info.getText().toString();
                        submitResults2(ratings, ki2);
                    }
                });
            }

            else if (!swToggle.isOn())
            {
                setContentView(R.layout.activity_feedback__submit_portraits);
                customer_info = findViewById(R.id.customer_info);
                signin2 = findViewById(R.id.signin2);
                home_btn = findViewById(R.id.home_btn);


                home_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Feedback_Submit.this, Value_Feedback.class);
                        startActivity(intent);
                        finish();

                    }
                });

                signin2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ki2 = customer_info.getText().toString();
                        submitResults2(ratings, ki2);
                    }
                });
            }
            }


//        bool_value = getIntent().getExtras().getBoolean("Toggle_State");
//        Log.e("Bool_Value2", ""+bool_value);



else if (orientation == Configuration.ORIENTATION_LANDSCAPE)
{
    //landscape
    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    setContentView(R.layout.activity_feedback__submit);

    textview = findViewById(R.id.textview);


    ratings = getIntent().getIntArrayExtra("Total_Things_Data");
    o = getIntent().getIntExtra("Total_Things", 0);

    String go = getIntent().getStringExtra("stock_list");


    emails_intent_final = getIntent().getStringExtra("Email_Value");
    contact_intent_final = getIntent().getIntExtra("Contact_Value", 0);

    Gson gson = new Gson();
    myData = gson.fromJson(go, MyData[].class);
//        ratings = new int[myData.length];
    Log.d("Array22", "" + ratings);


    if(savedInstanceState!=null)
    {
        user_Name = savedInstanceState.getString("userName");

        if (customer_info!=null)
        {
            customer_info.setText(user_Name);
        }


        toggle_state = savedInstanceState.getBoolean("toggle_state");
//                swToggle.setOn(toggle_state);
        if (toggle_state)
        {
            setContentView(R.layout.activity_feedback_submit_arabic);

            customer_info = findViewById(R.id.customer_info);
            signin2 = findViewById(R.id.signin2);
            home_btn = findViewById(R.id.home_btn);


            home_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Feedback_Submit.this, Value_Feedback.class);
                    startActivity(intent);
                    finish();

                }
            });

            signin2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ki2 = customer_info.getText().toString();
                    submitResults2(ratings, ki2);
                }
            });
        }

        else
        {
            setContentView(R.layout.activity_feedback__submit_portraits);
            customer_info = findViewById(R.id.customer_info);
            signin2 = findViewById(R.id.signin2);
            home_btn = findViewById(R.id.home_btn);


            home_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Feedback_Submit.this, Value_Feedback.class);
                    startActivity(intent);
                    finish();

                }
            });

            signin2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ki2 = customer_info.getText().toString();
                    submitResults2(ratings, ki2);
                }
            });
        }


    }




    if (swToggle.isOn()) {
        setContentView(R.layout.activity_feedback_submit_arabic_landss);

        customer_info = findViewById(R.id.customer_info);
        signin2 = findViewById(R.id.signin2);
        home_btn = findViewById(R.id.home_btn);


        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Feedback_Submit.this, Value_Feedback.class);
                startActivity(intent);
                finish();

            }
        });

        signin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ki2 = customer_info.getText().toString();
                submitResults2(ratings, ki2);
            }
        });
    }


    else if (!swToggle.isOn())
    {

        setContentView(R.layout.activity_feedback__submit);

        customer_info = findViewById(R.id.customer_info);
        signin2 = findViewById(R.id.signin2);
        home_btn = findViewById(R.id.home_btn);


        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Feedback_Submit.this, Value_Feedback.class);
                startActivity(intent);
                finish();

            }
        });

        signin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ki2 = customer_info.getText().toString();
                submitResults2(ratings, ki2);
            }
        });


    }
}




//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                submitResults(ratings,ki);
//            }
//        });
    }


//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig)
//    {
//        super.onConfigurationChanged(newConfig);
//
//        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
//        {
//            setContentView(R.layout.activity_feedback__submit_portrait);
//
//
//            textview = findViewById(R.id.textview);
//
//
//            ratings = getIntent().getIntArrayExtra("Total_Things_Data");
//            o = getIntent().getIntExtra("Total_Things", 0);
//
//            String go = getIntent().getStringExtra("stock_list");
//
//
//            emails_intent_final = getIntent().getStringExtra("Email_Value");
//            contact_intent_final = getIntent().getIntExtra("Contact_Value", 0);
//
//            Gson gson = new Gson();
//            myData = gson.fromJson(go, MyData[].class);
////        ratings = new int[myData.length];
//            Log.d("Array22", "" + ratings);
//
//
//            if (swToggle.isOn()) {
//                setContentView(R.layout.activity_feedback_submit_arabic);
//
//                customer_info = findViewById(R.id.customer_info);
//                signin2 = findViewById(R.id.signin2);
//                home_btn = findViewById(R.id.home_btn);
//
//
//                home_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent = new Intent(Feedback_Submit.this, Value_Feedback.class);
//                        startActivity(intent);
//                        finish();
//
//                    }
//                });
//
//                signin2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        ki2 = customer_info.getText().toString();
//                        submitResults2(ratings, ki2);
//                    }
//                });
//            }
//
//
//            else if (!swToggle.isOn())
//            {
//
//                setContentView(R.layout.activity_feedback__submit);
//
//                textview = findViewById(R.id.textview);
//
//
//                ratings = getIntent().getIntArrayExtra("Total_Things_Data");
//                o = getIntent().getIntExtra("Total_Things", 0);
//
//                go = getIntent().getStringExtra("stock_list");
//
//
//                emails_intent_final = getIntent().getStringExtra("Email_Value");
//                contact_intent_final = getIntent().getIntExtra("Contact_Value", 0);
//
//                gson = new Gson();
//                myData = gson.fromJson(go, MyData[].class);
////        ratings = new int[myData.length];
//                Log.d("Array22", "" + ratings);
//
//
//
//            }
//
//
//
//
//
//        }
//
//        else
//        {
//            setContentView(R.layout.activity_feedback__submit);
//
//
//            textview = findViewById(R.id.textview);
//
//
//            ratings = getIntent().getIntArrayExtra("Total_Things_Data");
//            o = getIntent().getIntExtra("Total_Things", 0);
//
//            String go = getIntent().getStringExtra("stock_list");
//
//
//            emails_intent_final = getIntent().getStringExtra("Email_Value");
//            contact_intent_final = getIntent().getIntExtra("Contact_Value", 0);
//
//            Gson gson = new Gson();
//            myData = gson.fromJson(go, MyData[].class);
////        ratings = new int[myData.length];
//            Log.d("Array22", "" + ratings);
//
//
//            if (swToggle.isOn()) {
//                setContentView(R.layout.activity_feedback_submit_arabic);
//
//                customer_info = findViewById(R.id.customer_info);
//                signin2 = findViewById(R.id.signin2);
//                home_btn = findViewById(R.id.home_btn);
//
//
//                home_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent = new Intent(Feedback_Submit.this, Value_Feedback.class);
//                        startActivity(intent);
//                        finish();
//
//                    }
//                });
//
//                signin2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        ki2 = customer_info.getText().toString();
//                        submitResults2(ratings, ki2);
//                    }
//                });
//            }
//
//
//            else if (!swToggle.isOn())
//            {
//
//                setContentView(R.layout.activity_feedback__submit);
//
//                textview = findViewById(R.id.textview);
//
//
//                ratings = getIntent().getIntArrayExtra("Total_Things_Data");
//                o = getIntent().getIntExtra("Total_Things", 0);
//
//                go = getIntent().getStringExtra("stock_list");
//
//
//                emails_intent_final = getIntent().getStringExtra("Email_Value");
//                contact_intent_final = getIntent().getIntExtra("Contact_Value", 0);
//
//                gson = new Gson();
//                myData = gson.fromJson(go, MyData[].class);
////        ratings = new int[myData.length];
//                Log.d("Array22", "" + ratings);
//
//
//
//            }
//        }
//
//
//
//    }
//
//
//
//
//    public void submitResults(int[] ratings , String comments)
//    {
//        pdialog = Utilss.showSweetLoader(Feedback_Submit.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
//
//        ki = customer_info.getText().toString();
//        JSONObject level1 = new JSONObject();         // Included object
//        try {
//
//            level1.put("feedback_id", MainActivity.value7);
//            level1.put("lang", "en");
//            JSONObject level3 = new JSONObject();
//            level3.put("email",emails_intent_final);
//            level3.put("contact",contact_intent_final);
//            level1.put("customer",level3);
//            JSONArray myArray = new JSONArray();
//            for(int i = 0; i< o; i++)
//            {
//
//
//                JSONObject level2 = new JSONObject();
//                level2.put("question_id",myData[i].getId());
//                Log.e("Questiion_ID", myData[i].getId().toString());
//                level2.put("response_id",ratings[i]);
//                Log.e("Rating", String.valueOf(ratings[i]));
//                myArray.put(level2);
//            }
//            level1.put("Rating",myArray);
//            level1.put("comment",comments);
//
//
//
//
//        } catch (JSONException e) {
//            Log.e("Json Error", e.toString());
//        }
//
//        Log.e("MyResult Request",level1.toString());
//        String myUrl = "http://api.surveymenu.dwtdemo.com/api/feedback/";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, myUrl, level1, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
////                Toast.makeText(Feedback_Submit.this, response.toString(), Toast.LENGTH_SHORT).show();
//                Log.e("Success",response.toString());
//
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        Utilss.hideSweetLoader(pdialog);
//                    }
//                });
//                Intent intent = new Intent(Feedback_Submit.this , ThankuActivity.class);
//                intent.putExtra("Bools" , bool_value);
//                startActivity(intent);
//                finish();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Feedback_Submit.this, error.toString(), Toast.LENGTH_SHORT).show();
//                Log.e("Error",error.toString());
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        Utilss.hideSweetLoader(pdialog);
//                    }
//                });
//
//            }
//        }){
//
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> headers = new HashMap<>();
//                headers.put("Authorization", "Bearer "+MainActivity.value2);
//                Log.e("Token", MainActivity.value2);
//                //headers.put("Content-Type", "application/json");
//                headers.put("User-Agent", "PostmanRuntime/7.17.1");
//                headers.put("Accept", "*/*");
//                headers.put("Cache-Control", "no-cache");
//                headers.put("Postman-Token", "6d6adfd9-12e3-4860-8cc2-ff458425702e,a4278764-20f9-4756-9edc-f5f1ceb629ec");
//                headers.put("Host", "api.surveymenu.dwtdemo.com");
//                headers.put("Accept-Encoding", "gzip, deflate");
//                headers.put("Connection", "keep-alive");
//                headers.put("cache-control", "no-cache");
//
//
//
//
//                return headers;
//            }};
//        Singleton.getInstance(this).getRequestQueue().add(jsonObjectRequest);
//
//    }





//    @Override
//    protected void onSaveInstanceState (Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("userName", customer_info.getText().toString());
////        outState.putString("password", customer_info2.getText().toString());
//        outState.putBoolean("toggle_state", swToggle.isOn());
////        Log.d("MyValuesss" , "Imhere" + "" +  swToggle.isOn());
//    }


    public void submitResults2(int[] ratings ,String comments)
    {
        pdialog = Utilss.showSweetLoader(Feedback_Submit.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

        ki = customer_info.getText().toString();
        JSONObject level1 = new JSONObject();         // Included object
        try {

            level1.put("feedback_id", MainActivity.value7);
            level1.put("lang", "ar");
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
            level1.put("comment",comments);




        } catch (JSONException e) {
            Log.e("Json Error", e.toString());
        }

        Log.e("MyResult Request2",level1.toString());
        String myUrl = "http://api.surveymenu.dwtdemo.com/api/feedback/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, myUrl, level1, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Toast.makeText(Feedback_Submit.this, response.toString(), Toast.LENGTH_SHORT).show();
                Log.e("Success2",response.toString());


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Utilss.hideSweetLoader(pdialog);
                    }
                });
                Intent intent = new Intent(Feedback_Submit.this , ThankuActivity.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Feedback_Submit.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("Error",error.toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Utilss.hideSweetLoader(pdialog);
                    }
                });

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

    public void onBackPressed() {
//        Intent intent = new Intent(Product_Detail.this, ProductsActivity.class);
//        finish();
//        startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}

