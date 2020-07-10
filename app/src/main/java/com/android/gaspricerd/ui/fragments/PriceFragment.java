package com.android.gaspricerd.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.gaspricerd.utils.ParserXmlMic;
import com.android.gaspricerd.R;
import com.android.gaspricerd.reposiroty.ClientApi;
import com.android.gaspricerd.reposiroty.ServiceGenerator;
import com.android.gaspricerd.utils.Utils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment
 */
public class PriceFragment extends Fragment {
    private ClientApi clientApi;
    private TextView textView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        clientApi = ServiceGenerator.createService(ClientApi.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prices, container, false);

        getLastWeekJSON();

        return view;
    }


    private void getRssForMic(Response<ResponseBody> response) {
        //TODO: remove from view.
        Call<ResponseBody> call = clientApi.getCurrentGasPrice();
        Log.d("URL: ", call.request().url().toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                getRssForMic(response);
                try {
                    ParserXmlMic parserXmlMic = new ParserXmlMic();
                    parserXmlMic.parser(response.body().byteStream());

//                textView = getActivity().findViewById(R.id.txt_test);
//                textView.setText(response.body().byteStream().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // DO nothing
            }
        });
    }


    private void getLastWeekJSON() {
        HandlerThread handlerThread = new HandlerThread("new_tread");
        handlerThread.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Do you work here.
                try {
                    Call<ResponseBody> call = clientApi.getLastThreeWeeksGasPrices();
                    Log.d("URL: ", call.request().url().toString());

                    Response<ResponseBody> response = call.execute();

                    if (response.body() != null)
                        Utils.readStream(response.body().byteStream());


                } catch (IOException io) {
                    io.getStackTrace();
                }
            }
        };

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(runnable);
    }
}
