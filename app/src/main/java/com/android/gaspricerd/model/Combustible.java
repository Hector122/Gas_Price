package com.android.gaspricerd.model;

import android.widget.ImageView;

/**
 * POJO Class
 */
public class Combustible {
    private String mTitle;
    private ImageView mVariationImage;
    private double mCurrentPrice;
    private double mLastPrice;


    public String getTitle() {
        return mTitle;
    }

    public ImageView getImageView() {
        return mVariationImage;
    }

    public void setImageView(ImageView imageView) {
        this.mVariationImage = imageView;

    }

    public double getCurrentPrice() {
        return mCurrentPrice;
    }

    public double getLastPrice() {
        return mLastPrice;
    }
}
