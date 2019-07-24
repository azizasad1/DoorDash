package com.example.aziz.doordash.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aziz.doordash.R;
import com.example.aziz.doordash.controller.RestaurantDetailsController;
import com.example.aziz.doordash.controller.SQLiteController;
import com.example.aziz.doordash.model.pojo.RestaurantDetails;
import com.squareup.picasso.Picasso;

/**
 * Created by Aziz on 6/1/2018.
 */
public class DetailsActivity extends AppCompatActivity implements RestaurantDetailsController.RestaurantDetailsCallbackListener, View.OnClickListener {
    LinearLayout loadingPB;
    private boolean removeFlag = true;
    private ImageView restaurantPhoto = null;
    private TextView restaurantName = null;
    private TextView restaurantCategory = null;
    private TextView restaurantPrice = null;
    private Button addToFave;
    private double RPrice;
    private String RName, RCategory, RPhoto;

    private RestaurantDetails restaurantObj;
    private com.example.aziz.doordash.controller.SQLiteController SQLiteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        RestaurantDetailsController mRestaurantDetailsController = new RestaurantDetailsController(DetailsActivity.this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            mRestaurantDetailsController.setId(getIntent().getExtras().getString("id"));
        }
        mRestaurantDetailsController.startFetching();
        setReferences();
    }

    private void setReferences() {
        addToFave = (Button) findViewById(R.id.addToFavB);
        loadingPB = (LinearLayout) findViewById(R.id.loadingPB);
        restaurantPhoto = (ImageView) findViewById(R.id.restaurantPhoto);
        restaurantName = (TextView) findViewById(R.id.restaurantName);
        restaurantPrice = (TextView) findViewById(R.id.restaurantPrice);
        restaurantCategory = (TextView) findViewById(R.id.restaurantCategory);
        addToFave.setOnClickListener(this);
        loadingPB.setVisibility(View.VISIBLE);
        SQLiteController = new SQLiteController(this);
    }

    //Add the restaurant to your favourite list OR remove it from the list if it is exit
    protected void insertIntoDB() {

        if(SQLiteController.isExist(restaurantObj.restaurantId))
        {
            SQLiteController.DeleteData(restaurantObj);
            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
        }else {
            SQLiteController.insertData(restaurantObj);
            Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    //Control the button text
    private void setUserData() {
        restaurantName.setText(RName);
        restaurantPrice.setText(Double.toString(RPrice));
        restaurantCategory.setText(RCategory);
        Picasso.with(this)
                .load(RPhoto)
                .into(restaurantPhoto);
        if(SQLiteController.isExist(restaurantObj.restaurantId))
        {
            removeFlag = true;
            addToFave.setText(R.string.remove_from_favorites);
        }else {
            removeFlag = false;
            addToFave.setText(R.string.add_to_favorites);
        }
    }

    @Override
    public void onFetchProgress(RestaurantDetails restaurant) {
        RPrice = restaurant.restaurantPrice / 100;
        RName = restaurant.restaurantName;
        RCategory = restaurant.restaurantCategory;
        RPhoto = restaurant.restaurantPhoto;

        restaurantObj = restaurant;

        setUserData();
    }


    @Override
    public void onFetchComplete() {
        loadingPB.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFetchFailed() {
        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v == addToFave) {
            if (removeFlag){
                addToFave.setText(R.string.add_to_favorites);
                removeFlag = false;
            }else if (!removeFlag){
                addToFave.setText(R.string.remove_from_favorites);
                removeFlag = true;
            }
            insertIntoDB();
        }
    }
}
