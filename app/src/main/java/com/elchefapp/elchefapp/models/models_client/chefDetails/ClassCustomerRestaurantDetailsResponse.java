package com.elchefapp.elchefapp.models.models_client.chefDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassCustomerRestaurantDetailsResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ClassCustomerRestaurantDetailsData data;

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

    public ClassCustomerRestaurantDetailsData getData() {
        return data;
    }

    public void setData(ClassCustomerRestaurantDetailsData data) {
        this.data = data;
    }

}
