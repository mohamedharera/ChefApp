package com.elchefapp.elchefapp.models.models_client.favourites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassCustomerFavouriteResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<ClassCustomerFavouriteDatum> data = null;

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

    public List<ClassCustomerFavouriteDatum> getData() {
        return data;
    }

    public void setData(List<ClassCustomerFavouriteDatum> data) {
        this.data = data;
    }
}
