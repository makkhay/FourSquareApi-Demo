package com.prakash.foursquare.JSONmodel.PhotosModel;

import com.google.gson.annotations.SerializedName;

public class PhotosBody {

    @SerializedName("response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
