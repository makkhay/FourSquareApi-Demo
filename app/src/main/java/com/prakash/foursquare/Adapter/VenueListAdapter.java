package com.prakash.foursquare.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;

import com.prakash.foursquare.Activity.VenuePhotoActivity;
import com.prakash.foursquare.JSONmodel.ExploreModel.Category;
import com.prakash.foursquare.JSONmodel.ExploreModel.Venue;
import com.prakash.foursquare.R;
import com.prakash.foursquare.Util.database.BookmarkedVenueDatabase;
import com.squareup.picasso.Picasso;

/**
 * Adapter to populate list of venues on the RecyclerView
 */

public class VenueListAdapter extends RecyclerView.Adapter<VenueHolder> {

    private final List<Venue> venueList;
    private Context context;
    private int itemResource;

    public VenueListAdapter(Context context, int itemResource, List<Venue> object) {

        //  Initialize our adapter
        this.venueList = object;
        this.context = context;
        this.itemResource = itemResource;

    }

    //  Override the onCreateViewHolder method
    @Override
    public VenueHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //  Inflate the view and return the new ViewHolder
        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.itemResource, parent, false);
        return new VenueHolder(context, view);
    }

    //  Override the onBindViewHolder method
    @Override
    public void onBindViewHolder(@NonNull VenueHolder holder, int position) {
        //  Use position to access the correct Bakery object
        Venue venue = this.venueList.get(position);

        //  Bind the Venue object to the holder
        holder.bindVenue(venue);
    }


    @Override
    public int getItemCount() {

        return this.venueList.size();
    }
}

class VenueHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = "VenueHolder";
    private final ImageView thumbnailImageView;
    private final TextView nameTextView;
    private final TextView locationTextView;
    private final TextView categoryTextView;
    private final ImageView bookmarkedTextView;
    private BookmarkedVenueDatabase database;



    private Venue venue;
    private Context context;

    public VenueHolder(Context context, View itemView) {

        super(itemView);

        //  Set the context
        this.context = context;

        //  Set up the UI widgets of the holder
        this.thumbnailImageView =  itemView.findViewById(R.id.thumbnail_imageview);
        this.nameTextView =  itemView.findViewById(R.id.name_textview);
        this.locationTextView = itemView.findViewById(R.id.location_textview);
        this.categoryTextView =  itemView.findViewById(R.id.category_textview);
        this.bookmarkedTextView = itemView.findViewById(R.id.bookmark_textview);

        database = new BookmarkedVenueDatabase(context);
        database.open();


        //  Set the "onClick" listener of the holder
        itemView.setOnClickListener(this);
    }




    public void bindVenue(Venue venue) {

        // Bind the data to the ViewHolder
        this.venue = venue;
        this.nameTextView.setText(venue.getName());
        this.locationTextView.setText(venue.getLocation().getAddress());

        if(database.getBookmarkedIDs().contains((venue.getId())))
           {
                    bookmarkedTextView.setImageResource(R.drawable.baseline_favorite_black_18dp);
                }
                else {
            bookmarkedTextView.setImageResource(R.drawable.baseline_favorite_border_black_18dp);


                }

        this.categoryTextView.setText(Category.categoryListToString(venue.getCategories()));


        // For venues, they don't come with any images so displaying the category thumbnail image
        String url = null;
        //url = venue.getPhotos().getGroups().get(0).getItems().get(0).getURLforThumbnail();
        try {
            url = venue.getCategories().get(0).getIcon().getURLforThumbnail();
            }
            catch (Exception e){
            Log.d(TAG, "Could not get picture URL for item "
                    + " , name: " + venue.getName().toString());
            }
            // if url is empty then show the generic image, else load picture using Picasso
        if(url == null) {

            thumbnailImageView.setImageResource(R.drawable.placeholder_image);
            }
            else {
            Picasso.with(this.context)
                    .load(url)
                    .placeholder(R.drawable.placeholder_image) // display this picture as a placeholder
                    .error(R.drawable.error_img)           // display this picture if error occurs
                    .into(thumbnailImageView);
            }

    }

    @Override
    public void onClick(View v) {

        // Handle the onClick event for the ViewHolder
        if (venue != null) {
            onVenueRowClick();
        }
    }

    /**
     * Open next screen when an item is clicked
     * and send venueId as an intent data
     */
    private void onVenueRowClick() {
        if(venue == null) return;
        Intent intent = new Intent(context, VenuePhotoActivity.class);
        intent.putExtra(VenuePhotoActivity.VENUE_NAME, venue.getName());
        intent.putExtra(VenuePhotoActivity.VENUE_ID, venue.getId());
        context.startActivity(intent);
    }
}



