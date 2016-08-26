package com.influx.pradip.rest;

import com.influx.pradip.MainActivity;
import com.influx.pradip.model.SlotSubModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Influx on 8/26/2016.
 */

public class RestClient {

    private static GitApiInterface gitApiInterface;
    private static String baseUrl = "http://family-expectation.opentestdrive.com";

    public static GitApiInterface getClient() {
        if (gitApiInterface == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient defaultHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(defaultHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            gitApiInterface = client.create(GitApiInterface.class);
        }
        return gitApiInterface;
    }


    public interface GitApiInterface {
        @POST("/api/user/register/")
        Call<SlotSubModel> getcity(@Body MainActivity.User cityId);
    }
}
