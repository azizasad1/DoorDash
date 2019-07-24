package com.example.aziz.doordash.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aziz.doordash.R;
import com.example.aziz.doordash.controller.RestaurantsListController;
import com.example.aziz.doordash.model.adapter.RestaurantsAdapter;
import com.example.aziz.doordash.model.pojo.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aziz on 6/1/2018.
 */
public class RestaurantsListFragment extends Fragment implements RestaurantsListController.RestaurantsCallbackListener {

    private RecyclerView mRecyclerView;
    ProgressBar loadingPB;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Restaurant> mRestaurantList = new ArrayList<>();
    private RestaurantsAdapter mRestaurantsAdapter;
    private RestaurantsListController mRestaurantsListController;

    public RestaurantsListFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(),1);
        View rootView = inflater.inflate(R.layout.fragment_restaurants_list, container, false);
        mRestaurantsListController = new RestaurantsListController(RestaurantsListFragment.this);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.list);
        mSwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe);
        loadingPB = (ProgressBar) rootView.findViewById(R.id.loadingPB);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        configViews();
        if (savedInstanceState==null) {
            mRestaurantsListController.startFetching();
            loadingPB.setVisibility(View.VISIBLE);
        }
        setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("checked","onRequestPermissionsResult");
    }

    private void configViews() {

        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRestaurantsAdapter = new RestaurantsAdapter(mRestaurantList);
        mRecyclerView.setAdapter(mRestaurantsAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRestaurantsListController.startFetching();
            }
        });

    }


    @Override
    public void onFetchProgress(Restaurant restaurant) {
        mRestaurantsAdapter.addRestaurant(restaurant);
    }


    @Override
    public void onFetchComplete() {
        loadingPB.setVisibility(View.INVISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchFailed() {
        loadingPB.setVisibility(View.INVISIBLE);
        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
    }
}
