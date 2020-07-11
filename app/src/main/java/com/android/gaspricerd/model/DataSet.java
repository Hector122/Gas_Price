package com.android.gaspricerd.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataSet {
    @SerializedName("S. Actual")
    ArrayList<Information> weekOne;
    @SerializedName("S. Anterior")
    ArrayList<Information> weekTwo;
    @SerializedName("3ra Semana")
    ArrayList<Information> weekThree;

    public ArrayList<Information> getWeekOne() {
        return weekOne;
    }

    public ArrayList<Information> getWeekTwo() {
        return weekTwo;
    }

    public ArrayList<Information> getWeekThree() {
        return weekThree;
    }
}
