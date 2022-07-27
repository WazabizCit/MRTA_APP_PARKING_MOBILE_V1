package com.example.mrta_app_parking_mobile_v1.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.mrta_app_parking_mobile_v1.R;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;

public class SubSettingDeviceMainActivity extends ImportantMethod implements View.OnClickListener {



    String TAG = "SubSettingDeviceMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_CABINET_ID = "pref_cabinet_id";
    private static final String PREF_CABINET_CODE = "pref_cabinet_code";
    private static final String PREF_BUILDING_ID = "pref_building_id";
    private static final String PREF_BUILDING_CODE = "pref_building_code";
    private static final String PREF_BUILDING_NAME = "pref_building_name";
    private static final String PREF_BUILDING_TEL = "pref_building_tel";




    private final int DefaultInt = 0;
    private final String DefaultString = "null";
    private String name_cabinet_id;
    private String name_cabinet_code;
    private String name_building_id;
    private String name_building_code;
    private String name_building_name;
    private String name_building_tel;





    EditText edit_cabinet_id;
    EditText edit_cabinet_code;
    EditText edit_building_id;
    EditText edit_building_code;
    EditText edit_building_name;
    EditText edit_building_tel;
    CardView card_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_setting_device_main);
        loadPreferences();
        inintInstances();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ตั้งค่าเครื่อง");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


    }

    private void inintInstances() {


        edit_cabinet_id = findViewById(R.id.edit_cabinet_id);
        edit_cabinet_code = findViewById(R.id.edit_cabinet_code);
        edit_building_id = findViewById(R.id.edit_building_id);
        edit_building_code = findViewById(R.id.edit_building_code);
        edit_building_name = findViewById(R.id.edit_building_name);
        edit_building_tel  = findViewById(R.id.edit_building_tel);
        card_ok = findViewById(R.id.card_ok);


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







        card_ok.setOnClickListener(this);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(SubSettingDeviceMainActivity.this, SettingMainActivity.class);
                startActivity(intent);
                finish();
                break;

            default:

        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SubSettingDeviceMainActivity.this, SettingMainActivity.class);
        startActivity(intent);
        finish();


    }


    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        name_cabinet_id = settings.getString(PREF_CABINET_ID , DefaultString);
        name_cabinet_code = settings.getString(PREF_CABINET_CODE , DefaultString);
        name_building_id = settings.getString(PREF_BUILDING_ID , DefaultString);
        name_building_code = settings.getString(PREF_BUILDING_CODE , DefaultString);
        name_building_name = settings.getString(PREF_BUILDING_NAME , DefaultString);
        name_building_tel = settings.getString(PREF_BUILDING_TEL , DefaultString);

    }

    @Override
    public void onClick(View v) {
        if (v == card_ok) {

            save_references();
            showToastSuccess("บันทึกสำเร็จ", getApplicationContext());

        }
    }





    private void save_references() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit
        editor.putString(PREF_CABINET_ID , edit_cabinet_id.getText() + "");
        editor.putString(PREF_CABINET_CODE , edit_cabinet_code.getText() + "");
        editor.putString(PREF_BUILDING_ID , edit_building_id.getText() + "");
        editor.putString(PREF_BUILDING_CODE , edit_building_code.getText() + "");
        editor.putString(PREF_BUILDING_NAME , edit_building_name.getText() + "");
        editor.putString(PREF_BUILDING_TEL , edit_building_tel.getText() + "");
        editor.commit();


    }








}