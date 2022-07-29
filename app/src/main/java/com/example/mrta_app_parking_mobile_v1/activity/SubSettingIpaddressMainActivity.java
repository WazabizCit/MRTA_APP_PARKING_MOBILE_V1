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
import com.example.mrta_app_parking_mobile_v1.manager.HttpManager;
import com.example.mrta_app_parking_mobile_v1.model.Result_action_mobile_login;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubSettingIpaddressMainActivity extends ImportantMethod implements View.OnClickListener {



    String TAG = "SubSettingIpaddressMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_IP_ADDRESS = "pref_ip_address";
    private static final String PREF_PORT = "pref_port";

    private final int DefaultInt = 0;
    private final String DefaultString = "null";


    private String ip_address ;
    private String port;


    ////////////////////////////////////////////////////////////////


    EditText edit_ip_server;
    EditText edit_port;
    CardView card_ok;
    CardView card_test_conncet;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_setting_ipaddress_main);


        loadPreferences();
        inintInstances();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("IP Address");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
    }


    private void inintInstances() {

        edit_ip_server = findViewById(R.id.edit_ip_server);
        edit_port = findViewById(R.id.edit_port);

        card_ok = findViewById(R.id.card_ok);
        card_test_conncet = findViewById(R.id.card_test_conncet);

        if(ip_address.replaceAll(" ","").equals("null")){
            edit_ip_server.setText("192.168.1.1");
        }else{
            edit_ip_server.setText(ip_address+"");
            edit_ip_server.setSelection(ip_address.length());
        }

        if(port.replaceAll(" ","").equals("null")){
            edit_port.setText("5000");
        }else{
            edit_port.setText(port+"");
            edit_port.setSelection(port.length());
        }



        card_ok.setOnClickListener(this);
        card_test_conncet.setOnClickListener(this);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(SubSettingIpaddressMainActivity.this, SettingMainActivity.class);
                startActivity(intent);
                finish();

                break;

            default:

        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SubSettingIpaddressMainActivity.this, SettingMainActivity.class);
        startActivity(intent);
        finish();


    }


    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        ip_address = settings.getString(PREF_IP_ADDRESS, DefaultString);
        port = settings.getString(PREF_PORT, DefaultString);



    }

    @Override
    public void onClick(View v) {
        if(v == card_ok){

            if(checkdata()){

                save_references();
                showToastSuccess("บันทึกสำเร็จ",getApplicationContext());

            }



        }else if(v == card_test_conncet){


            loadPreferences();

            Call<ResponseBody> call =
                    HttpManager.getInstance(ip_address, port).getService().test_con();
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {



                    try {

                        String result = response.body().string();
                        showToastSuccess( result+"",SubSettingIpaddressMainActivity.this);

                    } catch (IOException e) {
                        e.printStackTrace();
                        showToastDanger( "เชื่อมต่อไม่สำเร็จ",SubSettingIpaddressMainActivity.this);
                    }


                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    showToastWarning("ผิดพลาด" + t.toString(), getApplicationContext());
                    showToastLog(TAG, t.toString());


                }
            });




        }
    }

    private void save_references() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit

        editor.putString(PREF_IP_ADDRESS, edit_ip_server.getText().toString());
        editor.putString(PREF_PORT, edit_port.getText().toString());

        editor.commit();


    }


    private boolean checkdata() {
        String port_check = edit_port.getText().toString().replaceAll(" ","");
        String ip_check = edit_ip_server.getText().toString().replaceAll(" ","");
        boolean status = true;

        if (port_check.length() == 0) {
            status = false;
            showToastWarning("กรุณาใส่ Port ", getApplicationContext());

        } else if (ip_check.length()  == 0) {
            status = false;
            showToastWarning("กรุณาใส่ IP Server", getApplicationContext());
        }


        return status;
    }
}
