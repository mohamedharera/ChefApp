package com.elchefapp.elchefapp.models.models_chef.addImage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassAddImageResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ClassAddImageData data;

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

    public ClassAddImageData getData() {
        return data;
    }

    public void setData(ClassAddImageData data) {
        this.data = data;
    }

}
