package com.example.aziz.doordash.model.api;

import com.example.aziz.doordash.model.utilites.Constants;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Aziz on 6/1/2018.
 */
public class RestApiManager {
    private RestaurantsApi mRestaurantsApi;
    private RestaurantDetailsApi mRestaurantDetailsApi;

    public RestaurantsApi getRestaurantsApi() {

        if(mRestaurantsApi == null) {
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(String.class, new StringDesirializer());

            mRestaurantsApi = new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL)
                    .setConverter(new GsonConverter(gson.create()))
                    .build()
                    .create(RestaurantsApi.class);
        }
        return mRestaurantsApi;
    }

    public RestaurantDetailsApi getRestaurantDetailsApi() {

        if(mRestaurantDetailsApi == null) {
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(String.class, new StringDesirializer());

            mRestaurantDetailsApi = new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL)
                    .setConverter(new GsonConverter(gson.create()))
                    .build()
                    .create(RestaurantDetailsApi.class);
        }
        return mRestaurantDetailsApi;
    }
}
