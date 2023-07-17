package com.example.mrta_app_parking_mobile_v1.activity;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Looper;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.mrta_app_parking_mobile_v1.R;
import com.example.mrta_app_parking_mobile_v1.dao.DataHistoryCarInDao;
import com.example.mrta_app_parking_mobile_v1.dao.DataHistoryCarOutDao;
import com.example.mrta_app_parking_mobile_v1.manager.HttpManager;
import com.example.mrta_app_parking_mobile_v1.model.History_data_carin_dao;
import com.example.mrta_app_parking_mobile_v1.model.History_data_carout_dao;
import com.example.mrta_app_parking_mobile_v1.model.Result_action_mobile_out_call_checkpayment;
import com.example.mrta_app_parking_mobile_v1.model.Result_mobile_out_flag_card_no_pay;
import com.example.mrta_app_parking_mobile_v1.model.Result_mobile_out_payment_cash;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;
import com.zebra.sdk.comm.BluetoothConnectionInsecure;
import com.zebra.sdk.comm.Connection;

import java.text.ParseException;
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
    private static final String PREF_BUILDING_TEL = "pref_building_tel";


    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";
    private static final String PREF_STATUS_RADIO_CAROUT_NOT_PRINT = "pref_status_radio_carout_not_print";
    private static final String PREF_STATUS_RADIO_CAROUT_PRINT_ALL = "pref_status_radio_carout_print_all";
    private static final String PREF_STATUS_RADIO_CAROUT_PRINT_PRICE_ONLY = "pref_status_radio_carout_print_price_only";


    private boolean status_radio_carout_not_print;
    private boolean status_radio_carout_print_all;
    private boolean status_radio_carout_print_price_only;


    private final int DefaultInt = 0;
    private final boolean DefaultBoolean = false;
    private final String DefaultString = "null";
    private String ip_address;
    private String port;
    private String name_cabinet_id;
    private String name_cabinet_code;
    private String name_building_id;
    private String name_building_code;
    private String name_admin_id;
    private String name_admin_name;
    private String name_mac_address_print = "0";
    private String name_building_tel;
    private String carout_diff_time_in_out = "";


    private DrawerLayout drawer;
    CardView card_ok;
    EditText edit_id_card;
    EditText edit_info;
    private ProgressDialog progressDoalog;


    //////////////////  DATA RESULT //////////////////

    String data_lic_plate = "";
    String data_card_status = "";
    String data_car_type_status = "";
    String data_carparking_in_time = "";
    String data_carparking_out_time = "";
    String data_result_parking_interval = "";
    String data_estamp_type_name = "";
    String data_payment_totle = "";
    String data_discount_amount = "";
    String data_payment_fine_amount = "";
    String data_uat_text = "";
    String data_payment_type_id = "";
    String data_payment_event_id = "";
    String data_tci_id = "";
    String data_tcch_id = "";
    String data_payment_status = "";
    String data_payment_amount = "";


    //////////////////////////  NFC  //////////////////////////


    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private String tag_id_card = null;


    //////////////////////////  NFC  //////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_car_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


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


                        data_lic_plate = response.body().getData().getLicensePlateText() + "";
                        data_card_status = response.body().getData().getCardStatus() + "";
                        data_car_type_status = response.body().getData().getCarTypeStatus() + "";
                        data_carparking_in_time = response.body().getData().getCarparkingInTime() + "";
                        data_carparking_out_time = response.body().getData().getCarparkingOutTime() + "";
                        data_result_parking_interval = response.body().getData().getResultParkingInterval() + "";
                        data_estamp_type_name = response.body().getData().getEstampTypeName() + "";
                        data_payment_totle = response.body().getData().getPaymentTotle() + "";
                        data_payment_amount = response.body().getData().getPaymentAmount() + "";
                        data_discount_amount = response.body().getData().getDiscountAmount() + "";
                        data_payment_fine_amount = response.body().getData().getPaymentFineAmount() + "";
                        data_uat_text = response.body().getData().getUatText() + "";
                        data_payment_type_id = response.body().getData().getPaymentTypeId() + "";
                        data_payment_event_id = response.body().getData().getPaymentEventId() + "";
                        data_tci_id = response.body().getData().getTciId() + "";
                        data_tcch_id = response.body().getData().getTcchId() + "";
                        data_payment_status = response.body().getData().getPaymentStatus() + "";


                        if (data_payment_status.equals("PAY")) {


                            edit_info.setText(
                                    "สถานะระบบ : " + data_uat_text + "\n" +
                                            "สถานะชำระเงิน : " + data_payment_status + "\n" +
                                            "ทะเบียนรถ : " + data_lic_plate + "\n" +
                                            "ประเภทผู้ใช้ : " + data_card_status + "\n" +
                                            "ประเภทบัตร : " + data_car_type_status + "\n" +
                                            "เวลาเข้า : " + data_carparking_in_time + "\n" +
                                            "เวลาออก : " + data_carparking_out_time + "\n" +
                                            "เวลาจอดที่ปัดเศษ : " + data_result_parking_interval + "\n" +
                                            "สถานะ Estamp : " + data_estamp_type_name + "\n" +
                                            "ค่าปรับ : " + 0 + " บาท\n" +
                                            "ส่วนลด : " + 0 + " บาท\n" +
                                            "ค่าบริการสุทธิ : " + 0 + " บาท\n "
                            );


                        } else {


                            edit_info.setText(
                                    "สถานะระบบ : " + data_uat_text + "\n" +
                                            "สถานะชำระเงิน : " + data_payment_status + "\n" +
                                            "ทะเบียนรถ : " + data_lic_plate + "\n" +
                                            "ประเภทผู้ใช้ : " + data_card_status + "\n" +
                                            "ประเภทบัตร : " + data_car_type_status + "\n" +
                                            "เวลาเข้า : " + data_carparking_in_time + "\n" +
                                            "เวลาออก : " + data_carparking_out_time + "\n" +
                                            "เวลาจอดที่ปัดเศษ : " + data_result_parking_interval + "\n" +
                                            "สถานะ Estamp : " + data_estamp_type_name + "\n" +
                                            "ค่าปรับ : " + data_payment_fine_amount + " บาท\n" +
                                            "ส่วนลด : " + data_discount_amount + " บาท\n" +
                                            "ค่าบริการสุทธิ : " + data_payment_totle + " บาท\n "
                            );


                        }


                    } else {

                        String txt_error = response.body().getMessage() + "";
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

            case R.id.action_clear:

                tag_id_card = null;
                edit_id_card.setText("เลข Card");
                edit_info.setText("ข้อมูล");

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

            progressDoalog = new ProgressDialog(this);
            progressDoalog.setTitle("กำลังส่งข้อมูล Service");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.setMessage("กรุณารอสักครู่...");
            progressDoalog.setCancelable(false);
            progressDoalog.show();


            if (checkdata()) {


                switch (data_payment_totle) {
                    case "":
                        progressDoalog.dismiss();
                        showToastWarning("ระบบผิดพลาด เนื่องจาก payment_totle ไม่มีค่า", OutCarMainActivity.this);
                        break;
                    default:


                        showToastLog(TAG, data_payment_status + "");


                        if (data_payment_status.equals("PAY")) {

                            showToastLog(TAG, "not pay");
                            fun_mobile_out_flag_card_no_pay();

                        } else {

                            showToastLog(TAG, "pay");
                            fun_mobile_out_payment_cash();


                        }


                }


            } else {
                progressDoalog.dismiss();
            }


        }


    }


    private void fun_mobile_out_flag_card_no_pay() {

        final String timestamp = getCurrentTimeStamp();

        Call<Result_mobile_out_flag_card_no_pay> call = HttpManager.getInstance(ip_address, port).getService().action_mobile_out_flag_card_no_pay(
                name_cabinet_id, name_cabinet_code, name_building_id, name_building_code, tag_id_card, timestamp, name_admin_id, "", "",
                data_payment_type_id, data_payment_event_id, data_tci_id, "0", "0", "0", "0", "0",
                "0", "0", "0", "0", "0", "0", "0", "0"

        );
        call.enqueue(new Callback<Result_mobile_out_flag_card_no_pay>() {
            @Override
            public void onResponse(Call<Result_mobile_out_flag_card_no_pay> call, Response<Result_mobile_out_flag_card_no_pay> response) {

                progressDoalog.dismiss();
                if (null == response.body().getError()) {

                    showToastLog(TAG, response.body().getMessage() + "");

                    edit_id_card.setText("เลขบัตร");
                    edit_info.setText("ข้อมูล");
                    tag_id_card = null;
                    showToastSuccess("ทำรายการขาออกสำเร็จ", getApplicationContext());

                    String tran_carout_cabinet_send_time = timestamp;
                    String tran_carout_cabinet_id = response.body().getData().getResultReceiptData().getCabinetId() + "";
                    String tran_carout_cabinet_code = response.body().getData().getResultReceiptData().getCabinetCode() + "";
                    String tran_carout_cabinet_tax_code = response.body().getData().getCabinetObj().getCabinetTaxCode() + "";
                    String tran_carout_cabinet_name = response.body().getData().getCabinetObj().getCabinetName() + "";
                    String tran_carout_cabinet_type_id = response.body().getData().getCabinetObj().getCabinetTypeId() + "";
                    String tran_carout_registered_no = response.body().getData().getCabinetObj().getRegisteredNo() + "";
                    String tran_carout_building_tax = response.body().getData().getCabinetObj().getBuildingTax() + "";
                    String tran_carout_building_id = response.body().getData().getCabinetObj().getBuildingId() + "";
                    String tran_carout_building_code = response.body().getData().getCabinetObj().getBuildingCode() + "";
                    String tran_carout_building_name = response.body().getData().getCabinetObj().getBuildingName() + "";
                    String tran_carout_receipt_no = response.body().getData().getReceiptNo() + "";
                    String tran_carout_cardcode = response.body().getData().getResultReceiptData().getCardCode() + "";
                    String tran_carout_cardname = response.body().getData().getCardObj().getCardName() + "";
                    String tran_carout_license_plate = response.body().getData().getResultReceiptData().getLicensePlateText() + "";
                    String tran_carout_carparking_in_time = response.body().getData().getResultReceiptData().getCarparkingInTime() + "";
                    String tran_carout_carparking_out_time = response.body().getData().getResultReceiptData().getCarparkingOutTime() + "";
                    String tran_carout_car_type_name = response.body().getData().getResultReceiptData().getCarTypeName() + "";
                    String tran_carout_payment_type_name_th = response.body().getData().getResultReceiptData().getPaymentTypeNameTh() + "";
                    String tran_carout_estamp_status = response.body().getData().getResultReceiptData().getEstampStatus() + "";
                    String tran_carout_ref1 = response.body().getData().getResultReceiptData().getRef1() + "";
                    String tran_carout_ref2 = response.body().getData().getResultReceiptData().getRef2() + "";
                    String tran_carout_admin_id = name_admin_id + "";
                    String tran_carout_admin_name = name_admin_name + "";
                    String tran_carout_response = "ทำรายการสำเร็จ";

//                    String tran_carout_payment_amount = response.body().getData().getResultReceiptData().getPaymentAmount() + "";
//                    String tran_carout_payment_discount_amount = response.body().getData().getResultReceiptData().getPaymentDiscountAmount() + "";
//                    String tran_carout_payment_fine_amount = response.body().getData().getResultReceiptData().getPaymentFineAmount() + "";
//                    String tran_carout_payment_totle = response.body().getData().getResultReceiptData().getPaymentTotle() + "";


                    String tran_carout_payment_amount = 0+ "";
                    String tran_carout_payment_discount_amount = 0+"";
                    String tran_carout_payment_fine_amount = 0 + "";
                    String tran_carout_payment_totle = 0 + "";

                    String start_date = tran_carout_carparking_in_time;
                    String end_date = tran_carout_carparking_out_time;


                    findDifference(start_date, end_date);


                    if (status_radio_carout_not_print == true) {

                        showToastLog(TAG, "status_radio_carout_not_print");


                    } else if (status_radio_carout_print_all == true) {
                        //TODO Print Car OUT

                        showToastLog(TAG, "status_radio_carout_print_all");

                        sendZplOverBluetooth(
                                tran_carout_building_tax, tran_carout_registered_no,
                                tran_carout_cabinet_tax_code, tran_carout_cabinet_code,
                                tran_carout_cabinet_name, tran_carout_carparking_out_time,
                                tran_carout_receipt_no, data_estamp_type_name,
                                tran_carout_car_type_name, tran_carout_license_plate,
                                tran_carout_cardcode, tran_carout_carparking_in_time,
                                name_building_tel, tran_carout_payment_amount,
                                tran_carout_payment_fine_amount, tran_carout_payment_totle,
                                tran_carout_payment_type_name_th, carout_diff_time_in_out);


                    } else if (status_radio_carout_print_price_only == true) {
                        //TODO Print Car OUT
                        if (tran_carout_payment_totle.equals("0")) {

                            showToastLog(TAG, "no print price 0");

                        } else {

                            sendZplOverBluetooth(
                                    tran_carout_building_tax, tran_carout_registered_no,
                                    tran_carout_cabinet_tax_code, tran_carout_cabinet_code,
                                    tran_carout_cabinet_name, tran_carout_carparking_out_time,
                                    tran_carout_receipt_no, data_estamp_type_name,
                                    tran_carout_car_type_name, tran_carout_license_plate,
                                    tran_carout_cardcode, tran_carout_carparking_in_time,
                                    name_building_tel, tran_carout_payment_amount,
                                    tran_carout_payment_fine_amount, tran_carout_payment_totle,
                                    tran_carout_payment_type_name_th, carout_diff_time_in_out);


                        }

                    }


                    RecordHistoryCarOutData(
                            tran_carout_cabinet_send_time,
                            tran_carout_cabinet_id,
                            tran_carout_cabinet_code,
                            tran_carout_cabinet_tax_code,
                            tran_carout_cabinet_name,
                            tran_carout_cabinet_type_id,
                            tran_carout_registered_no,
                            tran_carout_building_tax,
                            tran_carout_building_id,
                            tran_carout_building_code,
                            tran_carout_building_name,
                            tran_carout_receipt_no,
                            tran_carout_cardcode,
                            tran_carout_cardname,
                            tran_carout_license_plate,
                            tran_carout_carparking_in_time,
                            tran_carout_carparking_out_time,
                            tran_carout_car_type_name,
                            tran_carout_payment_type_name_th,
                            data_estamp_type_name,
                            tran_carout_payment_amount,
                            tran_carout_payment_discount_amount,
                            tran_carout_payment_fine_amount,
                            tran_carout_payment_totle,
                            tran_carout_ref1,
                            tran_carout_ref2,
                            tran_carout_admin_id,
                            tran_carout_admin_name,
                            tran_carout_response
                    );


                } else {

                    showToastWarning(response.body().getMessage() + "", getApplicationContext());


                }


            }

            @Override
            public void onFailure(Call<Result_mobile_out_flag_card_no_pay> call, Throwable t) {

                showToastWarning("การบันทึกมีปัญหา" + t.toString(), getApplicationContext());
                showToastLog(TAG, t.toString());
                progressDoalog.dismiss();

            }
        });


    }


    private void fun_mobile_out_payment_cash() {

        final String timestamp = getCurrentTimeStamp();

        Call<Result_mobile_out_payment_cash> call = HttpManager.getInstance(ip_address, port).getService().action_mobile_out_payment_cash(
                name_cabinet_id, name_cabinet_code, name_building_id, name_building_code, tag_id_card, timestamp, name_admin_id, "", "",
                data_payment_type_id, data_payment_event_id, data_payment_status, data_tci_id, data_tcch_id, data_payment_amount, data_discount_amount, data_payment_totle, "0", "0",
                "0", "0", "0", "0", "0", "0", "0", data_payment_fine_amount

        );
        call.enqueue(new Callback<Result_mobile_out_payment_cash>() {
            @Override
            public void onResponse(Call<Result_mobile_out_payment_cash> call, Response<Result_mobile_out_payment_cash> response) {

                progressDoalog.dismiss();
                if (null == response.body().getError()) {

                    showToastLog(TAG, response.body().getMessage() + "");

                    edit_id_card.setText("เลขบัตร");
                    edit_info.setText("ข้อมูล");
                    tag_id_card = null;


                    showToastSuccess("ทำรายการขาออกสำเร็จ", getApplicationContext());

                    String tran_carout_cabinet_send_time = timestamp;
                    String tran_carout_cabinet_id = response.body().getData().getResultReceiptData().getCabinetId() + "";
                    String tran_carout_cabinet_code = response.body().getData().getResultReceiptData().getCabinetCode() + "";
                    String tran_carout_cabinet_tax_code = response.body().getData().getCabinetObj().getCabinetTaxCode() + "";
                    String tran_carout_cabinet_name = response.body().getData().getCabinetObj().getCabinetName() + "";
                    String tran_carout_cabinet_type_id = response.body().getData().getCabinetObj().getCabinetTypeId() + "";
                    String tran_carout_registered_no = response.body().getData().getCabinetObj().getRegisteredNo() + "";
                    String tran_carout_building_tax = response.body().getData().getCabinetObj().getBuildingTax() + "";
                    String tran_carout_building_id = response.body().getData().getCabinetObj().getBuildingId() + "";
                    String tran_carout_building_code = response.body().getData().getCabinetObj().getBuildingCode() + "";
                    String tran_carout_building_name = response.body().getData().getCabinetObj().getBuildingName() + "";
                    String tran_carout_receipt_no = response.body().getData().getReceiptNo() + "";
                    String tran_carout_cardcode = response.body().getData().getResultReceiptData().getCardCode() + "";
                    String tran_carout_cardname = response.body().getData().getCardObj().getCardName() + "";
                    String tran_carout_license_plate = response.body().getData().getResultReceiptData().getLicensePlateText() + "";
                    String tran_carout_carparking_in_time = response.body().getData().getResultReceiptData().getCarparkingInTime() + "";
                    String tran_carout_carparking_out_time = response.body().getData().getResultReceiptData().getCarparkingOutTime() + "";
                    String tran_carout_car_type_name = response.body().getData().getResultReceiptData().getCarTypeName() + "";
                    String tran_carout_payment_type_name_th = response.body().getData().getResultReceiptData().getPaymentTypeNameTh() + "";
                    String tran_carout_estamp_status = response.body().getData().getResultReceiptData().getEstampStatus() + "";
                    String tran_carout_payment_amount = response.body().getData().getResultReceiptData().getPaymentAmount() + "";
                    String tran_carout_payment_discount_amount = response.body().getData().getResultReceiptData().getPaymentDiscountAmount() + "";
                    String tran_carout_payment_fine_amount = response.body().getData().getResultReceiptData().getPaymentFineAmount() + "";
                    String tran_carout_payment_totle = response.body().getData().getResultReceiptData().getPaymentTotle() + "";
                    String tran_carout_ref1 = response.body().getData().getResultReceiptData().getRef1() + "";
                    String tran_carout_ref2 = response.body().getData().getResultReceiptData().getRef2() + "";
                    String tran_carout_admin_id = name_admin_id + "";
                    String tran_carout_admin_name = name_admin_name + "";
                    String tran_carout_response = "ทำรายการสำเร็จ";


                    String start_date = tran_carout_carparking_in_time;
                    String end_date = tran_carout_carparking_out_time;


                    findDifference(start_date, end_date);

                    if (status_radio_carout_not_print == true) {

                        showToastLog(TAG, "status_radio_carout_not_print");


                    } else if (status_radio_carout_print_all == true) {
                        //TODO Print Car OUT

                        showToastLog(TAG, "status_radio_carout_print_all");

                        sendZplOverBluetooth(
                                tran_carout_building_tax, tran_carout_registered_no,
                                tran_carout_cabinet_tax_code, tran_carout_cabinet_code,
                                tran_carout_cabinet_name, tran_carout_carparking_out_time,
                                tran_carout_receipt_no, data_estamp_type_name,
                                tran_carout_car_type_name, tran_carout_license_plate,
                                tran_carout_cardcode, tran_carout_carparking_in_time,
                                name_building_tel, tran_carout_payment_amount,
                                tran_carout_payment_fine_amount, tran_carout_payment_totle,
                                tran_carout_payment_type_name_th, carout_diff_time_in_out);


                    } else if (status_radio_carout_print_price_only == true) {
                        //TODO Print Car OUT
                        if (tran_carout_payment_totle.equals("0")) {

                            showToastLog(TAG, "no print price 0");

                        } else {

                            sendZplOverBluetooth(
                                    tran_carout_building_tax, tran_carout_registered_no,
                                    tran_carout_cabinet_tax_code, tran_carout_cabinet_code,
                                    tran_carout_cabinet_name, tran_carout_carparking_out_time,
                                    tran_carout_receipt_no, data_estamp_type_name,
                                    tran_carout_car_type_name, tran_carout_license_plate,
                                    tran_carout_cardcode, tran_carout_carparking_in_time,
                                    name_building_tel, tran_carout_payment_amount,
                                    tran_carout_payment_fine_amount, tran_carout_payment_totle,
                                    tran_carout_payment_type_name_th, carout_diff_time_in_out
                            );


                        }

                    }


                    RecordHistoryCarOutData(

                            tran_carout_cabinet_send_time,
                            tran_carout_cabinet_id,
                            tran_carout_cabinet_code,
                            tran_carout_cabinet_tax_code,
                            tran_carout_cabinet_name,
                            tran_carout_cabinet_type_id,
                            tran_carout_registered_no,
                            tran_carout_building_tax,
                            tran_carout_building_id,
                            tran_carout_building_code,
                            tran_carout_building_name,
                            tran_carout_receipt_no,
                            tran_carout_cardcode,
                            tran_carout_cardname,
                            tran_carout_license_plate,
                            tran_carout_carparking_in_time,
                            tran_carout_carparking_out_time,
                            tran_carout_car_type_name,
                            tran_carout_payment_type_name_th,
                            data_estamp_type_name,
                            tran_carout_payment_amount,
                            tran_carout_payment_discount_amount,
                            tran_carout_payment_fine_amount,
                            tran_carout_payment_totle,
                            tran_carout_ref1,
                            tran_carout_ref2,
                            tran_carout_admin_id,
                            tran_carout_admin_name,
                            tran_carout_response

                    );


                } else {
                    showToastWarning(response.body().getMessage() + "", getApplicationContext());
                }


            }

            @Override
            public void onFailure(Call<Result_mobile_out_payment_cash> call, Throwable t) {

                showToastWarning("การบันทึกมีปัญหา" + t.toString(), getApplicationContext());
                showToastLog(TAG, t.toString());
                progressDoalog.dismiss();

            }
        });


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


    private void RecordHistoryCarOutData(

            String tran_carout_cabinet_send_time,
            String tran_carout_cabinet_id,
            String tran_carout_cabinet_code,
            String tran_carout_cabinet_tax_code,
            String tran_carout_cabinet_name,
            String tran_carout_cabinet_type_id,
            String tran_carout_registered_no,
            String tran_carout_building_tax,
            String tran_carout_building_id,
            String tran_carout_building_code,
            String tran_carout_building_name,
            String tran_carout_receipt_no,
            String tran_carout_cardcode,
            String tran_carout_cardname,
            String tran_carout_license_plate,
            String tran_carout_carparking_in_time,
            String tran_carout_carparking_out_time,
            String tran_carout_car_type_name,
            String tran_carout_payment_type_name_th,
            String tran_carout_estamp_status,
            String tran_carout_payment_amount,
            String tran_carout_payment_discount_amount,
            String tran_carout_payment_fine_amount,
            String tran_carout_payment_totle,
            String tran_carout_ref1,
            String tran_carout_ref2,
            String tran_carout_admin_id,
            String tran_carout_admin_name,
            String tran_carout_response


    ) {


        History_data_carout_dao list = new History_data_carout_dao();

        list.setTran_carout_cabinet_send_time(tran_carout_cabinet_send_time);
        list.setTran_carout_cabinet_id(tran_carout_cabinet_id);
        list.setTran_carout_cabinet_code(tran_carout_cabinet_code);
        list.setTran_carout_cabinet_tax_code(tran_carout_cabinet_tax_code);
        list.setTran_carout_cabinet_name(tran_carout_cabinet_name);
        list.setTran_carout_cabinet_type_id(tran_carout_cabinet_type_id);
        list.setTran_carout_registered_no(tran_carout_registered_no);
        list.setTran_carout_building_tax(tran_carout_building_tax);
        list.setTran_carout_building_id(tran_carout_building_id);
        list.setTran_carout_building_code(tran_carout_building_code);
        list.setTran_carout_building_name(tran_carout_building_name);
        list.setTran_carout_receipt_no(tran_carout_receipt_no);
        list.setTran_carout_cardcode(tran_carout_cardcode);
        list.setTran_carout_cardname(tran_carout_cardname);
        list.setTran_carout_license_plate(tran_carout_license_plate);
        list.setTran_carout_carparking_in_time(tran_carout_carparking_in_time);
        list.setTran_carout_carparking_out_time(tran_carout_carparking_out_time);
        list.setTran_carout_car_type_name(tran_carout_car_type_name);
        list.setTran_carout_payment_type_name_th(tran_carout_payment_type_name_th);
        list.setTran_carout_estamp_status(tran_carout_estamp_status);
        list.setTran_carout_payment_amount(tran_carout_payment_amount);
        list.setTran_carout_payment_discount_amount(tran_carout_payment_discount_amount);
        list.setTran_carout_payment_fine_amount(tran_carout_payment_fine_amount);
        list.setTran_carout_payment_totle(tran_carout_payment_totle);
        list.setTran_carout_ref1(tran_carout_ref1);
        list.setTran_carout_ref2(tran_carout_ref2);
        list.setTran_carout_admin_id(tran_carout_admin_id);
        list.setTran_carout_admin_name(tran_carout_admin_name);
        list.setTran_carout_response(tran_carout_response);


        DataHistoryCarOutDao dao = new DataHistoryCarOutDao(getApplicationContext());
        dao.open();
        dao.add_tran_history_car_out(list);
        dao.close();


    }


    private void sendZplOverBluetooth(final String carout_building_tax,
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
                            carout_building_tax, carout_registered_no,
                            carout_cabinet_tax_code, carout_cabinet_code,
                            carout_cabinet_name, carout_carparking_out_time,
                            carout_receipt_no, carout_estamp_status, carout_car_type_name,
                            carout_license_plate, carout_cardcode, carout_carparking_in_time,
                            carout_building_tel, carout_payment_amount, carout_payment_fine_amount,
                            carout_payment_totle, carout_payment_type_name_th, carout_diff_time_in_out
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


    private void findDifference(String start_date,
                                String end_date) {

        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
                = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // Try Block
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                    = d2.getTime() - d1.getTime();

            // Calucalte time difference in
            // seconds, minutes, hours, years,
            // and days
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


            carout_diff_time_in_out = "" + difference_In_Days + "วัน " + difference_In_Hours + "ชั่วโมง " + difference_In_Minutes + "นาที " + difference_In_Seconds + "วินาที ";


        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private void loadPreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        // Get value
        name_mac_address_print = settings.getString(PREF_MAC_ADDRESS_PRINT, DefaultString);
        name_cabinet_id = settings.getString(PREF_CABINET_ID, DefaultString);
        name_cabinet_code = settings.getString(PREF_CABINET_CODE, DefaultString);
        name_building_id = settings.getString(PREF_BUILDING_ID, DefaultString);
        name_building_code = settings.getString(PREF_BUILDING_CODE, DefaultString);
        name_admin_id = settings.getString(PREF_ADMIN_ID, DefaultString);
        name_admin_name = settings.getString(PREF_ADMIN_NAME, DefaultString);
        ip_address = settings.getString(PREF_IP_ADDRESS, DefaultString);
        port = settings.getString(PREF_PORT, DefaultString);
        status_radio_carout_not_print = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_NOT_PRINT, DefaultBoolean);
        status_radio_carout_print_all = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_PRINT_ALL, DefaultBoolean);
        status_radio_carout_print_price_only = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_PRINT_PRICE_ONLY, DefaultBoolean);
        name_building_tel = settings.getString(PREF_BUILDING_TEL, DefaultString);


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
