package com.example.mrta_app_parking_mobile_v1.activity;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mrta_app_parking_mobile_v1.R;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;

public class InCarMainActivity extends ImportantMethod implements View.OnClickListener {

    String TAG = "InCarMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";
    private static final String PREF_IP_ADDRESS = "pref_ip_address";
    private static final String PREF_PORT = "pref_port";
    private static final String PREF_NAME_DEVICE = "pref_name_device";
    private final int DefaultInt = 0;
    private final String DefaultString = "null";
    private String mac_address_print;
    private String ip_address;
    private String port;
    private String name_device;

    private DrawerLayout drawer;
    CardView card_ok;
    EditText edit_license_plate;
    EditText edit_id_card;
    private ProgressDialog progressDoalog;


    //////////////////////////  NFC  //////////////////////////


    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private String tag_id_card = null;


    //////////////////////////  NFC  //////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_car_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ทำรายการขาเข้า");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        inintInstances();

        //////////////////////////  NFC  //////////////////////////
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (!mAdapter.isEnabled()) {
            showToastWarning("กรุณาเปิด NFC", this);
        }


        if (mAdapter == null) {
            //nfc not support your device.
            showToastWarning("nfc not support your device", this);
            return;
        }
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        //////////////////////////  NFC //////////////////////////

    }

    private void inintInstances() {

        edit_license_plate = findViewById(R.id.edit_license_plate);
        card_ok = findViewById(R.id.card_ok);
        edit_id_card = findViewById(R.id.edit_id_card);

        edit_id_card.setText(tag_id_card);
        card_ok.setOnClickListener(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        loadPreferences();
        if (mAdapter != null) {
            mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mAdapter != null) {
            mAdapter.disableForegroundDispatch(this);
        }
    }


    @Override
    public void onNewIntent(Intent intent) {
        getTagInfo(intent);
    }

    private void getTagInfo(Intent intent) {
        progressDoalog = new ProgressDialog(this);
        progressDoalog.setTitle("ตรวจสอบข้อมูล Card");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setMessage("กรุณารอสักครู่...");
        progressDoalog.setCancelable(true);
        progressDoalog.show();


        try {

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String tagInfo = "";
            byte[] tagId = tag.getId();
            tag_id_card = String.valueOf(Long.parseLong(bytesToHexString(tagId), 16));


            showToastLog(TAG, "formattag_HEX: " + bytesToHexString(tagId));
            showToastLog(TAG, "formattag_DEC: " + tag_id_card);


        } catch (Exception e) {

            showToastSuccess("RFID ไม่ตรงกัน", InCarMainActivity.this);
            tag_id_card = null;
            edit_id_card.setText("เลข Card");
            showToastLog(TAG, "Error: " + e);

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_clear, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(InCarMainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                break;

            default:

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InCarMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        if (card_ok == v) {

           // Log.d("xxasdkkasod","adkokaosdkpoaskdp");
           // showToastWarning("axcxzc", this);

            showToastSuccess("axcxzc", this);

          //  showToastDanger("axcxzc", this);

//            progressDoalog = new ProgressDialog(this);
//            progressDoalog.setTitle("กำลังส่งข้อมูล Service");
//            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDoalog.setMessage("กรุณารอสักครู่...");
//            progressDoalog.setCancelable(false);
//
//            final String license_plate = edit_license_plate.getText().toString().replaceAll(" ", "");
//            final String card_code = tag_id_card;
//            final String currentdate = getCurrentDate();
//            final String timestamp  =   getCurrentTimeStamp();
//            progressDoalog.show();
//
//
//            if (checkdata()) {
//
//
//
//
//            }else{
//                progressDoalog.dismiss();
//            }


        }


    }


    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
            System.out.println(buffer);
            stringBuilder.append(buffer);
        }
        return stringBuilder.toString();
    }


    private boolean checkdata() {

        boolean status = true;

        if (tag_id_card == null) {
            status = false;
            showToastWarning("กรุณาอ่าน Card ", getApplicationContext());

        } else if (edit_license_plate.getText().toString().replaceAll(" ", "").equals("")) {
            status = false;
            showToastWarning("กรุณาใส่ทะเบียนรถ", getApplicationContext());
        }
        return status;

    }




    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        // Get value

        mac_address_print = settings.getString(PREF_MAC_ADDRESS_PRINT, DefaultString);
        ip_address = settings.getString(PREF_IP_ADDRESS, DefaultString);
        port = settings.getString(PREF_PORT, DefaultString);
        name_device = settings.getString(PREF_NAME_DEVICE, DefaultString);


    }


}
