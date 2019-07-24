package com.example.aziz.doordash.model.pojo;

/**
 * Created by Aziz on 6/1/2018.
 */
public class Restaurant {

    public String mCategory, mInstructions, mPhoto, mName;
    public double mPrice;
    public int mProductId;

        private Restaurant(Builder builder){
            mCategory = builder.mCategory;
            mInstructions = builder.mInstructions;
            mPhoto = builder.mPhoto;
            mName = builder.mName;
            mPrice = builder.mPrice;
            mProductId = builder.mProductId;
        }

public static class Builder {

    private String mCategory, mInstructions, mPhoto, mName;
    private double mPrice;
    private int mProductId;

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
        mProductId = productId;
        return Builder.this;
    }

    public Restaurant build() {
        return new Restaurant(Builder.this);
    }
}
}

