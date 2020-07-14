package com.android.gaspricerd.model;

/**
 * POJO Class
 */
public class Combustible {
    private String mTitle;
    private int mVariationImageId;
    private double mCurrentPrice;
    private double mLastPrice;

    public Combustible(){
        super();
    }

    public Combustible(String title, int imageId, double currentPrice, double lastPrice){
        mTitle = title;
        mVariationImageId = imageId;
        mCurrentPrice = currentPrice;
        mLastPrice = lastPrice;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getImageViewId() {
        return mVariationImageId;
    }

    public void setImageView(int imageViewId) {
        this.mVariationImageId = imageViewId;
    }

    public double getCurrentPrice() {
        return mCurrentPrice;
    }

    public double getLastPrice() {
        return mLastPrice;
    }
}
