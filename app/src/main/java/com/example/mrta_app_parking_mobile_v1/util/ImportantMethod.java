package com.example.mrta_app_parking_mobile_v1.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ImportantMethod extends AppCompatActivity {

    public void showToastLog(String text1, String text2) {
        Log.d(text1, text2);
    }


    public void showToastWarning(String text, Context context) {

        int bg_color = Color.parseColor("#ffc000");

        Toast tost  = Toast.makeText(context,text,Toast.LENGTH_LONG);
        View view  = tost.getView();
        view.getBackground().setColorFilter(bg_color, PorterDuff.Mode.SRC_IN);
        TextView textx = view.findViewById(android.R.id.message);
        textx.setTextSize(20);
        textx.setTextColor(Color.BLACK);
        tost.show();



    }



    public void showToastSuccess(String text, Context context) {


        int bg_color = Color.parseColor("#28a953");

        Toast tost  = Toast.makeText(context,text,Toast.LENGTH_LONG);
        View view  = tost.getView();
        view.getBackground().setColorFilter(bg_color, PorterDuff.Mode.SRC_IN);
        TextView textx = view.findViewById(android.R.id.message);
        textx.setTextSize(20);
        textx.setTextColor(Color.WHITE);
        tost.show();




    }



    public void showToastDanger(String text, Context context) {


        int bg_color = Color.parseColor("#e60000");

        Toast tost  = Toast.makeText(context,text,Toast.LENGTH_LONG);
        View view  = tost.getView();
        view.getBackground().setColorFilter(bg_color, PorterDuff.Mode.SRC_IN);
        TextView textx = view.findViewById(android.R.id.message);
        textx.setTextSize(20);
        textx.setTextColor(Color.WHITE);
        tost.show();




    }




    public static String getCurrentTimeStamp() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


    public static String getCurrentDate() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

}
