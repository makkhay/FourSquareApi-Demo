package com.prakash.foursquare.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prakash.foursquare.Activity.VenueListActivity;
import com.prakash.foursquare.R;

import java.util.List;

import static com.prakash.foursquare.Activity.SelectCityActivity.EXTRA_CITY_NAME;


public class SelectCityAdapter extends RecyclerView.Adapter<SelectCityAdapter.ViewHolder> {

    private List<String> mData;
    private List<Integer> mImages;
    private LayoutInflater mInflater;
    private Context context;

    // data is passed into the constructor
    public SelectCityAdapter(Context context,List<String> data, List<Integer> images) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mImages= images;
        this.context=context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cardview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String city = mData.get(position);
        int picture = mImages.get(position);
        holder.myTextView.setText(city);
        holder.myImageView.setImageResource(picture);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView myImageView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.textView);
            myImageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            // open next screen and pass city name as an intent data
            Intent mIntent = new Intent(context, VenueListActivity.class);
            mIntent.putExtra(EXTRA_CITY_NAME, mData.get(getAdapterPosition()));
            context.startActivity(mIntent);

        }
    }

}

