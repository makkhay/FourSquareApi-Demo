package com.prakash.foursquare.JSONmodel.ExploreModel;

import com.google.gson.annotations.SerializedName;

public class IconImage {

    @SerializedName("prefix")
    private String prefix;

    @SerializedName("suffix")
    private String suffix;


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

    // how to request images: https://developer.foursquare.com/docs/responses/photo.html
    public String getURLforThumbnail() {
        return prefix + "bg_64" + suffix;
    }


}
