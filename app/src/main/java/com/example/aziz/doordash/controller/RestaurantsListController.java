package com.example.aziz.doordash.controller;

import android.util.Log;

import com.example.aziz.doordash.model.api.RestApiManager;
import com.example.aziz.doordash.model.pojo.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Aziz on 6/1/2018.
 */
public class RestaurantsListController {
    private static final String TAG = RestaurantsListController.class.getSimpleName();
    private RestaurantsCallbackListener mListener;
    private RestApiManager mApiManager;

    public RestaurantsListController(RestaurantsCallbackListener listener) {
        mListener = listener;
        mApiManager = new RestApiManager();
    }

    public void startFetching() {
        mApiManager.getRestaurantsApi().getRestaurants(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "JSON :: " + s);

                try {
                    JSONArray array = new JSONArray(s);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        Restaurant restaurant = new Restaurant.Builder()
                                .setStatus(object.getString("status"))
                                .setDeliveryFee(object.getDouble("delivery_fee"))
                                .setDescription(object.getString("description"))
                                .setPhoto(object.getString("cover_img_url"))
                                .setName(object.getString("name"))
                                .setProductId(object.getInt("id"))
                                .build();

                        mListener.onFetchProgress(restaurant);

                    }

                } catch (JSONException e) {
                    mListener.onFetchFailed();
                }


                mListener.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                mListener.onFetchComplete();
            }
        });
    }

    public interface RestaurantsCallbackListener {

        void onFetchProgress(Restaurant restaurant);
        void onFetchComplete();
        void onFetchFailed();
    }
}

