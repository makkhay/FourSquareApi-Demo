package com.prakash.foursquare.FoursquareREST;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit Builder class to send network requests to Foursquare API
 */
public class FoursquareClient {

    public static final String CLIENT_ID = "25E2SIYUC40ELD31WVBQIUJGZPGGQAGDTGGA5MCNGCAKG41N";
    public static final String CLIENT_SECRET = "CQYBY5DA4ZK3IVJOEQHEA0VSCKKJFSJPHCSRCGYC5DEDWB3N";
    public static final String BASE_URL = "https://api.foursquare.com/v2/";
    public static final String API_DATE = "20180901";
    private static Retrofit retrofit = null;



    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
