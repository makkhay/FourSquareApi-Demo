package com.prakash.foursquare.JSONmodel.ExploreModel;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("icon")
    private IconImage icon;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IconImage getIcon() {
        return icon;
    }

    public void setIcon(IconImage icon) {
        this.icon = icon;
    }





    // converts a list of Categories to a string
    public static String categoryListToString(List<Category> categories) {
        StringBuilder sb = new StringBuilder();
        for(Category category: categories)
            sb.append(category.getName()).append(", ");
        sb.delete(sb.length()-2, sb.length());
        return sb.toString();
    }




}
