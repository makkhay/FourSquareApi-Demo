package com.prakash.foursquare.JSONmodel.ExploreModel;

import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Thumbnail {

    @SerializedName("groups")
    private List<PhotoGroup> groups = null;

    public List<PhotoGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<PhotoGroup> groups) {
        this.groups = groups;
    }


}
