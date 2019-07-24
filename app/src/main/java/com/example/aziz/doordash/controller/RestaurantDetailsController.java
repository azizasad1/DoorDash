package com.example.aziz.doordash.controller;

import android.util.Log;

import com.example.aziz.doordash.model.api.RestApiManager;
import com.example.aziz.doordash.model.pojo.RestaurantDetails;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Aziz on 6/1/2018.
 */
public class RestaurantDetailsController {
    private static final String TAG = RestaurantDetailsController.class.getSimpleName();
    private RestaurantDetailsCallbackListener mListener;
    private RestApiManager mApiManager;
    private String id ;

    public RestaurantDetailsController(RestaurantDetailsCallbackListener listener) {
        mListener = listener;
        mApiManager = new RestApiManager();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void startFetching() {
        mApiManager.getRestaurantDetailsApi().getRestaurantDetails( id , new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "JSON :: " + s);

                try {
                    JSONObject object = new JSONObject(s);

                        RestaurantDetails restaurantDetails = new RestaurantDetails.Builder()
                                .setStatus(object.getString("status"))
                                .setDeliveryFee(object.getDouble("delivery_fee"))
                                .setDescription(object.getString("description"))
                                .setPhoto(object.getString("cover_img_url"))
                                .setName(object.getString("name"))
                                .setProductId(object.getInt("id"))
                                .build();
                        mListener.onFetchProgress(restaurantDetails);

                } catch (JSONException e) {
                    mListener.onFetchFailed();
                }


                mListener.onFetchComplete( );
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                mListener.onFetchComplete( );
            }
        });
    }

    public interface RestaurantDetailsCallbackListener {

        void onFetchProgress(RestaurantDetails restaurant);
        void onFetchComplete();
        void onFetchFailed();
    }
}

