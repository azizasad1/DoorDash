package com.example.aziz.doordash.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.aziz.doordash.R;
import com.example.aziz.doordash.controller.SQLiteController;
import com.example.aziz.doordash.model.adapter.RestaurantsAdapter;
import com.example.aziz.doordash.model.pojo.Restaurant;
import java.util.List;

/**
 * Created by Aziz on 6/1/2018.
 */
public class FavoritesRestaurantsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    ProgressBar loadingPB;
    private com.example.aziz.doordash.controller.SQLiteController SQLiteController;

    public FavoritesRestaurantsFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favorites_restaurants, container, false);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.list);
        loadingPB = (ProgressBar) rootView.findViewById(R.id.loadingPB);
        SQLiteController = new SQLiteController(getActivity());
        configViews();
        setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void configViews() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SQLiteController.getData();
        List<Restaurant> mRestaurantList = SQLiteController.restaurantObj;
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        RestaurantsAdapter mRestaurantsAdapter = new RestaurantsAdapter(mRestaurantList);
        mRecyclerView.setAdapter(mRestaurantsAdapter);
    }


}

