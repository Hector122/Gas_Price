package com.android.gaspricerd.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataSetHolder {
    @SerializedName("categories")
    private ArrayList<String> categories;
    @SerializedName("dataset")
    private DataSet dataSet;

    public ArrayList<String>  getCategories() { return categories; }
    public DataSet getDataSet() { return dataSet; }
}
