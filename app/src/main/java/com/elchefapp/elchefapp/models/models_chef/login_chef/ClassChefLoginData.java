package com.elchefapp.elchefapp.models.models_chef.login_chef;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassChefLoginData {
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
    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("type")
    @Expose
    private Object type;
    @SerializedName("remember_token")
    @Expose
    private Object rememberToken;
    @SerializedName("api_token")
    @Expose
    private Object apiToken;
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
    private Object image;
    @SerializedName("about")
    @Expose
    private Object about;
    @SerializedName("is_open")
    @Expose
    private Integer isOpen;
    @SerializedName("happy_hour")
    @Expose
    private Integer happyHour;
    @SerializedName("is_delivery")
    @Expose
    private Integer isDelivery;
    @SerializedName("facebook")
    @Expose
    private Object facebook;
    @SerializedName("twitter")
    @Expose
    private Object twitter;
    @SerializedName("instagram")
    @Expose
    private Object instagram;
    @SerializedName("youtube")
    @Expose
    private Object youtube;
    @SerializedName("open_from")
    @Expose
    private Object openFrom;
    @SerializedName("open_to")
    @Expose
    private Object openTo;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("visits_count")
    @Expose
    private Integer visitsCount;
    @SerializedName("is_favourite")
    @Expose
    private Integer isFavourite;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("rate_count")
    @Expose
    private Integer rateCount;
    @SerializedName("images")
    @Expose
    private List<Object> images = null;
    @SerializedName("foods")
    @Expose
    private List<ClassChefLoginFood> foods = null;

    public ClassChefLoginData(int id, String name, String email, String address, int city_id, int rate, int rate_count, int visits_count) {
    }

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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(Object rememberToken) {
        this.rememberToken = rememberToken;
    }

    public Object getApiToken() {
        return apiToken;
    }

    public void setApiToken(Object apiToken) {
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

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getAbout() {
        return about;
    }

    public void setAbout(Object about) {
        this.about = about;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getHappyHour() {
        return happyHour;
    }

    public void setHappyHour(Integer happyHour) {
        this.happyHour = happyHour;
    }

    public Integer getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(Integer isDelivery) {
        this.isDelivery = isDelivery;
    }

    public Object getFacebook() {
        return facebook;
    }

    public void setFacebook(Object facebook) {
        this.facebook = facebook;
    }

    public Object getTwitter() {
        return twitter;
    }

    public void setTwitter(Object twitter) {
        this.twitter = twitter;
    }

    public Object getInstagram() {
        return instagram;
    }

    public void setInstagram(Object instagram) {
        this.instagram = instagram;
    }

    public Object getYoutube() {
        return youtube;
    }

    public void setYoutube(Object youtube) {
        this.youtube = youtube;
    }

    public Object getOpenFrom() {
        return openFrom;
    }

    public void setOpenFrom(Object openFrom) {
        this.openFrom = openFrom;
    }

    public Object getOpenTo() {
        return openTo;
    }

    public void setOpenTo(Object openTo) {
        this.openTo = openTo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getVisitsCount() {
        return visitsCount;
    }

    public void setVisitsCount(Integer visitsCount) {
        this.visitsCount = visitsCount;
    }

    public Integer getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(Integer isFavourite) {
        this.isFavourite = isFavourite;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getRateCount() {
        return rateCount;
    }

    public void setRateCount(Integer rateCount) {
        this.rateCount = rateCount;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public List<ClassChefLoginFood> getFoods() {
        return foods;
    }

    public void setFoods(List<ClassChefLoginFood> foods) {
        this.foods = foods;
    }
}
