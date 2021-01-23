package com.android.gaspricerd.prices;

import android.app.Application;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.gaspricerd.model.Combustible;
import com.android.gaspricerd.model.DataSetHolder;
import com.android.gaspricerd.model.Information;
import com.android.gaspricerd.reposiroty.datasource.FuelContract;
import com.android.gaspricerd.reposiroty.datasource.FuelDbHelper;
import com.android.gaspricerd.reposiroty.server.RetrofitImplementation;
import com.android.gaspricerd.utils.Utils;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * View model class that store the combustibles list prices.
 */
public class PriceViewModel extends AndroidViewModel {
    private MutableLiveData<List<Combustible>> combustibles;

    public PriceViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Get the combustibles information need.
     *
     * @return LiveData<List < Combustible>>
     */
    public LiveData<List<Combustible>> getCombustibles() {
        if (combustibles == null) {
            combustibles = new MutableLiveData<>();

            loadCombustiblesPrices();
        }
        return combustibles;
    }

    private void loadCombustiblesPrices() {
        // Do an asynchronous operation to fetch users.


        //Get data form data base.
        // Get initial data.
        RetrofitImplementation.OnResponseCallBack callBack = (ResponseBody body) -> {
            insertInformationIntoDB(body);
        };

        RetrofitImplementation retrofitImp = new RetrofitImplementation(callBack);
        retrofitImp.getLastWeekJSON();

        //TODO: delete after read from repo.
//        private ArrayList<Combustible> getDataDummy() {
//            ArrayList<Combustible> combustibles = new ArrayList<>();
//
//            for (int i = 0; i < 15; i++) {
//                Combustible combustible = new Combustible(
//                        "Gas Natural Vehicular (GNV)",
//                        R.drawable.ic_down, 137.70, 103.00);
//
//                combustibles.add(combustible);
//            }
//
//            return combustibles;
//        }
    }


    private void insertInformationIntoDB(ResponseBody body) {
        String json = Utils.readStream(body.byteStream());
        DataSetHolder dataSetHolder = new Gson().fromJson(json, DataSetHolder.class);
        dataSetHolder.getCategories();

        SQLiteOpenHelper dbHelper = new FuelDbHelper(getApplication().getApplicationContext());
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        for (Information info : dataSetHolder.getDataSet().getWeekOne()){

            ContentValues testValues = new ContentValues();
            testValues.put(FuelContract.FuelEntry.COLUMN_NAME_TITLE,"Gasolina Premium");
            testValues.put(FuelContract.FuelEntry.COLUMN_NAME_PRICE, 204.0999999);
            testValues.put(FuelContract.FuelEntry.COLUMN_NAME_PUB_DATE, "new data this week  Viernes, 24 de Julio de 2020 11:22:00 - 0400");

            //sqLiteDatabase.insert(FuelContract.FuelEntry.TABLE_NAME, null, )
        }

        for (Information info : dataSetHolder.getDataSet().getWeekTwo()){

            ContentValues testValues = new ContentValues();
            testValues.put(FuelContract.FuelEntry.COLUMN_NAME_TITLE,"Gasolina Premium");
            testValues.put(FuelContract.FuelEntry.COLUMN_NAME_PRICE, 204.0999999);
            testValues.put(FuelContract.FuelEntry.COLUMN_NAME_PUB_DATE, "new data this week  Viernes, 24 de Julio de 2020 11:22:00 - 0400");

            //sqLiteDatabase.insert(FuelContract.FuelEntry.TABLE_NAME, null, )
        }

        for (Information info : dataSetHolder.getDataSet().getWeekThree()){

            ContentValues testValues = new ContentValues();
            testValues.put(FuelContract.FuelEntry.COLUMN_NAME_TITLE,"Gasolina Premium");
            testValues.put(FuelContract.FuelEntry.COLUMN_NAME_PRICE, 204.0999999);
            testValues.put(FuelContract.FuelEntry.COLUMN_NAME_PUB_DATE, "new data this week  Viernes, 24 de Julio de 2020 11:22:00 - 0400");

            //sqLiteDatabase.insert(FuelContract.FuelEntry.TABLE_NAME, null, )
        }
    }

    public void inserContenValues(){
//        for()
//        ContentValues testValues = new ContentValues();
//        testValues.put(FuelContract.FuelEntry.COLUMN_NAME_TITLE,"Gasolina Premium");
//        testValues.put(FuelContract.FuelEntry.COLUMN_NAME_PRICE, 204.0999999);
//        testValues.put(FuelContract.FuelEntry.COLUMN_NAME_PUB_DATE, "new data this week  Viernes, 24 de Julio de 2020 11:22:00 - 0400");
    }
}