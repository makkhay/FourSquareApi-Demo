package com.prakash.foursquare.JSONmodel.ExploreModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response {

    @SerializedName("headerLocation")
    private String headerLocation;

    @SerializedName("groups")
    private List<Group> groups = null;

    public String getHeaderLocation() {
        return headerLocation;
    }

    public void setHeaderLocation(String headerLocation) {
        this.headerLocation = headerLocation;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
