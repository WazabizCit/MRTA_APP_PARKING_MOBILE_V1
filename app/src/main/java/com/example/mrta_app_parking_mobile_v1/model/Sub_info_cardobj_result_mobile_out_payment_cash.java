package com.example.mrta_app_parking_mobile_v1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sub_info_cardobj_result_mobile_out_payment_cash {

    @SerializedName("card_id")
    @Expose
    private Integer cardId;
    @SerializedName("card_code")
    @Expose
    private String cardCode;
    @SerializedName("card_name")
    @Expose
    private String cardName;
    @SerializedName("card_type_remark")
    @Expose
    private String cardTypeRemark;
    @SerializedName("card_type_id")
    @Expose
    private Integer cardTypeId;
    @SerializedName("card_type_name")
    @Expose
    private String cardTypeName;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardTypeRemark() {
        return cardTypeRemark;
    }

    public void setCardTypeRemark(String cardTypeRemark) {
        this.cardTypeRemark = cardTypeRemark;
    }

    public Integer getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }
}
