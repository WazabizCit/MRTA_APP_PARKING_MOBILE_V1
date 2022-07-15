package com.example.mrta_app_parking_mobile_v1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sub_result_action_mobile_out_call_checkpayment {
    @SerializedName("tci_id")
    @Expose
    private Integer tciId;
    @SerializedName("card_code")
    @Expose
    private String cardCode;
    @SerializedName("payment_amount")
    @Expose
    private Integer paymentAmount;
    @SerializedName("discount_amount")
    @Expose
    private Integer discountAmount;
    @SerializedName("payment_totle")
    @Expose
    private Integer paymentTotle;
    @SerializedName("card_status")
    @Expose
    private String cardStatus;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("payment_time")
    @Expose
    private Object paymentTime;
    @SerializedName("out_early_time")
    @Expose
    private Object outEarlyTime;
    @SerializedName("estamp_status")
    @Expose
    private Boolean estampStatus;
    @SerializedName("payment_fine_amount")
    @Expose
    private Integer paymentFineAmount;
    @SerializedName("tcch_id")
    @Expose
    private Integer tcchId;
    @SerializedName("cabinet_id")
    @Expose
    private Integer cabinetId;
    @SerializedName("car_type_status")
    @Expose
    private String carTypeStatus;
    @SerializedName("license_plate_text")
    @Expose
    private String licensePlateText;
    @SerializedName("carparking_in_time")
    @Expose
    private String carparkingInTime;
    @SerializedName("carparking_out_time")
    @Expose
    private String carparkingOutTime;
    @SerializedName("result_case_cal")
    @Expose
    private String resultCaseCal;
    @SerializedName("result_parking_interval")
    @Expose
    private String resultParkingInterval;
    @SerializedName("result_parking_find")
    @Expose
    private Integer resultParkingFind;
    @SerializedName("estamp_type_name")
    @Expose
    private String estampTypeName;
    @SerializedName("ref1")
    @Expose
    private String ref1;
    @SerializedName("ref2")
    @Expose
    private String ref2;
    @SerializedName("in_path_car_picture")
    @Expose
    private Object inPathCarPicture;
    @SerializedName("in_path_user_picture")
    @Expose
    private Object inPathUserPicture;
    @SerializedName("card_name")
    @Expose
    private String cardName;
    @SerializedName("is_uat")
    @Expose
    private Boolean isUat;
    @SerializedName("uat_text")
    @Expose
    private String uatText;
    @SerializedName("cps_amount_value")
    @Expose
    private Integer cpsAmountValue;
    @SerializedName("cph_fine_amount")
    @Expose
    private Integer cphFineAmount;
    @SerializedName("car_type")
    @Expose
    private Integer carType;
    @SerializedName("card_type")
    @Expose
    private Integer cardType;
    @SerializedName("estamp_id")
    @Expose
    private Integer estampId;
    @SerializedName("card_expire_date")
    @Expose
    private String cardExpireDate;
    @SerializedName("card_exprie_days")
    @Expose
    private String cardExprieDays;
    @SerializedName("payment_type_id")
    @Expose
    private Integer paymentTypeId;
    @SerializedName("payment_event_id")
    @Expose
    private Integer paymentEventId;

    public Integer getTciId() {
        return tciId;
    }

    public void setTciId(Integer tciId) {
        this.tciId = tciId;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getPaymentTotle() {
        return paymentTotle;
    }

    public void setPaymentTotle(Integer paymentTotle) {
        this.paymentTotle = paymentTotle;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Object getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Object paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Object getOutEarlyTime() {
        return outEarlyTime;
    }

    public void setOutEarlyTime(Object outEarlyTime) {
        this.outEarlyTime = outEarlyTime;
    }

    public Boolean getEstampStatus() {
        return estampStatus;
    }

    public void setEstampStatus(Boolean estampStatus) {
        this.estampStatus = estampStatus;
    }

    public Integer getPaymentFineAmount() {
        return paymentFineAmount;
    }

    public void setPaymentFineAmount(Integer paymentFineAmount) {
        this.paymentFineAmount = paymentFineAmount;
    }

    public Integer getTcchId() {
        return tcchId;
    }

    public void setTcchId(Integer tcchId) {
        this.tcchId = tcchId;
    }

    public Integer getCabinetId() {
        return cabinetId;
    }

    public void setCabinetId(Integer cabinetId) {
        this.cabinetId = cabinetId;
    }

    public String getCarTypeStatus() {
        return carTypeStatus;
    }

    public void setCarTypeStatus(String carTypeStatus) {
        this.carTypeStatus = carTypeStatus;
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

    public String getResultCaseCal() {
        return resultCaseCal;
    }

    public void setResultCaseCal(String resultCaseCal) {
        this.resultCaseCal = resultCaseCal;
    }

    public String getResultParkingInterval() {
        return resultParkingInterval;
    }

    public void setResultParkingInterval(String resultParkingInterval) {
        this.resultParkingInterval = resultParkingInterval;
    }

    public Integer getResultParkingFind() {
        return resultParkingFind;
    }

    public void setResultParkingFind(Integer resultParkingFind) {
        this.resultParkingFind = resultParkingFind;
    }

    public String getEstampTypeName() {
        return estampTypeName;
    }

    public void setEstampTypeName(String estampTypeName) {
        this.estampTypeName = estampTypeName;
    }

    public String getRef1() {
        return ref1;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public String getRef2() {
        return ref2;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public Object getInPathCarPicture() {
        return inPathCarPicture;
    }

    public void setInPathCarPicture(Object inPathCarPicture) {
        this.inPathCarPicture = inPathCarPicture;
    }

    public Object getInPathUserPicture() {
        return inPathUserPicture;
    }

    public void setInPathUserPicture(Object inPathUserPicture) {
        this.inPathUserPicture = inPathUserPicture;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Boolean getIsUat() {
        return isUat;
    }

    public void setIsUat(Boolean isUat) {
        this.isUat = isUat;
    }

    public String getUatText() {
        return uatText;
    }

    public void setUatText(String uatText) {
        this.uatText = uatText;
    }

    public Integer getCpsAmountValue() {
        return cpsAmountValue;
    }

    public void setCpsAmountValue(Integer cpsAmountValue) {
        this.cpsAmountValue = cpsAmountValue;
    }

    public Integer getCphFineAmount() {
        return cphFineAmount;
    }

    public void setCphFineAmount(Integer cphFineAmount) {
        this.cphFineAmount = cphFineAmount;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getEstampId() {
        return estampId;
    }

    public void setEstampId(Integer estampId) {
        this.estampId = estampId;
    }

    public String getCardExpireDate() {
        return cardExpireDate;
    }

    public void setCardExpireDate(String cardExpireDate) {
        this.cardExpireDate = cardExpireDate;
    }

    public String getCardExprieDays() {
        return cardExprieDays;
    }

    public void setCardExprieDays(String cardExprieDays) {
        this.cardExprieDays = cardExprieDays;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Integer getPaymentEventId() {
        return paymentEventId;
    }

    public void setPaymentEventId(Integer paymentEventId) {
        this.paymentEventId = paymentEventId;
    }

}
