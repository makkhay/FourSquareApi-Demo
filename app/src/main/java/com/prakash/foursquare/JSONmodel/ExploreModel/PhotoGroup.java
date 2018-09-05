package com.prakash.foursquare.JSONmodel.ExploreModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PhotoGroup {

    @SerializedName("items")
    private List<PhotoItem> items = null;

    public List<PhotoItem> getItems() {
        return items;
    }

    public void setItems(List<PhotoItem> items) {
        this.items = items;
    }


}
