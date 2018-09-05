package com.prakash.foursquare.JSONmodel.ExploreModel;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("address")
    private String address;

    @SerializedName("city")
    private String city;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
