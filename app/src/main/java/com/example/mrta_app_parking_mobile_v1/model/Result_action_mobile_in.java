package com.example.mrta_app_parking_mobile_v1.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_action_mobile_in {

    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("error")
    @Expose
    private Object error;
    @SerializedName("data")
    @Expose
    private Object data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}