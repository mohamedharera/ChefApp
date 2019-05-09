package com.elchefapp.elchefapp.models.models_chef.addMeal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassAddMealResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ClassAddMealData data;

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

    public ClassAddMealData getData() {
        return data;
    }

    public void setData(ClassAddMealData data) {
        this.data = data;
    }

}
