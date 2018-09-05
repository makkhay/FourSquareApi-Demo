package com.prakash.foursquare.JSONmodel.PhotosModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Photos {

    @SerializedName("count")
    private Integer count;

    @SerializedName("items")
    private List<PhotoItem> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PhotoItem> getItems() {
        return items;
    }

    public void setItems(List<PhotoItem> items) {
        this.items = items;
    }
}
