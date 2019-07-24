package com.example.aziz.doordash.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aziz.doordash.model.pojo.Restaurant;
import com.example.aziz.doordash.model.pojo.RestaurantDetails;

import java.util.ArrayList;
import java.util.List;

import static com.example.aziz.doordash.model.utilites.Constants.DATABASE_NAME;
import static com.example.aziz.doordash.model.utilites.Constants.DATABASE_VERSION;
import static com.example.aziz.doordash.model.utilites.Constants.SQL_CREATE_ENTRIES;
import static com.example.aziz.doordash.model.utilites.Constants.SQL_DELETE_ENTRIES;

/**
 * Created by Aziz on 6/1/2018.
 */
public class SQLiteController extends SQLiteOpenHelper {

    public List<Restaurant> restaurantObj;

    // Define a projection that specifies which columns from the database
   private String[] projection = {"id", "name", "photo", "price", "category"};


    public SQLiteController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


    public void insertData(RestaurantDetails restaurant) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("id", restaurant.restaurantId);
        values.put("name", restaurant.restaurantName);
        values.put("photo", restaurant.restaurantPhoto);
        values.put("price", restaurant.restaurantPrice);
        values.put("category", restaurant.restaurantCategory);

// Insert the new row, returning the primary key value of the new row
        db.insert("restaurants", null, values);
        db.close();
    }


    public List<Restaurant> getData() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query("restaurants", projection, null, null, null, null, null, null);

        List<Restaurant> restaurants = new ArrayList<>();
        while (cursor.moveToNext()) {
            Restaurant.Builder builder = new Restaurant.Builder();
            builder.setProductId(cursor.getInt(
                    cursor.getColumnIndexOrThrow("id")));
            builder.setName(cursor.getString(
                    cursor.getColumnIndexOrThrow("name")));
            builder.setPhoto(cursor.getString(
                    cursor.getColumnIndexOrThrow("photo")));
            builder.setDeliveryFee(cursor.getDouble(
                    cursor.getColumnIndexOrThrow("price")));
            builder.setDescription(cursor.getString(
                    cursor.getColumnIndexOrThrow("category")));

            Restaurant restaurantDetails = builder.build();

            restaurants.add(restaurantDetails);

        }
        cursor.close();
        db.close();
        return restaurantObj =  restaurants;

    }

    public Boolean isExist(int id) {
        RestaurantDetails restaurantDetails = null;

        SQLiteDatabase db = getReadableDatabase();

        // Filter results WHERE "id" = 'id'
        String selection = "id = ?";
        String[] selectionArgs = {Integer.toString(id)};

        Cursor cursor = db.query(
                "restaurants", projection, selection, selectionArgs, null, null, null, null);

        while (cursor.moveToNext()) {
            RestaurantDetails.Builder builder = new RestaurantDetails.Builder();
            builder.setProductId(cursor.getInt(
                    cursor.getColumnIndexOrThrow("id")));
            builder.setName(cursor.getString(
                    cursor.getColumnIndexOrThrow("name")));
            builder.setPhoto(cursor.getString(
                    cursor.getColumnIndexOrThrow("photo")));
            builder.setDeliveryFee(cursor.getDouble(
                    cursor.getColumnIndexOrThrow("price")));
            builder.setDescription(cursor.getString(
                    cursor.getColumnIndexOrThrow("category")));

            restaurantDetails = builder.build();
        }


        cursor.close();

        db.close();

        return restaurantDetails != null ? true : false;

    }

    public void DeleteData(RestaurantDetails restaurant) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "DELETE FROM restaurants WHERE id=" + restaurant.restaurantId + ";";
         db.execSQL(sql);
        db.close();
    }

}
