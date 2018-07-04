package me.yasiru.udacityapi.rest;


import java.util.concurrent.TimeUnit;

import me.yasiru.udacityapi.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestClient {

    //create reset client
    public static RetrofitService getRetrofitService() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        //give some time to get large set of courses
        client.connectTimeout(100, TimeUnit.SECONDS);
        client.readTimeout(100, TimeUnit.SECONDS);
        client.writeTimeout(100, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build().create(RetrofitService.class);
    }
}