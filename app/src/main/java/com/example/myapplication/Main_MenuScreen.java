package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.droidnet.DroidListener;
import com.droidnet.DroidNet;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.R.layout.preference_category;
import static android.R.layout.simple_spinner_item;

public class Main_MenuScreen extends BaseActivity implements DroidListener {

    TextView first;
    RecyclerView mRecyclerView;
    ArrayList<MainCategories_Pojo> mFlowerList;
    ArrayList<MainCategories_Pojo> mFlowerList5;

    protected SweetAlertDialog pDialog;
    String token;
    TextView  textview;
    ArrayList<MainCategories_Pojo> mFlowerList2;
    String statusCode;

    ArrayList<MainCategories_Pojo> mFlowerList3;

    //    FlowerData mFlowerData;
    Main_Manu_Adapter dataListAdapter;
//    Main_Manu_Adapter2 dataListAdapter2;

    public static LabeledSwitch swToggle;
    DroidNet mDroidNet;
    int orientation;
    boolean bool_value;
    GridLayoutManager mGridLayoutManager;

    Button imageView4;

//    SwipeRefreshLayout mSwipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main__menu_screen);

        swToggle = findViewById(R.id.iv_toggle);



        if (MainActivity.orientation==Configuration.ORIENTATION_PORTRAIT)
        {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            lockActivityOrientation(Main_MenuScreen.this);

            setContentView(R.layout.activity_main__menu_screen);

            mDroidNet = DroidNet.getInstance();
            mDroidNet.addInternetConnectivityListener(this);

            bool_value = getIntent().getBooleanExtra("Toggle_Value_Back",false);
            Log.d("bool_value" , ""+bool_value);


            mFlowerList = new ArrayList<>();
            mFlowerList2 = new ArrayList<>();
            mFlowerList3 = new ArrayList<>();


            imageView4 = findViewById(R.id.imageView4);


            imageView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =new Intent(Main_MenuScreen.this , Value_Feedback.class);
                    startActivity(intent);
                    finish();
                }
            });




            swToggle = findViewById(R.id.iv_toggle);


//        final CircleProgressBar circleProgressBar = (CircleProgressBar) rootView.findViewById(R.id.custom_progressBar);
            mRecyclerView = findViewById(R.id.recyclerview);
            mRecyclerView.setHasFixedSize(true);


            dataListAdapter = new Main_Manu_Adapter(Main_MenuScreen.this, mFlowerList);
            dataListAdapter.setDataList(mFlowerList);


            int spanCount = 2; // 3 columns
            int spacing = 20; // 50px
            boolean includeEdge = false;
            mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
            mRecyclerView.setLayoutManager(mGridLayoutManager);
            mRecyclerView.setAdapter(dataListAdapter);


            textview = (TextView )findViewById(R.id.textView123);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                updatemenu();
//            }
//        });


            if (bool_value)    //if true
            {
                showdatainarabicportrait();
                swToggle.setOn(true);
            }

            else         //false
            {
                retreiveCategoriesinenglishportrait();
            }


            swToggle.setOnToggledListener(new OnToggledListener() {
                @Override
                public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                    // Implement your switching logic here

                    if (isOn)
                    {
                        showdatainarabicportrait();
                    } else {

                        retreiveCategoriesinenglishportrait();
                    }
                }

            });
        }

else
        {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            //landscape
            setContentView(R.layout.activity_main__menu_screen_landscape);

            mDroidNet = DroidNet.getInstance();
            mDroidNet.addInternetConnectivityListener(this);

            bool_value = getIntent().getBooleanExtra("Toggle_Value_Back",false);
            Log.d("bool_value" , ""+bool_value);




            mFlowerList = new ArrayList<>();
            mFlowerList2 = new ArrayList<>();
            mFlowerList3 = new ArrayList<>();


            imageView4 = findViewById(R.id.imageView4);


            imageView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =new Intent(Main_MenuScreen.this , Value_Feedback.class);
                    startActivity(intent);
                    finish();
                }
            });




            swToggle = findViewById(R.id.iv_toggle);


//        final CircleProgressBar circleProgressBar = (CircleProgressBar) rootView.findViewById(R.id.custom_progressBar);
            mRecyclerView = findViewById(R.id.recyclerview);
            mRecyclerView.setHasFixedSize(true);


            dataListAdapter = new Main_Manu_Adapter(Main_MenuScreen.this, mFlowerList);
            dataListAdapter.setDataList(mFlowerList);


            int spanCount = 4; // 3 columns
            int spacing = 20; // 50px
            boolean includeEdge = false;
            mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
            mRecyclerView.setLayoutManager(mGridLayoutManager);
            mRecyclerView.setAdapter(dataListAdapter);


            textview = (TextView )findViewById(R.id.textView123);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                updatemenu();
//            }
//        });


            if (bool_value)    //if true
            {
                showdatainarabiclandscape();
                swToggle.setOn(true);
            }

            else         //false
            {
                retreiveCategoriesinenglishlandscape();
            }


            swToggle.setOnToggledListener(new OnToggledListener() {
                @Override
                public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                    // Implement your switching logic here

                    if (isOn)
                    {
                        showdatainarabiclandscape();
                    } else {

                        retreiveCategoriesinenglishlandscape(); //                        retreiveCategoriesinenglishlandscape();
                    }
                }

            });
        }

//        int currentOrientation = this.getResources().getConfiguration().orientation;
//
//        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT)
//        {
//
//
//
//
//        }
//
//
//        else
//        {
//
//        }
    }


    public static void lockActivityOrientation(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        int rotation = display.getRotation();
        int height;
        int width;
        Point size = new Point();
        display.getSize(size);
        height = size.y;
        width = size.x;
        switch (rotation) {
            case Surface.ROTATION_90:
                if (width > height)
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                else
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                break;
            case Surface.ROTATION_180:
                if (height > width)
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                else
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                break;
            case Surface.ROTATION_270:
                if (width > height)
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                else
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            default:
                if (height > width)
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                else
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
//        setContentView(R.layout.activity_main__menu_screens2);
        super.onConfigurationChanged(newConfig);
    }



    @Override
    protected void onSaveInstanceState (Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putBoolean("toggle_state", swToggle.isOn());
        Log.d("MyValuesss" , "Imhere" + "" +  swToggle.isOn());
    }
    //
//
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        boolean myBoolean = savedInstanceState.getBoolean("toggle_state");
        Log.d("MyValueee", "" + myBoolean);
        swToggle.setOn(myBoolean);
    }


//
//        if (myBoolean && orientation==Configuration.ORIENTATION_LANDSCAPE )
//        {
//            showdatainarabiclandscape();
////            swToggle.setOn(myBoolean);
//        }
//
//        else
//        {
//
//        }
//
//
//
//         if (myBoolean && orientation==Configuration.ORIENTATION_PORTRAIT) {
//
//
//            showdatainarabicportrait();
////            swToggle.setOn(myBoolean);
//        }
//
//         else
//         {
//
//         }
//
//
//          if (!myBoolean && orientation==Configuration.ORIENTATION_PORTRAIT)
//        {
//            retreiveCategoriesinenglishportrait();
////            swToggle.setOn(myBoolean);
//        }
//
//          else
//          {
//
//          }
//
//
//         if (!myBoolean && orientation==Configuration.ORIENTATION_LANDSCAPE)
//        {
//            retreiveCategoriesinenglishlandscape();
//
//        }
//
//
//         else
//         {
//
//         }
//
//
//
//
//    }
//


//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//
//        super.onConfigurationChanged(newConfig);
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
//        {
//           setContentView(R.layout.activity_main__menu_screens2);
//
//
//            mDroidNet = DroidNet.getInstance();
//            mDroidNet.addInternetConnectivityListener(this);
//
//            bool_value = getIntent().getBooleanExtra("Toggle_Value_Back",false);
//            Log.d("bool_value" , ""+bool_value);
//
//
//
//
//            mFlowerList = new ArrayList<>();
//            mFlowerList2 = new ArrayList<>();
//            mFlowerList3 = new ArrayList<>();
//
//
//            imageView4 = findViewById(R.id.imageView4);
//
//
//            imageView4.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent =new Intent(Main_MenuScreen.this , Value_Feedback.class);
//                    startActivity(intent);
//                    finish();
//                }
//            });
//
//
//
//
//            swToggle = findViewById(R.id.iv_toggle);
//
//
////        final CircleProgressBar circleProgressBar = (CircleProgressBar) rootView.findViewById(R.id.custom_progressBar);
//            mRecyclerView = findViewById(R.id.recyclerview);
//            mRecyclerView.setHasFixedSize(true);
//
//
//            dataListAdapter = new Main_Manu_Adapter(Main_MenuScreen.this, mFlowerList);
//            dataListAdapter.setDataList(mFlowerList);
//
//
//            int spanCount = 4; // 3 columns
//            int spacing = 20; // 50px
//            boolean includeEdge = false;
//            mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
//            mRecyclerView.setLayoutManager(mGridLayoutManager);
//            mRecyclerView.setAdapter(dataListAdapter);
//
//
//            textview = (TextView )findViewById(R.id.textView123);
////        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
////            @Override
////            public void onRefresh() {
////                updatemenu();
////            }
////        });
//
//
//            if (bool_value)    //if true
//            {
//                showdatainarabiclandscape();
//                swToggle.setOn(true);
//            }
//
//            else         //false
//            {
//                retreiveCategoriesinenglishlandscape();
//            }
//
//
//            swToggle.setOnToggledListener(new OnToggledListener() {
//                @Override
//                public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
//                    // Implement your switching logic here
//
//                    if (isOn)
//                    {
//                        showdatainarabiclandscape();
//                    } else {
//
//                        retreiveCategoriesinenglishlandscape(); //                        retreiveCategoriesinenglishlandscape();
//                    }
//                }
//
//            });
//
//
//        }
//
//        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
//        {
//            setContentView(R.layout.activity_main__menu_screen);
//
//            mDroidNet = DroidNet.getInstance();
//            mDroidNet.addInternetConnectivityListener(this);
//
//            bool_value = getIntent().getBooleanExtra("Toggle_Value_Back",false);
//            Log.d("bool_value" , ""+bool_value);
//
//
//            mFlowerList = new ArrayList<>();
//            mFlowerList2 = new ArrayList<>();
//            mFlowerList3 = new ArrayList<>();
//
//
//            imageView4 = findViewById(R.id.imageView4);
//
//
//            imageView4.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent =new Intent(Main_MenuScreen.this , Value_Feedback.class);
//                    startActivity(intent);
//                    finish();
//                }
//            });
//
//
//
//
//            swToggle = findViewById(R.id.iv_toggle);
//
//
////        final CircleProgressBar circleProgressBar = (CircleProgressBar) rootView.findViewById(R.id.custom_progressBar);
//            mRecyclerView = findViewById(R.id.recyclerview);
//            mRecyclerView.setHasFixedSize(true);
//
//
//            dataListAdapter = new Main_Manu_Adapter(Main_MenuScreen.this, mFlowerList);
//            dataListAdapter.setDataList(mFlowerList);
//
//
//            int spanCount = 2; // 3 columns
//            int spacing = 20; // 50px
//            boolean includeEdge = false;
//            mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
//            mRecyclerView.setLayoutManager(mGridLayoutManager);
//            mRecyclerView.setAdapter(dataListAdapter);
//
//
//            textview = (TextView )findViewById(R.id.textView123);
////        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
////            @Override
////            public void onRefresh() {
////                updatemenu();
////            }
////        });
//
//
//            if (bool_value)    //if true
//            {
//                showdatainarabicportrait();
//                swToggle.setOn(true);
//            }
//
//            else         //false
//            {
//                retreiveCategoriesinenglishportrait();
//            }
//
//
//            swToggle.setOnToggledListener(new OnToggledListener() {
//                @Override
//                public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
//                    // Implement your switching logic here
//
//                    if (isOn)
//                    {
//                        showdatainarabicportrait();
//                    } else {
//
//                        retreiveCategoriesinenglishportrait();
////                        retreiveCategoriesinenglishlandscape();
//                    }
//                }
//
//            });
//
//        }
//    }




    private void showdatainarabicportrait()
    {

        textview.setText("ماذا تأكل اليوم؟ اختر الفئة!");
        mFlowerList2=new ArrayList<>();
        pDialog = Utilss.showSweetLoader(Main_MenuScreen.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(Main_MenuScreen.this);
        String url = "http://api.surveymenu.dwtdemo.com/api/ar/category";



        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Json-response12", response);


                if (statusCode.equals("200"))
                {
                    try {

                        JSONArray jsonArray =new JSONArray(response);

                        if (jsonArray.length()==0)
                        {
                            SweetAlertDialog pDialog = new SweetAlertDialog(Main_MenuScreen.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("حسنا" , new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {


                                }
                            });

                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            pDialog.setTitleText("لاتوجد بيانات");
                            pDialog.setCancelable(true);
                            pDialog.show();
                        }

                        else
                        {
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.get(i)));
                                MainCategories_Pojo dataObject = new MainCategories_Pojo();

                                dataObject.setID(jsonObject.getInt("ID"));
                                dataObject.setCategory(jsonObject.getString("Category"));
                                dataObject.setImage(jsonObject.getString("Image"));
                                dataObject.setSubCategory(jsonObject.getInt("SubCategory"));
                                mFlowerList2.add(dataObject);
                                dataListAdapter.notifyDataSetChanged();


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });


                            }

                            dataListAdapter = new Main_Manu_Adapter(Main_MenuScreen.this, mFlowerList2);
                            GridLayoutManager mGridLayoutManager = new GridLayoutManager(Main_MenuScreen.this, 2);
                            int spanCount = 2; // 3 columns
                            int spacing = 0; // 50px
                            boolean includeEdge = false;
                            mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
                            mRecyclerView.setLayoutManager(mGridLayoutManager);
                            mRecyclerView.setAdapter(dataListAdapter);
                        }

//                            mFlowerList.clear();




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                else if (statusCode.equals("401"))
                {
                    SweetAlertDialog pDialog = new SweetAlertDialog(Main_MenuScreen.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            System.exit(0);
                        }
                    });

                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Session Expired..!!");
                    pDialog.setCancelable(true);
                    pDialog.show();
                }

            }


        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("Error_Fetch", error.toString());
                        Utilss.hideSweetLoader(pDialog);
                    }

                });

            }

        }) {
            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
                headers.put("Authorization", "Bearer "+MainActivity.value2);
                return headers;
            }


            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                statusCode = String.valueOf(response.statusCode);
                Log.d("StatusCode", statusCode);
                //Handling logic
                return super.parseNetworkResponse(response);
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void showdatainarabiclandscape()
    {
        textview.setText("ماذا تأكل اليوم؟ اختر الفئة!");
        mFlowerList2=new ArrayList<>();
        pDialog = Utilss.showSweetLoader(Main_MenuScreen.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(Main_MenuScreen.this);
        String url = "http://api.surveymenu.dwtdemo.com/api/ar/category";



        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Json-response125", response);


                if (statusCode.equals("200"))
                {
                    try {

                        JSONArray jsonArray =new JSONArray(response);

                        if (jsonArray.length()==0)
                        {
                            SweetAlertDialog pDialog = new SweetAlertDialog(Main_MenuScreen.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("حسنا" , new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {


                                }
                            });

                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            pDialog.setTitleText("لاتوجد بيانات");
                            pDialog.setCancelable(true);
                            pDialog.show();
                        }

                        else
                        {
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.get(i)));
                                MainCategories_Pojo dataObject = new MainCategories_Pojo();

                                dataObject.setID(jsonObject.getInt("ID"));
                                dataObject.setCategory(jsonObject.getString("Category"));
                                dataObject.setImage(jsonObject.getString("Image"));
                                dataObject.setSubCategory(jsonObject.getInt("SubCategory"));
                                mFlowerList2.add(dataObject);
                                dataListAdapter.notifyDataSetChanged();


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Utilss.hideSweetLoader(pDialog);
                                    }

                                });


                            }

                            dataListAdapter = new Main_Manu_Adapter(Main_MenuScreen.this, mFlowerList2);
                            GridLayoutManager mGridLayoutManager = new GridLayoutManager(Main_MenuScreen.this, 4);
                            int spanCount = 4; // 3 columns
                            int spacing = 0; // 50px
                            boolean includeEdge = false;
                            mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
                            mRecyclerView.setLayoutManager(mGridLayoutManager);
                            mRecyclerView.setAdapter(dataListAdapter);
                        }

//                            mFlowerList.clear();




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                else if (statusCode.equals("401"))
                {
                    SweetAlertDialog pDialog = new SweetAlertDialog(Main_MenuScreen.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            System.exit(0);
                        }
                    });

                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Session Expired..!!");
                    pDialog.setCancelable(true);
                    pDialog.show();
                }

            }


        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("Error_Fetch", error.toString());
                        Utilss.hideSweetLoader(pDialog);
                    }

                });

            }

        }) {
            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
                headers.put("Authorization", "Bearer "+MainActivity.value2);
                return headers;
            }


            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                statusCode = String.valueOf(response.statusCode);
                Log.d("StatusCode", statusCode);
                //Handling logic
                return super.parseNetworkResponse(response);
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void retreiveCategoriesinenglishportrait()
    {

        mFlowerList3 =new ArrayList<>();
        textview.setText("What are you eating today? Select Category!");
        pDialog = Utilss.showSweetLoader(Main_MenuScreen.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(Main_MenuScreen.this);
        String url = "http://api.surveymenu.dwtdemo.com/api/en/category";
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Json-response1", response);

                        if (statusCode.equals("200"))
                        {
                            try {

                                JSONArray jsonArray =new JSONArray(response);

                                if (jsonArray.length()==0)
                                {
                                    SweetAlertDialog pDialog = new SweetAlertDialog(Main_MenuScreen.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {


                                        }
                                    });

                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("No Data Found");
                                    pDialog.setCancelable(true);
                                    pDialog.show();
                                }

                                else
                                {
                                    mFlowerList3.clear();

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.get(i)));
                                        MainCategories_Pojo dataObject = new MainCategories_Pojo();

                                        dataObject.setID(jsonObject.getInt("ID"));
                                        dataObject.setCategory(jsonObject.getString("Category"));
                                        dataObject.setImage(jsonObject.getString("Image"));
                                        dataObject.setSubCategory(jsonObject.getInt("SubCategory"));
                                        mFlowerList3.add(dataObject);
                                        dataListAdapter.notifyDataSetChanged();


                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                Utilss.hideSweetLoader(pDialog);
                                            }

                                        });


                                    }

                                    dataListAdapter = new Main_Manu_Adapter(Main_MenuScreen.this, mFlowerList3);
                                    GridLayoutManager mGridLayoutManager = new GridLayoutManager(Main_MenuScreen.this, 2);
                                    int spanCount = 2; // 3 columns
                                    int spacing = 0; // 50px
                                    boolean includeEdge = false;
                                    mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
                                    mRecyclerView.setLayoutManager(mGridLayoutManager);
                                    mRecyclerView.setAdapter(dataListAdapter);

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        else if (statusCode.equals("401"))
                        {
                            SweetAlertDialog pDialog = new SweetAlertDialog(Main_MenuScreen.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {

                                    System.exit(0);
                                }
                            });

                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            pDialog.setTitleText("Session Expired..!!");
                            pDialog.setCancelable(true);
                            pDialog.show();
                        }

                    }


                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("Error_Fetch", error.toString());
                        Utilss.hideSweetLoader(pDialog);
                    }

                });

            }
        }) {
            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
                headers.put("Authorization", "Bearer "+MainActivity.value2);
                return headers;
            }


            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                statusCode = String.valueOf(response.statusCode);
                Log.d("StatusCode3", statusCode);
                //Handling logic
                return super.parseNetworkResponse(response);
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }


    public void onBackPressed() {
//        Intent intent = new Intent(Product_Detail.this, ProductsActivity.class);
//        finish();
//        startActivity(intent);
    }


//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//            mRecyclerView.setLayoutManager(new GridLayoutManager(Main_MenuScreen.this, 2));
//        }
//        else{
//            mRecyclerView.setLayoutManager(new GridLayoutManager(Main_MenuScreen.this, 4));
//        }
//    }




    private void retreiveCategoriesinenglishlandscape()
    {
        mFlowerList3 =new ArrayList<>();
        textview.setText("What are you eating today? Select Category!");
        pDialog = Utilss.showSweetLoader(Main_MenuScreen.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(Main_MenuScreen.this);
        String url = "http://api.surveymenu.dwtdemo.com/api/en/category";
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Json-response1", response);

                        if (statusCode.equals("200"))
                        {
                            try {

                                JSONArray jsonArray =new JSONArray(response);

                                if (jsonArray.length()==0)
                                {
                                    SweetAlertDialog pDialog = new SweetAlertDialog(Main_MenuScreen.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {


                                        }
                                    });

                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("No Data Found");
                                    pDialog.setCancelable(true);
                                    pDialog.show();
                                }

                                else
                                {
                                    mFlowerList3.clear();

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.get(i)));
                                        MainCategories_Pojo dataObject = new MainCategories_Pojo();

                                        dataObject.setID(jsonObject.getInt("ID"));
                                        dataObject.setCategory(jsonObject.getString("Category"));
                                        dataObject.setImage(jsonObject.getString("Image"));
                                        dataObject.setSubCategory(jsonObject.getInt("SubCategory"));
                                        mFlowerList3.add(dataObject);
                                        dataListAdapter.notifyDataSetChanged();


                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                Utilss.hideSweetLoader(pDialog);
                                            }

                                        });


                                    }

                                    dataListAdapter = new Main_Manu_Adapter(Main_MenuScreen.this, mFlowerList3);
                                    GridLayoutManager mGridLayoutManager = new GridLayoutManager(Main_MenuScreen.this, 4);
                                    int spanCount = 4; // 3 columns
                                    int spacing = 0; // 50px
                                    boolean includeEdge = false;
                                    mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
                                    mRecyclerView.setLayoutManager(mGridLayoutManager);
                                    mRecyclerView.setAdapter(dataListAdapter);

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        else if (statusCode.equals("401"))
                        {
                            SweetAlertDialog pDialog = new SweetAlertDialog(Main_MenuScreen.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {

                                    System.exit(0);
                                }
                            });

                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            pDialog.setTitleText("Session Expired..!!");
                            pDialog.setCancelable(true);
                            pDialog.show();
                        }

                    }


                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("Error_Fetch", error.toString());
                        Utilss.hideSweetLoader(pDialog);
                    }

                });

            }
        }) {
            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
                headers.put("Authorization", "Bearer "+MainActivity.value2);
                return headers;
            }


            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                statusCode = String.valueOf(response.statusCode);
                Log.d("StatusCode3", statusCode);
                //Handling logic
                return super.parseNetworkResponse(response);
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDroidNet.removeInternetConnectivityChangeListener(this);
    }



    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {

        if (isConnected) {
            //do Stuff with internet
//            netIsOn();
        } else {
            //no internet

//            Toast.makeText(this, "Internet Off..!!", Toast.LENGTH_SHORT).show();

            SweetAlertDialog pDialog = new SweetAlertDialog(Main_MenuScreen.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {

                    finishAffinity();
                }
            });

            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Internet Not Found");
            pDialog.setCancelable(true);
            pDialog.show();


        }
    }
}

