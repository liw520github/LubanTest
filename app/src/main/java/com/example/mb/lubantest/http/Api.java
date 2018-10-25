package com.example.mb.lubantest.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public static String baseUrl="https://api.douban.com/v2/movie/";
    public static ApiService apiService;
    public static ApiService getApiService(){
        if (apiService==null){
            synchronized (Api.class){
                if (apiService==null){
                    new Api();
                }
            }
        }
        return apiService;
    }
    private Api(){
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService =retrofit.create(ApiService.class);
    }
}
