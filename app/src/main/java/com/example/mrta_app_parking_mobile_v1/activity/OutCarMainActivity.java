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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.mrta_app_parking_mobile_v1.R;
import com.example.mrta_app_parking_mobile_v1.dao.DataHistoryCarBookingInDao;
import com.example.mrta_app_parking_mobile_v1.manager.HttpManager;
import com.example.mrta_app_parking_mobile_v1.model.History_data_carin_dao;
import com.example.mrta_app_parking_mobile_v1.model.Result_action_mobile_in;
import com.example.mrta_app_parking_mobile_v1.model.Result_action_mobile_out_call_checkpayment;
import com.example.mrta_app_parking_mobile_v1.model.Result_checkcard;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutCarMainActivity extends ImportantMethod implements View.OnClickListener {


    String TAG = "OutCarMainActivity";


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
    private String ip_address;
    private String port;
    private String name_cabinet_id;
    private String name_cabinet_code;
    private String name_building_id;
    private String name_building_code;
    private String name_admin_id;
    private String name_admin_name;


    private DrawerLayout drawer;
    CardView card_ok;
    EditText edit_id_card;
    EditText edit_info;
    private ProgressDialog progressDoalog;


    //////////////////////////  NFC  //////////////////////////


    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private String tag_id_card = null;


    //////////////////////////  NFC  //////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_car_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ทำรายการขาออก");

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
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        //////////////////////////  NFC //////////////////////////

    }

    private void inintInstances() {

        edit_info = findViewById(R.id.edit_info);
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

        final String timestamp = getCurrentTimeStamp();

        try {

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String tagInfo = "";
            byte[] tagId = tag.getId();
            tag_id_card = String.valueOf(Long.parseLong(bytesToHexString(tagId), 16));


            showToastLog(TAG, "formattag_HEX: " + bytesToHexString(tagId));
            showToastLog(TAG, "formattag_DEC: " + tag_id_card);

            Call<Result_action_mobile_out_call_checkpayment> call =
                    HttpManager.getInstance(ip_address, port).getService().action_mobile_out_call_checkpayment(name_cabinet_id, name_cabinet_code, name_building_id, name_building_code, tag_id_card, timestamp, name_admin_id, "", "");
            call.enqueue(new Callback<Result_action_mobile_out_call_checkpayment>() {
                @Override
                public void onResponse(Call<Result_action_mobile_out_call_checkpayment> call, Response<Result_action_mobile_out_call_checkpayment> response) {


                    if (null == response.body().getError()) {




                        edit_id_card.setText(tag_id_card + "");

                        String txt_lic_plate = response.body().getData().getLicensePlateText() + "";
                        String txt_card_status = response.body().getData().getCardStatus() + "";
                        String txt_car_type_status = response.body().getData().getCarTypeStatus() + "";
                        String txt_carparking_in_time = response.body().getData().getCarparkingInTime() + "";
                        String txt_carparking_out_time = response.body().getData().getCarparkingOutTime() + "";
                        String txt_result_parking_interval = response.body().getData().getResultParkingInterval() + "";
                        String txt_estamp_type_name = response.body().getData().getEstampTypeName() + "";
                        String txt_payment_totle = response.body().getData().getPaymentTotle() + "";
                        String txt_discount_amount = response.body().getData().getDiscountAmount() + "";
                        String txt_payment_fine_amount = response.body().getData().getPaymentFineAmount() + "";
                        String txt_uat_text = response.body().getData().getUatText() + "";


                        edit_info.setText(
                                "สถานะระบบ : " + txt_uat_text + "\n" +
                                        "ทะเบียนรถ : " + txt_lic_plate + "\n" +
                                        "ประเภทผู้ใช้ : " + txt_card_status + "\n" +
                                        "ประเภทบัตร : " + txt_car_type_status + "\n" +
                                        "เวลาเข้า : " + txt_carparking_in_time + "\n" +
                                        "เวลาออก : " + txt_carparking_out_time + "\n" +
                                        "เวลาจอดที่ปัดเศษ : " + txt_result_parking_interval + "\n" +
                                        "สถานะ Estamp : " + txt_estamp_type_name + "\n" +
                                        "ค่าปรับ : " + txt_payment_fine_amount + " บาท\n" +
                                        "ส่วนลด : " + txt_discount_amount + " บาท\n" +
                                        "ค่าบริการสุทธิ : " + txt_payment_totle + " บาท\n "
                        );


                    } else {

                        String txt_error = response.body().getMessage()+"";
                        showToastWarning(txt_error, OutCarMainActivity.this);
                        tag_id_card = null;
                        edit_id_card.setText("เลข Card");
                        edit_info.setText("ข้อมูล");



                    }

                    if (progressDoalog != null) {
                        progressDoalog.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<Result_action_mobile_out_call_checkpayment> call, Throwable t) {
                    showToastWarning("การบันทึกมีปัญหา" + t.toString(), OutCarMainActivity.this);
                    if (progressDoalog != null) {
                        progressDoalog.dismiss();
                    }

                }
            });


        } catch (Exception e) {

            showToastSuccess("RFID ไม่ตรงกัน", OutCarMainActivity.this);
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
                Intent intent = new Intent(OutCarMainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                break;

            default:

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OutCarMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        if (card_ok == v) {


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
        }

        return status;

    }


    private void RecordHistoryCarInData(String tran_carin_cabinet_id,
                                        String tran_carin_cabinet_code,
                                        String tran_carin_building_id,
                                        String tran_carin_building_code,
                                        String tran_carin_cabinet_send_time,
                                        String tran_carin_cardcode,
                                        String tran_carin_license_plate,
                                        String tran_carin_admin_id,
                                        String tran_carin_admin_name,
                                        String tran_carin_response

    ) {


        History_data_carin_dao list = new History_data_carin_dao();
        list.setTran_carin_cabinet_id(tran_carin_cabinet_id);
        list.setTran_carin_cabinet_code(tran_carin_cabinet_code);
        list.setTran_carin_building_id(tran_carin_building_id);
        list.setTran_carin_building_code(tran_carin_building_code);
        list.setTran_carin_cabinet_send_time(tran_carin_cabinet_send_time);
        list.setTran_carin_cardcode(tran_carin_cardcode);
        list.setTran_carin_license_plate(tran_carin_license_plate);
        list.setTran_carin_admin_id(tran_carin_admin_id);
        list.setTran_carin_admin_name(tran_carin_admin_name);
        list.setTran_carin_response(tran_carin_response);


        DataHistoryCarBookingInDao dao = new DataHistoryCarBookingInDao(getApplicationContext());
        dao.open();
        dao.add_tran_history_car_in(list);
        dao.close();


    }


    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        name_cabinet_id = settings.getString(PREF_CABINET_ID, DefaultString);
        name_cabinet_code = settings.getString(PREF_CABINET_CODE, DefaultString);
        name_building_id = settings.getString(PREF_BUILDING_ID, DefaultString);
        name_building_code = settings.getString(PREF_BUILDING_CODE, DefaultString);
        name_admin_id = settings.getString(PREF_ADMIN_ID, DefaultString);
        name_admin_name = settings.getString(PREF_ADMIN_NAME, DefaultString);
        ip_address = settings.getString(PREF_IP_ADDRESS, DefaultString);
        port = settings.getString(PREF_PORT, DefaultString);


        showToastLog(TAG,
                "name_cabinet_id :" + name_cabinet_id +
                        ",name_cabinet_code :" + name_cabinet_code +
                        ",name_building_id :" + name_building_id +
                        ",name_building_code :" + name_building_code +
                        ",name_admin_id :" + name_admin_id +
                        ",name_admin_name :" + name_admin_name +
                        ",ip_address :" + ip_address +
                        ",port :" + port +
                        ""


        );

    }


}
