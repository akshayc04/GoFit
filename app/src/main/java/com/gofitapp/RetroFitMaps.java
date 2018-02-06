package com.gofitapp;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RetroFitMaps {

    /*
     * Retrofit get annotation with our URL

     */
    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyB5HTIRI50JYUOmpef72nyZUpSyaES_FxI")
    Call<Example> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);

}
