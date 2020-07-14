package com.android.gaspricerd.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.gaspricerd.R;
import com.android.gaspricerd.model.Combustible;
import com.android.gaspricerd.ui.adapters.CombustiblePricesAdapter;
import com.android.gaspricerd.ui.models.PriceViewModel;

import java.util.ArrayList;

/**
 * Fragment
 */
public class PriceFragment extends Fragment {
    PriceViewModel model;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prices, container, false);

        setAdapter(view);

        return view;
    }


    private void setAdapter(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_prices);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CombustiblePricesAdapter adapter = new CombustiblePricesAdapter(getContext(), getDataDummy());
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Combustible> getDataDummy() {
        ArrayList<Combustible> combustibles = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Combustible combustible = new Combustible(
                    "Gas Natural Vehicular (GNV)",
                    R.drawable.ic_down, 137.70, 103.00);

            combustibles.add(combustible);
        }

        return combustibles;
    }
}
