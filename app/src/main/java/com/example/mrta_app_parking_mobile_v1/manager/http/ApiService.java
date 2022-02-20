package com.example.mrta_app_parking_mobile_v1.manager.http;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {





    //// Central Chidlom
    @GET("api/check_con")
    Call<ResponseBody> check_con();

    @FormUrlEncoded
    @POST("api/check_card_valet")
    Call<ResponseBody> check_card_valet(
            @Field("m_cardcode") String m_card
    );

    @FormUrlEncoded
    @POST("api/insert_valet_parking")
    Call<ResponseBody> insert_data_mysql
            (
                    @Field("m_cardcode") String m_card,
                    @Field("m_license") String m_license


            );






}
