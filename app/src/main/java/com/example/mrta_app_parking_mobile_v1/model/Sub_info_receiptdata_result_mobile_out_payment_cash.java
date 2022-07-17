package com.example.mrta_app_parking_mobile_v1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sub_info_receiptdata_result_mobile_out_payment_cash {

    @SerializedName("card_code")
    @Expose
    private String cardCode;
    @SerializedName("license_plate_text")
    @Expose
    private String licensePlateText;
    @SerializedName("carparking_in_time")
    @Expose
    private String carparkingInTime;
    @SerializedName("carparking_out_time")
    @Expose
    private String carparkingOutTime;
    @SerializedName("before_out_area_time")
    @Expose
    private String beforeOutAreaTime;
    @SerializedName("car_type_name")
    @Expose
    private String carTypeName;
    @SerializedName("estamp_status")
    @Expose
    private Boolean estampStatus;
    @SerializedName("cabinet_id")
    @Expose
    private Integer cabinetId;
    @SerializedName("cabinet_code")
    @Expose
    private String cabinetCode;
    @SerializedName("payment_type_id")
    @Expose
    private Integer paymentTypeId;
    @SerializedName("payment_type_name_th")
    @Expose
    private String paymentTypeNameTh;
    @SerializedName("payment_type_name_en")
    @Expose
    private String paymentTypeNameEn;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("payment_amount")
    @Expose
    private Integer paymentAmount;
    @SerializedName("payment_discount_amount")
    @Expose
    private Integer paymentDiscountAmount;
    @SerializedName("payment_fine_amount")
    @Expose
    private Integer paymentFineAmount;
    @SerializedName("payment_totle")
    @Expose
    private Integer paymentTotle;
    @SerializedName("payment_customer_amount")
    @Expose
    private Integer paymentCustomerAmount;
    @SerializedName("payment_withdraw_amount")
    @Expose
    private Integer paymentWithdrawAmount;
    @SerializedName("ref1")
    @Expose
    private Object ref1;
    @SerializedName("ref2")
    @Expose
    private Object ref2;

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getLicensePlateText() {
        return licensePlateText;
    }

    public void setLicensePlateText(String licensePlateText) {
        this.licensePlateText = licensePlateText;
    }

    public String getCarparkingInTime() {
        return carparkingInTime;
    }

    public void setCarparkingInTime(String carparkingInTime) {
        this.carparkingInTime = carparkingInTime;
    }

    public String getCarparkingOutTime() {
        return carparkingOutTime;
    }

    public void setCarparkingOutTime(String carparkingOutTime) {
        this.carparkingOutTime = carparkingOutTime;
    }

    public String getBeforeOutAreaTime() {
        return beforeOutAreaTime;
    }

    public void setBeforeOutAreaTime(String beforeOutAreaTime) {
        this.beforeOutAreaTime = beforeOutAreaTime;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public Boolean getEstampStatus() {
        return estampStatus;
    }

    public void setEstampStatus(Boolean estampStatus) {
        this.estampStatus = estampStatus;
    }

    public Integer getCabinetId() {
        return cabinetId;
    }

    public void setCabinetId(Integer cabinetId) {
        this.cabinetId = cabinetId;
    }

    public String getCabinetCode() {
        return cabinetCode;
    }

    public void setCabinetCode(String cabinetCode) {
        this.cabinetCode = cabinetCode;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPaymentTypeNameTh() {
        return paymentTypeNameTh;
    }

    public void setPaymentTypeNameTh(String paymentTypeNameTh) {
        this.paymentTypeNameTh = paymentTypeNameTh;
    }

    public String getPaymentTypeNameEn() {
        return paymentTypeNameEn;
    }

    public void setPaymentTypeNameEn(String paymentTypeNameEn) {
        this.paymentTypeNameEn = paymentTypeNameEn;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Integer getPaymentDiscountAmount() {
        return paymentDiscountAmount;
    }

    public void setPaymentDiscountAmount(Integer paymentDiscountAmount) {
        this.paymentDiscountAmount = paymentDiscountAmount;
    }

    public Integer getPaymentFineAmount() {
        return paymentFineAmount;
    }

    public void setPaymentFineAmount(Integer paymentFineAmount) {
        this.paymentFineAmount = paymentFineAmount;
    }

    public Integer getPaymentTotle() {
        return paymentTotle;
    }

    public void setPaymentTotle(Integer paymentTotle) {
        this.paymentTotle = paymentTotle;
    }

    public Integer getPaymentCustomerAmount() {
        return paymentCustomerAmount;
    }

    public void setPaymentCustomerAmount(Integer paymentCustomerAmount) {
        this.paymentCustomerAmount = paymentCustomerAmount;
    }

    public Integer getPaymentWithdrawAmount() {
        return paymentWithdrawAmount;
    }

    public void setPaymentWithdrawAmount(Integer paymentWithdrawAmount) {
        this.paymentWithdrawAmount = paymentWithdrawAmount;
    }

    public Object getRef1() {
        return ref1;
    }

    public void setRef1(Object ref1) {
        this.ref1 = ref1;
    }

    public Object getRef2() {
        return ref2;
    }

    public void setRef2(Object ref2) {
        this.ref2 = ref2;
    }
}
