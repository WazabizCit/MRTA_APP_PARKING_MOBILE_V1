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
            TextView txt_cabinet_id  = (TextView) v.findViewById(R.id.txt_cabinet_id);
            TextView txt_cabinet_code  = (TextView) v.findViewById(R.id.txt_cabinet_code);
            TextView txt_building_id  = (TextView) v.findViewById(R.id.txt_building_id);
            TextView txt_building_code = (TextView) v.findViewById(R.id.txt_building_code);
            TextView txt_card_code  = (TextView) v.findViewById(R.id.txt_card_code);
            TextView txt_license_plate  = (TextView) v.findViewById(R.id.txt_license_plate);
            TextView txt_admin_name  = (TextView) v.findViewById(R.id.txt_admin_name);




            History_data_carout_dao list = dao.get(position);

            String date = list.getTran_carout_carparking_out_time();
            String cabinet_id = list.getTran_carout_cabinet_id();
            String cabinet_code = list.getTran_carout_cabinet_code();
            String building_id  = list.getTran_carout_building_id();
            String building_code  = list.getTran_carout_building_code();
            String card_code  = list.getTran_carout_cardcode();
            String license_plate  = list.getTran_carout_license_plate();
            String admin_name  = list.getTran_carout_admin_name();



            txt_date.setText(date+"");
            txt_cabinet_id.setText(cabinet_id+"");
            txt_cabinet_code.setText(cabinet_code+"");
            txt_building_id.setText(building_id+"");
            txt_building_code.setText(building_code+"");
            txt_card_code.setText(card_code+"");
            txt_license_plate.setText(license_plate+"");
            txt_admin_name.setText(admin_name+"");




            return v;
        }


    }
