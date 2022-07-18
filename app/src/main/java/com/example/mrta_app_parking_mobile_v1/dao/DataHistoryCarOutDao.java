package com.example.mrta_app_parking_mobile_v1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mrta_app_parking_mobile_v1.model.History_data_carin_dao;
import com.example.mrta_app_parking_mobile_v1.model.History_data_carout_dao;

import java.util.ArrayList;

public class DataHistoryCarOutDao {
    private SQLiteDatabase database;
    private DbHelper dbHelperCar;

    public DataHistoryCarOutDao(Context context) {
        dbHelperCar = new DbHelper(context);
    }

    public void open() {
        database = dbHelperCar.getWritableDatabase();
    }

    public void close() {
        dbHelperCar.close();

    }

    public ArrayList<History_data_carout_dao> getDataHistory() {

        ArrayList<History_data_carout_dao> list = new ArrayList<>();
        String query = "select * from tran_history_car_out  order by tran_carout_id desc limit 50";

        Cursor cursor = database.rawQuery(query,new String[]{});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            History_data_carout_dao dao = new History_data_carout_dao();
            dao.setTran_carout_id(cursor.getInt(0));
            dao.setTran_carout_cabinet_send_time(cursor.getString(1));
            dao.setTran_carout_cabinet_id(cursor.getString(2));
            dao.setTran_carout_cabinet_code(cursor.getString(3));
            dao.setTran_carout_cabinet_tax_code(cursor.getString(4));
            dao.setTran_carout_cabinet_name(cursor.getString(5));
            dao.setTran_carout_cabinet_type_id(cursor.getString(6));
            dao.setTran_carout_registered_no(cursor.getString(7));
            dao.setTran_carout_building_tax(cursor.getString(8));
            dao.setTran_carout_building_id(cursor.getString(9));
            dao.setTran_carout_building_code(cursor.getString(10));
            dao.setTran_carout_receipt_no(cursor.getString(11));
            dao.setTran_carout_cardcode(cursor.getString(12));
            dao.setTran_carout_cardname(cursor.getString(13));
            dao.setTran_carout_license_plate(cursor.getString(14));
            dao.setTran_carout_carparking_in_time(cursor.getString(15));
            dao.setTran_carout_carparking_out_time(cursor.getString(16));
            dao.setTran_carout_car_type_name(cursor.getString(17));
            dao.setTran_carout_payment_type_name_th(cursor.getString(18));
            dao.setTran_carout_estamp_status(cursor.getString(19));
            dao.setTran_carout_payment_amount(cursor.getString(20));
            dao.setTran_carout_payment_discount_amount(cursor.getString(21));
            dao.setTran_carout_payment_fine_amount(cursor.getString(22));
            dao.setTran_carout_payment_totle(cursor.getString(23));
            dao.setTran_carout_ref1(cursor.getString(24));
            dao.setTran_carout_ref2(cursor.getString(25));
            dao.setTran_carout_admin_id(cursor.getString(26));
            dao.setTran_carout_admin_name(cursor.getString(27));
            dao.setTran_carout_response(cursor.getString(28));



            list.add(dao);
            cursor.moveToNext();



        }

        return list;
    }

    public void add_tran_history_car_out (History_data_carout_dao list) {


        ContentValues values = new ContentValues();
        values.put("tran_carout_cabinet_send_time", list.getTran_carout_cabinet_send_time());
        values.put("tran_carout_cabinet_id", list.getTran_carout_cabinet_id());
        values.put("tran_carout_cabinet_code", list.getTran_carout_cabinet_code());
        values.put("tran_carout_cabinet_tax_code", list.getTran_carout_cabinet_tax_code());
        values.put("tran_carout_cabinet_name", list.getTran_carout_cabinet_name());
        values.put("tran_carout_cabinet_type_id", list.getTran_carout_cabinet_type_id());
        values.put("tran_carout_registered_no", list.getTran_carout_registered_no());
        values.put("tran_carout_building_tax", list.getTran_carout_building_tax());
        values.put("tran_carout_building_id", list.getTran_carout_building_id());
        values.put("tran_carout_building_code", list.getTran_carout_building_code());
        values.put("tran_carout_receipt_no", list.getTran_carout_receipt_no());
        values.put("tran_carout_cardcode", list.getTran_carout_cardcode());
        values.put("tran_carout_cardname", list.getTran_carout_cardname());
        values.put("tran_carout_license_plate", list.getTran_carout_license_plate());
        values.put("tran_carout_carparking_in_time", list.getTran_carout_carparking_in_time());
        values.put("tran_carout_carparking_out_time", list.getTran_carout_carparking_out_time());
        values.put("tran_carout_car_type_name", list.getTran_carout_car_type_name());
        values.put("tran_carout_payment_type_name_th", list.getTran_carout_payment_type_name_th());
        values.put("tran_carout_estamp_status", list.getTran_carout_estamp_status());
        values.put("tran_carout_payment_amount", list.getTran_carout_payment_amount());
        values.put("tran_carout_payment_discount_amount", list.getTran_carout_payment_discount_amount());
        values.put("tran_carout_payment_fine_amount", list.getTran_carout_payment_fine_amount());
        values.put("tran_carout_payment_totle", list.getTran_carout_payment_totle());
        values.put("tran_carout_ref1", list.getTran_carout_ref1());
        values.put("tran_carout_ref2", list.getTran_carout_ref2());
        values.put("tran_carout_admin_id", list.getTran_carout_admin_id());
        values.put("tran_carout_admin_name", list.getTran_carout_admin_name());
        values.put("tran_carout_response", list.getTran_carout_response());




        this.database.insert("tran_history_car_out", null, values);









    }




}
