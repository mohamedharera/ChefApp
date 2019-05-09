package com.elchefapp.elchefapp.models.models_client.changePassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassChangePasswordData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("remember_token")
    @Expose
    private Object rememberToken;
    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("fb_token")
    @Expose
    private Object fbToken;
    @SerializedName("twitter_token")
    @Expose
    private Object twitterToken;
    @SerializedName("google_plus_token")
    @Expose
    private Object googlePlusToken;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("city_id")
    @Expose
    private Object cityId;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(Object rememberToken) {
        this.rememberToken = rememberToken;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Object getFbToken() {
        return fbToken;
    }

    public void setFbToken(Object fbToken) {
        this.fbToken = fbToken;
    }

    public Object getTwitterToken() {
        return twitterToken;
    }

    public void setTwitterToken(Object twitterToken) {
        this.twitterToken = twitterToken;
    }

    public Object getGooglePlusToken() {
        return googlePlusToken;
    }

    public void setGooglePlusToken(Object googlePlusToken) {
        this.googlePlusToken = googlePlusToken;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getCityId() {
        return cityId;
    }

    public void setCityId(Object cityId) {
        this.cityId = cityId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
