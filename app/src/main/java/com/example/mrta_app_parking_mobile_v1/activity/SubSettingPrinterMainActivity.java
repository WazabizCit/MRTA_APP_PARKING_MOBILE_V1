package com.example.mrta_app_parking_mobile_v1.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.mrta_app_parking_mobile_v1.R;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;
import com.zebra.sdk.comm.BluetoothConnectionInsecure;
import com.zebra.sdk.comm.Connection;


public class SubSettingPrinterMainActivity extends ImportantMethod implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {




    String TAG = "SubSettingPrinterMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";

    private static final String PREF_STATUS_RADIO_CARIN_NOT_PRINT = "pref_status_radio_carin_not_print";
    private static final String PREF_STATUS_RADIO_CARIN_PRINT_ALL = "pref_status_radio_carin_print_all";

    private static final String PREF_STATUS_RADIO_CAROUT_NOT_PRINT = "pref_status_radio_carout_not_print";
    private static final String PREF_STATUS_RADIO_CAROUT_PRINT_ALL = "pref_status_radio_carout_print_all";
    private static final String PREF_STATUS_RADIO_CAROUT_PRINT_PRICE_ONLY = "pref_status_radio_carout_print_price_only";


    private final int DefaultInt = 0;
    private final boolean DefaultBoolean = false;
    private final String DefaultString = "null";
    private boolean status_radio_carin_not_print;
    private boolean status_radio_carin_print_all;

    private boolean status_radio_carout_not_print;
    private boolean status_radio_carout_print_all;
    private boolean status_radio_carout_print_price_only;


    private String name_mac_address_print = "0";

    EditText edit_ip_printer;
    CardView card_ok;
    CardView card_test_print;



    RadioButton radio_carin_not_print;
    RadioButton radio_carin_print_all;

    RadioButton radio_carout_not_print;
    RadioButton radio_carout_print_all;
    RadioButton radio_carout_print_price_only;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_setting_printer_main);


        loadPreferences();
        inintInstances();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ตั้งค่าเครื่อง  Print");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

    }



    private void inintInstances() {


        edit_ip_printer = findViewById(R.id.edit_ip_printer);
        card_ok = findViewById(R.id.card_ok);
        card_test_print  = findViewById(R.id.card_test_print);


        radio_carout_print_all = findViewById(R.id.radio_carout_print_all);
        radio_carout_not_print = findViewById(R.id.radio_carout_not_print);
        radio_carout_print_price_only = findViewById(R.id.radio_carout_print_price_only);

        radio_carin_not_print = findViewById(R.id.radio_carin_not_print);
        radio_carin_print_all = findViewById(R.id.radio_carin_print_all);

        edit_ip_printer.setText(name_mac_address_print + "");



        edit_ip_printer.setSelection(name_mac_address_print.length());


        card_ok.setOnClickListener(this);
        card_test_print.setOnClickListener(this);


        radio_carout_print_all.setChecked(status_radio_carout_print_all);
        radio_carout_not_print.setChecked(status_radio_carout_not_print);
        radio_carout_print_price_only.setChecked(status_radio_carout_print_price_only);

        radio_carin_not_print.setChecked(status_radio_carin_not_print);
        radio_carin_print_all.setChecked(status_radio_carin_print_all);



        radio_carin_print_all.setOnCheckedChangeListener(this);
        radio_carin_not_print.setOnCheckedChangeListener(this);

        radio_carout_print_all.setOnCheckedChangeListener(this);
        radio_carout_not_print.setOnCheckedChangeListener(this);
        radio_carout_print_price_only.setOnCheckedChangeListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(SubSettingPrinterMainActivity.this, SettingMainActivity.class);
                startActivity(intent);
                finish();
                break;

            default:

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SubSettingPrinterMainActivity.this, SettingMainActivity.class);
        startActivity(intent);
        finish();


    }


    @Override
    public void onClick(View view) {

        if (view == card_ok) {

            if(checkdata()){
                save_references();
                showToastSuccess("บันทึกสำเร็จ",getApplicationContext());
            }


        }else if(view == card_test_print){

            loadPreferences();
            sendZplOverBluetooth();


        }

    }

    private boolean checkdata() {
        String ip_printer = edit_ip_printer.getText().toString().replaceAll(" ", "");

        boolean status = true;

        if (ip_printer.length() == 0) {
            status = false;
            showToastWarning("กรุณาใส่ IP Mac Address Print", getApplicationContext());
        }


        return status;
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


        if (compoundButton == radio_carin_not_print) {
            status_radio_carin_not_print = b;

        } else if (compoundButton == radio_carin_print_all) {
            status_radio_carin_print_all = b;

        } else if (compoundButton == radio_carout_print_all) {
            status_radio_carout_print_all = b;

        } else if (compoundButton == radio_carout_not_print) {
            status_radio_carout_not_print = b;

        } else if (compoundButton == radio_carout_print_price_only) {

            status_radio_carout_print_price_only = b;

        }


    }


    private void save_references() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit

        editor.putString(PREF_MAC_ADDRESS_PRINT, edit_ip_printer.getText().toString());
        editor.putBoolean(PREF_STATUS_RADIO_CARIN_NOT_PRINT, status_radio_carin_not_print);
        editor.putBoolean(PREF_STATUS_RADIO_CARIN_PRINT_ALL, status_radio_carin_print_all);
        editor.putBoolean(PREF_STATUS_RADIO_CAROUT_NOT_PRINT, status_radio_carout_not_print);
        editor.putBoolean(PREF_STATUS_RADIO_CAROUT_PRINT_ALL, status_radio_carout_print_all);
        editor.putBoolean(PREF_STATUS_RADIO_CAROUT_PRINT_PRICE_ONLY, status_radio_carout_print_price_only);
        editor.commit();

    }


    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        name_mac_address_print = settings.getString(PREF_MAC_ADDRESS_PRINT, DefaultString);
        status_radio_carin_not_print = settings.getBoolean(PREF_STATUS_RADIO_CARIN_NOT_PRINT, DefaultBoolean);
        status_radio_carin_print_all = settings.getBoolean(PREF_STATUS_RADIO_CARIN_PRINT_ALL, DefaultBoolean);

        status_radio_carout_not_print = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_NOT_PRINT, DefaultBoolean);
        status_radio_carout_print_all = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_PRINT_ALL, DefaultBoolean);
        status_radio_carout_print_price_only = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_PRINT_PRICE_ONLY, DefaultBoolean);



    }


    private void sendZplOverBluetooth() {

        new Thread(new Runnable() {
            public void run() {
                try {
                    // Instantiate connection for given Bluetooth&reg; MAC Address.
                    Connection thePrinterConn = new BluetoothConnectionInsecure(name_mac_address_print);

                    // Initialize
                    Looper.prepare();

                    // Open the connection - physical connection is established here.
                    thePrinterConn.open();





                    String zpl = getStringZplTEST();



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