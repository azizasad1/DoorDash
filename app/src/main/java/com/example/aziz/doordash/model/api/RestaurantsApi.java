package com.example.aziz.doordash.model.api;


import com.example.aziz.doordash.model.utilites.Constants;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Aziz on 6/1/2018.
 */
public interface RestaurantsApi {

    @GET(Constants.LIST_OF_RESTAURANTS)
    void getRestaurants(Callback<String> restaurants);
}
