package com.example.mrta_app_parking_mobile_v1.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mrta_app_parking_mobile_v1.R;
import com.example.mrta_app_parking_mobile_v1.adapter.History_CarIn_Data_Adapter;
import com.example.mrta_app_parking_mobile_v1.adapter.History_CarOut_Data_Adapter;
import com.example.mrta_app_parking_mobile_v1.dao.DataHistoryCarInDao;
import com.example.mrta_app_parking_mobile_v1.dao.DataHistoryCarOutDao;
import com.example.mrta_app_parking_mobile_v1.model.History_data_carin_dao;
import com.example.mrta_app_parking_mobile_v1.model.History_data_carout_dao;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;

import java.util.ArrayList;

public class HistoryCarOutMainActivity extends ImportantMethod {



    ListView listview;
    History_CarOut_Data_Adapter adapter;
    ArrayList<History_data_carout_dao> mlist;
    TextView txt_view_nodata;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDoalog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_car_out_main);
        inintInstances();
        getListHistory();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ประวัติงานขาออก Parking");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

    }
    private void inintInstances() {
        txt_view_nodata = findViewById(R.id.txt_view_nodata);
        listview = findViewById(R.id.listview);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(pullToRefreshListener);




    }


    SwipeRefreshLayout.OnRefreshListener pullToRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshData();
        }
    };

    private void refreshData() {
        getListHistory();
        swipeRefreshLayout.setRefreshing(false);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == android.R.id.home) {

            Intent intent = new Intent(HistoryCarOutMainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(HistoryCarOutMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void getListHistory() {
        String datetime = getCurrentDate();

        DataHistoryCarOutDao dao = new DataHistoryCarOutDao(getApplicationContext());
        dao.open();
        ArrayList<History_data_carout_dao> mlist = dao.getDataHistory();
        dao.close();
        if (mlist.size() == 0) {
            listview.setVisibility(View.GONE);
            txt_view_nodata.setVisibility(View.VISIBLE);

        } else {
            adapter = new History_CarOut_Data_Adapter(HistoryCarOutMainActivity.this, mlist);
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            txt_view_nodata.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
        }


    }










}
