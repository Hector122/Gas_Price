package com.android.gaspricerd.model.datasource;

import android.provider.BaseColumns;

public class FuelContract {
    //To prevent instantiate this class,
    private FuelContract() {
    }

    public static final class FuelEntry implements BaseColumns {

        public static final String TABLE_NAME = "fuel";
        public static final String COLUMN_NAME_PUB_DATE = "publication_date";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PRICE = "price";
    }
}
