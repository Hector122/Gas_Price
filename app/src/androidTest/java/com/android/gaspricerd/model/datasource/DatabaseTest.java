package com.android.gaspricerd.model.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;


/**
 * Instrumentation test, which will execute on an android device.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    // Context use to perform db operation.
    private final Context mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private final Class<FuelDbHelper> mDbHelperClass = FuelDbHelper.class;

    @Before
    public void setUp() {
        deleteTestingDatabase();
    }

    /**
     * This method tests that our database contains all of the tables that we think it should
     * contain.
     * @throws Exception in case the constructor hasn't been implemented yet
     */
    @Test
    public void create_database_test() throws Exception {
        // User reflection to try to run the correct constructor
        SQLiteOpenHelper dbHelper =
                mDbHelperClass.getConstructor(Context.class).newInstance(mContext);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        String databaseIsNotOpen = "the database should be open and isn't";
        Assert.assertTrue(databaseIsNotOpen, sqLiteDatabase.isOpen());

        // Contain the names of each table
        Cursor tableNameCursor = sqLiteDatabase.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='" +
                        FuelContract.FuelEntry.TABLE_NAME + "'", null);

        //if .moveToFirst return false from this query, database was not create properly.
        String errorInCreatingDatabase = "Error: Database has not been created correctly";
        Assert.assertTrue(errorInCreatingDatabase, tableNameCursor.moveToFirst());

        // If one of this fail database not contains the expected table.
        Assert.assertEquals("Database was created without the expected tables",
                FuelContract.FuelEntry.TABLE_NAME, tableNameCursor.getString(0));

        tableNameCursor.close();
    }

    /**
     * This method tests inserting a single record into an empty table from a brand new database.
     * The purpose is to test that the database is working as expected
     * @throws Exception in case the constructor hasn't been implemented yet
     */
    @Test
    public void insert_single_record_test() throws Exception {
        // User reflection to try to run the correct constructor
        SQLiteOpenHelper dbHelper =
                mDbHelperClass.getConstructor(Context.class).newInstance(mContext);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        // Insert Content into database and get first row Id
        long firsRowId = sqLiteDatabase.insert(FuelContract.FuelEntry.TABLE_NAME,
                null, getTestValues());

        Assert.assertNotEquals("Unable to inset into the database", -1, firsRowId);

        //Query the database and receive a Cursor
        Cursor cursor = sqLiteDatabase.query( FuelContract.FuelEntry.TABLE_NAME, null,
                null, null, null, null, null);

        Assert.assertTrue("Error: No records returned from database query",
                cursor.moveToFirst());

        //Close cursor
        cursor.close();;
        dbHelper.close();
    }


    /**
     * Tests to ensure that inserts into your database results in automatically
     * incrementing row IDs.
     * @throws Exception in case the constructor hasn't been implemented yet
     */
    //@Test
    public void autoincrement_test() throws Exception{
        /* First, let's ensure we have some values in our table initially */
        insert_single_record_test();

        /* Use reflection to try to run the correct constructor whenever implemented */
        SQLiteOpenHelper dbHelper =
                (SQLiteOpenHelper) mDbHelperClass.getConstructor(Context.class).newInstance(mContext);

        /* Use WaitlistDbHelper to get access to a writable database */
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        /* Insert ContentValues into database and get first row ID back */
        long firstRowId = database.insert(FuelContract.FuelEntry.TABLE_NAME,
                null, getTestValues());

        /* Insert ContentValues into database and get another row ID back */
        //TODO: get date  and decrease by a week.
        long secondRowId = database.insert(FuelContract.FuelEntry.TABLE_NAME,
                null, getTestValues());

        assertEquals("ID Autoincrement test failed!",
                firstRowId + 1, secondRowId);
    }

    /**
     *  Create the object to insert a single row.
     * @return return a ContentValues to insert in the db.
     */
    ContentValues getTestValues(){
        ContentValues testValues = new ContentValues();
        testValues.put(FuelContract.FuelEntry.COLUMN_NAME_TITLE, "Gasolina Premium");
        testValues.put(FuelContract.FuelEntry.COLUMN_NAME_PRICE, 204.0999999);
        testValues.put(FuelContract.FuelEntry.COLUMN_NAME_PUB_DATE, "Viernes, 24 de Julio de 2020 11:22:00 - 0400");

        return testValues;
    }

    /**
     * Delete the entire database.
     */
    void deleteTestingDatabase() {
        try {
            Field field = mDbHelperClass.getDeclaredField("DATABASE_NAME");
            field.setAccessible(true);
            mContext.deleteDatabase((String) field.get(null));

        } catch (Exception exception) {
            Assert.fail(exception.getMessage());
        }
    }
}
