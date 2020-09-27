package com.android.gaspricerd.reposiroty.server;

import android.util.Log;

import com.android.gaspricerd.model.DataSetHolder;
import com.android.gaspricerd.utils.ParserXmlMic;
import com.android.gaspricerd.utils.Utils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation class of the services client.
 */
public class RetrofitImplementation {
    private final ClientApi mClientApi;
    private final OnResponseCallBack mOnResponseCallBack;

    public interface OnResponseCallBack {
       void onSuccessFull(ResponseBody body);
    }

    /**
     * Constructor.
     */
    public RetrofitImplementation(OnResponseCallBack callBack) {
        mClientApi = ServiceGenerator.createService(ClientApi.class);
        this.mOnResponseCallBack = callBack;
    }

    /**
     * Get the current gas price data for the current week.
     *
     * @param response {@link Response}
     */
    public void getRssForMic(Response<ResponseBody> response) {
        Call<ResponseBody> call = mClientApi.getCurrentGasPrice();
        Log.d("URL: ", call.request().url().toString());

        // sync task
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

    /** TODO:
     * Get the JSON data with the list of the last 3 week prices.
     * Synchronous request that block UI treat.
     */
//    public void getLastWeekJSON() {
//        HandlerThread handlerThread = new HandlerThread("new_tread");
//        handlerThread.start();
//
//        final DataSetHolder dataSetHolder = null;
//
//        Runnable runnable = () -> {
//            try {
//                Call<ResponseBody> call = mClientApi.getLastThreeWeeksGasPrices();
//                Log.d("URL: ", call.request().url().toString());
//
//                Response<ResponseBody> response = call.execute();
//                if (response.body() != null) {
//
//                }
//
//            } catch (IOException ioException) {
//                Log.e("REPO_IMP", Arrays.toString(ioException.getStackTrace()));
//            }
//        };
//
//        Handler handler = new Handler(handlerThread.getLooper());
//        handler.post(runnable);
//
//        return dataSetHolder;
//    }

    /**
     * TODO: NOT DELETE
     *  Get Results from Asynchronous Requests
     *
     */
    public void getLastWeekJSON() {
        Call<ResponseBody> call = mClientApi.getLastThreeWeeksGasPrices();
        Log.d("URL: ", call.request().url().toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mOnResponseCallBack.onSuccessFull(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("RETROFIT_ERROR: ", t.getMessage());
            }
        });
    }

    /**
     * Handler server response.
     *
     * @param responseBody The deserialized response body of a successful response.
     * @throws IOException
     */
    public DataSetHolder handlerRepose(ResponseBody responseBody)  {
        String json = Utils.readStream(responseBody.byteStream());
        return new Gson().fromJson(json, DataSetHolder.class);
    }
}
