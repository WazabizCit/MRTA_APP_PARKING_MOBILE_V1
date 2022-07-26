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





    public  String getStringZplIN(String location_zpl,String license_plate,String time,String card_code,String card_name,
                                  String cashier,String type_car,String name_building_name) {

        String zpl_in = "^XA\n" +
                "^CFA,30  +\n" +
                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,80,80\n" +
                "^CI28\n" +
                "^FO210,80^FDMRTA^FS\n" +
                "^FS\n" +


                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,50,50\n" +
                "^CI28\n" +
                "^FO220,145^FD"+name_building_name+"^FS\n" +
                "^FS\n" +


                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO150,190^FDใบเสร็จทำรายการขาเข้า^FS\n" +
                "^FS\n" +


                "^FO70,270^GB460,1,3^FS\n" +
                "^CFA,30\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28 \n" +
                "^FO70,300^FDตำแหน่งเข้า :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28 \n" +
                "^FO250,300^FD "+location_zpl+" ^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28 \n" +
                "^FO70,340^FDทะเบียนรถ :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO250,340^FD"+license_plate+"^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO70,380^FDเวลาเข้า :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO180,380^FD"+time+"^FS\n" +
                "^FS\n" +



                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO70,420^FDCode :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO180,420^FD"+card_code+"^FS\n" +
                "^FS\n" +


                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO70,460^FDรหัสหลังบัตร :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO250,460^FD"+card_name+"^FS\n" +
                "^FS\n" +



                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO70,500^FDประภท :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO180,500^FD"+type_car+"^FS\n" +
                "^FS\n" +





                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO70,540^FDCashier :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO180,540^FD"+cashier+"^FS\n" +
                "^FS\n" +


                          "^CFA,15\n" +
                "^FO70,600^GB460,1,3^FS\n" +
                "^FO120,570^BY2\n" +


//                "^BCN,50,Y,N,N\n" +
//                "^FD "+card_code+" ^FS\n" +

                "^XZ;";





        return zpl_in;
    }

}
