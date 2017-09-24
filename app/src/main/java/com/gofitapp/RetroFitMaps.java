package com.gofitapp;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by navneet on 17/7/16.
 */
public interface RetroFitMaps {

    /*
     * Retrofit get annotation with our URL

     */
    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyB5HTIRI50JYUOmpef72nyZUpSyaES_FxI")
    Call<Example> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);

}
