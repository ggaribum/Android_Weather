package com.example.a301.myapplication;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 301 on 2017-09-19.
 */
public class RetrofitClient {
    private static Retrofit retrofit =null;

    public static Retrofit getClient(String URL)
    {
        String temp=URL+"/";
        retrofit = new Retrofit.Builder()
                .baseUrl(temp)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}