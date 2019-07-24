package com.example.aziz.doordash.model.api;


import com.example.aziz.doordash.model.utilites.Constants;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Aziz on 6/1/2018.
 */
public interface RestaurantDetailsApi {
    @GET(Constants.DETAILS_OF_RESTAURANT )
    void getRestaurantDetails(@Path("id") String id, Callback<String> RestaurantDetails);
}
