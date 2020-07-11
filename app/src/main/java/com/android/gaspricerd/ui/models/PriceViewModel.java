package com.android.gaspricerd.ui.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.gaspricerd.model.Combustible;

import java.util.List;

public class PriceViewModel extends ViewModel {
    private MutableLiveData<List<Combustible>> combustibles;

    public LiveData<List<Combustible>> getCombustibles (){
        if(combustibles == null){
            combustibles = new MutableLiveData<List<Combustible>>();
            loadCombustiblesPrices();
        }
        return combustibles;
    }

    private void loadCombustiblesPrices(){
        //Get data form data base.
    }
}
