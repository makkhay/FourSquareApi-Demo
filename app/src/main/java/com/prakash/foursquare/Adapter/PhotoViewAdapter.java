package com.prakash.foursquare.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.prakash.foursquare.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.prakash.foursquare.JSONmodel.PhotosModel.PhotoItem;

import static android.content.ContentValues.TAG;
import static com.prakash.foursquare.Activity.VenuePhotoActivity.VENUE_NAME;

/**
 * Adapter to load photos
 */
public class PhotoViewAdapter extends ArrayAdapter<PhotoItem> {

    private List<PhotoItem> photos;
    private Context context;

    public PhotoViewAdapter(Context context, int resource, List<PhotoItem> objects) {
        super(context, resource, objects);
        this.photos = objects;
        this.context = context;
    }


    @Override
    public int getCount() {
        return photos.size();
    }

    // This method indicates how each item or row of listview should look
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_photo, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.venue_imageView);
        PhotoItem photoItem = photos.get(position);
        String url = photoItem.getURLforGrid();
        Log.d(TAG, "getView: " + url);

        TextView venueNameTV = convertView.findViewById(R.id.venue_Tv);
        Intent intent = ((Activity) context).getIntent();
        final String venueName = intent.getStringExtra(VENUE_NAME);
        venueNameTV.setText(venueName);

        Picasso.with(this.context)
                .load(url)
                .placeholder(R.drawable.placeholder_image) // show this if no image received
                .error(R.drawable.error_img)           // show this when error occurs
                .into(imageView);

        return convertView;
    }
}
