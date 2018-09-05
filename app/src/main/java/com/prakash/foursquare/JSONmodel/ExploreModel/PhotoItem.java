package com.prakash.foursquare.JSONmodel.ExploreModel;


import com.google.gson.annotations.SerializedName;

public class PhotoItem {

    @SerializedName("id")
    private String id;

    @SerializedName("prefix")
    private String prefix;

    @SerializedName("suffix")
    private String suffix;

    public String getURLforThumbnail() {
        return prefix + "width100" + suffix;
    }

    public String getURLforGrid() {
        return prefix + "width300" + suffix;
    }

    public String getURLforOriginal() {
        return prefix + "original" + suffix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
