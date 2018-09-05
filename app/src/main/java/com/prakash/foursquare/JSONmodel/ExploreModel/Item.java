package com.prakash.foursquare.JSONmodel.ExploreModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Item {

    @SerializedName("venue")
    private Venue venue;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    // Get a list of venues from a list of items
    public static List<Venue> ItemsToVenues(List<Item> items) {
        List<Venue> venues = new ArrayList<>();
        for(int i = 0; i < items.size(); i++) {
            venues.add(items.get(i).getVenue());
        }
        return venues;
    }

}
