package com.example.aziz.doordash.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aziz.doordash.R;
import com.example.aziz.doordash.model.pojo.Restaurant;
import com.example.aziz.doordash.model.utilites.Constants;
import com.example.aziz.doordash.view.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Aziz on 6/1/2018.
 */
public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.Holder> {
    private List<Restaurant> mRestaurants;
    public RestaurantsAdapter(List<Restaurant> restaurants) {
        mRestaurants = restaurants;
    }

    public void addRestaurant(Restaurant restaurant) {
        mRestaurants.add(restaurant);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Restaurant currentRestaurant = mRestaurants.get(position);
        holder.mName.setText(currentRestaurant.mName);
        holder.mCategory.setText(currentRestaurant.mCategory);
        holder.mPrice.setText(Double.toString(currentRestaurant.mPrice/100));
        holder.mInstructions.setText(currentRestaurant.mInstructions);
        holder.mProductId.setText("" +currentRestaurant.mProductId);

        Picasso.with(holder.itemView.getContext()).load(currentRestaurant.mPhoto).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }



    protected class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mName, mCategory, mPrice, mInstructions,mProductId;
        private ImageView mImage;
        private Holder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.flowerImage);
            mName = (TextView) itemView.findViewById(R.id.restaurant_name);
            mCategory = (TextView) itemView.findViewById(R.id.restaurant_status);
            mPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
            mInstructions = (TextView) itemView.findViewById(R.id.restaurant_description);
            mProductId = (TextView) itemView.findViewById(R.id.id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("id",mProductId.getText().toString());
            context.startActivity(intent);
        }
    }


}
