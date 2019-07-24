package com.example.aziz.doordash.model.utilites;


/**
 * Created by Aziz on 6/1/2018.
 */
public class Constants {

    public static final String BASE_URL = "https://api.doordash.com";
    public static final String DETAILS_OF_RESTAURANT = "/v2/restaurant/{id}";
    public static final String LIST_OF_RESTAURANTS = "/v2/restaurant/?lat=37.422740&lng=-122.139956";
    //DataBase

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS restaurants";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "restaurants.db";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS restaurants(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, photo VARCHAR, price VARCHAR, category VARCHAR)";

}
