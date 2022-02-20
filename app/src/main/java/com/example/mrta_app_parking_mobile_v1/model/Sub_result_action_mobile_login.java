package com.example.mrta_app_parking_mobile_v1.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sub_result_action_mobile_login {
    @SerializedName("employee_id")
    @Expose
    private Integer employeeId;
    @SerializedName("employee_code")
    @Expose
    private String employeeCode;
    @SerializedName("first_name_th")
    @Expose
    private String firstNameTh;
    @SerializedName("last_name_th")
    @Expose
    private String lastNameTh;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password_status")
    @Expose
    private Boolean passwordStatus;
    @SerializedName("login_master_status")
    @Expose
    private String loginMasterStatus;
    @SerializedName("login_document_status")
    @Expose
    private String loginDocumentStatus;
    @SerializedName("login_report_status")
    @Expose
    private String loginReportStatus;
    @SerializedName("login_maintenance_status")
    @Expose
    private String loginMaintenanceStatus;
    @SerializedName("is_uat")
    @Expose
    private Boolean isUat;
    @SerializedName("uat_text")
    @Expose
    private String uatText;
    @SerializedName("login_maintenance_data")
    @Expose
    private Sub_loginMaintenancedata_result_action_mobile_login loginMaintenanceData;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFirstNameTh() {
        return firstNameTh;
    }

    public void setFirstNameTh(String firstNameTh) {
        this.firstNameTh = firstNameTh;
    }

    public String getLastNameTh() {
        return lastNameTh;
    }

    public void setLastNameTh(String lastNameTh) {
        this.lastNameTh = lastNameTh;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getPasswordStatus() {
        return passwordStatus;
    }

    public void setPasswordStatus(Boolean passwordStatus) {
        this.passwordStatus = passwordStatus;
    }

    public String getLoginMasterStatus() {
        return loginMasterStatus;
    }

    public void setLoginMasterStatus(String loginMasterStatus) {
        this.loginMasterStatus = loginMasterStatus;
    }

    public String getLoginDocumentStatus() {
        return loginDocumentStatus;
    }

    public void setLoginDocumentStatus(String loginDocumentStatus) {
        this.loginDocumentStatus = loginDocumentStatus;
    }

    public String getLoginReportStatus() {
        return loginReportStatus;
    }

    public void setLoginReportStatus(String loginReportStatus) {
        this.loginReportStatus = loginReportStatus;
    }

    public String getLoginMaintenanceStatus() {
        return loginMaintenanceStatus;
    }

    public void setLoginMaintenanceStatus(String loginMaintenanceStatus) {
        this.loginMaintenanceStatus = loginMaintenanceStatus;
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

    public Sub_loginMaintenancedata_result_action_mobile_login getLoginMaintenanceData() {
        return loginMaintenanceData;
    }

    public void setLoginMaintenanceData(Sub_loginMaintenancedata_result_action_mobile_login loginMaintenanceData) {
        this.loginMaintenanceData = loginMaintenanceData;
    }
}