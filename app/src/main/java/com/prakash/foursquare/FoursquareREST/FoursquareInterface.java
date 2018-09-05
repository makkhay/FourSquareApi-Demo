package com.prakash.foursquare.FoursquareREST;

import com.prakash.foursquare.JSONmodel.ExploreModel.ExploreBody;
import com.prakash.foursquare.JSONmodel.PhotosModel.PhotosBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Defining the end points using Retrofit annotations
 */
public interface FoursquareInterface {

    // Get venues
    @GET("venues/explore")
    Call<ExploreBody> getVenues(@Query("client_id") String client_id,
                                @Query("client_secret") String client_secret,
                                @Query("near") String city,
                                @Query("limit") String limit,
                                @Query("venuePhotos") String count,
                                @Query("v") String date);

    // Get venues using the category
    @GET("venues/explore")
    Call<ExploreBody> getVenuesWithCategory(@Query("client_id") String client_id,
                                            @Query("client_secret") String client_secret,
                                            @Query("near") String city,
                                            @Query("section") String category,
                                            @Query("limit") String limit,
                                            @Query("venuePhotos") String count,
                                            @Query("v") String date);

    // Get photos given a venue ID
    @GET("venues/{venue_id}/photos")
    Call<PhotosBody> getPictures(@Path("venue_id") String venue_id,
                                 @Query("client_id") String client_id,
                                 @Query("client_secret") String client_secret,
                                 @Query("limit") String limit,
                                 @Query("v") String date);


}
