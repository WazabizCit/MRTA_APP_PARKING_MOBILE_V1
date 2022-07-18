package com.example.mrta_app_parking_mobile_v1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mrta_app_parking_mobile_v1.R;
import com.example.mrta_app_parking_mobile_v1.model.History_data_carout_dao;

import java.util.ArrayList;

public class History_CarOut_Data_Adapter extends BaseAdapter {
        //('WAIT','COMPLETE','INPROGRESS')
        private static Activity activity;
        private static LayoutInflater inflater;
        ArrayList<History_data_carout_dao> dao;



    public History_CarOut_Data_Adapter(Activity activity, ArrayList<History_data_carout_dao> dao) {

            this.dao = dao;
            this.activity = activity;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return dao.size();
        }

        @Override
        public Object getItem(int position) {
            return dao.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            View v = convertView;


            if (convertView == null) {
                v = inflater.inflate(R.layout.list_data_history_carout, null);
            }





            TextView txt_date  = (TextView) v.findViewById(R.id.txt_date);
            TextView txt_building_name  = (TextView) v.findViewById(R.id.txt_building_name);
            TextView txt_estamp_status  = (TextView) v.findViewById(R.id.txt_estamp_status);
            TextView txt_car_type_name  = (TextView) v.findViewById(R.id.txt_car_type_name);
            TextView txt_receipt_no = (TextView) v.findViewById(R.id.txt_receipt_no);
            TextView txt_carparking_in_time   = (TextView) v.findViewById(R.id.txt_carparking_in_time );
            TextView txt_carparking_out_time  = (TextView) v.findViewById(R.id.txt_carparking_out_time);
            TextView txt_license_plate_text  = (TextView) v.findViewById(R.id.txt_license_plate_text);
            TextView txt_card_name  = (TextView) v.findViewById(R.id.txt_card_name);
            TextView txt_card_code  = (TextView) v.findViewById(R.id.txt_card_code);
            TextView txt_payment_amount  = (TextView) v.findViewById(R.id.txt_payment_amount);
            TextView txt_payment_fine_amount  = (TextView) v.findViewById(R.id.txt_payment_fine_amount);
            TextView txt_payment_totle  = (TextView) v.findViewById(R.id.txt_payment_totle);
            TextView txt_admin_name  = (TextView) v.findViewById(R.id.txt_admin_name);






            History_data_carout_dao list = dao.get(position);

            String date = list.getTran_carout_carparking_out_time();
            String building_name = list.getTran_carout_building_name();
            String estamp_status = list.getTran_carout_estamp_status();
            String car_type_name  = list.getTran_carout_car_type_name();
            String receipt_no  = list.getTran_carout_receipt_no();
            String carparking_in_time  = list.getTran_carout_carparking_in_time();
            String carparking_out_time  = list.getTran_carout_carparking_out_time();
            String license_plate_text  = list.getTran_carout_license_plate();
            String card_name  = list.getTran_carout_cardname();
            String card_code  = list.getTran_carout_cardcode();
            String payment_amount  = list.getTran_carout_payment_amount();
            String payment_fine_amount  = list.getTran_carout_payment_fine_amount();
            String payment_totle  = list.getTran_carout_payment_totle();
            String admin_name  = list.getTran_carout_admin_name();



            txt_date.setText(date+"");
            txt_building_name.setText(building_name+"");
            txt_estamp_status.setText(estamp_status+"");
            txt_car_type_name.setText(car_type_name+"");
            txt_receipt_no.setText(receipt_no+"");
            txt_carparking_in_time .setText(carparking_in_time+"");
            txt_carparking_out_time.setText(carparking_out_time+"");
            txt_license_plate_text.setText(license_plate_text+"");
            txt_card_name.setText(card_name+"");
            txt_card_code.setText(card_code+"");
            txt_payment_amount.setText(payment_amount+"");
            txt_payment_fine_amount.setText(payment_fine_amount+"");
            txt_payment_totle.setText(payment_totle+"");
            txt_admin_name.setText(admin_name+"");


            return v;
        }


    }
