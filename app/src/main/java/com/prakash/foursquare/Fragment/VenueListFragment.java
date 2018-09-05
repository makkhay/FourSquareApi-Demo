package com.prakash.foursquare.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.prakash.foursquare.R;
import com.prakash.foursquare.Activity.SelectCityActivity;
import com.prakash.foursquare.Adapter.VenueListAdapter;
import com.prakash.foursquare.Activity.VenuePhotoActivity;

import java.util.List;
import com.prakash.foursquare.JSONmodel.ExploreModel.ExploreBody;
import com.prakash.foursquare.JSONmodel.ExploreModel.Venue;
import com.prakash.foursquare.JSONmodel.ExploreModel.Item;
import com.prakash.foursquare.Util.VerticalSpaceItemDecorator;
import com.prakash.foursquare.FoursquareREST.FoursquareClient;
import com.prakash.foursquare.FoursquareREST.FoursquareInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Fragment to show the list of venues for a city
 */
public class VenueListFragment extends Fragment{

    private static final String TAG = "VenuListFrag";
    private RecyclerView venueListView;
    private String cityName;
    private List<Venue> venues;
    private ProgressBar progressBar;

    public VenueListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        View view;
        if (isNetworkAvailable()) {
            view = inflater.inflate(R.layout.fragment_venue_list, container, false);
            Intent intent = getActivity().getIntent();
            cityName = intent.getStringExtra(SelectCityActivity.EXTRA_CITY_NAME);

            getVenuesFromFoursquare(cityName, null, 5, 1, FoursquareClient.API_DATE);
            progressBar = view.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

        }
        else {
            view = inflater.inflate(R.layout.no_network_layout, container, false);
        }
        return view;
    }


    // To dynamically show the bookmark icon when screen is changed
    @Override
    public void onStart() {
        super.onStart();
        showVenuesOnListview();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    /**
     * Call to Foursqaure API get a list of all the venues
     *
     * @param  city  The target city
     * @param  category  The target category
     * @param  limit  Max number of returned result
     * @param  numPhotos  The date of the api
     * @param  date  Number of photos to return
     */
    private void getVenuesFromFoursquare(String city, String category, int limit, int numPhotos,
                                         String date) {

        Call<ExploreBody> call;
        FoursquareInterface apiService =
                FoursquareClient.getClient().create(FoursquareInterface.class);

        if(category == null) {  //if category is not specified
            call = apiService.getVenues(FoursquareClient.CLIENT_ID,
                    FoursquareClient.CLIENT_SECRET,
                    city,
                    Integer.toString(limit),
                    Integer.toString(numPhotos),
                    date);
        }
        else {
            call = apiService.getVenuesWithCategory(FoursquareClient.CLIENT_ID,
                    FoursquareClient.CLIENT_SECRET,
                    city,
                    category,
                    Integer.toString(limit),
                    Integer.toString(numPhotos),
                    date);
        }

        call.enqueue(new Callback<ExploreBody>() {

            @Override
            public void onResponse(Call<ExploreBody> call, Response<ExploreBody> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "API call was successful. ");
                    List<Item> items = response.body().getResponse().getGroups().get(0).getItems();
                    venues = Item.ItemsToVenues(items);
                    Log.d(TAG, "onResponseee: " + call.request().url().toString());
                    progressBar.setVisibility(View.GONE);

                }
                else {
                    Log.d(TAG, "API call was not successful. Error: " + response.errorBody());
                }
                showVenuesOnListview();
            }

            @Override
            public void onFailure(Call<ExploreBody> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                showVenuesOnListview();
            }
        });
    }


    /**
     * Populates fragment's recycleview with the data received from foursquare client api call
     *
     * @return  void
     */
    private void showVenuesOnListview() {
        if(venues == null || venues.isEmpty()) return;

        // Get the view
        View view = getView();
        venueListView =  view.findViewById(R.id.venue_listView);

        // connecting the adapter to recyclerview
        VenueListAdapter venueListAdapter = new VenueListAdapter(getActivity(), R.layout.item_venue_list, venues);

        //  Initialize ItemAnimator, LayoutManager and ItemDecorators
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        int verticalSpacing = 5;
        VerticalSpaceItemDecorator itemDecorator = new VerticalSpaceItemDecorator(verticalSpacing);

        //  Set the LayoutManager
        venueListView.setLayoutManager(layoutManager);

        //  Set the ItemDecorators
        venueListView.addItemDecoration(itemDecorator);
        venueListView.setAdapter(venueListAdapter);
        venueListView.setHasFixedSize(true);
    }
    /**
     * Check if there is an internet connection
     *
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
