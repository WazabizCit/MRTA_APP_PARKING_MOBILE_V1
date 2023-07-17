package com.example.mrta_app_parking_mobile_v1.manager;


import com.example.mrta_app_parking_mobile_v1.manager.http.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import android.content.Context;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static HttpManager instance;

    public static HttpManager getInstance(String ip_address,String port) {
        if (instance == null)
            instance = new HttpManager(ip_address,port);
        return instance;
    }

    private Context mContext;

    private ApiService service;

    private HttpManager(String ip_address,String port) {

        mContext = Contextor.getInstance().getContext();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()

                //.baseUrl("http://68.183.191.174:8000/")

                //ip Test
                //.baseUrl("http://192.168.1.48:10020/")

                .baseUrl("http://"+ip_address+":"+port+"/")


                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return service;

    }
}

