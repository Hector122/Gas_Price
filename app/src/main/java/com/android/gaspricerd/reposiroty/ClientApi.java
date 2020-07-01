package com.android.gaspricerd.reposiroty;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 *
 */
public interface ClientApi {
    /**
     * Get the current gat price list of the week.
     *
     * @return Call <ResponseBody> with the server information.</>
     */
    @GET("combustibleRSS.xml")
    Call<ResponseBody> getCurrentGasPrice();
}
