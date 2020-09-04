package com.android.gaspricerd.ui.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.gaspricerd.model.Combustible;

import java.util.List;

/**
 * View model class that store the combustibles list prices.
 */
public class PriceViewModel extends ViewModel {
    private MutableLiveData<List<Combustible>> combustibles;

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
        //TODO
//    ServerImp retrofitImp = new ServerImp();
//        retrofitImp.getLastWeekJSON();

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
}
