package com.android.gaspricerd.model.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FuelDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "";
    public static final int DATABASE_VERSION = 1;

    public FuelDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FuelContract.FuelEntry.TABLE_NAME + "(" +
                FuelContract.FuelEntry._ID + " PRIMARY KEY AUTOINCREMENT, " +
                FuelContract.FuelEntry.COLUMN_NAME_TITLE + " TEXT NOT NULL, " +
                FuelContract.FuelEntry.COLUMN_NAME_PRICE + " NUMERIC(4,2) NOT NULL, " +
                FuelContract.FuelEntry.COLUMN_NAME_PUB_DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP UNIQUE)";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +
                FuelContract.FuelEntry.TABLE_NAME;
        // In a production, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        db.execSQL(SQL_DELETE_ENTRIES);

        onCreate(db);
    }
}
