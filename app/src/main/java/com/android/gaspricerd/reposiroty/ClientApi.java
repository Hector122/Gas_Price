package com.android.gaspricerd.reposiroty;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface with the url used.
 */
public interface ClientApi {
    /**
     * Get the current gat price list of the week.
     *
     * @return Call <ResponseBody> with the server information.</>
     */
    @GET("combustibleRSS.xml")
    Call<ResponseBody> getCurrentGasPrice();

    /**
     * Get the price for the last 3 weeks.
     *
     * @return Call <ResponseBody> with the server information.</>
     */
    @GET("direcciones/combustibles/estadisticas-institucionales")
    Call<ResponseBody> getLastThreeWeeksGasPrices();
}
