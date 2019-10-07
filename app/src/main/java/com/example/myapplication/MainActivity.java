package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.droidnet.DroidListener;
import com.droidnet.DroidNet;
import com.splunk.mint.Mint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static android.R.layout.simple_spinner_item;
import static com.example.myapplication.Feedback_Menu.swToggle;

//implements AdapterView.OnItemSelectedListener , DroidListener

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener , DroidListener {

    private ArrayList<String> names7 = new ArrayList<String>();

    String password;
    String spinnervalue;

    ImageView imageView4;

    private ArrayList<String> names8 = new ArrayList<String>();
    private ArrayList<String> names9 = new ArrayList<String>();

    Spinner spLeaveSubject2;
    public ArrayList<Pojo> lstAnime = new ArrayList<Pojo>();


    public ArrayList<Pojo> lstAnime2 = new ArrayList<Pojo>();
    public ArrayList<Pojo> lstAnim3 = new ArrayList<Pojo>();

    SweetAlertDialog pDialogss;
    SweetAlertDialog pdialog;
    ArrayAdapter<String> spinnerArrayAdapter;
    OkHttpClient client;
    JSONObject json;
    static String value2, value7, value24;
    String v1;
    EditText customer_info;
    private DroidNet mDroidNet;
    Button signin;
    int v11;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        int orientation = this.getResources().getConfiguration().orientation;


if (orientation == Configuration.ORIENTATION_LANDSCAPE)
{

    //landscape
    setContentView(R.layout.activity_main_portrait);

    Mint.initAndStartSession(this.getApplication(), "8566b133");
    mDroidNet = DroidNet.getInstance();
    mDroidNet.addInternetConnectivityListener(this);

    client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
    lstAnime = new ArrayList<>();


    spLeaveSubject2 = (Spinner) findViewById(R.id.spLeaveSubject2);
    customer_info = (EditText) findViewById(R.id.customer_info);
    spLeaveSubject2.setOnItemSelectedListener(MainActivity.this);

    signin = (Button) findViewById(R.id.signin);

    imageView4 = (ImageView) findViewById(R.id.imageView4);

    imageView4.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {

            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.custom_dialogss, null);

            android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(MainActivity.this);

            Button image_portrait = layout.findViewById(R.id.portrait);
            Button image_landscape = layout.findViewById(R.id.landscape);
            aDialog.setView(layout);
            final android.app.AlertDialog ad = aDialog.create();
            ad.show();


            image_landscape.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                    setContentView(R.layout.activity_main_portrait);
                    ad.dismiss();
//                    Toast.makeText(MainActivity.this, "Landscape1", Toast.LENGTH_SHORT).show();
                }
            });


            image_portrait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    setContentView(R.layout.activity_main);
//                    Toast.makeText(MainActivity.this, "Portrait1", Toast.LENGTH_SHORT).show();
                    ad.dismiss();
                }
            });
        }
    });



    signin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            loginapi_landscape();
        }
    });

    retreivebranches2();

}






else
{

    //PORTRAIT
    setContentView(R.layout.activity_main);

    Mint.initAndStartSession(this.getApplication(), "8566b133");
    mDroidNet = DroidNet.getInstance();
    mDroidNet.addInternetConnectivityListener(this);

    client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
    lstAnime = new ArrayList<>();


    spLeaveSubject2 = (Spinner) findViewById(R.id.spLeaveSubject2);
    customer_info = (EditText) findViewById(R.id.customer_info);
    spLeaveSubject2.setOnItemSelectedListener(MainActivity.this);

    signin = (Button) findViewById(R.id.signin);

    imageView4 = (ImageView) findViewById(R.id.imageView4);


    imageView4.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {

            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.custom_dialogss, null);

            android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(MainActivity.this);

            Button image_landscape = layout.findViewById(R.id.landscape);
            Button image_portrait = layout.findViewById(R.id.portrait);
            aDialog.setView(layout);
            final android.app.AlertDialog ad = aDialog.create();
            ad.show();


            image_landscape.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    setContentView(R.layout.activity_main_portrait);
                    ad.dismiss();
                }
            });


            image_portrait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ad.dismiss();
                }
            });







        }
    });


    signin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            loginapi_landscape();
        }
    });

    retreivebranches2();



//
//
//    signin.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//
//            loginapi_landscape();
//
//            // code for portrait mode
//        }
//    });
//
//    retreivebranches2();

}

        //            spLeaveSubject2 = (Spinner) findViewById(R.id.spLeaveSubject2);
//            customer_info = (EditText) findViewById(R.id.customer_info);
//            spLeaveSubject2.setOnItemSelectedListener(MainActivity.this);
//
//





        // code for landscape mode
    }

    private void loginapi_landscape()
    {

                if (customer_info.getText().toString().equals(""))
        {
            SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Please Fill Form Properly");
            pDialog.setCancelable(true);
            pDialog.show();

        }

        else
        {
            pdialog = Utilss.showSweetLoader(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
            Log.e("Editext_text", customer_info.getText().toString()           + "            " + v11);


            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("BranchName", v1);
                jsonObject.put("BranchPin", customer_info.getText().toString());

                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                // put your json here
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                okhttp3.Request request = new okhttp3.Request.Builder() .url("http://api.surveymenu.dwtdemo.com/api/Branch/Login").post(body).build();


                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, final IOException e) {


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pdialog);

                                Log.e("HttpService", "onFailure() Request was: " + call);
                                e.printStackTrace();
                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {



                        String responses = response.body().string();
                        Log.e("response", "onResponse(): " + responses);

                        try {

                            json = new JSONObject(responses);

                            if (response.code() == 200)
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pdialog);
                                    }
                                });

                                JSONObject json2 = json.getJSONObject("data");
                                value2 = json2.getString("accesstoken");
                                value7 = json2.getString("feedback_id");

                                Log.d("Valuesss" , value2);

                                Intent intent = new Intent(MainActivity.this, Value_Feedback.class);
//                            intent.putExtra("Access_Token", value2);
                                startActivity(intent);





                            }

                            else if (response.code()==404)
                            {


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Utilss.hideSweetLoader(pdialog);

                                                }
                                            });

                                            JSONObject json2 = json.getJSONObject("data");
                                            value24 = json2.getString("message");
                                            SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                            pDialog.setTitleText(value24);
                                            pDialog.setCancelable(true);
                                            pDialog.show();



                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }



                                    }
                                });


                            }


                            else if (response.code()==401)
                            {
                                SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                                        System.exit(0);
                                    }
                                });

                                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                pDialog.setTitleText("Session Expired");
                                pDialog.setCancelable(true);
                                pDialog.show();

                            }
                        }

                        catch (JSONException e)
                        {

                        }






                    }
                });




            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void loginapiportrait()
    {

                if (customer_info.getText().toString().equals(""))
        {
            SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Please Fill Form Properly");
            pDialog.setCancelable(true);
            pDialog.show();

        }

        else
        {
            pdialog = Utilss.showSweetLoader(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
            Log.e("Editext_text", customer_info.getText().toString()           + "            " + v11);


            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("BranchName", v1);
                jsonObject.put("BranchPin", customer_info.getText().toString());

                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                // put your json here
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                okhttp3.Request request = new okhttp3.Request.Builder() .url("http://api.surveymenu.dwtdemo.com/api/Branch/Login").post(body).build();


                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, final IOException e) {


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pdialog);

                                Log.e("HttpService", "onFailure() Request was: " + call);
                                e.printStackTrace();
                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {



                        String responses = response.body().string();
                        Log.e("response", "onResponse(): " + responses);

                        try {

                            json = new JSONObject(responses);

                            if (response.code() == 200)
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pdialog);
                                    }
                                });

                                JSONObject json2 = json.getJSONObject("data");
                                value2 = json2.getString("accesstoken");
                                value7 = json2.getString("feedback_id");

                                Log.d("Valuesss" , value2);

                                Intent intent = new Intent(MainActivity.this, Value_Feedback.class);
//                            intent.putExtra("Access_Token", value2);
                                startActivity(intent);





                            }

                            else if (response.code()==404)
                            {


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Utilss.hideSweetLoader(pdialog);

                                                }
                                            });

                                            JSONObject json2 = json.getJSONObject("data");
                                            value24 = json2.getString("message");
                                            SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                            pDialog.setTitleText(value24);
                                            pDialog.setCancelable(true);
                                            pDialog.show();



                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }



                                    }
                                });


                            }


                            else if (response.code()==401)
                            {
                                SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                                        System.exit(0);
                                    }
                                });

                                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                pDialog.setTitleText("Session Expired");
                                pDialog.setCancelable(true);
                                pDialog.show();

                            }
                        }

                        catch (JSONException e)
                        {

                        }






                    }
                });




            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


//
//
////        setContentView(R.layout.activity_main);
//
//        Mint.initAndStartSession(this.getApplication(), "8566b133");
//
//        mDroidNet = DroidNet.getInstance();
//        mDroidNet.addInternetConnectivityListener(this);
//
//        client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
//
//        lstAnime = new ArrayList<>();
//
////        Window window = this.getWindow();
////// clear FLAG_TRANSLUCENT_STATUS flag:
////        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
////// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
////        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
////// finally change the color
////        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorback));
//
//
//        spLeaveSubject2 = (Spinner) findViewById(R.id.spLeaveSubject2);
//        customer_info = (EditText) findViewById(R.id.customer_info);
//
//        spLeaveSubject2.setOnItemSelectedListener(MainActivity.this);
////        spLeaveSubject2.setPrompt("Select your favorite Planet!");
//
////        spLeaveSubject2.setAdapter(
////                new NothingSelectedSpinnerAdapter(adapter,
////                        R.layout.contact_spinner_row_nothing_selected,
////                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
////                        this));
//
//
//        signin = (Button) findViewById(R.id.signin);
//
//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                loginapi();
////                Intent intent = new Intent( MainActivity.this , Value_Feedback.class);
////                startActivity(intent);
//            }
//        });
//
//        retreivebranches();
//
//    }
   // }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
////        setContentView(R.layout.activity_main);
//
//
//
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//
////            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
//            setContentView(R.layout.activity_main_portrait);
//
//            spLeaveSubject2 = (Spinner) findViewById(R.id.spLeaveSubject2);
//            customer_info = (EditText) findViewById(R.id.customer_info);
//            spLeaveSubject2.setOnItemSelectedListener(MainActivity.this);
//
//
//            signin = (Button) findViewById(R.id.signin);
//
//            signin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    loginapi_landscape();
//
//                    // code for portrait mode
//                }
//            });
//
//            retreivebranches2();
//
//
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
////            createVerticalLayout();
//
//            //            Toast.makeText(this, "2288", Toast.LENGTH_SHORT).show();
//            setContentView(R.layout.activity_main);
//
//            spLeaveSubject2 = (Spinner) findViewById(R.id.spLeaveSubject2);
//            customer_info = (EditText) findViewById(R.id.customer_info);
//            spLeaveSubject2.setOnItemSelectedListener(MainActivity.this);
//
//            signin = (Button) findViewById(R.id.signin);
//
//            signin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    loginapiportrait();
//
//                    // code for portrait mode
//                }
//            });
//
//            retreivebranches3();
//
//        }
//
//    }


//
//    private void loginapi()
//    {
//
//
//        if (customer_info.getText().toString().equals(""))
//        {
//            SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
//            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//            pDialog.setTitleText("Please Fill Form Properly");
//            pDialog.setCancelable(true);
//            pDialog.show();
//
//        }
//
//        else
//        {
//            pdialog = Utilss.showSweetLoader(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
//            Log.e("Editext_text", customer_info.getText().toString()           + "            " + v11);
//
//
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("BranchName", v1);
//                jsonObject.put("BranchPin", customer_info.getText().toString());
//
//                OkHttpClient client = new OkHttpClient();
//                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//                // put your json here
//                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
//                okhttp3.Request request = new okhttp3.Request.Builder() .url("http://api.surveymenu.dwtdemo.com/api/Branch/Login").post(body).build();
//
//
//                Call call = client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(final Call call, final IOException e) {
//
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Utilss.hideSweetLoader(pdialog);
//
//                                Log.e("HttpService", "onFailure() Request was: " + call);
//                                e.printStackTrace();
//                            }
//                        });
//
//                    }
//
//                    @Override
//                    public void onResponse(Call call, okhttp3.Response response) throws IOException {
//
//
//
//                        String responses = response.body().string();
//                        Log.e("response", "onResponse(): " + responses);
//
//                        try {
//
//                            json = new JSONObject(responses);
//
//                            if (response.code() == 200)
//                            {
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Utilss.hideSweetLoader(pdialog);
//                                    }
//                                });
//
//                                JSONObject json2 = json.getJSONObject("data");
//                                value2 = json2.getString("accesstoken");
//                                value7 = json2.getString("feedback_id");
//
//                                Log.d("Valuesss" , value2);
//
//                                Intent intent = new Intent(MainActivity.this, Value_Feedback.class);
////                            intent.putExtra("Access_Token", value2);
//                                startActivity(intent);
//
//
//
//
//
//                            }
//
//                            else if (response.code()==404)
//                            {
//
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//                                        try {
//
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    Utilss.hideSweetLoader(pdialog);
//
//                                                }
//                                            });
//
//                                            JSONObject json2 = json.getJSONObject("data");
//                                            value24 = json2.getString("message");
//                                            SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
//                                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//                                            pDialog.setTitleText(value24);
//                                            pDialog.setCancelable(true);
//                                            pDialog.show();
//
//
//
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//
//
//
//                                    }
//                                });
//
//
//                            }
//
//
//                            else if (response.code()==401)
//                            {
//                                SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
//                                    @Override
//                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//
//                                        System.exit(0);
//                                    }
//                                });
//
//                                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//                                pDialog.setTitleText("Session Expired");
//                                pDialog.setCancelable(true);
//                                pDialog.show();
//
//                            }
//                        }
//
//                        catch (JSONException e)
//                        {
//
//                        }
//
//
//
//
//
//
//                    }
//                });
//
//
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//
//
//
//    }
//
    private void retreivebranches()
    {

        pdialog = Utilss.showSweetLoader(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://api.surveymenu.dwtdemo.com/api/Branch/GetBranches", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.d("ResponseIs" , response);

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONArray obj = new JSONArray(response);

                            lstAnime = new ArrayList<>();

                            for (int i = 0; i < obj.length(); i++) {

                                lstAnime.clear();
                                Pojo playerModel7 = new Pojo();
                                JSONObject dataobj = obj.getJSONObject(i);

                                playerModel7.setValue(dataobj.getInt("value"));
                                playerModel7.setText(dataobj.getString("text"));
                                lstAnime.add(playerModel7);

                            }

                            for (int i = 0; i < lstAnime.size(); i++) {
                                names7.add(lstAnime.get(i).getText());
                            }

                            spinnerArrayAdapter = new ArrayAdapter<>(MainActivity.this, simple_spinner_item, names7);
////                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
////                            spLeaveSubject2.setAdapter(spinnerArrayAdapter );

                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spLeaveSubject2.setAdapter(new NothingSelectedSpinnerAdapter(spinnerArrayAdapter, R.layout.contact_spinner_row_nothing_selected,MainActivity.this));

                                            // R.layout.contact_spinner_nothing_selected_dropdown, // Optional



                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {


                                    Utilss.hideSweetLoader(pdialog);
                                }
                            });



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        //displaying the error in toast if occurrs

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                Log.d("ErrorIs" , error.toString());

                                Utilss.hideSweetLoader(pdialog);
//                                   runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//
//
//                            }
//                        });
                            }
                        });

//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


//        {
//            @Override
//            public Map getHeaders() throws AuthFailureError {
//                HashMap headers = new HashMap();
//                headers.put("token", sk);
//                return headers;
//            }
//        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }


















    private void retreivebranches2()
    {

        pdialog = Utilss.showSweetLoader(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://api.surveymenu.dwtdemo.com/api/Branch/GetBranches", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("ResponseIs" , response);

                Log.d("strrrrr", ">>" + response);

                try {

                    JSONArray obj = new JSONArray(response);

                    lstAnime2 = new ArrayList<>();

                    for (int i = 0; i < obj.length(); i++) {

                        lstAnime2.clear();
                        Pojo playerModel7 = new Pojo();
                        JSONObject dataobj = obj.getJSONObject(i);

                        playerModel7.setValue(dataobj.getInt("value"));
                        playerModel7.setText(dataobj.getString("text"));
                        lstAnime2.add(playerModel7);

                    }

                    for (int i = 0; i < lstAnime2.size(); i++) {
                        names8.add(lstAnime2.get(i).getText());
                    }

                    spinnerArrayAdapter = new ArrayAdapter<>(MainActivity.this, simple_spinner_item, names8);
////                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
////                            spLeaveSubject2.setAdapter(spinnerArrayAdapter );

                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spLeaveSubject2.setAdapter(new NothingSelectedSpinnerAdapter(spinnerArrayAdapter, R.layout.contact_spinner_row_nothing_selected,MainActivity.this));

                    // R.layout.contact_spinner_nothing_selected_dropdown, // Optional



                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            Utilss.hideSweetLoader(pdialog);
                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        //displaying the error in toast if occurrs

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                Log.d("ErrorIs" , error.toString());

                                Utilss.hideSweetLoader(pdialog);
//                                   runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//
//
//                            }
//                        });
                            }
                        });

//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


//        {
//            @Override
//            public Map getHeaders() throws AuthFailureError {
//                HashMap headers = new HashMap();
//                headers.put("token", sk);
//                return headers;
//            }
//        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }


















    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("password", customer_info.getText().toString());
        outState.putString("mySpinner", v1);
        Log.d("SPinsss" , ""+ v1);

    }




    private void retreivebranches3()
    {

        pdialog = Utilss.showSweetLoader(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://api.surveymenu.dwtdemo.com/api/Branch/GetBranches", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("ResponseIs" , response);

                Log.d("strrrrr", ">>" + response);

                try {

                    JSONArray obj = new JSONArray(response);

                    lstAnim3 = new ArrayList<>();

                    for (int i = 0; i < obj.length(); i++) {

                        lstAnim3.clear();
                        Pojo playerModel7 = new Pojo();
                        JSONObject dataobj = obj.getJSONObject(i);

                        playerModel7.setValue(dataobj.getInt("value"));
                        playerModel7.setText(dataobj.getString("text"));
                        lstAnim3.add(playerModel7);

                    }

                    for (int i = 0; i < lstAnim3.size(); i++) {
                        names9.add(lstAnim3.get(i).getText());
                    }

                    spinnerArrayAdapter = new ArrayAdapter<>(MainActivity.this, simple_spinner_item, names9);
////                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
////                            spLeaveSubject2.setAdapter(spinnerArrayAdapter );

                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spLeaveSubject2.setAdapter(new NothingSelectedSpinnerAdapter(spinnerArrayAdapter, R.layout.contact_spinner_row_nothing_selected,MainActivity.this));

                    // R.layout.contact_spinner_nothing_selected_dropdown, // Optional



                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            Utilss.hideSweetLoader(pdialog);
                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        //displaying the error in toast if occurrs

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                Log.d("ErrorIs" , error.toString());

                                Utilss.hideSweetLoader(pdialog);
//                                   runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//
//
//                            }
//                        });
                            }
                        });

//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


//        {
//            @Override
//            public Map getHeaders() throws AuthFailureError {
//                HashMap headers = new HashMap();
//                headers.put("token", sk);
//                return headers;
//            }
//        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }









//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId()==R.id.spLeaveSubject2)
        {
            v1 = String.valueOf(spLeaveSubject2.getSelectedItem());
//            v11 = (lstAnime.get(position).getValue());
            Log.d("spinnervalue1" , ""+v1);
//            Log.d("spinnervalue11" ,""+v11);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDroidNet.removeInternetConnectivityChangeListener(this);
    }

    @Override
    public void onInternetConnectivityChanged(final boolean isConnected) {


        if (isConnected) {
            //do Stuff with internet



        } else {
            //no internet

//            Toast.makeText(this, "Internet Off..!!", Toast.LENGTH_SHORT).show();

             pDialogss = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {


                    finishAffinity();
                }
            });

            pDialogss.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialogss.setTitleText("Internet Not Found");
            pDialogss.setCancelable(true);
            pDialogss.show();


        }
    }

}
