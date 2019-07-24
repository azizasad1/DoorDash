package com.example.aziz.doordash.model.pojo;

/**
 * Created by Aziz on 6/1/2018.
 */
public class RestaurantDetails {
    public String restaurantCategory, restaurantInstruction, restaurantPhoto, restaurantName;
    public double restaurantPrice;
    public int restaurantId;

    private RestaurantDetails(Builder builder){
        restaurantCategory = builder.mCategory;
        restaurantInstruction = builder.mInstructions;
        restaurantPhoto = builder.mPhoto;
        restaurantName = builder.mName;
        restaurantPrice = builder.mPrice;
        restaurantId = builder.mRestaurantId;
    }

    public static class Builder {

        private String mCategory, mInstructions, mPhoto, mName;
        private double mPrice;
        private int mRestaurantId;

        public Builder setStatus(String category) {
            mCategory = category;
            return Builder.this;
        }

        public Builder setDescription(String instructions) {
            mInstructions = instructions;
            return Builder.this;
        }

        public Builder setPhoto(String photo) {
            mPhoto = photo;
            return Builder.this;
        }

        public Builder setName(String name) {
            mName = name;
            return Builder.this;
        }

        public Builder setDeliveryFee(double price) {
            mPrice = price;
            return Builder.this;
        }

        public Builder setProductId(int productId) {
            mRestaurantId = productId;
            return Builder.this;
        }

        public RestaurantDetails build() {
            return new RestaurantDetails(Builder.this);
        }
    }
}
