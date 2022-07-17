package com.example.mrta_app_parking_mobile_v1.manager.http;

import com.example.mrta_app_parking_mobile_v1.model.Result_action_mobile_in;
import com.example.mrta_app_parking_mobile_v1.model.Result_action_mobile_login;
import com.example.mrta_app_parking_mobile_v1.model.Result_action_mobile_out_call_checkpayment;
import com.example.mrta_app_parking_mobile_v1.model.Result_checkcard;
import com.example.mrta_app_parking_mobile_v1.model.Result_mobile_out_flag_card_no_pay;
import com.example.mrta_app_parking_mobile_v1.model.Result_mobile_out_payment_cash;

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


    @FormUrlEncoded
    @POST("v1/parking_out/mobile_out_call_checkpayment")
    Call<Result_action_mobile_out_call_checkpayment> action_mobile_out_call_checkpayment(
            @Field("m_cabinet_id") String m_cabinet_id,
            @Field("m_cabinet_code") String m_cabinet_code,
            @Field("m_building_id") String m_building_id,
            @Field("m_building_code") String m_building_code,
            @Field("m_card_code") String m_card_code,
            @Field("m_cabinet_send_time") String m_cabinet_send_time,
            @Field("m_admin") String m_admin,
            @Field("m_card_signature") String m_card_signature,
            @Field("m_location_user_picture") String m_location_user_picture

    );


    @FormUrlEncoded
    @POST("v1/parking_out/mobile_out_flag_card_no_pay")
    Call<Result_mobile_out_flag_card_no_pay> action_mobile_out_flag_card_no_pay(
            @Field("m_cabinet_id") String m_cabinet_id,
            @Field("m_cabinet_code") String m_cabinet_code,
            @Field("m_building_id") String m_building_id,
            @Field("m_building_code") String m_building_code,
            @Field("m_card_code") String m_card_code,
            @Field("m_cabinet_send_time") String m_cabinet_send_time,
            @Field("m_admin") String m_admin,
            @Field("m_card_signature") String m_card_signature,
            @Field("m_location_user_picture") String m_location_user_picture,
            @Field("m_payment_type_id") String m_payment_type_id,
            @Field("m_payment_event_id") String m_payment_event_id,
            @Field("m_tci_id") String m_tci_id,
            @Field("m_payment_amount") String m_payment_amount,
            @Field("m_discount_amount") String m_discount_amount,
            @Field("m_payment_totle") String m_payment_totle,
            @Field("m_payment_withdraw_amount") String m_payment_withdraw_amount,
            @Field("m_payment_customer_amount") String m_payment_customer_amount,
            @Field("m_cash_1000") String m_cash_1000,
            @Field("m_cash_500") String m_cash_500,
            @Field("m_cash_100") String m_cash_100,
            @Field("m_cash_50") String m_cash_50,
            @Field("m_cash_20") String m_cash_20,
            @Field("m_cash_10") String m_cash_10,
            @Field("m_cash_5") String m_cash_5,
            @Field("m_payment_fine_amount") String m_payment_fine_amount
            );


    @FormUrlEncoded
    @POST("v1/parking_out/mobile_out_payment/cash")
    Call<Result_mobile_out_payment_cash> action_mobile_out_payment_cash(
            @Field("m_cabinet_id") String m_cabinet_id,
            @Field("m_cabinet_code") String m_cabinet_code,
            @Field("m_building_id") String m_building_id,
            @Field("m_building_code") String m_building_code,
            @Field("m_card_code") String m_card_code,
            @Field("m_cabinet_send_time") String m_cabinet_send_time,
            @Field("m_admin") String m_admin,
            @Field("m_card_signature") String m_card_signature,
            @Field("m_location_user_picture") String m_location_user_picture,
            @Field("m_payment_type_id") String m_payment_type_id,
            @Field("m_payment_event_id") String m_payment_event_id,
            @Field("m_payment_status") String m_payment_status,
            @Field("m_tci_id") String m_tci_id,
            @Field("m_tcch_id") String m_tcch_id,
            @Field("m_payment_amount") String m_payment_amount,
            @Field("m_discount_amount") String m_discount_amount,
            @Field("m_payment_totle") String m_payment_totle,
            @Field("m_payment_withdraw_amount") String m_payment_withdraw_amount,
            @Field("m_payment_customer_amount") String m_payment_customer_amount,
            @Field("m_cash_1000") String m_cash_1000,
            @Field("m_cash_500") String m_cash_500,
            @Field("m_cash_100") String m_cash_100,
            @Field("m_cash_50") String m_cash_50,
            @Field("m_cash_20") String m_cash_20,
            @Field("m_cash_10") String m_cash_10,
            @Field("m_cash_5") String m_cash_5,
            @Field("m_payment_fine_amount") String m_payment_fine_amount
    );



}
