package com.elchefapp.elchefapp.models.models_client.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassCustomerLoginResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ClassCustomerLoginData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClassCustomerLoginData getData() {
        return data;
    }

    public void setData(ClassCustomerLoginData data) {
        this.data = data;
    }

}
