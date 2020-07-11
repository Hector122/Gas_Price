package com.android.gaspricerd.reposiroty;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.android.gaspricerd.model.DataSetHolder;
import com.android.gaspricerd.utils.ParserXmlMic;
import com.android.gaspricerd.utils.Utils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryImp {
    private ClientApi mClientApi;

    public RepositoryImp() {
        mClientApi = ServiceGenerator.createService(ClientApi.class);
    }

    public void getRssForMic(Response<ResponseBody> response) {
        Call<ResponseBody> call = mClientApi.getCurrentGasPrice();
        Log.d("URL: ", call.request().url().toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                getRssForMic(response);
                try {
                    ParserXmlMic parserXmlMic = new ParserXmlMic();
                    parserXmlMic.parser(response.body().byteStream());

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


    public void getLastWeekJSON() {
        HandlerThread handlerThread = new HandlerThread("new_tread");
        handlerThread.start();

        Runnable runnable = () -> {
            try {
                Call<ResponseBody> call = mClientApi.getLastThreeWeeksGasPrices();
                Log.d("URL: ", call.request().url().toString());

                Response<ResponseBody> response = call.execute();
                if (response.body() != null) {
                    String json = Utils.readStream(response.body().byteStream());
                    DataSetHolder holder = new Gson().fromJson(json, DataSetHolder.class);
                    holder.getCategories();
                }

            } catch (IOException ioException) {
                Log.e("REPO_IMP", Arrays.toString(ioException.getStackTrace()));
            }
        };

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(runnable);
    }

    public void getLastWeekJSON(String json) {
        Call<ResponseBody> call = mClientApi.getLastThreeWeeksGasPrices();
        Log.d("URL: ", call.request().url().toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String json = Utils.readStream(response.body().byteStream());
                        DataSetHolder holder = new Gson().fromJson(json, DataSetHolder.class);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
