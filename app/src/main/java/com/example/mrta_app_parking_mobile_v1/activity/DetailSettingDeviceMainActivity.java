package com.example.mrta_app_parking_mobile_v1.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.mrta_app_parking_mobile_v1.R;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;

public class DetailSettingDeviceMainActivity extends ImportantMethod {


    String TAG = "SubSettingDeviceMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_CABINET_ID = "pref_cabinet_id";
    private static final String PREF_CABINET_CODE = "pref_cabinet_code";
    private static final String PREF_BUILDING_ID = "pref_building_id";
    private static final String PREF_BUILDING_CODE = "pref_building_code";
    private static final String PREF_BUILDING_NAME = "pref_building_name";
    private static final String PREF_BUILDING_TEL = "pref_building_tel";
    private static final String PREF_IP_ADDRESS = "pref_ip_address";
    private static final String PREF_PORT = "pref_port";


    private final int DefaultInt = 0;
    private final String DefaultString = "null";
    private String name_cabinet_id;
    private String name_cabinet_code;
    private String name_building_id;
    private String name_building_code;
    private String name_building_name;
    private String name_building_tel;
    private String name_ip_address ;
    private String name_port;



    EditText edit_cabinet_id;
    EditText edit_cabinet_code;
    EditText edit_building_id;
    EditText edit_building_code;
    EditText edit_building_name;
    EditText edit_building_tel;
    EditText edit_ip_server;
    EditText edit_port;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_setting_device_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        loadPreferences();
        inintInstances();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ข้อมูลตั้งค่าเครื่อง");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
    }


        private void inintInstances () {


            edit_cabinet_id = findViewById(R.id.edit_cabinet_id);
            edit_cabinet_code = findViewById(R.id.edit_cabinet_code);
            edit_building_id = findViewById(R.id.edit_building_id);
            edit_building_code = findViewById(R.id.edit_building_code);
            edit_building_name = findViewById(R.id.edit_building_name);
            edit_building_tel = findViewById(R.id.edit_building_tel);
            edit_ip_server = findViewById(R.id.edit_ip_server);
            edit_port = findViewById(R.id.edit_port);


            edit_cabinet_id.setText(name_cabinet_id + "");
            edit_cabinet_id.setSelection(name_cabinet_id.length());

            edit_cabinet_code.setText(name_cabinet_code + "");
            edit_cabinet_code.setSelection(name_cabinet_code.length());

            edit_building_id.setText(name_building_id + "");
            edit_building_id.setSelection(name_building_id.length());

            edit_building_code.setText(name_building_code + "");
            edit_building_code.setSelection(name_building_code.length());

            edit_building_name.setText(name_building_name + "");
            edit_building_name.setSelection(name_building_name.length());


            edit_building_tel.setText(name_building_tel + "");
            edit_building_tel.setSelection(name_building_tel.length());

            edit_ip_server.setText(name_ip_address + "");
            edit_ip_server.setSelection(name_ip_address.length());


            edit_port.setText(name_port + "");
            edit_port.setSelection(name_port.length());






        }


        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // handle arrow click here

            int id = item.getItemId();

            switch (id) {
                case android.R.id.home:
                    Intent intent = new Intent(DetailSettingDeviceMainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                default:

            }


            return super.onOptionsItemSelected(item);
        }


        @Override
        public void onBackPressed () {

            Intent intent = new Intent(DetailSettingDeviceMainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();


        }


        private void loadPreferences () {

            SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);

            // Get value
            name_cabinet_id = settings.getString(PREF_CABINET_ID, DefaultString);
            name_cabinet_code = settings.getString(PREF_CABINET_CODE, DefaultString);
            name_building_id = settings.getString(PREF_BUILDING_ID, DefaultString);
            name_building_code = settings.getString(PREF_BUILDING_CODE, DefaultString);
            name_building_name = settings.getString(PREF_BUILDING_NAME, DefaultString);
            name_building_tel = settings.getString(PREF_BUILDING_TEL, DefaultString);
            name_ip_address = settings.getString(PREF_IP_ADDRESS, DefaultString);
            name_port = settings.getString(PREF_PORT, DefaultString);





        }






}