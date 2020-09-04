package com.android.gaspricerd.reposiroty.datasource;

import android.provider.BaseColumns;

/**
 * Contract class for the DB.
 */
public class FuelContract {
    //To prevent instantiate this class,
    private FuelContract() {
    }

    /**
     * Inner class with the define structure.
     */
    public static final class FuelEntry implements BaseColumns {
        public static final String TABLE_NAME = "fuel";
        public static final String COLUMN_NAME_PUB_DATE = "publication_date";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PRICE = "price";
    }
}
