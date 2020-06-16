package com.android.gaspricerd.reposiroty;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 *
 */
public interface ClientApi {
    /**
     *
     * @return
     */
    @GET("combustibleRSS.xml")
    Call<ResponseBody> getCurrentGasPrice();
}
