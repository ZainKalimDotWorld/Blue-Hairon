package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProductsActivity extends BaseActivity {

    ImageView imageView2;
    private ArrayList<Item> items;
    private ArrayList<Item> items2;
    private RecyclerView recyclerView;
    SnapRecyclerAdapter adapter;
    SweetAlertDialog pDialog;
//    SwipeRefreshLayout mSwipeRefreshLayout;
    int products_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        items=new ArrayList<>();
        items2=new ArrayList<>();


        products_intent = getIntent().getIntExtra("Products_Id" , 0);
        Log.d("Products_Value" , ""+products_intent);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);


        imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        adapter = new SnapRecyclerAdapter(this, items);
        adapter.setDataList(items);
        recyclerView.setAdapter(adapter);

//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                updatemenu();
//            }
//        });


        loadallimages();
    }

//    private void updatemenu()
//    {
//        pDialog = Utilss.showSweetLoader(ProductsActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");
//        items2= new ArrayList<>();
//
//        com.android.volley.RequestQueue queue = Volley.newRequestQueue(ProductsActivity.this);
//        String url = "http://api.surveymenu.dwtdemo.com/api/en/category/"+products_intent+"/products?pg=1";
//        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.d("Json-response1", response);
//
//                        try {
//                            JSONArray jsonArray =new JSONArray(response);
//
//                            items.clear();
//
//                            for (int i = 0; i < jsonArray.length(); i++)
//                            {
//                                JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.get(i)));
//                                Item dataObject = new Item();
//
//                                dataObject.setID(jsonObject.getInt("ID"));
//                                dataObject.setCategory(jsonObject.getString("Category"));
//                                dataObject.setProduct(jsonObject.getString("Product"));
//                                dataObject.setDescription(jsonObject.getString("Description"));
//                                dataObject.setImage(jsonObject.getString("Image"));
//                                dataObject.setPrice(jsonObject.getLong("Price"));
//
//
//                                items2.add(dataObject);
////                                adapter.notifyDataSetChanged();
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Utilss.hideSweetLoader(pDialog);
//                                    }
//                                });
//
//                            }
//
//
//                            adapter = new SnapRecyclerAdapter(ProductsActivity.this, items2);
//                            recyclerView.setLayoutManager(new LinearLayoutManager(ProductsActivity.this, LinearLayoutManager.HORIZONTAL, false));
//                            recyclerView.setAdapter(adapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//
//
//                }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });
//            }
//        }) {
//            @Override
//            public Map getHeaders() {
//                HashMap headers = new HashMap();
//                headers.put("Authorization", "Bearer "+MainActivity.value2);
//                return headers;
//            }
//        };
//
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(stringRequest);
//        mSwipeRefreshLayout.setRefreshing(false);
//
//    }










    private void loadallimages()
    {

                pDialog = Utilss.showSweetLoader(ProductsActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(ProductsActivity.this);
        String url = "http://api.surveymenu.dwtdemo.com/api/en/category/"+products_intent+"/products?pg=1";
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {







                        Log.d("Json-response1", response);

                        try {
                            JSONArray jsonArray =new JSONArray(response);

                            items.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.get(i)));
                                Item dataObject = new Item();

                                dataObject.setID(jsonObject.getInt("ID"));
                                dataObject.setCategory(jsonObject.getString("Category"));
                                dataObject.setProduct(jsonObject.getString("Product"));
                                dataObject.setDescription(jsonObject.getString("Description"));
                                dataObject.setImage(jsonObject.getString("Image"));
                                dataObject.setPrice(jsonObject.getLong("Price"));


                                items.add(dataObject);
                                adapter.notifyDataSetChanged();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
