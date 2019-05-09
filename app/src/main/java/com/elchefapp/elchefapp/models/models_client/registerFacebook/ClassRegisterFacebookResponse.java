package com.elchefapp.elchefapp.models.models_client.registerFacebook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassRegisterFacebookResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ClassRegisterFacebookData data;

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

    public ClassRegisterFacebookData getData() {
        return data;
    }

    public void setData(ClassRegisterFacebookData data) {
        this.data = data;
    }
}
