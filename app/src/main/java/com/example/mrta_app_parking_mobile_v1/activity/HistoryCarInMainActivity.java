package com.example.mrta_app_parking_mobile_v1.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.mrta_app_parking_mobile_v1.dao.DataHistoryCarInDao;
import com.example.mrta_app_parking_mobile_v1.model.History_data_carin_dao;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;
import com.zebra.sdk.comm.BluetoothConnectionInsecure;
import com.zebra.sdk.comm.Connection;


import java.util.ArrayList;

public class HistoryCarInMainActivity extends ImportantMethod {



    ListView listview;
    History_CarIn_Data_Adapter adapter;
    ArrayList<History_data_carin_dao> mlist;
    TextView txt_view_nodata;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDoalog;



    private static final String PREFS_NAME = "preferences";
    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";
    private static final String PREF_STATUS_RADIO_CARIN_NOT_PRINT = "pref_status_radio_carin_not_print";
    private static final String PREF_STATUS_RADIO_CARIN_PRINT_ALL = "pref_status_radio_carin_print_all";
    private static final String PREF_BUILDING_CODE = "pref_building_code";
    private static final String PREF_BUILDING_NAME = "pref_building_name";



    private final boolean DefaultBoolean = false;
    private final int DefaultInt = 0;
    private final String DefaultString = "null";
    private String name_building_code;
    private String name_building_name;






    private String name_mac_address_print = "0";
    private boolean status_radio_carin_not_print;
    private boolean status_radio_carin_print_all;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_car_in_main);

        inintInstances();
        getListHistory();
        loadPreferences();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ประวัติงานขาเข้า Parking");

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


                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryCarInMainActivity.this);
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
                        String card_code = ((History_data_carin_dao) adapter.getItem(position)).getTran_carin_cardcode();
                        String card_name = ((History_data_carin_dao) adapter.getItem(position)).getTran_carin_cardname();
                        String timestamp = ((History_data_carin_dao) adapter.getItem(position)).getTran_carin_cabinet_send_time();
                        String license_plate = ((History_data_carin_dao) adapter.getItem(position)).getTran_carin_license_plate();
                        String carin_guardhouse_in = ((History_data_carin_dao) adapter.getItem(position)).getTran_carin_cabinet_code();
                        String carin_user_name = ((History_data_carin_dao) adapter.getItem(position)).getTran_carin_admin_name();
                        String carin_cartype_name = ((History_data_carin_dao) adapter.getItem(position)).getTran_carin_card_type_name();


                        sendZplOverBluetooth(carin_guardhouse_in, license_plate, timestamp, card_code,card_name,carin_user_name,carin_cartype_name,name_building_name);
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

            Intent intent = new Intent(HistoryCarInMainActivity.this, MainActivity.class);
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

        Intent intent = new Intent(HistoryCarInMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void getListHistory() {
        String datetime = getCurrentDate();

        DataHistoryCarInDao dao = new DataHistoryCarInDao(getApplicationContext());
        dao.open();
        ArrayList<History_data_carin_dao> mlist = dao.getDataHistory();
        dao.close();
        if (mlist.size() == 0) {
            listview.setVisibility(View.GONE);
            txt_view_nodata.setVisibility(View.VISIBLE);

        } else {
            adapter = new History_CarIn_Data_Adapter(HistoryCarInMainActivity.this, mlist);
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            txt_view_nodata.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
        }


    }



    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // Get value

        name_mac_address_print = settings.getString(PREF_MAC_ADDRESS_PRINT, DefaultString);
        status_radio_carin_print_all = settings.getBoolean(PREF_STATUS_RADIO_CARIN_PRINT_ALL, DefaultBoolean);
        status_radio_carin_not_print = settings.getBoolean(PREF_STATUS_RADIO_CARIN_NOT_PRINT, DefaultBoolean);
        name_building_code = settings.getString(PREF_BUILDING_CODE , DefaultString);
        name_building_name = settings.getString(PREF_BUILDING_NAME , DefaultString);

    }





    private void sendZplOverBluetooth(final String location_zpl, final String license_plate, final String time,
                                      final String card_code,final String card_name,final String cashier,final String type_car,final String name_building_name) {

        new Thread(new Runnable() {
            public void run() {
                try {
                    // Instantiate connection for given Bluetooth&reg; MAC Address.
                    Connection thePrinterConn = new BluetoothConnectionInsecure(name_mac_address_print);

                    // Initialize
                    Looper.prepare();

                    // Open the connection - physical connection is established here.
                    thePrinterConn.open();

                    String zpl = getStringZplIN(location_zpl, license_plate, time, card_code,card_name,cashier,type_car,name_building_name);
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






}
