package com.prakash.foursquare.JSONmodel.ExploreModel;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Group {

    @SerializedName("items")
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
