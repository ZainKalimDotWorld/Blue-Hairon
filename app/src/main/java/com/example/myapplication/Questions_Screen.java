package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class Questions_Screen extends AppCompatActivity {

    TextView textview;
    SweetAlertDialog pdialog;
    String s1;
    int int_s1;

    int valuess=0;
    ImageView imageView1,imageView25,imageView3,imageView4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions__screen);

        textview=findViewById(R.id.textview);

        imageView1=findViewById(R.id.imageView1);
        imageView25=findViewById(R.id.imageView25);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valuess=1;
                Log.d("Valuess" , ""+valuess);

            }
        });



        imageView25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                valuess=2;
                Log.d("Valuess" , ""+valuess);
            }
        });




        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                valuess=3;
                Log.d("Valuess" , ""+valuess);

            }
        });




        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                valuess=4;
                Log.d("Valuess" , ""+valuess);

            }
        });



        getquestions();
    }
//    value7

    private void getquestions()
    {
        pdialog = Utilss.showSweetLoader(Questions_Screen.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Questions...");

        RequestQueue queue = Volley.newRequestQueue(Questions_Screen.this);
        String url = "http://api.surveymenu.dwtdemo.com/api/en/feedback/"+MainActivity.value7+"/question";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pdialog);
                            }
                        });

                        Log.d("Json-response1", response);

                        try {
                            JSONObject respone = new JSONObject(response);
                            JSONObject respones = respone.getJSONObject("data");
                            JSONArray jsonArray = respones.getJSONArray("questions");
//

//                            JSONObject jsonObject = jsonArray.optJSONObject();
//                            String s1 = jsonObject.getString("question");
//                            textview.setText(s1);
//                            Log.d("Quesionsss" , s1);



                          for (int i = 0; i < jsonArray.length(); i++)
                            {
//                                JSONObject jsonObject = jsonArray.optJSONObject(String.valueOf(jsonArray.get(i)));

                            }

                            JSONObject jsonObject = jsonArray.optJSONObject(0);
                             s1 = jsonObject.getString("question");
                             int_s1 = jsonObject.getInt("id");

                            textview.setText(s1);
                            Log.d("Quesionsss" , s1  +   "    " + int_s1);

//
//                            for (int i = 0; i < jsonArray.length(); i++)
//                            {
////                                JSONObject jsonObject = jsonArray.optJSONObject(String.valueOf(jsonArray.get(i)));
//
//                            }

//                            {
//                                String s1 = jsonObject.getString("question");
//                                Log.d("Quesionsss" , s1);
//                            }

//                            //



                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }



                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utilss.hideSweetLoader(pdialog);

                        Log.d("Failure" , error.toString());

                    }
                });
            }
        }){
            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
                headers.put("Authorization", "Bearer "+MainActivity.value2);
                return headers;
            }
        };
        queue.add(stringRequest);







    }
}
