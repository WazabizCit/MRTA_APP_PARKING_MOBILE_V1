package com.example.mrta_app_parking_mobile_v1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mrta_app_parking_mobile_v1.model.History_data_carin_dao;

import java.util.ArrayList;

public class DataHistoryCarInDao {
    private SQLiteDatabase database;
    private DbHelper dbHelperCar;

    public DataHistoryCarInDao(Context context) {
        dbHelperCar = new DbHelper(context);
    }

    public void open() {
        database = dbHelperCar.getWritableDatabase();
    }

    public void close() {
        dbHelperCar.close();

    }

    public ArrayList<History_data_carin_dao> getDataHistory() {

        ArrayList<History_data_carin_dao> list = new ArrayList<>();
        String query = "select * from tran_history_car_in  order by tran_carin_id desc limit 50";

        Cursor cursor = database.rawQuery(query,new String[]{});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            History_data_carin_dao dao = new History_data_carin_dao();
            dao.setTran_carin_id(cursor.getInt(0));
            dao.setTran_carin_cabinet_id(cursor.getString(1));
            dao.setTran_carin_cabinet_code(cursor.getString(2));
            dao.setTran_carin_building_id(cursor.getString(3));
            dao.setTran_carin_building_code(cursor.getString(4));
            dao.setTran_carin_cabinet_send_time(cursor.getString(5));
            dao.setTran_carin_cardcode(cursor.getString(6));
            dao.setTran_carin_license_plate(cursor.getString(7));
            dao.setTran_carin_admin_id(cursor.getString(8));
            dao.setTran_carin_admin_name(cursor.getString(9));
            dao.setTran_carin_response(cursor.getString(10));

            list.add(dao);
            cursor.moveToNext();



        }

        return list;
    }

    public void add_tran_history_car_in (History_data_carin_dao list) {


        ContentValues values = new ContentValues();
        values.put("tran_carin_cabinet_id", list.getTran_carin_cabinet_id());
        values.put("tran_carin_cabinet_code", list.getTran_carin_cabinet_code());
        values.put("tran_carin_building_id", list.getTran_carin_building_id());
        values.put("tran_carin_building_code", list.getTran_carin_building_code());
        values.put("tran_carin_cabinet_send_time", list.getTran_carin_cabinet_send_time());
        values.put("tran_carin_cardcode", list.getTran_carin_cardcode());
        values.put("tran_carin_license_plate", list.getTran_carin_license_plate());
        values.put("tran_carin_admin_id", list.getTran_carin_admin_id());
        values.put("tran_carin_admin_name", list.getTran_carin_admin_name());
        values.put("tran_carin_response", list.getTran_carin_response());





        this.database.insert("tran_history_car_in", null, values);









    }




}
