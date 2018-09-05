package com.prakash.foursquare.Fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.prakash.foursquare.Adapter.PhotoViewAdapter;
import com.prakash.foursquare.R;
import com.prakash.foursquare.Activity.VenuePhotoActivity;

import java.util.List;
import com.prakash.foursquare.JSONmodel.PhotosModel.PhotosBody;
import com.prakash.foursquare.JSONmodel.PhotosModel.PhotoItem;
import com.prakash.foursquare.FoursquareREST.FoursquareClient;
import com.prakash.foursquare.FoursquareREST.FoursquareInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Fragment to display a picture.
 */
public class VenuePhotoFragment extends Fragment {

    private static final String TAG = "VenueFrag";
    private String venueId;
    private ListView photoView;
    private ProgressBar progressBar;

    public VenuePhotoFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view;
        if(isNetworkAvailable()) {

            view = inflater.inflate(R.layout.fragment_venue_photo, container, false);
            venueId = getActivity().getIntent().getStringExtra(VenuePhotoActivity.VENUE_ID);
            getPhotosFromFoursquare(venueId, 1, FoursquareClient.API_DATE);

            progressBar = view.findViewById(R.id.progressBarVenue);
            progressBar.setVisibility(View.VISIBLE);
        }
        else {

            view = inflater.inflate(R.layout.no_network_layout, container, false);
        }
        return view;
    }


    /**
     * Call to Foursqaure API get pictures for a venue
     *
     * @param  venueId  The target city
     * @param  limit  Max number of returned result
     * @param  date  Number of photos to return
     */
    private void getPhotosFromFoursquare(String venueId, int limit, String date) {
        Log.d(TAG, "Getting pictures for venue ID: " + venueId);

        Call<PhotosBody> call;
        FoursquareInterface apiService = FoursquareClient.getClient().create(FoursquareInterface.class);
        call = apiService.getPictures(venueId, FoursquareClient.CLIENT_ID,
                FoursquareClient.CLIENT_SECRET, Integer.toString(limit), date);
        Log.d(TAG, "getPhotosFromFoursquare: " + call.toString());

        call.enqueue(new Callback<PhotosBody>() {

            List<PhotoItem> items;
            @Override
            public void onResponse(Call<PhotosBody> call, Response<PhotosBody> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "API call for pictures was successful. Got " +
                            response.body().getResponse().getPhotos().getCount() + " pictures");
                    items = response.body().getResponse().getPhotos().getItems();


                    Log.d(TAG, "onResponseee: " + call.request().url().toString());
                    progressBar.setVisibility(View.GONE);


                }
                else {
                    Log.d(TAG, "API call for pictures was not successful. Error: " + response.errorBody());
                }
                showPicturesOnGrid(items);
            }

            @Override
            public void onFailure(Call<PhotosBody> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                showPicturesOnGrid(items);
            }
        });
    }


    /**
     * Display the image received from api
     * @param photos List of photos
     * @return  void
     */
    private void showPicturesOnGrid(List<PhotoItem> photos) {
        View view = getView();
        if(view != null && photos != null) {
            photoView =  view.findViewById(R.id.gridView);

            PhotoViewAdapter photoGridAdapter = new PhotoViewAdapter(getActivity(), R.layout.item_photo, photos);
            photoView.setAdapter(photoGridAdapter);
        }
    }


    /**
     * Check if there is internet connection
     * @return  boolean
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
