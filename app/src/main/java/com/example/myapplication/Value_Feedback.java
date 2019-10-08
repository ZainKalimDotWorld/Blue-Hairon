package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;

import com.droidnet.DroidListener;
import com.droidnet.DroidNet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.text.method.CharacterPickerDialog;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Value_Feedback extends AppCompatActivity implements DroidListener {


    ImageView imageView1;
    ImageView imageView2;
    DroidNet mDroidNet;







    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


if (MainActivity.orientation==Configuration.ORIENTATION_LANDSCAPE)
{
    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    lockActivityOrientation(Value_Feedback.this);

    setContentView(R.layout.activity_value__feedback_landscape);

            mDroidNet = DroidNet.getInstance();
        mDroidNet.addInternetConnectivityListener(this);

    imageView1 = (ImageView) findViewById(R.id.imageView1);

    imageView1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Value_Feedback.this , Main_MenuScreen.class);
            intent.putExtra("LandscapeMode" , "Landscape");
            startActivity(intent);
        }
    });

    imageView2 = (ImageView) findViewById(R.id.imageView2);


    imageView2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent(Value_Feedback.this , Feedback_Menu.class);
            startActivity(intent);
        }
    });
}



else
{
    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    lockActivityOrientation(Value_Feedback.this);

    setContentView(R.layout.activity_value__feedback);
    imageView1 = (ImageView) findViewById(R.id.imageView1);

            mDroidNet = DroidNet.getInstance();
        mDroidNet.addInternetConnectivityListener(this);


    imageView1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Value_Feedback.this , Main_MenuScreen.class);
            intent.putExtra("PortraitMode" , "Portrait");
            startActivity(intent);
        }
    });

    imageView2 = (ImageView) findViewById(R.id.imageView2);


    imageView2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent(Value_Feedback.this , Feedback_Menu.class);
            startActivity(intent);
        }
    });
}















//        int currentOrientation = this.getResources().getConfiguration().orientation;
//        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT)
//        {
//
//        }
//
//
//                else
//        {
//
//
//        }




//        int orientation = this.getResources().getConfiguration().orientation;
//        if (orientation==Configuration.ORIENTATION_PORTRAIT)
//        {
//            setContentView(R.layout.activity_value__feedback);
//
//            imageView1 = (ImageView) findViewById(R.id.imageView1);
//
//            imageView1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(Value_Feedback.this , Main_MenuScreen.class);
//                    intent.putExtra("PortraitMode" , "Portrait");
//                    startActivity(intent);
//                }
//            });
//
//            imageView2 = (ImageView) findViewById(R.id.imageView2);
//
//
//            imageView2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    Intent intent = new Intent(Value_Feedback.this , Feedback_Menu.class);
//                    startActivity(intent);
//                }
//            });
//        }
//
//        else
//        {
//            setContentView(R.layout.activity_value__feedback_landscape);
//
//            imageView1 = (ImageView) findViewById(R.id.imageView1);
//
//            imageView1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(Value_Feedback.this , Main_MenuScreen.class);
//                    intent.putExtra("LandscapeMode" , "Landscape");
//                    startActivity(intent);
//                }
//            });
//
//            imageView2 = (ImageView) findViewById(R.id.imageView2);
//
//
//            imageView2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    Intent intent = new Intent(Value_Feedback.this , Feedback_Menu.class);
//                    startActivity(intent);
//                }
//            });
//
//        }
//
//
//        mDroidNet = DroidNet.getInstance();
//        mDroidNet.addInternetConnectivityListener(this);

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
//        setContentView(R.layout.activity_main__menu_screens2);
        super.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDroidNet.removeInternetConnectivityChangeListener(this);
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
//            setContentView(R.layout.activity_value__feedback_landscape);
//
//            imageView1 = (ImageView) findViewById(R.id.imageView1);
//
//            imageView1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(Value_Feedback.this , Main_MenuScreen.class);
//                    startActivity(intent);
//                }
//            });
//
//            imageView2 = (ImageView) findViewById(R.id.imageView2);
//
//
//            imageView2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    Intent intent = new Intent(Value_Feedback.this , Feedback_Menu.class);
//                    startActivity(intent);
//                }
//            });
//
//
//
//
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
////            createVerticalLayout();
//
//            //            Toast.makeText(this, "2288", Toast.LENGTH_SHORT).show();
//            setContentView(R.layout.activity_value__feedback);
//
//            imageView1 = (ImageView) findViewById(R.id.imageView1);
//
//            imageView1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(Value_Feedback.this , Main_MenuScreen.class);
//                    startActivity(intent);
//                }
//            });
//
//            imageView2 = (ImageView) findViewById(R.id.imageView2);
//
//
//            imageView2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    Intent intent = new Intent(Value_Feedback.this , Feedback_Menu.class);
//                    startActivity(intent);
//                }
//            });
//
//
//
//
//        }
//
//    }

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
    public void onInternetConnectivityChanged(boolean isConnected) {

        if (isConnected) {
            //do Stuff with internet
//            netIsOn();
        } else {
            //no internet

//            Toast.makeText(this, "Internet Off..!!", Toast.LENGTH_SHORT).show();

            SweetAlertDialog pDialog = new SweetAlertDialog(Value_Feedback.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
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
