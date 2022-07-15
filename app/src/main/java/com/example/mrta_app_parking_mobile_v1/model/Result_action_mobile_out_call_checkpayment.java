package com.example.mrta_app_parking_mobile_v1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_action_mobile_out_call_checkpayment {
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("error")
    @Expose
    private Object error;
    @SerializedName("data")
    @Expose
    private sub_result_action_mobile_out_call_checkpayment data;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public sub_result_action_mobile_out_call_checkpayment getData() {
        return data;
    }

    public void setData(sub_result_action_mobile_out_call_checkpayment data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}