package com.elchefapp.elchefapp.models.models_client.registerFacebook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassRegisterFacebookData {
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
    private String fbToken;
    @SerializedName("twitter_token")
    @Expose
    private String twitterToken;
    @SerializedName("google_plus_token")
    @Expose
    private String googlePlusToken;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("city_id")
    @Expose
    private Integer cityId;
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

    public String getFbToken() {
        return fbToken;
    }

    public void setFbToken(String fbToken) {
        this.fbToken = fbToken;
    }

    public String getTwitterToken() {
        return twitterToken;
    }

    public void setTwitterToken(String twitterToken) {
        this.twitterToken = twitterToken;
    }

    public String getGooglePlusToken() {
        return googlePlusToken;
    }

    public void setGooglePlusToken(String googlePlusToken) {
        this.googlePlusToken = googlePlusToken;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
