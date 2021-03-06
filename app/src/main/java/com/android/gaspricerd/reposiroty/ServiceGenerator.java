package com.android.gaspricerd.reposiroty;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 *
 */
public class ServiceGenerator {
    private static final String BASE_URL = "https://micm.gob.do/";
    private static final int TIME_OUT = 10;

    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS);

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create());

    /**
     * @param serviceClass
     * @param <S>
     *
     * @return
     */
    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(okHttpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
