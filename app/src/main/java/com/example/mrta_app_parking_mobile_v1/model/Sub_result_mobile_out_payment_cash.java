package com.example.mrta_app_parking_mobile_v1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sub_result_mobile_out_payment_cash {
    @SerializedName("receipt_no")
    @Expose
    private String receiptNo;
    @SerializedName("result_receipt_data")
    @Expose
    private Sub_info_receiptdata_result_mobile_out_payment_cash resultReceiptData;
    @SerializedName("cabinet_obj")
    @Expose
    private Sub_info_cabinetobj_result_mobile_out_payment_cash cabinetObj;
    @SerializedName("card_obj")
    @Expose
    private Sub_info_cardobj_result_mobile_out_payment_cash cardObj;

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Sub_info_receiptdata_result_mobile_out_payment_cash getResultReceiptData() {
        return resultReceiptData;
    }

    public void setResultReceiptData(Sub_info_receiptdata_result_mobile_out_payment_cash resultReceiptData) {
        this.resultReceiptData = resultReceiptData;
    }

    public Sub_info_cabinetobj_result_mobile_out_payment_cash getCabinetObj() {
        return cabinetObj;
    }

    public void setCabinetObj(Sub_info_cabinetobj_result_mobile_out_payment_cash cabinetObj) {
        this.cabinetObj = cabinetObj;
    }

    public Sub_info_cardobj_result_mobile_out_payment_cash getCardObj() {
        return cardObj;
    }

    public void setCardObj(Sub_info_cardobj_result_mobile_out_payment_cash cardObj) {
        this.cardObj = cardObj;
    }
}
