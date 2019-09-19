package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class Main_MenuScreen extends BaseActivity {

    TextView first;
    RecyclerView mRecyclerView;
    ArrayList<MainCategories_Pojo> mFlowerList;
    protected SweetAlertDialog pDialog;
String token;
TextView  textview;
    ArrayList<MainCategories_Pojo> mFlowerList2;
    //    FlowerData mFlowerData;
    Main_Manu_Adapter dataListAdapter;
    private LabeledSwitch swToggle;

//    SwipeRefreshLayout mSwipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu_screen);



//        token = getIntent().getStringExtra("Access_Token");
//        Log.d("Tokens" , token);

        mFlowerList = new ArrayList<>();
        mFlowerList2 = new ArrayList<>();


        swToggle = findViewById(R.id.iv_toggle);


//        final CircleProgressBar circleProgressBar = (CircleProgressBar) rootView.findViewById(R.id.custom_progressBar);
        mRecyclerView = findViewById(R.id.recyclerview);
//        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        dataListAdapter = new Main_Manu_Adapter(Main_MenuScreen.this, mFlowerList);
        dataListAdapter.setDataList(mFlowerList);

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(Main_MenuScreen.this, 4);

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


        swToggle.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here

                if (isOn)
                {
                    showdatainarabic();
                } else {
                    retreiveCategories();
                }
            }

        });

        retreiveCategories();
    }

    private void showdatainarabic()
    {

        textview.setText("ماذا تأكل اليوم؟ اختر الفئة!");
        mFlowerList2=new ArrayList<>();
        pDialog = Utilss.showSweetLoader(Main_MenuScreen.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(Main_MenuScreen.this);
        String url = "http://api.surveymenu.dwtdemo.com/api/ar/category";
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Json-response12", response);

                        try {

                            JSONArray jsonArray =new JSONArray(response);
//                            mFlowerList.clear();

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


                        } catch (JSONException e) {
                            e.printStackTrace();
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
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);

    }

//



    private void retreiveCategories() {


        textview.setText("What are you eating today? Select Category!");
        pDialog = Utilss.showSweetLoader(Main_MenuScreen.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(Main_MenuScreen.this);
        String url = "http://api.surveymenu.dwtdemo.com/api/en/category";
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {






                        Log.d("Json-response1", response);

                        try {

                            JSONArray jsonArray =new JSONArray(response);
//                            mFlowerList.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.get(i)));
                                MainCategories_Pojo dataObject = new MainCategories_Pojo();

                                dataObject.setID(jsonObject.getInt("ID"));
                                dataObject.setCategory(jsonObject.getString("Category"));
                                dataObject.setImage(jsonObject.getString("Image"));
                                dataObject.setSubCategory(jsonObject.getInt("SubCategory"));
                                mFlowerList.add(dataObject);
                                dataListAdapter.notifyDataSetChanged();


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Utilss.hideSweetLoader(pDialog);
                                    }

                                });


                            }

                            dataListAdapter = new Main_Manu_Adapter(Main_MenuScreen.this, mFlowerList);
                            GridLayoutManager mGridLayoutManager = new GridLayoutManager(Main_MenuScreen.this, 4);
                            int spanCount = 4; // 3 columns
                            int spacing = 0; // 50px
                            boolean includeEdge = false;
                            mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
                            mRecyclerView.setLayoutManager(mGridLayoutManager);
                            mRecyclerView.setAdapter(dataListAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
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
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

}
