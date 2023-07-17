package com.example.mrta_app_parking_mobile_v1.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mrta_app_parking_mobile_v1.R;
import com.example.mrta_app_parking_mobile_v1.adapter.History_CarIn_Data_Adapter;
import com.example.mrta_app_parking_mobile_v1.adapter.History_CarOut_Data_Adapter;
import com.example.mrta_app_parking_mobile_v1.dao.DataHistoryCarInDao;
import com.example.mrta_app_parking_mobile_v1.dao.DataHistoryCarOutDao;
import com.example.mrta_app_parking_mobile_v1.model.History_data_carin_dao;
import com.example.mrta_app_parking_mobile_v1.model.History_data_carout_dao;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;
import com.zebra.sdk.comm.BluetoothConnectionInsecure;
import com.zebra.sdk.comm.Connection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class HistoryCarOutMainActivity extends ImportantMethod {

    String TAG = "HistoryCarOutMainActivity";


    ListView listview;
    History_CarOut_Data_Adapter adapter;
    ArrayList<History_data_carout_dao> mlist;
    TextView txt_view_nodata;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDoalog;


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";
    private static final String PREF_STATUS_RADIO_CARIN_NOT_PRINT = "pref_status_radio_carin_not_print";
    private static final String PREF_STATUS_RADIO_CARIN_PRINT_ALL = "pref_status_radio_carin_print_all";
    private static final String PREF_BUILDING_CODE = "pref_building_code";
    private static final String PREF_BUILDING_NAME = "pref_building_name";
    private static final String PREF_BUILDING_TEL = "pref_building_tel";



    private final boolean DefaultBoolean = false;
    private final int DefaultInt = 0;
    private final String DefaultString = "null";
    private String name_building_code;
    private String name_building_name;
    private String name_building_tel;


    private String name_mac_address_print = "0";
    private boolean status_radio_carin_not_print;
    private boolean status_radio_carin_print_all;


    private String carout_diff_time_in_out = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_car_out_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        loadPreferences();
        inintInstances();
        getListHistory();



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ประวัติงานขาออก Parking");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

    }
    private void inintInstances() {

        txt_view_nodata = findViewById(R.id.txt_view_nodata);
        listview = findViewById(R.id.listview);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(pullToRefreshListener);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryCarOutMainActivity.this);
                builder.setCancelable(false);
                builder.setMessage("คุณต้องการที่จะพิมพ์ข้อมูลบัตรใช่หรือไม่");
                builder.setPositiveButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("ใช่", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        // Do something
                        String carout_building_tax = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_building_tax();
                        String carout_registered_no = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_registered_no();
                        String carout_cabinet_tax_code = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_cabinet_tax_code();
                        String carout_cabinet_code = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_cabinet_code();
                        String carout_cabinet_name = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_cabinet_name();
                        String carout_carparking_out_time = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_carparking_out_time();
                        String carout_receipt_no = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_receipt_no();
                        String carout_estamp_status = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_estamp_status();
                        String carout_car_type_name = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_car_type_name();
                        String carout_license_plate = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_license_plate();
                        String carout_cardcode = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_cardcode();
                        String carout_carparking_in_time = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_carparking_in_time();
                        String carout_payment_amount = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_payment_amount();
                        String carout_payment_fine_amount = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_payment_fine_amount();
                        String carout_payment_totle = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_payment_totle();
                        String carout_payment_type_name_th = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_payment_type_name_th();

                        String start_date    =  carout_carparking_in_time;
                        String end_date   =  carout_carparking_out_time;



                        findDifference(start_date,end_date);


                        sendZplOverBluetooth(
                                carout_building_tax,carout_registered_no,
                                carout_cabinet_tax_code,carout_cabinet_code,carout_cabinet_name,
                                carout_carparking_out_time,carout_receipt_no,carout_estamp_status,carout_car_type_name,
                                carout_license_plate,carout_cardcode,carout_carparking_in_time,name_building_tel,
                                carout_payment_amount,carout_payment_fine_amount,carout_payment_totle,carout_payment_type_name_th
                                ,carout_diff_time_in_out);


                        dialog.dismiss();

                    }

                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });





    }


    SwipeRefreshLayout.OnRefreshListener pullToRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshData();
        }
    };

    private void refreshData() {
        getListHistory();
        swipeRefreshLayout.setRefreshing(false);
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == android.R.id.home) {

            Intent intent = new Intent(HistoryCarOutMainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(HistoryCarOutMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }


    private void getListHistory() {
        String datetime = getCurrentDate();

        DataHistoryCarOutDao dao = new DataHistoryCarOutDao(getApplicationContext());
        dao.open();
        ArrayList<History_data_carout_dao> mlist = dao.getDataHistory();
        dao.close();
        if (mlist.size() == 0) {
            listview.setVisibility(View.GONE);
            txt_view_nodata.setVisibility(View.VISIBLE);

        } else {
            adapter = new History_CarOut_Data_Adapter(HistoryCarOutMainActivity.this, mlist);
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            txt_view_nodata.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
        }


    }




    private void sendZplOverBluetooth(
                                      final String carout_building_tax,
                                      final String carout_registered_no,
                                      final String carout_cabinet_tax_code,
                                      final String carout_cabinet_code,
                                      final String carout_cabinet_name,
                                      final String carout_carparking_out_time,
                                      final String carout_receipt_no,
                                      final String carout_estamp_status,
                                      final String carout_car_type_name,
                                      final String carout_license_plate,
                                      final String carout_cardcode,
                                      final String carout_carparking_in_time,
                                      final String carout_building_tel,
                                      final String carout_payment_amount,
                                      final String carout_payment_fine_amount,
                                      final String carout_payment_totle,
                                      final String carout_payment_type_name_th,
                                      final String carout_diff_time_in_out

    ) {

        new Thread(new Runnable() {
            public void run() {
                try {
                    // Instantiate connection for given Bluetooth&reg; MAC Address.
                    Connection thePrinterConn = new BluetoothConnectionInsecure(name_mac_address_print);

                    // Initialize
                    Looper.prepare();

                    // Open the connection - physical connection is established here.
                    thePrinterConn.open();





                    String zpl = getStringZplOUT(
                            carout_building_tax,carout_registered_no,
                            carout_cabinet_tax_code,carout_cabinet_code,
                            carout_cabinet_name,carout_carparking_out_time,
                            carout_receipt_no,carout_estamp_status,carout_car_type_name,
                            carout_license_plate,carout_cardcode,carout_carparking_in_time,
                            carout_building_tel,carout_payment_amount,carout_payment_fine_amount,
                            carout_payment_totle,carout_payment_type_name_th,carout_diff_time_in_out
                            );



                    thePrinterConn.write(zpl.getBytes());


                    // Make sure the data got to the printer before closing the connection
                    Thread.sleep(500);

                    // Close the connection to release resources.
                    thePrinterConn.close();

                    Looper.myLooper().quit();
                } catch (Exception e) {
                    // Handle communications error here.
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // Get value

        name_mac_address_print = settings.getString(PREF_MAC_ADDRESS_PRINT, DefaultString);
        status_radio_carin_print_all = settings.getBoolean(PREF_STATUS_RADIO_CARIN_PRINT_ALL, DefaultBoolean);
        status_radio_carin_not_print = settings.getBoolean(PREF_STATUS_RADIO_CARIN_NOT_PRINT, DefaultBoolean);
        name_building_code = settings.getString(PREF_BUILDING_CODE , DefaultString);
        name_building_name = settings.getString(PREF_BUILDING_NAME , DefaultString);
        name_building_tel = settings.getString(PREF_BUILDING_TEL , DefaultString);


    }





    private void  findDifference(String start_date,
                                 String end_date)
    {

        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
                = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss");

        // Try Block
        try {


            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);


            long difference_In_Time
                    = d2.getTime() - d1.getTime();


            long difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;


            carout_diff_time_in_out = "" + difference_In_Days +"วัน "  +difference_In_Hours+"ชั่วโมง " +difference_In_Minutes+ "นาที " +difference_In_Seconds+ "วินาที ";



        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
    }








}
