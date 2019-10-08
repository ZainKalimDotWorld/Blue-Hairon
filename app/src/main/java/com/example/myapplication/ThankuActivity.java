package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ThankuActivity extends AppCompatActivity {

    boolean Bools;
    TextView textView1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanku);



        if (MainActivity.orientation==Configuration.ORIENTATION_PORTRAIT)
        {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            lockActivityOrientation(ThankuActivity.this);


            setContentView(R.layout.activity_thanku);

            textView1234 = findViewById(R.id.textView1234);


            if (Feedback_Menu.swToggle.isOn())
            {
                textView1234.setText("شكراً لك على ملاحظاتك القيمة، قم بزيارتنا مرة أخرى");
            }

            else
            {

                textView1234.setText("Thank you for your valuable feedback. Visit us again..");
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent mainIntent = new Intent(ThankuActivity.this, Value_Feedback.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 5000);
        }

        else
        {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            lockActivityOrientation(ThankuActivity.this);

            setContentView(R.layout.activity_thankus);

            textView1234 = findViewById(R.id.textView1234);


            if (Feedback_Menu.swToggle.isOn())
            {
                textView1234.setText("شكراً لك على ملاحظاتك القيمة، قم بزيارتنا مرة أخرى");
            }

            else
            {

                textView1234.setText("Thank you for your valuable feedback. Visit us again..");
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent mainIntent = new Intent(ThankuActivity.this, Value_Feedback.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 5000);

        }





//        Bools = getIntent().getExtras().getBoolean("Bools");
//        Log.e("Bool_Value3", ""+Bools);




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

    public void onBackPressed() {
//        Intent intent = new Intent(Product_Detail.this, ProductsActivity.class);
//        finish();
//        startActivity(intent);
    }
}
