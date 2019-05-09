package com.elchefapp.elchefapp.models.models_client.favourites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassCustomerFavouriteDatum {
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
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("type")
    @Expose
    private Object type;
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
    private Object phone;
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
    @SerializedName("menu_avg")
    @Expose
    private Integer menuAvg;
    @SerializedName("images")
    @Expose
    private List<ClassCustomerFavouriteImage> images = null;
    @SerializedName("foods")
    @Expose
    private List<ClassCustomerFavouriteFood> foods = null;

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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
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

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
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

    public Integer getMenuAvg() {
        return menuAvg;
    }

    public void setMenuAvg(Integer menuAvg) {
        this.menuAvg = menuAvg;
    }

    public List<ClassCustomerFavouriteImage> getImages() {
        return images;
    }

    public void setImages(List<ClassCustomerFavouriteImage> images) {
        this.images = images;
    }

    public List<ClassCustomerFavouriteFood> getFoods() {
        return foods;
    }

    public void setFoods(List<ClassCustomerFavouriteFood> foods) {
        this.foods = foods;
    }
}
