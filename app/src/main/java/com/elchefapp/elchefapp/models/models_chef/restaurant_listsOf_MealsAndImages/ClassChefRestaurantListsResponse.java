package com.elchefapp.elchefapp.models.models_chef.restaurant_listsOf_MealsAndImages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassChefRestaurantListsResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ClassChefRestaurantListslData data;

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

    public ClassChefRestaurantListslData getData() {
        return data;
    }

    public void setData(ClassChefRestaurantListslData data) {
        this.data = data;
    }
}
