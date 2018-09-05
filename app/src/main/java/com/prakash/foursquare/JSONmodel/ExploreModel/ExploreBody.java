package com.prakash.foursquare.JSONmodel.ExploreModel;


import com.google.gson.annotations.SerializedName;

/**
 * Class representing a call to Foursquare API
 */
public class ExploreBody {

    @SerializedName("response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
