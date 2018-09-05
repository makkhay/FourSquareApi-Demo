package com.prakash.foursquare.JSONmodel.PhotosModel;


import com.google.gson.annotations.SerializedName;


public class Response {

    @SerializedName("photos")
    private Photos photos;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }
}