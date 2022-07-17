package com.example.mrta_app_parking_mobile_v1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sub_info_cabinetobj_result_mobile_out_flag_card_no_pay {



    @SerializedName("cabinet_tax_code")
    @Expose
    private String cabinetTaxCode;
    @SerializedName("cabinet_name")
    @Expose
    private String cabinetName;
    @SerializedName("cabinet_type_id")
    @Expose
    private Object cabinetTypeId;
    @SerializedName("cabinet_code")
    @Expose
    private String cabinetCode;
    @SerializedName("building_id")
    @Expose
    private Integer buildingId;
    @SerializedName("building_code")
    @Expose
    private String buildingCode;
    @SerializedName("building_name")
    @Expose
    private String buildingName;
    @SerializedName("registered_no")
    @Expose
    private String registeredNo;
    @SerializedName("building_tax")
    @Expose
    private String buildingTax;

    public String getCabinetTaxCode() {
        return cabinetTaxCode;
    }

    public void setCabinetTaxCode(String cabinetTaxCode) {
        this.cabinetTaxCode = cabinetTaxCode;
    }

    public String getCabinetName() {
        return cabinetName;
    }

    public void setCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
    }

    public Object getCabinetTypeId() {
        return cabinetTypeId;
    }

    public void setCabinetTypeId(Object cabinetTypeId) {
        this.cabinetTypeId = cabinetTypeId;
    }

    public String getCabinetCode() {
        return cabinetCode;
    }

    public void setCabinetCode(String cabinetCode) {
        this.cabinetCode = cabinetCode;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRegisteredNo() {
        return registeredNo;
    }

    public void setRegisteredNo(String registeredNo) {
        this.registeredNo = registeredNo;
    }

    public String getBuildingTax() {
        return buildingTax;
    }

    public void setBuildingTax(String buildingTax) {
        this.buildingTax = buildingTax;
    }

}
