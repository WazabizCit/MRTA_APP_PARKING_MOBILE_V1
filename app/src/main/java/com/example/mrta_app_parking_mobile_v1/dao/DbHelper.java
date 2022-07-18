package com.example.mrta_app_parking_mobile_v1.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String databaseName = "MRTAParkingMobileDB.Sqlite";
    private static final int databaseVerSion = 1;
    Context mContect;

    public DbHelper(Context context) {
        super(context, databaseName, null, databaseVerSion);
        this.mContect = context;
    }


    private static final String tableDataHistoryCarInCreateSQL = "CREATE TABLE tran_history_car_in("
            + "tran_carin_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "tran_carin_cabinet_id TEXT  DEFAULT '',"
            + "tran_carin_cabinet_code TEXT  DEFAULT '',"
            + "tran_carin_building_id TEXT  DEFAULT '',"
            + "tran_carin_building_code TEXT  DEFAULT '',"
            + "tran_carin_cabinet_send_time TEXT  DEFAULT '',"
            + "tran_carin_cardcode TEXT DEFAULT '',"
            + "tran_carin_license_plate TEXT DEFAULT '',"
            + "tran_carin_admin_id TEXT DEFAULT '',"
            + "tran_carin_admin_name TEXT DEFAULT '',"
            + "tran_carin_response TEXT DEFAULT '',"
            + "create_date TEXT  DEFAULT  (datetime('now','localtime')),"
            + "delete_flag TEXT  DEFAULT 'N'"
            + ");";




    private static final String tableDataHistoryCarOutCreateSQL = "CREATE TABLE tran_history_car_out("
            + "tran_carout_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "tran_carout_cabinet_send_time TEXT  DEFAULT '',"
            + "tran_carout_cabinet_id TEXT  DEFAULT '',"
            + "tran_carout_cabinet_code TEXT  DEFAULT '',"
            + "tran_carout_cabinet_tax_code TEXT  DEFAULT '',"
            + "tran_carout_cabinet_name TEXT  DEFAULT '',"
            + "tran_carout_cabinet_type_id TEXT  DEFAULT '',"
            + "tran_carout_registered_no TEXT  DEFAULT '',"
            + "tran_carout_building_tax TEXT  DEFAULT '',"
            + "tran_carout_building_id TEXT  DEFAULT '',"
            + "tran_carout_building_code TEXT  DEFAULT '',"
            + "tran_carout_receipt_no TEXT  DEFAULT '',"
            + "tran_carout_cardcode TEXT  DEFAULT '',"
            + "tran_carout_cardname TEXT  DEFAULT '',"
            + "tran_carout_license_plate TEXT  DEFAULT '',"
            + "tran_carout_carparking_in_time TEXT  DEFAULT '',"
            + "tran_carout_carparking_out_time TEXT  DEFAULT '',"
            + "tran_carout_car_type_name TEXT  DEFAULT '',"
            + "tran_carout_payment_type_name_th TEXT  DEFAULT '',"
            + "tran_carout_estamp_status TEXT  DEFAULT '',"
            + "tran_carout_payment_amount TEXT  DEFAULT '',"
            + "tran_carout_payment_discount_amount TEXT  DEFAULT '',"
            + "tran_carout_payment_fine_amount TEXT  DEFAULT '',"
            + "tran_carout_payment_totle TEXT  DEFAULT '',"
            + "tran_carout_ref1 TEXT  DEFAULT '',"
            + "tran_carout_ref2 TEXT  DEFAULT '',"
            + "tran_carout_admin_id TEXT  DEFAULT '',"
            + "tran_carout_admin_name TEXT  DEFAULT '',"
            + "tran_carout_response TEXT  DEFAULT '',"
            + "create_date TEXT  DEFAULT  (datetime('now','localtime')),"
            + "delete_flag TEXT  DEFAULT 'N'"
            + ");";










    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tableDataHistoryCarInCreateSQL);
      sqLiteDatabase.execSQL(tableDataHistoryCarOutCreateSQL);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
