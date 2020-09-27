package com.android.gaspricerd.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.gaspricerd.R;
import com.android.gaspricerd.model.Combustible;
import com.android.gaspricerd.ui.adapters.CombustiblePricesAdapter;
import com.android.gaspricerd.ui.models.PriceViewModel;

import java.util.List;

/**
 * View that show the list of gas prices and types.
 */
public class PriceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prices, container, false);

        PriceViewModel priceViewModel = new ViewModelProvider(this).get(PriceViewModel.class);
        priceViewModel.getCombustibles().observe(getViewLifecycleOwner(), combustibles -> {
            //TODO: update ui. create the list.
            setAdapter(view, combustibles);
        });

        return view;
    }

    /**
     * Set the recycler and initializer the adapter items.
     *
     * @param view         layout view to show the list of item.
     * @param combustibles  List of combustibles data.
     */
    private void setAdapter(View view, List<Combustible> combustibles) {
        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_prices);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        CombustiblePricesAdapter adapter = new CombustiblePricesAdapter(getContext(), combustibles);
        recyclerView.setAdapter(adapter);
    }
}
