package com.example.mrta_app_parking_mobile_v1.manager.http;

import com.example.mrta_app_parking_mobile_v1.model.Result_action_mobile_in;
import com.example.mrta_app_parking_mobile_v1.model.Result_action_mobile_login;
import com.example.mrta_app_parking_mobile_v1.model.Result_checkcard;

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


    @GET("v1/test")
    Call<ResponseBody> test_con();

    @FormUrlEncoded
    @POST("v1/parking_auth/login")
    Call<Result_action_mobile_login> action_mobile_login(
            @Field("m_cabinet_id") String m_cabinet_id,
            @Field("m_cabinet_in_send_time") String m_cabinet_in_send_time,
            @Field("m_cabinet_code") String m_cabinet_code,
            @Field("m_building_id") String m_building_id,
            @Field("m_building_code") String m_building_code,
            @Field("m_username") String m_username,
            @Field("m_password") String m_password


    );


    @FormUrlEncoded
    @POST("v1/parking_util/action_checkcard_in")
    Call<Result_checkcard> action_checkcard_in(
            @Field("m_cabinet_id") String m_cabinet_id,
            @Field("m_cabinet_code") String m_cabinet_code,
            @Field("m_building_id") String m_building_id,
            @Field("m_building_code") String m_building_code,
            @Field("m_cabinet_in_send_time") String m_cabinet_in_send_time,
            @Field("m_card_code") String m_card_code,
            @Field("m_admin") String m_admin

    );


    @FormUrlEncoded
    @POST("v1/parking_in/action_mobile_in")
    Call<Result_action_mobile_in> action_mobile_in(
            @Field("m_cabinet_id") String m_cabinet_id,
            @Field("m_cabinet_code") String m_cabinet_code,
            @Field("m_building_id") String m_building_id,
            @Field("m_building_code") String m_building_code,
            @Field("m_cabinet_in_send_time") String m_cabinet_in_send_time,
            @Field("m_card_code") String m_card_code,
            @Field("m_license_plate_text") String m_license_plate_text,
            @Field("m_admin") String m_admin

    );


}
