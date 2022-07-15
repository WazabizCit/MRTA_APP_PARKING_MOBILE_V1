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


//
//
//    private static final String tableDataHistoryCarBookingOutCreateSQL = "CREATE TABLE tran_history_carbooking_out("
//            + "tran_carbookingout_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + "tran_carbookingout_cabinet_id TEXT  DEFAULT '',"
//            + "tran_carbookingout_cabinet_code TEXT  DEFAULT '',"
//            + "tran_carbookingout_building_id TEXT  DEFAULT '',"
//            + "tran_carbookingout_building_code TEXT  DEFAULT '',"
//            + "tran_carbookingout_cabinet_send_time TEXT  DEFAULT '',"
//            + "tran_carbookingout_qrcode TEXT DEFAULT '',"
//            + "tran_carbookingout_response TEXT DEFAULT '',"
//            + "create_date TEXT  DEFAULT  (datetime('now','localtime')),"
//            + "delete_flag TEXT  DEFAULT 'N'"
//            + ");";










    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL(tableDataHistoryCarInCreateSQL);
      // sqLiteDatabase.execSQL(tableDataHistoryCarBookingOutCreateSQL);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
