package com.example.mrta_app_parking_mobile_v1.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mrta_app_parking_mobile_v1.R;
import com.example.mrta_app_parking_mobile_v1.manager.HttpManager;
import com.example.mrta_app_parking_mobile_v1.model.Result_action_mobile_in;
import com.example.mrta_app_parking_mobile_v1.model.Result_action_mobile_login;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginMainActivity extends ImportantMethod implements View.OnClickListener {


    String TAG = "LoginMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_IP_ADDRESS = "pref_ip_address";
    private static final String PREF_PORT = "pref_port";
    private static final String PREF_CABINET_ID = "pref_cabinet_id";
    private static final String PREF_CABINET_CODE = "pref_cabinet_code";
    private static final String PREF_BUILDING_ID = "pref_building_id";
    private static final String PREF_BUILDING_CODE = "pref_building_code";
    private static final String PREF_ADMIN_ID = "pref_admin_id";
    private static final String PREF_ADMIN_NAME = "pref_admin_name";







    private final int DefaultInt = 0;
    private final String DefaultString = "null";
    private String name_cabinet_id;
    private String name_cabinet_code;
    private String name_building_id;
    private String name_building_code;
    private String name_admin_id;
    private String name_admin_name;
    private String ip_address;
    private String port;


    private ProgressDialog progressDoalog;
    RelativeLayout relativeLayout_login;
    EditText edit_username;
    EditText edit_password;
    Button btn_login;
    TextView btn_admin_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        loadPreferences();
        inintInstances();
    }


    private void inintInstances() {

        relativeLayout_login = findViewById(R.id.relativeLayout_login);
        edit_username = findViewById(R.id.edit_username);
        edit_password = findViewById(R.id.edit_password);
        btn_login = findViewById(R.id.btn_login);
        btn_admin_setting = findViewById(R.id.btn_admin_setting);


       // relativeLayout_login.setOnTouchListener(this);

        btn_login.setOnClickListener(this);
        btn_admin_setting.setOnClickListener(this);
        edit_username.setText("0023");
        edit_password.setText("0023");

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();

    }


/*
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }*/

    @Override
    public void onClick(View v) {
        if (btn_login == v) {

            progressDoalog = new ProgressDialog(this);
            progressDoalog.setTitle("กำลังส่งข้อมูล Service");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.setMessage("กรุณารอสักครู่...");
            progressDoalog.setCancelable(false);

            final String username = edit_username.getText().toString().replaceAll(" ", "");
            final String password = edit_password.getText().toString().replaceAll(" ", "");
            final String timestamp = getCurrentTimeStamp();
            progressDoalog.show();
            if (checkdata()) {
                Call<Result_action_mobile_login> call =
                        HttpManager.getInstance(ip_address, port).getService().action_mobile_login(name_cabinet_id, timestamp, name_cabinet_code, name_building_id,
                             name_building_code   , username, password);
                call.enqueue(new Callback<Result_action_mobile_login>() {
                    @Override
                    public void onResponse(Call<Result_action_mobile_login> call, Response<Result_action_mobile_login> response) {

                        progressDoalog.dismiss();
                        if (null == response.body().getError()) {

                            name_admin_id = response.body().getData().getEmployeeId()+"";
                            name_admin_name = response.body().getData().getFirstNameTh()+"";
                            save_references();
                            Intent intent = new Intent(LoginMainActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();





                        } else {

                            showToastWarning(response.body().getMessage(), getApplicationContext());

                        }


                    }

                    @Override
                    public void onFailure(Call<Result_action_mobile_login> call, Throwable t) {

                        showToastWarning("Login ผิดพลาด" + t.toString(), getApplicationContext());
                        showToastLog(TAG, t.toString());
                        progressDoalog.dismiss();

                    }
                });


            } else {
                progressDoalog.dismiss();
            }

        }else if(v == btn_admin_setting ){



            if(checkdata()){

                final String username = edit_username.getText().toString().replaceAll(" ", "");
                final String password = edit_password.getText().toString().replaceAll(" ", "");


                if(username.equals("00231") && password.equals("00231")){

                    Intent intent = new Intent(LoginMainActivity.this, SettingMainActivity.class);
                    startActivity(intent);

                }else {

                    showToastWarning("Username หรือ Password Admin ไม่ถูกต้อง",LoginMainActivity.this);

                }
            }

        }

    }


    private boolean checkdata() {

        boolean status = true;

        if (edit_username.getText().toString().replaceAll(" ", "").equals("")) {
            status = false;
            showToastWarning("กรุณาใส่ Username", getApplicationContext());
        } else if (edit_password.getText().toString().replaceAll(" ", "").equals("")) {
            status = false;
            showToastWarning("กรุณาใส่ Password", getApplicationContext());
        }
        return status;

    }




    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        name_cabinet_id = settings.getString(PREF_CABINET_ID , DefaultString);
        name_cabinet_code = settings.getString(PREF_CABINET_CODE , DefaultString);
        name_building_id = settings.getString(PREF_BUILDING_ID , DefaultString);
        name_building_code = settings.getString(PREF_BUILDING_CODE , DefaultString);
        ip_address = settings.getString(PREF_IP_ADDRESS, DefaultString);
        port = settings.getString(PREF_PORT, DefaultString);


        showToastLog(TAG,
                "name_cabinet_id :"+name_cabinet_id+
                        "name_cabinet_code :"+name_cabinet_code+
                        "name_building_id :"+name_building_id+
                        "name_building_code :"+name_building_code+
                        "ip_address :"+ip_address+
                        "port :"+port+
                        ""


        );

    }


    private void save_references() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        // Edit and commit
        editor.putString(PREF_ADMIN_ID , name_admin_id+ "");
        editor.putString(PREF_ADMIN_NAME , name_admin_name+ "");
        editor.commit();


    }





}