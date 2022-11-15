package com.wisomleaf.framework.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Api {
    public static String baseURL = "https://picsum.photos/";
    private static OkHttpClient OK_HTTP_CLIENT;

    public static ApiInterface getClient() {
        //  if(retrofit == null){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL + "/")
                .client(getOKHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // }
        return retrofit.create(ApiInterface.class);
    }

    private static OkHttpClient getOKHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (OK_HTTP_CLIENT == null) {
            OK_HTTP_CLIENT = new OkHttpClient.Builder()
                    .connectTimeout(3, TimeUnit.MINUTES)
                    .readTimeout(3, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .build();
        }
        return OK_HTTP_CLIENT;
    }


}
