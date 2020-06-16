package com.android.gaspricerd.ui.fragments;

import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.gaspricerd.R;
import com.android.gaspricerd.reposiroty.ClientApi;
import com.android.gaspricerd.reposiroty.ServiceGenerator;

import retrofit2.Response;

public class MapFragment extends Fragment {
    ClientApi clientApi;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);

    }
}
